package agile.battleship.utils

import agile.battleship.ServerActions
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.concurrent.Future

trait BaseAcceptanceTest {
  val serverHost = "localhost"
  val serverPort = 9000 // make it random!

  val currentEndpoint = s"http://${serverHost}:${serverPort}"

  implicit val sys = ActorSystem()
  implicit val mat = ActorMaterializer()
  val serverActions = new ServerActions()
  lazy val bindingServerFuture: Future[Http.ServerBinding] = serverActions.startServer(serverHost, serverPort)
}
