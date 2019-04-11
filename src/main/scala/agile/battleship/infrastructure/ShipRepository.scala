package agile.battleship.infrastructure

import agile.battleship.core.domain.Ship

trait Ships {
  def getShips(): List[Ship]
}

class ShipRepository extends Ships {
  val ships: List[Ship] = List(
    Ship("s01", 2),
    Ship("s02", 3),
    Ship("s03", 3),
    Ship("s04", 4),
    Ship("s05", 5)
  )

  override def getShips(): List[Ship] = ships

}
