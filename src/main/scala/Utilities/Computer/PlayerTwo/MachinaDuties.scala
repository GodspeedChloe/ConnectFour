package Utilities.Computer.PlayerTwo


import lib.Support

/**
  * Created by Chloe on 4/8/16.
  * This file houses all the supporting functions for the AI controller
  */
class MachinaDuties {


  val library = new Support


  /**
    * This function checks the board for the human user's potential win move.
    * The AI then knows where to block the human user
 *
    * @param my_board the current game board
    * @return -1 for no blocking move, integer 0 to 6 inclusive for blocking placement
    */
  def checkBlockMove(my_board: GameBoard.Board): Int={

    for(column <- 0 to 6){

      val new_chips : Array[Array[String]] = library.placeChip(column, 2, my_board)

      if (library.checkWinner(new_chips) == 1) {
        return column
      }
    }

    -1
  }


  /**
    * This function checks the board for the AI's potential win move.
    * The AI then knows where to place to win
 *
    * @param my_board the current game board
    * @return -1 for no winning move, integer 0 to 6 inclusive for winning placement
    */
  def checkWinMove(my_board: GameBoard.Board): Int={

    for(column <- 0 to 6){

      val new_chips : Array[Array[String]] = library.placeChip(column, 1, my_board)

      if (library.checkWinner(new_chips) == 2) {
        return column
      }
    }

    -1
  }



  def checkLosingMove(my_board: GameBoard.Board): Int={

    for(column <- 0 to 6) {

      var new_board : GameBoard.Board = my_board
      library.placeChip(column, 1, new_board)


    }

    -1
  }
}
