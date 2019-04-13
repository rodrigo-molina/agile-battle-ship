package agile.battleship

import agile.battleship.utils.BaseAcceptanceTest
import com.github.agourlay.cornichon.CornichonFeature


class ShipsFeature extends CornichonFeature with BaseAcceptanceTest {

  def feature = Feature("Get Battleships") {

    Scenario("Get All Battleships") {

      When I get(s"${currentEndpoint}/ships")

      Then assert status.is(200)
//      val jsonResponse = Source.fromFile("C:/workspace/AgileBattleship/src/test/scala/agile/battleship/jsonResponses/GetAllShips.json").getLines.mkString
      Then assert body.is("[\n  {\n    \"id\": \"s01\",\n    \"size\": 2\n  },\n  {\n    \"id\": \"s02\",\n    \"size\": 3\n  },\n  {\n    \"id\": \"s03\",\n    \"size\": 3\n  },\n  {\n    \"id\": \"s04\",\n    \"size\": 4\n  },\n  {\n    \"id\": \"s05\",\n    \"size\": 5\n  }\n]")
    }
  }

  beforeFeature {
    bindingServerFuture
    println("start server")
  }

  afterFeature {
    println("stop server")
    serverActions.stopServer(bindingServerFuture)
  }
}
