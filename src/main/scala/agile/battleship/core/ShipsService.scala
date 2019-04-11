package agile.battleship.core

import agile.battleship.core.domain.Ship
import agile.battleship.infrastructure.Ships

case class ShipsService(ships: Ships) {
  def getAllShips(): List[Ship] = ships.getShips()
}
