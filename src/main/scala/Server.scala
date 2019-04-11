import agile.battleship.core.ShipsService
import agile.battleship.infrastructure.{ShipRepository, Ships, ShipsApi}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.io.StdIn

object ServerConfiguration {
  val host = "localhost"
  val port = 9000
}

object Server extends App {

  override def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    // needed for the future map/flatmap in the end
    implicit val executionContext = system.dispatcher

    val shipsRepository: Ships = new ShipRepository()
    val shipsService: ShipsService = ShipsService(shipsRepository)
    val route: Route = ShipsApi(shipsService).route

    val bindingFuture = Http().bindAndHandle(route, ServerConfiguration.host, ServerConfiguration.port)
    println(s"Server online at ${ServerConfiguration.host}:${ServerConfiguration.port}\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
