package hangman;


import java.io.FileNotFoundException;



public class Main {

	
	
	public static void main(String[] args) throws FileNotFoundException {
		Game game = new Game();
		game.startTheGame(args);
		game.printUI();
	}

}
