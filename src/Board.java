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

    public ArrayList<ArrayList<Move>> displayBoard(){
        ArrayList<ArrayList<Move>> movables = new ArrayList<>();
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
                else if(Moves(current).size() > 0){
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

    public void displayMoves(ArrayList<Move> moves){
        Move[] canMoves = new Move[moves.size()];
        for (int i = 0; i < moves.size(); i++){
            canMoves[i] = moves.get(i);
        }
        int movables = 1;
        System.out.println("---------------------------------");
        for (int row = 0; row < 8; row++) {
            System.out.print(row + 1 + "|");
            for (int collumn = 0; collumn < 8; collumn++) {
                Piece current = board[row][collumn];
                int[] position = new int[]{row, collumn};
                boolean posMove = false;
                for (int i = 0; i < canMoves.length; i++) {
                    if (row == canMoves[i].getEndRow() && collumn == canMoves[i].getEndCol()){
                        posMove = true;
                    }

                }
                if (posMove){
                    System.out.print(" " + movables + " |");
                    movables++;
                }
                else if(board[row][collumn] == null){
                    System.out.print("   |");
                }
                else if(!isTurn(current)){
                    System.out.print(" X |");
                }
                else{
                    System.out.print(" O |");
                }
            }
            System.out.println();
            System.out.println("---------------------------------");
        }
    }

    public ArrayList<Move> Moves(Piece piece){
        ArrayList<Move> moves = new ArrayList<Move>();
        int direction = 1;
        int row = piece.getRow();
        int collumn = piece.getCollumn();
        if (!piece.isP2() || piece.isKing()){
            if (row - 1 > 0 && collumn - 1 > 0 && board[row-1][collumn-1] == null){
                Move move = new Move(row, collumn, row - 1, collumn - 1);
                moves.add(move);
            }
            if (row - 1 > 0 && collumn + 1 < 7 && board[row-1][collumn+1] == null){
                Move move = new Move(row, collumn, row - 1, collumn + 1);
                moves.add(move);
            }
            if (row - 2 > 0 && collumn - 2 > 0 && ((board[row-1][collumn-1] != null && (!isTurn(board[row-1][collumn-1]))) && board[row-2][collumn-2] == null)){
                Move move = new Move(row, collumn, row - 2, collumn - 2);
                moves.add(move);
            }
            if (row - 2 > 0 && collumn + 2 < 7 && ((board[row-1][collumn+1] != null && (!isTurn(board[row-1][collumn+1]))) && board[row-2][collumn+2] == null)){
                Move move = new Move(row, collumn, row - 2, collumn + 2);
                moves.add(move);
            }
        }
        if (piece.isP2() || piece.isKing()){
            if (row + 1 < 7 && collumn - 1 > 0 && board[row+1][collumn-1] == null){
                Move move = new Move(row, collumn, row + 1, collumn - 1);
                moves.add(move);
            }
            if (row + 1 < 7 && collumn + 1 < 7 && board[row+1][collumn+1] == null){
                Move move = new Move(row, collumn, row + 1, collumn + 1);
                moves.add(move);
            }
            if (row + 2 < 7 && collumn - 2 > 0 && ((board[row+1][collumn-1] != null && (!isTurn(board[row+1][collumn-1]))) && board[row+2][collumn-2] == null)){
                Move move = new Move(row, collumn, row + 2, collumn + 2);
                moves.add(move);
            }
            if (row + 2 < 7 && collumn + 2 < 7 && ((board[row+1][collumn+1] != null && (!isTurn(board[row+1][collumn+1])))  && board[row+2][collumn+2] == null)){
                Move move = new Move(row, collumn, row + 2, collumn + 2);
                moves.add(move);
            }
        }
        return moves;
    }

    public void move(Move currentMove){
        board[currentMove.getEndRow()][currentMove.getEndCol()] = board[currentMove.getStartRow()][currentMove.getStartCol()];
        board[currentMove.getStartRow()][currentMove.getStartCol()] = null;
        if(currentMove.canKill()){
            board[currentMove.killRow()][currentMove.killCol()] = null;
        }
        if (currentMove.canKing()){
            board[currentMove.getEndRow()][currentMove.getEndCol()].setKing();
        }
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
