package Utilities.Computer.PlayerTwo

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Chloe on 4/8/16.
  * This class houses the controller for the AI.  It arranges function calls
  * In order to generate the next move for the AI.
  */
class ArtificialIntelligence {

  /*                                                                THERE IS A WHISPER IN MY GHOST        */

  val duties = new MachinaDuties


  /**
    * This method makes all the appropriate calls and moves defensively or aggressively
    *
    * @param my_board the current game board
    * @return the column choice
    */
  def makeNextMove(my_board : GameBoard.Board): Int ={

    //Return the winning move first as it is the best choice
    val win : Int = duties.checkWinMove(my_board)

    if (win != -1){ return win }

    //Return the blocking move secondly to stay alive
    val block : Int = duties.checkBlockMove(my_board)

    if (block != -1){ return block }

    //Return a random neutral move if no block and win moves exist
    val bad_moves = duties.checkLosingMoves(my_board)
    val decent_moves = duties.checkDecentMoves(my_board, bad_moves)
    val decent_move = pickRandom(decent_moves)

    if (decent_move != -1) { return decent_move }

    //If no possible move, return -1 to forfeit
    -1
  }


  /**
    * This function picks a random move out a move set to make
    * @param move_set the set of moves we are to pick from
    * @return the column choice
    */
  def pickRandom(move_set : ArrayBuffer[Int]): Int ={

    if (move_set.isEmpty) {
      return -1
    }

    val r = scala.util.Random
    val decent_move = r.nextInt(move_set.size)
    decent_move
  }

  /*                                                     It's time to become a part of all things        */
}
