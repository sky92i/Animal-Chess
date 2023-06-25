import java.util.Scanner;

public class GameView {

    public void printBoard(GameModel.GameBoard gameBoard){
        int i;
        for(i = 0; i < 10; i++){
            System.out.print("+");
            for(int j = 0; j < 7; j++){
                System.out.print("------");
                System.out.print("+");
            }
            System.out.println();
            if(i < 9) {
                for (int j = 0; j < 7; j++) {
                    System.out.print("| ");
                    System.out.print(gameBoard.GBoard[i][j].getName() + " ");
                }
                System.out.println("| ");
            }
        }
    }

    public String[] promptPlayer(boolean ATurn, Scanner input){
        String[] playerPrompt = {"", ""};
        if(ATurn){
            System.out.println("Player A's turn, please enter selected piece: ");
            playerPrompt[0] = input.nextLine();

            System.out.println("Player A's turn, please enter direction: ");

            playerPrompt[1] = input.nextLine();
            while (playerPrompt[0].length() != 4 || playerPrompt[1].length() != 1) {
                System.out.println("Invalid input, please input again\n");
                System.out.println("Player A's turn, please enter selected piece: ");  
                playerPrompt[0] = input.nextLine();
                System.out.println("Player A's turn, please enter direction: ");
                  
                playerPrompt[1] = input.nextLine();
            }
            
        }
        else {
            System.out.println("Player B's turn, please enter selected piece: ");
            playerPrompt[0] = input.nextLine();

            System.out.println("Player B's turn, please enter direction: ");

            playerPrompt[1] = input.nextLine();

            while (playerPrompt[0].length() != 4 || playerPrompt[1].length() != 1) {
                System.out.println("Invalid input, please input again\n");
                System.out.println("Player B's turn, please enter selected piece: ");

                playerPrompt[0] = input.nextLine();

                System.out.println("Player B's turn, please enter direction: ");

                playerPrompt[1] = input.nextLine();
            }
        }
        
        return playerPrompt;
    }
}
