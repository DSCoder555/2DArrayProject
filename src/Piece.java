public class Piece {
    private boolean isKing = false;
    private boolean isP2 = false;

    public Piece(boolean isP2){
        this.isP2 = isP2;
    }

    public boolean isP2(){
        return isP2;
    }

    public boolean isKing(){
        return isKing;
    }


}
