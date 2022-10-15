public class GameBoard {
    public Piece[][] GBoard; //A gameboard that is a 2d array of pieces and empty squares

    public GameBoard(){
        this.GBoard = new Piece[9][7];
    }

    public void printBoard(){
        int i;
        for(i = 0; i < 10; i++){
            System.out.print("+");
            for(int j = 0; j < 7; j++){
                System.out.print("----");
                System.out.print("+");
            }
            System.out.println();
            if(i < 9) {
                for (int j = 0; j < 7; j++) {
                    System.out.print("| ");
                    System.out.print(this.GBoard[i][j].getPiece() + " ");
                }
                System.out.println("| ");
            }
        }
    }
}
