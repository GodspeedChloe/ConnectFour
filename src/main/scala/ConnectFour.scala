import GameBoard.Board

/**
  * Created by Chloe on 3/19/16.
  * This object is the game.  It interacts with users and applies logic to
  * a single Board instance
  */
object ConnectFour {


  /**
    * The main class to run the game
    * @param args standards arguments
    */
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

      val input : Int = takeInput(turn)

      if (input > 0){
        placeChip(input, turn, my_board)

        turn += 1

        val winner : Int = checkWinner(my_board)

        displayBoard(my_board)

        if (winner == 1) {
          println("\n" + "!!!!!!!!!!!!!!!! Player 1 WINS !!!!!!!!!!!!!!!!" + "\nThanks for playing!")
          go = false
          System.exit(0)
        }

        else if (winner == 2) {
          println("\n" + "!!!!!!!!!!!!!!!! Player 2 WINS !!!!!!!!!!!!!!!!" + "\nThanks for playing!")
          go = false
          System.exit(0)
        }
      }
    }
  }


  /**
    * This function displays whose turn it is, takes the turn from
    * console and parses it
    * @param turn the iteration of the game
    * @return Either 0 or the column the player want's to place the chip
    */
  def takeInput(turn : Int): Int = {

    var player : Int = 1

    if (turn % 2 != 0){
      player = 2
    }

    print("\nPlayer" + player + "'s move: ")
    val input: String = readLine()

    if(parseInput(input) != 1) {
      return 0
    }

    return input.toInt
  }


  /**
    * This function checks whether or not a String can be cast to an integer
    * @param string the input from takeInput
    * @return 0 or 1, treated as booleans
    */
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


  /**
    * This function places chips into the correct spot on the board,
    * simulating gravity and mindful of which player placed the chip
    * @param col the user input for their move
    * @param turn the iteration that the game is on
    * @param my_board the game board we are to be updating
    */
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


  /**
    * This function iterates through our board and prints it so the players
    * can digest all the information
    * @param my_board the board we are to be displaying
    */
  def displayBoard(my_board : Board): Unit ={

    val my_chips : Array[Array[String]] = my_board.getChips()
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
    * This function iterates through the board and tries to locate a winner
    * It is to be called after every move
    * @param my_board the board we are to be checking for winners
    * @return 0 for no winner, 1 for a Player 1 win, 2 for a Player 2 win
    */
  def checkWinner(my_board : Board): Int ={

    val my_chips : Array[Array[String]] = my_board.getChips()

    for (col <- 0 to 6) {

      for (row <- 0 to 5) {

        if (my_chips(col)(row) != "_") {

          //max height where a vertical winner can start
          if (row + 3 < 5) {

            if ((my_chips(col)(row) == my_chips(col)(row + 1)) &&
              (my_chips(col)(row) == my_chips(col)(row + 2)) &&
              (my_chips(col)(row) == my_chips(col)(row + 3))) {

              if (my_chips(col)(row) == "$") {
                return 1
              }

              else {
                return 2
              }
            }
          }

          //max width where a horizontal winner can start
          if (col + 3 < 6) {

            if ((my_chips(col)(row) == my_chips(col + 1)(row)) &&
              (my_chips(col)(row) == my_chips(col + 2)(row)) &&
              (my_chips(col)(row) == my_chips(col + 3)(row))) {

              if (my_chips(col)(row) == "$") {
                return 1
              }

              else {
                return 2
              }
            }
          }

          //max width and height for a diagonal + slope winner
          if ((col + 3 < 6) && (row + 3 < 5)) {
            if ((my_chips(col)(row) == my_chips(col + 1)(row + 1)) &&
              (my_chips(col)(row) == my_chips(col + 2)(row + 2)) &&
              (my_chips(col)(row) == my_chips(col + 3)(row + 3))) {

              if (my_chips(col)(row) == "$") {
                return 1
              }

              else {
                return 2
              }
            }
          }

          //max width and height for a diagonal - slope winner
          if ((col + 3 < 6) && (row > 2)) {
            if ((my_chips(col)(row) == my_chips(col + 1)(row - 1)) &&
              (my_chips(col)(row) == my_chips(col + 2)(row - 2)) &&
              (my_chips(col)(row) == my_chips(col + 3)(row - 3))) {

              if (my_chips(col)(row) == "$") {
                return 1
              }

              else {
                return 2
              }
            }
          }
        }
      }
    }
    0
  }


  /**
    * Helper function for the parseInput function
    * Gracefully tries and catches whether or not casting String to Integer is possible
    * @param string the string we are trying to cast as an Integer
    * @return 0 for non-integer, or the integer
    */
  def toInt(string: String): Int ={

    try {
      string.toInt
    } catch {
      case e: Exception => 0
    }
  }
}
