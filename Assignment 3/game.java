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
	private boolean isValidUserInput = false;
	private char in;
	private boolean isValidChoice;
	private boolean isStart;

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
			isValidChoice = checkIfPlayerWantToQuit(number);
			if(isValidChoice && isStart)
			{
				startGuessing(false);
			}
			else if(isValidChoice && !isStart)
			{
				quite();
			}		
			else {
				System.out.println("please choose a valid number");
			}
		}
		while(!isValidChoice);
	}


	protected boolean checkIfPlayerWantToQuit(String numberChoosen) {
		if(numberChoosen.equals("1")) {
			isStart = true;
			return true;
		}
		else if(numberChoosen.equals("2"))
		{
			isStart = true;
			return true;
		}
		else
			return false;
	}

	private void quite() {
		System.out.println("The game is over");

		System.exit(0);
	}

	private void startGuessing(boolean isChar) {
		System.out.println("Guess the word which consists of "+generateRandomWord.length()+" Characters");
		do {
			String userInput = input.next();
			isValidUserInput = validUserInputWhilePlaying(userInput);
			if(isValidUserInput)
			{
				rounds--;								
				checkIfCharsEqual(in);							
				System.out.println(+rounds+" rounds left");		
			}
			else
				System.out.println("please enter a character");

		}while((rounds != 0 && checkResult() == false) || isValidUserInput == false);

		if(checkResult() == true)							
			System.out.println("You Win");
		else {
			System.out.println("You Lose");
			System.out.println("The word is :"+generateRandomWord);
		}
	}

	protected boolean validUserInputWhilePlaying(String userInput) {
		if(userInput.length() == 0)
			return false;
		in = userInput.charAt(0);
		boolean isChar = Character.isLetter(in);
		if(userInput.length() == 1 && isChar == true)
			return true;
		else
			return false;
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
