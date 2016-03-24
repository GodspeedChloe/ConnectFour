import GameBoard.Board

/**
  * Created by godspeedchloe on 3/19/16.
  */
object ConnectFour {


  def main(args: Array[String]) {
    val my_board = new Board
    var go : Boolean = true
    var turn : Int = 0

    println("Welcome to Connect four!!!" +
      "\n" + "Player1 goes first.  Connect four in a row to win!" +
      "\n    Enter in a positive integer [1-7] inclusive" +
      "\n    to place your chip were 1 is the leftmost column" +
      "\n    $ -> Player1, @ -> Player2")

    while (go) {
      val input: Int = takeInput(turn)

      if (input > 0){
        placeChip(input, turn, my_board)
        turn += 1
        checkWinner(my_board)
      }
    }
  }


  def takeInput(turn : Int): Int = {
    var player : Int = 1

    if (turn % 2 != 0){
      player = 2
    }

    print("Player" + player + "'s move: ")
    val input: String = readLine()

    if(parseInput(input) != 1) {
      return 0
    }

    return input.toInt
  }


  def parseInput(string: String): Int = {
    var valid: Int = 0
    val column : Int = toInt(string)

    if (column < 0){
      println("Negative column entered.  Try again.")
      return valid
    }

    else if (column == 0){
      println("Not an integer.  Try again.")
      return valid
    }

    else if (column > 7){
      println("Column out of bounds.  Try again.")
      return valid
    }

    else {
      valid = 1
    }

    valid
  }


  def placeChip(col : Int, turn : Int, my_board : Board): Unit ={
    val new_board : Array[Array[String]] = my_board.getChips()
    var index : Int = 0

    for (chip <- new_board(col-1)) {

      if (chip == "_") {

        if (turn % 2 == 0) {
          new_board(col - 1)(index) = "$"
          my_board.setChips(new_board)
          return
        }

        else {
          new_board(col - 1)(index) = "@"
          my_board.setChips(new_board)
          return
        }
      }

      index += 1
    }
  }


  def displayBoard(my_board : Board): Unit ={
    val my_chips : Array[Array[String]] = my_board.getChips()
    var row : Int = 5

    println("Latest Board:")
    while(row >= 0){
      var row_total : String = ""

      for(col <- 0 to 6){
        row_total = row_total + my_chips(col)(row) + " "
      }

      row -= 1
      println(row_total)
    }
  }


  def checkWinner(my_board : Board): Int ={
    val my_chips : Array[Array[String]] = my_board.getChips()

    for (col <- 0 to 3) {
      for (row <- 0 to 5) {

        //max height where a vertical winner can start
        if (row + 3 < 5) {
          if ((my_chips(col)(row) == my_chips(col)(row+1)) ||
            (my_chips(col)(row) == my_chips(col)(row+2)) ||
            (my_chips(col)(row) == my_chips(col)(row+3))) {

            if (my_chips(col)(row) == "$"){
              return 1
            }
            else {
              return 2
            }
          }
        }

        //max width where a horizontal winner can start
        if (col + 3 < 6) {
          if ((my_chips(col)(row) == my_chips(col+1)(row)) ||
            (my_chips(col)(row) == my_chips(col+2)(row)) ||
            (my_chips(col)(row) == my_chips(col+3)(row))){

            if (my_chips(col)(row) == "$"){
              return 1
            }
            else {
              return 2
            }
          }
        }
      }
    }

    0
  }

  def toInt(string: String): Int ={

    try {
      string.toInt
    } catch {
      case e: Exception => 0
    }
  }


}
