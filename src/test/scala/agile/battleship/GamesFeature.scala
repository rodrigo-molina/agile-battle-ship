package agile.battleship

import agile.battleship.utils.BaseAcceptanceTest
import com.github.agourlay.cornichon.CornichonFeature


class GamesFeature extends CornichonFeature with BaseAcceptanceTest {

  def feature = Feature("Get Games") {

    Scenario("Get Games") {

      When I get(s"${currentEndpoint}/games")

      Then assert status.is(200)
      Then assert body.is("games api !")
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
