import java.util.ArrayList;
import java.util.Scanner;
public class Runner {
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Board gameboard = new Board();
        int response = -1;
        System.out.println("Welcome to 2D Checkers.  Players will take turns moving pieces.  The current player's pieces are represented by numbers or 'X's depending on if they can move.  The player who eliminates all of the opponents pieces wins!");
        while (!gameboard.hasWon()){
            ArrayList movables = gameboard.displayBoard();
            if (movables.size() > 0){
                while (response < 0 ||  response > movables.size()){
                    System.out.print("Player " + gameboard.getCurrentPlayer() + ", which piece do you want to move? : ");
                    response = scan.nextInt();
                    System.out.println(movables.size());
                }
                gameboard.displayMoves((ArrayList) movables.get(response-1));
                System.out.print("Player " + gameboard.getCurrentPlayer() + ", where do you want to move that piece? : ");
                response = scan.nextInt();

            }
            else{
                System.out.print("You have no moves!  You skip your turn! Press enter to Continue");
                scan.nextLine();
            }
            gameboard.endTurn();
        }
        
    }
}
