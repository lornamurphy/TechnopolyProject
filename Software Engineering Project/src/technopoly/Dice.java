package technopoly;

public class Dice {

	/**
	 * Generates roll of dice and returns int value
	 * @return
	 */
	public static int rollDice() {
		int roll1 = 0;
		int roll2 = 0;
		int total = 0;
		roll1 = (int) ((Math.random() * 6) + 1);
		roll2 = (int) ((Math.random() * 6) + 1);

		total = roll1 + roll2;
		System.out.println("You rolled " + roll1 + " and " + roll2 + " to move " +total+ " places on the board.");
		
		return total;
	}

}