package GameBoard

/**
  * Created by Chloe on 3/19/16.
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


  /**
    * This function iterates through our board and prints it so the players
    * can digest all the information
    */
  def displayBoard(): Unit ={

    val my_chips : Array[Array[String]] = this.getChips()
    var row : Int = 5

    println("\nLatest Board:")

    while(row >= 0){

      var row_total : String = ""

      for(col <- 0 to 6){
        row_total = row_total + my_chips(col)(row) + " "
      }

      row -= 1
      println(row_total)
    }
  }


  /**
    * This function checks whether or not a open slot is open in the board
    * @param column
    * @return true for open, false for full column
    */
  def openSlot(column : Int): AnyVal ={
    if (this.getChips()(column)(5) == "_") {
      return true
    }
    false
  }


  /**
    * This function checks whether the board is full or not
    * @return false for not full, true for full
    */
  def isFull(): AnyVal ={
    for (column <- 0 to 6) {
      if (this.getChips()(column)(5) == "_") {
        return false
      }
    }
    true
  }
}