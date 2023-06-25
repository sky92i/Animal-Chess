import java.util.Objects;

/**
 * Contains data to be read & update
 */
public class GameModel {
    GameBoard gameBoard;

    public class Piece{
        private String piece; //String to hold piece value
        private String name; // name of chess for print function

        public Piece(){ // Constructor
            this.piece = "";
            this.name = "";
        }

        public String getPiece(){
            return this.piece;
        }
        public String getName(){ return this.name; }

        public void setPiece(String newPiece){
            this.piece = newPiece;
            this.name = newPiece;
        }

        public void setPiece(String newPiece, String newName){
            this.piece = newPiece;
            this.name = newName;
        }
    }

    public class GameBoard {
        public Piece[][] GBoard; //A gameboard that is a 2d array of pieces and empty squares

        public GameBoard(){
            GBoard = new Piece[9][7];

            for (int j = 0; j < 7; j++) {
                for (int i = 0; i < 9; i++) {
                    GBoard[i][j] = new Piece();
                    GBoard[i][j].setPiece("    ");

                    if (i == 0 && j == 0) GBoard[i][j].setPiece("A7", "Li_A");
                    if (i == 2 && j == 0) GBoard[i][j].setPiece("A1", "Ra_A");
                    if (i == 6 && j == 0) GBoard[i][j].setPiece("B8", "El_B");
                    if (i == 8 && j == 0) GBoard[i][j].setPiece("B6", "Ti_B");

                    if (i == 1 && j == 1) GBoard[i][j].setPiece("A3", "Do_A");
                    if (i == 7 && j == 1) GBoard[i][j].setPiece("B2", "Ca_B");

                    if (i == 2 && j == 2) GBoard[i][j].setPiece("A5", "Le_A");
                    if (i == 6 && j == 2) GBoard[i][j].setPiece("B4", "Wo_B");

                    if (i == 0 && j == 3) GBoard[i][j].setPiece(" Dn ");
                    if (i == 8 && j == 3) GBoard[i][j].setPiece(" Dn ");

                    if (i == 2 && j == 4) GBoard[i][j].setPiece("A4", "Wo_A");
                    if (i == 6 && j == 4) GBoard[i][j].setPiece("B5", "Le_B");

                    if (i == 1 && j == 5) GBoard[i][j].setPiece("A2", "Ca_A");
                    if (i == 7 && j == 5) GBoard[i][j].setPiece("B3", "Do_B");

                    if (i == 0 && j == 6) GBoard[i][j].setPiece("A6", "Ti_A");
                    if (i == 2 && j == 6) GBoard[i][j].setPiece("A8", "El_A");
                    if (i == 6 && j == 6) GBoard[i][j].setPiece("B1", "Ra_B");
                    if (i == 8 && j == 6) GBoard[i][j].setPiece("B7", "Li_B");

                    if ((i == 3 || i == 4 || i == 5) && (j == 1 || j == 2 || j == 4 || j == 5)) GBoard[i][j].setPiece("~~~~");
                    if ((i == 0) && (j == 2 || j == 4) || (i == 1 && j == 3)) GBoard[i][j].setPiece("++++");
                    if ((i == 8) && (j == 2 || j == 4) || (i == 7 && j == 3)) GBoard[i][j].setPiece("++++");
                }
            }
        }

        public boolean isTheRatInWater(int i, int j){
            return (i == 3 && (j == 1 || j == 2 || j == 4 || j == 5)) || (i == 4 && (j == 1 || j == 2 || j == 4 || j == 5)) ||
                    (i == 5 && (j == 1 || j == 2 || j == 4 || j == 5));
        }

        public boolean isTigerLionNearWater(int i, int j, int x, int y, GameBoard gameboard){
            int a = Character.getNumericValue(gameboard.GBoard[i][j].getPiece().charAt(1));
            if (a == 6 || a == 7){
                return Objects.equals(gameboard.GBoard[i + y][j + x].getPiece(), "~~~~");
            }
            return false;
        }

