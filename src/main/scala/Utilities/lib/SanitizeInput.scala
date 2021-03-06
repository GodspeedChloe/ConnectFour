package Utilities.lib

/**
  * Created by Chloe on 4/5/16.
  * This class takes input and sanitizes it/parses it
  */
class SanitizeInput {


  /**
    * This function parses an input string and discerns the game mode the user(s) wishes therein
    * @param game_mode the yes or no answer to the type of game the user wishes for
    * @return 1 for two-player game, 0 for one-player game, -1 for invalid input
    */
  def getGameMode(game_mode : String): Int = {
    
    if ((game_mode.toLowerCase == "yes") || (game_mode.toLowerCase == "y")) {
      return 1
    }

    if ((game_mode.toLowerCase == "no") || (game_mode.toLowerCase == "n")) {
      return 2
    }

    -1 //no valid input was given by the user
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
