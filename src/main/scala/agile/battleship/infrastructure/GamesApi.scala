package agile.battleship.infrastructure

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route


case class GamesApi[ActorSystem, ActorMaterializer](){
  val basePath: String = "games"

  def route: Route = get {
    path(basePath) {
      complete("games api !")
    }
  }

}
