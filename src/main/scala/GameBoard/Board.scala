package GameBoard

/**
  * Created by godspeedchloe on 3/19/16.
  */
class Board {

  private var chips = Array.fill(7){Array.fill(6){"_"}}


  def getChips(): Array[Array[String]] ={
    chips
  }


  def setChips(newChips : Array[Array[String]]): Unit ={
    chips = newChips
  }
}