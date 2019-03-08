package hangman;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

	
	private String validInputWhilePlaying = "f";
	private String inValidInput = "long text with many characters";
	private String inValidInputInteger = "3";
	private String emptyInput = "";
	
	private String validChoiceFromMenu = "1";
	private String validChoiceFromMenu2 = "2";

	Game hangMan = new Game();
	
	@Test
	void testcheckIfChooseFromMenu() {
		assertEquals(true, hangMan.checkIfPlayerWantToQuit(validChoiceFromMenu));
		assertEquals(true, hangMan.checkIfPlayerWantToQuit(validChoiceFromMenu2));
		assertEquals(false, hangMan.checkIfPlayerWantToQuit(inValidInput));
		assertEquals(false, hangMan.checkIfPlayerWantToQuit(inValidInputInteger));
		assertEquals(false, hangMan.checkIfPlayerWantToQuit(emptyInput));

	}
	
	// the player will press number 2 to quit the game
	@Test
	void testcheckIfPlayerWantToQuit() {
		assertEquals(true, hangMan.checkIfPlayerWantToQuit("1"));
		assertEquals(false, hangMan.checkIfPlayerWantToQuit("2"));

	}
	
	
	
	@Test
	void testValidUserInputWhilePlaying() {
		assertEquals(true, hangMan.validUserInputWhilePlaying(validInputWhilePlaying));
		assertEquals(false, hangMan.validUserInputWhilePlaying(inValidInput));
		assertEquals(false, hangMan.validUserInputWhilePlaying(inValidInputInteger));
		assertEquals(false, hangMan.validUserInputWhilePlaying(emptyInput));

	}
}
