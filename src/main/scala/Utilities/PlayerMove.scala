package Utilities

import GameBoard.Board

/**
  * Created by Chloe on 4/5/16.
  * This method reads and updates a Board instance
  */
class PlayerMove {


  /**
    * This function places chips into the correct spot on the board,
    * simulating gravity and mindful of which player placed the chip
    *
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
}
