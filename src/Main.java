public class Main {

    public static void main(String[] args) {
	// write your code here

        GameModel gameModel = new GameModel();
        GameView gameView = new GameView();
        GameController gameController = new GameController(gameModel, gameView);
        gameController.run_game();
    }
}
