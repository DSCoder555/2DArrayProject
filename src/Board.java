//Darius Saadat
import java.util.ArrayList;

// class to represent the checkerboard
// sets and changes the position of pieces on the board
public class Board {
    //instance variables
    //checkerboard with pieces in a 2D Piece array
    private Piece[][] board;
    private int currentPlayer = 1;

    /**Constructor; creates and fills the starting board
     *
     */
    public Board(){
        board = new Piece[8][8];
        boolean player = true;
        boolean hasPiece = false;
        //assigns correct pieces to correct positions to start game
        //for loop to access each row
        for (int row = 0; row < 8; row++) {
            //for loop to access each column
            for (int column = 0; column < 8; column++) {
                // alternates between placing and not placing a piece
                if (hasPiece){
                    board[row][column] = new Piece(player, row, column);
                    hasPiece = false;
                }
                else {
                    hasPiece = true;
                }

            }
            hasPiece = !hasPiece;
            //skips the two middle rows
            if(row == 2){
                row = 4;
                player = false;
            }
        }
    }

    //getter methods

    /** getCurrentPlayer; getter method of currentPlayer
     *
     * @return number of current player
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }

    /**displayBoard; prints the entire board and creates a list of lists of piece's moves
     *
     * @return a list of lists of piece's moves
     */
    public ArrayList<ArrayList<Move>> displayBoard(){
        ArrayList<ArrayList<Move>> movables = new ArrayList<>();
        //creates top of board
        System.out.println("---------------------------------");
        //for loop to print each row of the board
        for (int row = 0; row < 8; row++) {
            System.out.print("|");
            //for loop to print each column of the board
            for (int column = 0; column < 8; column++) {
                // creates current piece and identifies the appropriate symbol
                Piece current = board[row][column];
                // print blank space if empty
                if (current == null){
                    System.out.print("   |");
                }
                // print different number for each movable piece
                else if(isTurn(current) &&allPossibleMoves(current).size() > 0){
                    // printing for moves with number greater than or equal to 10
                    if (movables.size() <10){
                        System.out.print(" " + (movables.size() + 1) + " |");
                    }
                    // printing for moves with number less than 10
                    else{
                        System.out.print((movables.size() + 1) + " |");
                    }
                    //adds their moves to the arrayList
                    movables.add(allPossibleMoves(current));
                }
                //add symbol for every other piece
                else{
                    if (current.isKing()){
                        if (current.isP2()){
                            //P2 king
                            System.out.print(" W |");
                        }
                        else{
                            //P1 king
                            System.out.print(" M |");
                        }
                    }
                    else{
                        if (current.isP2()){
                            //P2 non-king
                            System.out.print(" X |");
                        }
                        else{
                            //P1 non-king
                            System.out.print(" O |");
                        }
                    }
                }
            }
            System.out.println();
            //Bottom of the board
            System.out.println("---------------------------------");
        }
        return movables;
    }

    /**displayMoves; prints the board with possible moves
     *
     * @param moves all the moves of the selected piece
     */
    public void displayMoves(ArrayList<Move> moves){
        // creates a new array of all possible moves with a for loop
        Move[] canMoves = new Move[moves.size()];
        for (int i = 0; i < moves.size(); i++){
            canMoves[i] = moves.get(i);
            System.out.println(moves.get(i).getEndRow() + " " + moves.get(i).getEndCol());
        }
        int movables = 1;
        //Top of the board
        System.out.println("---------------------------------");
        //for loop to print all rows
        for (int row = 0; row < 8; row++) {
            System.out.print("|");
            //for loop to print all columns
            for (int column = 0; column < 8; column++) {
                Piece current = board[row][column];
                //checks if current position is a possible move
                boolean posMove = false;
                for (Move canMove : canMoves) {
                    if (row == canMove.getEndRow() && column == canMove.getEndCol()) {
                        posMove = true;
                        break;
                    }

                }
                //prints different number if a possible move
                if (posMove){
                    System.out.print(" " + movables + " |");
                    movables++;
                }
                //prints a blank space if empty
                else if(board[row][column] == null){
                    System.out.print("   |");
                }
                //fills with correct symbol
                else{
                    if (current.isKing()){
                        if (current.isP2()){
                            //P2 king
                            System.out.print(" W |");
                        }
                        else{
                            //P1 king
                            System.out.print(" M |");
                        }
                    }
                    else{
                        if (current.isP2()){
                            //P2 non-king
                            System.out.print(" X |");
                        }
                        else{
                            //P1 non-king
                            System.out.print(" O |");
                        }
                    }
                }
            }
            System.out.println();
            //Bottom of the board
            System.out.println("---------------------------------");
        }
    }

