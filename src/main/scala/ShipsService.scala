case class ShipsService(ships: Ships) {
  def getAllShips(): List[Ship] = ships.getShips()
}
