import java.util.ArrayList;
import java.util.Scanner;
public class Runner {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Board gameboard = new Board();
        int pieceResponse = -1;
        int moveResponse = -1;
        System.out.println("Welcome to 2D Checkers.  Players will take turns moving pieces.  The current player's pieces are represented by numbers or 'X's depending on if they can move.  The player who eliminates all of the opponents pieces wins!");
        while (!gameboard.hasWon()){
            pieceResponse = -1;
            moveResponse = -1;
            ArrayList<ArrayList<Move>> movables = gameboard.displayBoard();
            if (movables.size() > 0){
                while (pieceResponse < 0 ||  pieceResponse > movables.size()){
                    System.out.print("Player " + gameboard.getCurrentPlayer() + ", which piece do you want to move? : ");
                    pieceResponse = scan.nextInt();
                    System.out.println(movables.size());
                }
                while (moveResponse < 0 || moveResponse > movables.get(pieceResponse-1).size()){
                    gameboard.displayMoves((ArrayList) movables.get(pieceResponse-1));
                    System.out.print("Player " + gameboard.getCurrentPlayer() + ", where do you want to move that piece? : ");
                    moveResponse = scan.nextInt();
                }
                gameboard.move(movables.get(pieceResponse-1).get(moveResponse-1));
            }
            else{
                System.out.print("You have no moves!  You skip your turn! Press enter to Continue");
                scan.nextLine();
            }
            gameboard.endTurn();
        }
        
    }
}
