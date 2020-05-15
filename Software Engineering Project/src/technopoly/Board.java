package technopoly;

import java.util.ArrayList;

public class Board {

	/**
	 * ArrayList of Board Squares - scalable to preferred number of squares
	 */
	public static ArrayList<Square> gameSquares = new ArrayList<Square>(12);
	
	/**
	 * Sets up Board and properties
	 */
	public void setUpBoard() {

		Square sq1 = new Square(0, "Recruitment Drive", "General", false, 0);
		Square sq2 = new Square(1, "Maintenance", "Support", false, 5);
		Square sq3 = new Square(2, "Consulting", "Support", false, 5);
		Square sq4 = new Square(3, "Python", "Development", false,  10);
		Square sq5 = new Square(4, "Java", "Development", false,  10);
		Square sq6 = new Square(5, "C#", "Development", false,  10);
		Square sq7 = new Square(6, "Work From Home", "General", false,  0);
		Square sq8 = new Square(7, "Software", "Cloud", false,  20);
		Square sq9 = new Square(8, "Infrastructure", "Cloud", false,  20);
		Square sq10 = new Square(9, "Platform", "Cloud", false,  20);
		Square sq11 = new Square(10, "Data Integrity", "Security", false,  30);
		Square sq12 = new Square(11, "Threat Intelligence", "Security", false, 30);

		gameSquares.add(sq1);
		gameSquares.add(sq2);
		gameSquares.add(sq3);
		gameSquares.add(sq4);
		gameSquares.add(sq5);
		gameSquares.add(sq6);
		gameSquares.add(sq7);
		gameSquares.add(sq8);
		gameSquares.add(sq9);
		gameSquares.add(sq10);
		gameSquares.add(sq11);
		gameSquares.add(sq12);

	}
	
	/**
	 * Sets the players new position on the Board allowing play to move through another loop of the Board
	 * @param diceRoll - random number generated, 2 dice
	 * @param boardPosition - current position before dice roll
	 * @return - new square ID
	 */
	public static int setPosition(int diceRoll, int boardPosition) {
		int boardSize = gameSquares.size();
		int spacesLeft = 0;
		int overflow = 0;
		
		if((diceRoll+boardPosition)>= boardSize) {
			spacesLeft = (boardSize - boardPosition);
			overflow = diceRoll - spacesLeft;
			return overflow;
		} else {
			return boardPosition += diceRoll;
		}
		
	}
}
