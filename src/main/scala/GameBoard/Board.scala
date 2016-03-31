package GameBoard

/**
  * Created by godspeedchloe on 3/19/16.
  * This class is the board that we store chips in.
  */
class Board {

  private var chips = Array.fill(7){Array.fill(6){"_"}}

  /**
    * Getter method for the chips in the board
    * @return the 2D array of strings
    */
  def getChips(): Array[Array[String]] ={
    chips
  }

  /**
    * Setter method for the chips in the board
    * @param newChips the new 2D array of chips we want to save
    */
  def setChips(newChips : Array[Array[String]]): Unit ={
    chips = newChips
  }
}