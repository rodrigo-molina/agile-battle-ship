import agile.battleship.core.ShipsService
import agile.battleship.infrastructure.{GamesApi, ShipRepository, Ships, ShipsApi}
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.Future



class ServerActions()(implicit val system: ActorSystem, implicit  val materializer: ActorMaterializer) {

  def startServer(host: String, port: Int): Future[Http.ServerBinding] = {
    val shipsRepository: Ships = new ShipRepository()
    val shipsService: ShipsService = ShipsService(shipsRepository)
    val shipsRoute: Route = ShipsApi(shipsService).route
    val gamesRoute: Route = GamesApi().route

    val routes: Route = shipsRoute ~ gamesRoute
    val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(routes, host, port)
    bindingFuture
  }

  def stopServer(bindingFuture: Future[Http.ServerBinding]): Unit = {
    // needed for the future map/flatmap in the end
    implicit val executionContext = system.dispatcher

    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
