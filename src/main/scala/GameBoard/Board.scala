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
}