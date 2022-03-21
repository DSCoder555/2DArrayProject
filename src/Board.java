import java.util.ArrayList;

//Darius Saadat
public class Board {
    private Piece[][] board;
    private int currentPlayer = 1;

    public Board(){
        board = new Piece[8][8];
        boolean player = true;
        boolean hasPiece = false;
        for (int row = 0; row < 8; row++) {
            for (int collumn = 0; collumn < 8; collumn++) {
                if (hasPiece){
                    board[row][collumn] = new Piece(player, row, collumn);
                    hasPiece = false;
                }
                else {
                    hasPiece = true;
                }

            }
            hasPiece = !hasPiece;
            if(row == 2){
                row = 4;
                player = false;
            }
        }
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public ArrayList<ArrayList<int[]>> displayBoard(){
        ArrayList<ArrayList<int[]>> movables = new ArrayList<>();
        System.out.println("---------------------------------");
        for (int row = 0; row < 8; row++) {
            System.out.print(row + 1 + "|");
            for (int collumn = 0; collumn < 8; collumn++) {
                Piece current = board[row][collumn];
                if (current == null){
                    System.out.print("   |");
                }
                else if(!isTurn(current)){
                    System.out.print(" O |");
                }
                else if(Moves(current).size() > 1){
                    if (movables.size() <10){
                        System.out.print(" " + (movables.size() + 1) + " |");
                    }
                    else{
                        System.out.print((movables.size() + 1) + " |");
                    }

                    movables.add(Moves(current));
                }
                else{
                    System.out.print(" X |");
                }
            }
            System.out.println();
            System.out.println("---------------------------------");
        }
        return movables;
    }

    public void displayMoves(ArrayList moves){
        int movables = 1;
        System.out.println("---------------------------------");
        for (int row = 0; row < 8; row++) {
            System.out.print(row + 1 + "|");
            for (int collumn = 0; collumn < 8; collumn++) {
                Piece current = board[row][collumn];
                if (current == null){
                    System.out.print("   |");
                }
                else if(!isTurn(current)){
                    System.out.print(" O |");
                }
                else if(moves.indexOf(current) > 0){
                    if (moves.indexOf(current)  == 0){
                        System.out.print(" O |");
                    }
                    else{

                        System.out.print(" " + movables + " |");
                        movables++;
                    }
                }
                else{
                    System.out.print(" X |");
                }
            }
            System.out.println();
            System.out.println("---------------------------------");
        }
    }

    public ArrayList<int[]> Moves(Piece piece){
        ArrayList<int[]> moves = new ArrayList<>();
        int direction = 1;
        int row = piece.getRow();
        int collumn = piece.getCollumn();
        moves.add(new int[]{row,collumn});
        if (!piece.isP2() || piece.isKing()){
            if (row - 1 > 0 && collumn - 1 > 0 && board[row-1][collumn-1] == null){
                int[] move = {row-1,collumn-1,0};
                moves.add(move);
            }
            if (row - 1 > 0 && collumn + 1 < 7 && board[row-1][collumn+1] == null){
                int[] move = {row-1,collumn+1,0};
                moves.add(move);
            }
            if (row - 2 > 0 && collumn - 2 > 0 && ((board[row-1][collumn-1] != null && (!isTurn(board[row-1][collumn-1]))) && board[row-2][collumn-2] == null)){
                int[] move = {row-1,collumn-1,1};
                moves.add(move);
            }
            if (row - 2 > 0 && collumn + 2 < 7 && ((board[row-1][collumn+1] != null && (!isTurn(board[row-1][collumn+1]))) && board[row-2][collumn+2] == null)){
                int[] move = {row-1,collumn+1,1};
                moves.add(move);
            }
        }
        if (piece.isP2() || piece.isKing()){
            if (row + 1 < 7 && collumn - 1 > 0 && board[row+1][collumn-1] == null){
                int[] move = {row+1,collumn-1,0};
                moves.add(move);
            }
            if (row + 1 < 7 && collumn + 1 < 7 && board[row+1][collumn+1] == null){
                int[] move = {row+1,collumn+1,0};
                moves.add(move);
            }
            if (row + 2 < 7 && collumn - 2 > 0 && ((board[row+1][collumn-1] != null && (!isTurn(board[row+1][collumn-1]))) && board[row+2][collumn-2] == null)){
                int[] move = {row+1,collumn-1,1};
                moves.add(move);
            }
            if (row + 2 < 7 && collumn + 2 < 7 && ((board[row+1][collumn+1] != null && (!isTurn(board[row+1][collumn+1])))  && board[row+2][collumn+2] == null)){
                int[] move = {row+1,collumn+1,1};
                moves.add(move);
            }
        }
        return moves;
    }

    public void move(){
        
    }

    public boolean isTurn(Piece check){
        return (check.isP2() == (currentPlayer == 2));
    }

    public void endTurn(){
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }
    }

    public boolean hasWon(){
        boolean P1lost = true;
        boolean P2lost = true;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board[row][column] != null){
                    if (board[row][column].isP2()){
                        P1lost = false;
                    }
                    if (!board[row][column].isP2()){
                        P2lost = false;
                    }
                }

            }

        }
        return (P1lost || P2lost);
    }

}
