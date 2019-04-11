package agile.battleship.infrastructure

import agile.battleship.core.ShipsService
import agile.battleship.core.domain.Ship
import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol


trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val shipFormat = jsonFormat2(Ship)
}

case class ShipsApi(shipService: ShipsService)(implicit val system: ActorSystem, implicit val materializer: ActorMaterializer) extends JsonSupport {
  val basePath: String = "ships"

  def route: Route = get {
    path(basePath) {
      complete(shipService.getAllShips())
    }
  }

}
