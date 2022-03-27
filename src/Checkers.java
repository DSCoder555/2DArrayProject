//Darius Saadat
import java.util.ArrayList;
import java.util.Scanner;

//main class for the program
//The methods of this class set up the board and run the game
public class Checkers {
    //instance variables
    //gameBoard: the checkerboard with all players' pieces
    private Board gameBoard;
    private int pieceResponse;
    private int moveResponse;

    /**Constructor; creates a Checkers game
     *
     */
    public Checkers(){
        gameBoard = new Board();
        int pieceResponse = -1;
        int moveResponse = -1;
    }

    /**play; plays a Checkers game, finishes when game ends
     *
     */
    public void play(){
        System.out.println("Welcome to 2D Checkers.  Players will take turns moving pieces.  " +
                "\nThe current player's movable pieces are represented by numbers and after they select a piece, their possible moves will be represented by numbers.  \nThere is no multiples jumps in one turn." +
                "\nPlayer 1's pieces will be represented with 'O's while Player 2's pieces are represented by 'X's." +
                "\nThe player who eliminates all of the opponents pieces wins!");
        //noMoves: used to see if both players have no moves
        boolean noMoves = false;
        //while loop, allowing player to keep making moves until the game ends
        while (!gameBoard.hasWon()){
            Scanner scan = new Scanner(System.in);
            pieceResponse = -1;
            moveResponse = -1;
            //stores all moves for current player
            ArrayList<ArrayList<Move>> movables = gameBoard.displayBoard();
            //checks if the current player has any possible moves
            if (movables.size() > 0){
                //while loop used to ask player for piece to move until valid response given
                while (pieceResponse < 0 ||  pieceResponse > movables.size()){
                    System.out.print("Player " + gameBoard.getCurrentPlayer() + ", which piece do you want to move? : ");
                    pieceResponse = scan.nextInt();
                    System.out.println(movables.size());
                }
                //while loop used to ask player for move until valid response given
                while (moveResponse < 0 || moveResponse > movables.get(pieceResponse-1).size()){
                    gameBoard.displayMoves(movables.get(pieceResponse-1));
                    System.out.print("Player " + gameBoard.getCurrentPlayer() + ", where do you want to move that piece? : ");
                    moveResponse = scan.nextInt();
                }
                //moves the selected piece with the selected move
                gameBoard.movePiece(movables.get(pieceResponse-1).get(moveResponse-1));
                noMoves = false;
            }
            //if player has no possible moves
            else{
                // if other player also does not have any possible moves, game ends
                if(noMoves){
                    break;
                }
                else{
                    noMoves = true;
                }
                System.out.print("You have no moves!  You skip your turn! Press enter to Continue");
                scan.nextLine();
            }
            //swaps the current player
            gameBoard.endTurn();
        }
        //if game ended because of tie, prints tie message
        if (noMoves){
            System.out.println("Both player have no possible moves!  It is a tie.");
        }
        //if a player won, print player won message
        else {
            gameBoard.endTurn();
            System.out.println("Player " + gameBoard.getCurrentPlayer() + " won!");
        }
    }
}
