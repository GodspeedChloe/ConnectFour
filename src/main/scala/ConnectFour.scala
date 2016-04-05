import Utilities.Run

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
    val game = new Run
    game.beginGame()
  }
}
