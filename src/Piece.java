//Darius Saadat
// class representing a piece on the game board
// stores the piece's position and attributes
// used to get and change piece's position
public class Piece {
    //instance variables
    private boolean isKing = false;
    private final boolean isP2;
    private int row;
    private int col;

    /** Constructor; all Pieces are initialized at the beginning of game
     *
     * @param isP2 whether this piece belongs to player 2. (final variable)
     * @param row current row of this piece
     * @param col current column of this piece
     */
    public Piece(boolean isP2, int row, int col){
        this.isP2 = isP2;
        this.row = row;
        this.col = col;
    }

    //getter methods

    /** isP2; getter method for isP2
     *
     * @return whether this piece belongs to player 2.
     */
    public boolean isP2(){
        return isP2;
    }

    /** isKing; getter method for isKing
     *
     * @return whether the piece is currently a king
     */
    public boolean isKing(){
        return isKing;
    }

    /** getRow; getter method for row
     *
     * @return row of the current position of the piece
     */
    public int getRow(){
        return row;
    }

    /** getColumn; getter method for column
     *
     * @return column of the current position of the piece
     */
    public int getColumn(){
        return col;
    }

    //setter methods

    /** setRow; setter method for row
     *
     * @param newRow row of the piece's current position
     */
    public void setRow(int newRow) {
        this.row = newRow;
    }

    /** setCol; setter method for column
     *
     * @param newCol column of the piece's current position
     */
    public void setCol(int newCol) {
        this.col = newCol;
    }

    /** becomeKing; turns piece into king piece once it reaches end of board
     *
     */
    public void becomeKing(){
        isKing = true;
    }


}
