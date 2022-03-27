//Darius Saadat
//class for creating a piece's possible moves
//if move is chosen, information from this class is used to identify movement and proper actions
public class Move {
    // instance variables
    private final int startRow;
    private final int startCol;
    private final int endRow;
    private final int endCol;
    private boolean kill = false;
    private boolean king = false;

    /**Constructor; moves are initialized in allPossibleMove method
     *
     * @param startRow row of piece's current position
     * @param startCol column of piece's current position
     * @param endRow row of move's final position
     * @param endCol col of move's final position
     */
    public Move(int startRow, int startCol, int endRow, int endCol){
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        if(endRow - startRow == 2 || endRow - startRow == -2){
            kill = true;
        }
        if(endRow == 0 || endRow == 7){
            king = true;
        }
    }

    // getter methods

    /** getStartRow; getter method for startRow
     *
     * @return row of piece's current position
     */
    public int getStartRow(){
        return startRow;
    }

    /** getStartCol; getter method for startCol
     *
     * @return column of piece's current position
     */
    public int getStartCol(){
        return startCol;
    }

    /** getEndRow; getter method for endRow
     *
     * @return row of move's final position
     */
    public int getEndRow(){
        return endRow;
    }

    /** getEndCol; getter method for endCol
     *
     * @return col of move's final position
     */
    public int getEndCol(){
        return endCol;
    }

    /** canKing; getter method of king
     *
     * @return whether move will cause piece to become a king piece
     */
    public boolean canKing(){
        return king;
    }

    /** canKill; getter method of kill
     *
     * @return whether move will kill another piece
     */
    public boolean canKill(){
        return kill;
    }

    /** killRow; calculates and returns row of the killed piece
     * Precondition: only used when kill is true
     * @return row of the killed piece
     */
    public int killRow(){
        return(startRow + endRow)/2;
    }

    /** killCol; calculates and returns column of the killed piece
     * Precondition: only used when kill is true
     * @return column of the killed piece
     */
    public int killCol(){
        return (startCol + endCol)/2;
    }
}
