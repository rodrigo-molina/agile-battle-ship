package agile.battleship.infrastructure

import agile.battleship.core.ShipsService
import agile.battleship.core.domain.Ship
import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.Uri.Path
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol



trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val shipFormat = jsonFormat2(Ship)
}

case class ShipsApi(shipService: ShipsService) extends JsonSupport {
  val basePath: Path = Uri.Path("/ships")

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  def route: Route = get {
      path("ships") {
        complete(shipService.getAllShips())
      }
  }

}