    /**allPossibleMoves; checks all 8 move methods and creates an array list with all possible moves for one piece
     *
     * @param piece the piece whose moves are being checked
     * @return an array list with all possible moves for one piece
     */
    public ArrayList<Move> allPossibleMoves(Piece piece){
        ArrayList<Move> moves = new ArrayList<>();
        int row = piece.getRow();
        int column = piece.getColumn();
        //Checks for each of the possible 8 methods of movement to find and add all possible moves to arrayList
        if (!piece.isP2() || piece.isKing()){
            if (row - 2 >= 0 && column - 2 >= 0 && ((board[row-1][column-1] != null && (!isTurn(board[row-1][column-1]))) && board[row-2][column-2] == null)){
                Move move = new Move(row, column, row - 2, column - 2);
                moves.add(move);
            }
            if (row - 2 >= 0 && column + 2 <= 7 && ((board[row-1][column+1] != null && (!isTurn(board[row-1][column+1]))) && board[row-2][column+2] == null)){
                Move move = new Move(row, column, row - 2, column + 2);
                moves.add(move);
            }
            if (row - 1 >= 0 && column - 1 >= 0 && board[row-1][column-1] == null){
                Move move = new Move(row, column, row - 1, column - 1);
                moves.add(move);
            }
            if (row - 1 >= 0 && column + 1 <= 7 && board[row-1][column+1] == null){
                Move move = new Move(row, column, row - 1, column + 1);
                moves.add(move);
            }
        }
        if (piece.isP2() || piece.isKing()){
            if (row + 1 <= 7 && column - 1 >= 0 && board[row+1][column-1] == null){
                Move move = new Move(row, column, row + 1, column - 1);
                moves.add(move);
            }
            if (row + 1 <= 7 && column + 1 <= 7 && board[row+1][column+1] == null){
                Move move = new Move(row, column, row + 1, column + 1);
                moves.add(move);
            }
            if (row + 2 <= 7 && column - 2 >= 0 && ((board[row+1][column-1] != null && (!isTurn(board[row+1][column-1]))) && board[row+2][column-2] == null)){
                Move move = new Move(row, column, row + 2, column - 2);
                moves.add(move);
            }
            if (row + 2 <= 7 && column + 2 <= 7 && ((board[row+1][column+1] != null && (!isTurn(board[row+1][column+1])))  && board[row+2][column+2] == null)){
                Move move = new Move(row, column, row + 2, column + 2);
                moves.add(move);
            }
        }
        return moves;
    }

    /**movePiece; once a move is chosen, the pieces are moved and valued changed
     *
     * @param currentMove chosen which will be used to determine the piece moves and changes.
     */
    public void movePiece(Move currentMove){
        //moves piece to new position
        board[currentMove.getEndRow()][currentMove.getEndCol()] = board[currentMove.getStartRow()][currentMove.getStartCol()];
        board[currentMove.getStartRow()][currentMove.getStartCol()] = null;
        //changes saved position in piece
        board[currentMove.getEndRow()][currentMove.getEndCol()].setRow(currentMove.getEndRow());
        board[currentMove.getEndRow()][currentMove.getEndCol()].setCol(currentMove.getEndCol());
        // kills piece jumped over if possible
        if(currentMove.canKill()){
            board[currentMove.killRow()][currentMove.killCol()] = null;
        }
        // kings piece if possible
        if (currentMove.canKing()){
            board[currentMove.getEndRow()][currentMove.getEndCol()].becomeKing();
        }
    }

    /**isTurn; checks is it is a piece's turn
     *
     * @param check the piece whose checked to see if it is their turn
     * @return whether is it is a piece's turn
     */
    public boolean isTurn(Piece check){
        return (check.isP2() == (currentPlayer == 2));
    }

    /**endTurn; changes current player
     *
     */
    public void endTurn(){
        if (currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }
    }

    /**hasWon; checks to see if either player has won the game yet
     *
     * @return whether either player has won the game yet
     */
    public boolean hasWon(){
        boolean P1lost = true;
        boolean P2lost = true;
        //iterates thoughe every row and column to see if a P1 or P2 piece exists
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
