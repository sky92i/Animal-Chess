import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GameBoard gameboard = Model.createBoard();
        GameBoard newGameboard = null;
        gameboard.printBoard();

        boolean ATurn = true;

        while(true) {
            String piece;
            String direction;

            //Turn prompt
            if(ATurn){
                System.out.println("Player A's turn, please enter selected piece: ");
                Scanner input = new Scanner(System.in);
                piece = "A" + input.nextLine();

                System.out.println("Player A's turn, please enter direction: ");
                input = new Scanner(System.in);
                direction = input.nextLine();

                ATurn = false;
            }
            else{
                System.out.println("Player B's turn, please enter selected piece: ");
                Scanner input = new Scanner(System.in);
                piece = "B" + input.nextLine();

                System.out.println("Player B's turn, please enter direction: ");
                input = new Scanner(System.in);
                direction = input.nextLine();

                ATurn = true;
            }

            if(Objects.equals(direction, "u")) {
                newGameboard = Model.Move(piece, 0, -1, gameboard);
            }
            if(Objects.equals(direction, "d")) {
                newGameboard = Model.Move(piece, 0,1, gameboard);
            }
            if(Objects.equals(direction, "l")) {
                newGameboard = Model.Move(piece, -1, 0, gameboard);
            }
            if(Objects.equals(direction, "r")) {
                newGameboard = Model.Move(piece, 1,0, gameboard);
            }
            //clearConsole();
            if(newGameboard == null){
                ATurn = !ATurn;
                continue;
            }
            gameboard = newGameboard;
            gameboard.printBoard();
            if(Model.isWin(gameboard) && !ATurn){
                System.out.println("Player A win!");
                break;
            }
            if(Model.isWin(gameboard) && ATurn){
                System.out.println("Player B win!");
                break;
            }

        }
    }

    /*public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }*/
}