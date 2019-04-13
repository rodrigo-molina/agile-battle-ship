package agile.battleship

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.github.agourlay.cornichon.CornichonFeature

import scala.concurrent.Future


class GamesFeature extends CornichonFeature {


  val serverHost = "localhost"
  val serverPort = 9090

  val currentEndpoint = s"http://${serverHost}:${serverPort}"

  implicit val sys = ActorSystem()
  implicit val mat = ActorMaterializer()
  val serverActions = new ServerActions()
  val bindingServerFuture: Future[Http.ServerBinding] = serverActions.startServer(serverHost, serverPort)


  def feature = Feature("Get Games") {

    Scenario("Get Games") {

      When I get(s"${currentEndpoint}/games")

      Then assert status.is(200)
    }
  }


  afterFeature {
    println("stop server")
    serverActions.stopServer(bindingServerFuture)
  }
}
