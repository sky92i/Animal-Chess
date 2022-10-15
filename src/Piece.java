public class Piece {
    private String piece; //String to hold piece value

    public Piece(){ // Constructor
        this.piece = "";
    }

    public String getPiece(){
        return this.piece;
    }

    public void setPiece(String newPiece){
        this.piece = newPiece;
    }
}
