package hangman;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private Scanner input = new Scanner(System.in);
	private int rounds = 7;					
	private Object[] result;
	private String generateRandomWord;


	protected void startTheGame(String args[]) {
		generateRandomWord = generateRandomWord(args);
	}

	@SuppressWarnings("resource")
	private String generateRandomWord(String args[]) {
		File file = new File(args[0]);
		Random random = new Random();
		int randomNumber = random.nextInt(70);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext() && randomNumber != 0) {
				generateRandomWord = scan.next();
				randomNumber--;
			}
			generateRandomWord = generateRandomWord.toLowerCase();				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		result = new Object[generateRandomWord.length()];					
		for(int i = 0; i <= result.length-1; i++)
			result[i] = "-";

		return generateRandomWord;
	}

	@SuppressWarnings("resource")
	protected void printUI() {
		Scanner input = new Scanner(System.in);
		System.out.println("choose a number");
		System.out.println("1.Strat the game");
		System.out.println("2.Quite the game");
		String number = "";
		do {
			number = input.next();
			if(number.equals("1"))
			{
				startGuessing(false);
			}
			else if(number.equals("2"))
			{
				quite();
			}		
			else {
				System.out.println("please choose a valid number");
			}
		}
		while( !number.equals("1") && !number.equals("2") );
	}


	private void quite() {
		System.out.println("The game is over");

		System.exit(0);
	}

	private void startGuessing(boolean isChar) {
		System.out.println("Guess the word which consists of "+generateRandomWord.length()+" Characters");
		do {
			String userInput = input.next();
			char in = userInput.charAt(0);
			isChar = Character.isLetter(in);
			if(userInput.length() == 1 && isChar == true)
			{
				rounds--;								
				checkIfCharsEqual(in);							
				System.out.println(+rounds+" rounds left");		

			}
			else {
				System.out.println("please enter a character"); 
			}

		}while(rounds != 0 && checkResult() == false);

		if(checkResult() == true)							
			System.out.println("You Win");
		else {
			System.out.println("You Lose");
			System.out.println("The word is :"+generateRandomWord);
		}
	}

	private boolean checkResult() {
		for(Object o : result)
			if(o.equals("-"))
				return false;
		return true;
	}

	private void checkIfCharsEqual(char userInput) {
		for(int index = 0; index < generateRandomWord.length() ; index++)
		{
			Character ch = generateRandomWord.charAt(index);
			if(ch.equals(userInput)) {
				result[index] = userInput;
			}
		}

		System.out.print("Result Of Guessing : ");
		for(Object ch : result)
			System.out.print(ch);
		System.out.println();
	}

}
