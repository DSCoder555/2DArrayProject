public class Move {
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private boolean kill = false;
    private boolean king = false;
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

    public int getStartRow(){
        return startRow;
    }

    public int getStartCol(){
        return startCol;
    }

    public int getEndRow(){
        return endRow;
    }

    public int getEndCol(){
        return endCol;
    }

    public boolean canKing(){
        return king;
    }

    public boolean canKill(){
        return kill;
    }

    public int killRow(){
        return(startRow + endRow)/2;
    }

    public int killCol(){
        return (startCol + endCol)/2;
    }


}
