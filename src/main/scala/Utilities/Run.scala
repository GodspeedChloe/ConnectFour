package Utilities

import GameBoard.Board
import Utilities.lib.{SanitizeInput, PlayerMove}
import Utilities.Computer.PlayerTwo.{ArtificialIntelligence, MachinaDuties}

/**
  * Created by Chloe on 4/5/16.
  * This file is supposed to make all appropriate calls to run the game
  * Tasks include:
  * > Starting information
  * > Choice for single or double player
  * > Execution of turns
  * > Updating and displaying the game board
  * > Deciding a winner after every turn
  * > Repeating all the tasks after a game mode is chosen or quitting
  * > Running a game against an AI
  * > Creating and running the AI
  */
class Run {


  /**
    * This method is the controller for the game
    */
  def beginGame(): Unit ={

    val my_board = new Board
    val user_input = new SanitizeInput
    val user_move = new PlayerMove

    var has_not_chosen_game : Boolean = true
    var imitation_game : Boolean = false
    var one_person: Boolean = false
    var turn : Int = 0

    println("Welcome to Connect four!!!" +
      "\n" + "Player1 goes first.  Connect four in a row to win!" +
      "\n    Enter in a positive integer [1-7] inclusive" +
      "\n    to place your chip were 1 is the leftmost column" +
      "\n    @ -> Player1, $ -> Player2 or AI")


    // this block is for finding out the game mode
    while(has_not_chosen_game == true)

      println("\nDo you wish to play a two player game? (yes/no)")
      val game_mode: String = readLine()

      if (getGameMode(game_mode) == 1) {
        one_person = false
        has_not_chosen_game = false
      }
      else if (getGameMode(game_mode) == 2) {
        one_person = true
        has_not_chosen_game = false
      }
      else {
        println("\nInvalid input.  Do you wish to play a two player game? (yes/no)")
      }
    }
    

    //this block is for one-person vs. AI games
    while (one_person) {

      val motoko = new ArtificialIntelligence
      
      if (turn % 2 == 0) {
        val input: Int = user_input.takeInput(turn)
      }

      else {
        for(wait <- 0 to 1000) {
          //make the AI take its time
        }
        val input: Int = motoko.makeNextMove(my_board)
      }

      if (input > 0) {

        if (my_board.openSlot(input-1) == true) {
          turn += 1
          user_move.placeChip(input, turn, my_board)

          val winner: Int = user_move.checkWinner(my_board)

          my_board.displayBoard()

          if (winner == 1) {
            println("\n" + "!!!!!!!!!!!!!!!!  Motoko WINS  !!!!!!!!!!!!!!!!" + "\nThanks for playing!")
            go = false
            System.exit(0)
          }

          else if (winner == 2) {
            println("\n" + "!!!!!!!!!!!!!!!! Player 2 WINS !!!!!!!!!!!!!!!!" + "\nThanks for playing!")
            go = false
            System.exit(0)
          }
        }

        else {
          println("Column full, try another")
        } 
      }
    }  

    //this block is for two-person games
    while (!(one_person)) {

      val input: Int = user_input.takeInput(turn)

      if (my_board.isFull() == true) {
        println("GAME IS A STALEMATE!")
        System.exit(0)
      }

      if (input > 0) {

        if (my_board.openSlot(input-1) == true) {
          turn += 1
          user_move.placeChip(input, turn, my_board)

          val winner: Int = user_move.checkWinner(my_board)

          my_board.displayBoard()

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

        else {
          println("Column full, try another")
        }
      }
    }
  }
}
