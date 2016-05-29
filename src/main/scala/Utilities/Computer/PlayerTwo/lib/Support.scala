package Utilities.Computer.PlayerTwo.lib

import GameBoard.Board

/**
  * Created by Chloe on 4/8/16.
  * This file houses broader functions for the AI duties to reference
  */
class Support {


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
          return
        }

        else {
          new_board(col - 1)(index) = "@"
          return
        }
      }

      index += 1
    }
  }


  /**
    * This function iterates through the board and tries to locate a winner
    * It is to be called after every move
    * @param my_board the board we are scanning
    * @return 0 for no winner, 1 for a Player 1 win, 2 for a Player 2 win
    */
  def checkWinner(my_board : Board): Int ={

    val my_chips : Array[Array[String]] = my_board.getChips()

    for (col <- 0 to 6) {

      for (row <- 0 to 5) {

        if (my_chips(col)(row) != "_") {

          if ((checkVertical(row, col, my_chips) == 1) ||
            (checkHorizontal(row, col, my_chips) == 1) ||
            (checkDiagonalPositive(row, col, my_chips) == 1) ||
            (checkDiagonalNegative(row, col, my_chips) == 1)) {

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
    
    0
  }


  /**
    * This function checks Vertical 4-in-a-row winners
    *
    * @param my_chips The chips on the board
    * @param row the row of a chip
    * @param col the col of a chip
    * @return 1 for winner, 0 for not a winner
    */
  def checkVertical(row : Int, col : Int, my_chips : Array[Array[String]]): AnyVal ={

    if (row + 3 < 5) {

      if ((my_chips(col)(row) == my_chips(col)(row + 1)) &&
        (my_chips(col)(row) == my_chips(col)(row + 2)) &&
        (my_chips(col)(row) == my_chips(col)(row + 3))) {
        return 1
      }

      0
    }
  }


  /**
    * This function checks horizontal 4-in-a-row winners
    *
    * @param my_chips The chips on the board
    * @param row the row of a chip
    * @param col the col of a chip
    * @return 1 for winner, 0 for not a winner
    */
  def checkHorizontal(row : Int, col : Int, my_chips : Array[Array[String]]): AnyVal ={

    if (col + 3 < 6) {

      if ((my_chips(col)(row) == my_chips(col + 1)(row)) &&
        (my_chips(col)(row) == my_chips(col + 2)(row)) &&
        (my_chips(col)(row) == my_chips(col + 3)(row))) {
        return 1
      }

      0
    }
  }


  /**
    * This function checks diagonal positive slope 4-in-a-row winners
    *
    * @param my_chips The chips on the board
    * @param row the row of a chip
    * @param col the col of a chip
    * @return 1 for winner, 0 for not a winner
    */
  def checkDiagonalPositive(row : Int, col : Int, my_chips : Array[Array[String]]): AnyVal ={

    if ((col + 3 < 6) && (row + 3 < 5)) {
      if ((my_chips(col)(row) == my_chips(col + 1)(row + 1)) &&
        (my_chips(col)(row) == my_chips(col + 2)(row + 2)) &&
        (my_chips(col)(row) == my_chips(col + 3)(row + 3))) {
        return 1
      }

      0
    }
  }


  /**
    * This function checks diagonal negative slope 4-in-a-row winners
    *
    * @param my_chips The chips on the board
    * @param row the row of a chip
    * @param col the col of a chip
    * @return 1 for winner, 0 for not a winner
    */
  def checkDiagonalNegative(row : Int, col : Int, my_chips : Array[Array[String]]): AnyVal ={

    if ((col + 3 < 6) && (row > 2)) {
      if ((my_chips(col)(row) == my_chips(col + 1)(row - 1)) &&
        (my_chips(col)(row) == my_chips(col + 2)(row - 2)) &&
        (my_chips(col)(row) == my_chips(col + 3)(row - 3))) {
        return 1
      }

      0
    }
  }

}