        public GameBoard Move(String name, int x, int y, GameBoard gameboard){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 7; j++) {
                    if(Objects.equals(gameboard.GBoard[i][j].getName(), name)){
                        if ((i + y >= 0) && (i + y <= 8) && (j + x >= 0) && (j + x <= 6)) {
                            if (isTigerLionNearWater(i, j, x, y, gameboard)) {
                                if (x == 1) x += 2;
                                if (x == -1) x -= 2;
                                if (y == 1) y += 3;
                                if (y == -1) y -= 3;
                            }
                            if (isValidMove(i, j, x, y, gameboard)) {
                                Piece temp = new Piece();
                                temp.setPiece(gameboard.GBoard[i][j].getPiece(),name);
                                gameboard.GBoard[i][j].setPiece("    ");
                                gameboard.GBoard[i + y][j + x] = temp;
                                addBackTrapWaterSign(gameboard);
                                return gameboard;
                            }
                            else return null;
                        }
                        System.out.println("Out of bounds!");
                        return null;
                    }
                }
            }
            System.out.println("Invalid input, please input valid piece.");
            return null;
        }

        public boolean isValidMove(int i, int j, int x, int y, GameBoard gameboard) {
            int a = Character.getNumericValue(gameboard.GBoard[i][j].getPiece().charAt(1));
            int b = Character.getNumericValue(gameboard.GBoard[i + y][j + x].getPiece().charAt(1));
            // b = 13 when den
            if (b == 13){
                b = 0;
            }
            String aStr = gameboard.GBoard[i][j].getPiece().substring(0, 1);
            String bStr = gameboard.GBoard[i + y][j + x].getPiece().substring(0, 1);

            if((Objects.equals(aStr, "A") && (i == 0 && j == 3)) || (Objects.equals(aStr, "B") && (i == 8 && j == 3))){
                System.out.println("Cannot move to your own den");
                return false;
            }
            if((Objects.equals(gameboard.GBoard[i + y][j + x].getPiece(), "~~~~") || isTheRatInWater(i + y, j + x)) && a != 1 ){
                System.out.println("Cannot move to water");
                return false;
            }
            if(x == 3 && (!Objects.equals(gameboard.GBoard[i][j + 1].getPiece(), "~~~~") || !Objects.equals(gameboard.GBoard[i][j + 2].getPiece(), "~~~~"))){
                System.out.println("There is a rat on any of the intervening water squares.");
                return false;
            }
            if(x == -3 && (!Objects.equals(gameboard.GBoard[i][j - 1].getPiece(), "~~~~") || !Objects.equals(gameboard.GBoard[i][j - 2].getPiece(), "~~~~"))){
                System.out.println("There is a rat on any of the intervening water squares.");
                return false;
            }
            if(y == 4 && (!Objects.equals(gameboard.GBoard[i + 1][j].getPiece(), "~~~~") || !Objects.equals(gameboard.GBoard[i + 2][j].getPiece(), "~~~~")
                    || !Objects.equals(gameboard.GBoard[i + 3][j].getPiece(), "~~~~"))){
                System.out.println("There is a rat on any of the intervening water squares.");
                return false;
            }
            if(y == -4 && (!Objects.equals(gameboard.GBoard[i - 1][j].getPiece(), "~~~~") || !Objects.equals(gameboard.GBoard[i - 2][j].getPiece(), "~~~~")
                    || !Objects.equals(gameboard.GBoard[i - 3][j].getPiece(), "~~~~"))){
                System.out.println("There is a rat on any of the intervening water squares.");
                return false;
            }
            if(a == 1 && isTheRatInWater(i, j) && ( b == 1 && isTheRatInWater(i + y, j + x) )){
                return true;
            }
            if(a == 1 && isTheRatInWater(i, j) && (b == 8 || b == 1)){
                System.out.println("Rat in water cannot eat elephant or rat on land");
                return false;
            }
            if(a == 1 && !isTheRatInWater(i, j) && b == 1 && isTheRatInWater(i + y, j + x)){
                System.out.println("Rat on land cannot attack a rat in the water");
                return false;
            }
            if(Objects.equals(bStr, aStr)){
                System.out.println("The slot is occupied");
                return false;
            }
            if(!Objects.equals(bStr, aStr) && a == 8 && b == 1){
                System.out.println("Elephant cannot eat rat");
                return false;
            }
            if(!Objects.equals(bStr, aStr) && a == 1 && b == 8){
                return true;
            }
            if( !Objects.equals(bStr, aStr) && Objects.equals(aStr, "A") && (((i + y) == 0 && ((j + x) == 2 || (j + x) == 4)) || ((i + y) == 1 && (j + x) == 3)) ){
                return true;
            }
            if( !Objects.equals(bStr, aStr) && Objects.equals(aStr, "B") && (((i + y) == 8 && ((j + x) == 2 || (j + x) == 4)) || ((i + y) == 7 && (j + x) == 3)) ){
                return true;
            }
            if( !Objects.equals(bStr, aStr) && b > a ){
                System.out.println("Cannot eat higher rank");
                return false;
            }
            return true;
        }

        public void addBackTrapWaterSign(GameBoard gameboard){
            if(Objects.equals(gameboard.GBoard[0][2].getPiece(), "    ")) gameboard.GBoard[0][2].setPiece("++++");
            if(Objects.equals(gameboard.GBoard[0][4].getPiece(), "    ")) gameboard.GBoard[0][4].setPiece("++++");
            if(Objects.equals(gameboard.GBoard[1][3].getPiece(), "    ")) gameboard.GBoard[1][3].setPiece("++++");
            if(Objects.equals(gameboard.GBoard[8][2].getPiece(), "    ")) gameboard.GBoard[8][2].setPiece("++++");
            if(Objects.equals(gameboard.GBoard[8][4].getPiece(), "    ")) gameboard.GBoard[8][4].setPiece("++++");
            if(Objects.equals(gameboard.GBoard[7][3].getPiece(), "    ")) gameboard.GBoard[7][3].setPiece("++++");

            if(Objects.equals(gameboard.GBoard[3][1].getPiece(), "    ")) gameboard.GBoard[3][1].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[3][2].getPiece(), "    ")) gameboard.GBoard[3][2].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[3][4].getPiece(), "    ")) gameboard.GBoard[3][4].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[3][5].getPiece(), "    ")) gameboard.GBoard[3][5].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[4][1].getPiece(), "    ")) gameboard.GBoard[4][1].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[4][2].getPiece(), "    ")) gameboard.GBoard[4][2].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[4][4].getPiece(), "    ")) gameboard.GBoard[4][4].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[4][5].getPiece(), "    ")) gameboard.GBoard[4][5].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[5][1].getPiece(), "    ")) gameboard.GBoard[5][1].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[5][2].getPiece(), "    ")) gameboard.GBoard[5][2].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[5][4].getPiece(), "    ")) gameboard.GBoard[5][4].setPiece("~~~~");
            if(Objects.equals(gameboard.GBoard[5][5].getPiece(), "    ")) gameboard.GBoard[5][5].setPiece("~~~~");
        }
    }

    public static boolean isWin(GameBoard gameboard){
        boolean playerA = true, playerB = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 7; j++) {
                String piece = gameboard.GBoard[i][j].getName();
                if (piece.length() == 4){
                    if(Objects.equals(piece.charAt(3), 'A')){
                        playerA = false;
                    }
                    if(Objects.equals(piece.charAt(3), 'B')){
                        playerB = false;
                    }
                }
            }
        }
        if (playerA || playerB){
            System.out.print("null");
            return true;
        }
        
        return !Objects.equals(gameboard.GBoard[0][3].getPiece(), " Dn ") || !Objects.equals(gameboard.GBoard[8][3].getPiece(), " Dn ");
    }

    public GameModel(){
        gameBoard = new GameBoard();
    }

}
