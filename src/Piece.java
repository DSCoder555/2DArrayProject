import java.util.ArrayList;

public class Piece {
    private boolean isKing = false;
    private boolean isP2 = false;
    private int x;
    private int y;

    public Piece(boolean isP2, int x, int y){
        this.isP2 = isP2;
        this.x = x;
        this.y = y;
    }

    public boolean isP2(){
        return isP2;
    }

    public boolean isKing(){
        return isKing;
    }

    public int getRow(){
        return x;
    }

    public int getCollumn(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setKing(){
        isKing = true;
    }


}
