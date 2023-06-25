import java.util.Scanner;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;

    public GameController(GameModel gameModel, GameView gameView){
        this.gameModel = gameModel;
        this.gameView = gameView;

    }

    public void run_game(){
        boolean Aturn = true;
        GameModel.GameBoard newGameboard = null;
        String[] playerPrompt = {"", ""};
        this.gameView.printBoard(this.gameModel.gameBoard);
        Scanner input = new Scanner(System.in);
        while (true){
            playerPrompt = gameView.promptPlayer(Aturn,input);
            // checks if player is moving his chess
            if ((playerPrompt[0].charAt(3) == 'A' && Aturn) || (playerPrompt[0].charAt(3) == 'B' && !Aturn)){
                if (playerPrompt[1].equals("u")){
                    newGameboard = this.gameModel.gameBoard.Move(playerPrompt[0], 0, -1, this.gameModel.gameBoard);
                }
                else if (playerPrompt[1].equals("d")){
                    newGameboard = this.gameModel.gameBoard.Move(playerPrompt[0], 0, 1, this.gameModel.gameBoard);
                }
                else if (playerPrompt[1].equals("l")){
                    newGameboard = this.gameModel.gameBoard.Move(playerPrompt[0], -1, 0, this.gameModel.gameBoard);
                }
                else if (playerPrompt[1].equals("r")){
                    newGameboard = this.gameModel.gameBoard.Move(playerPrompt[0], 1, 0, this.gameModel.gameBoard);
                }
                else { // if direction input is invalid input
                    newGameboard = null;
                    System.out.println("Invalid direction, please enter again");
                }
            }
            else {
                newGameboard = null;
                System.out.println("Invalid piece, please enter again");
            }


            if (newGameboard == null){
                continue;
            }
            Aturn = !Aturn;
            this.gameModel.gameBoard = newGameboard;
            this.gameView.printBoard(this.gameModel.gameBoard);

            if(GameModel.isWin(this.gameModel.gameBoard) && !Aturn){
                System.out.println("Player A win!");
                break;
            }
            if(GameModel.isWin(this.gameModel.gameBoard) && Aturn){
                System.out.println("Player B win!");
                break;
            }
        }
        input.close();
    }

}
