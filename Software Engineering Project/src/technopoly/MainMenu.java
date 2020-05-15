 package technopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

	private static Scanner scanner = null;
	
	public static void main(String[] args) {
		begin();
	}
	/**
	 * Explanation of the game and menu options
	 */
	public static void begin() {

		Board b = new Board();
		b.setUpBoard();
		
		System.out.print(" \t Welcome to Technopoly!\n\n");
		
		System.out.println("The aim of the game is to develop your start-up Software Company by building a highly-skilled workforce!");
		System.out.println("You will start off with 50 junior engineers and as you land on squares,");
		System.out.println("you will get the opportunity to train them in various areas of technology.");
		System.out.println("You can upskill junior engineers to senior, and then lead engineer.");
		System.out.println("However, you must sacrifice the salary of a specified number of juniors");
		System.out.println("in order to gain a senior or lead position....");
		System.out.print("\n\n");
		
	
			int userOption;
			String userInput = null;
			Scanner scanner = MainMenu.getScanner();	
			
		System.out.println("Please choose one from the following options: ");
		System.out.println("1. Start New Game");
		System.out.println("2. Leave Game");
		System.out.println("3. End Game");

		System.out.println();
		System.out.println("Please make your selection (enter 1-3) ...");
		userOption = scanner.nextInt();

		switch (userOption) {
		case 1:
			System.out.println("You have chosen to start a New Game...");
			PlayMenu.start();
			break;
		case 2:
			System.out.println("Are you sure you want to leave the game? y/n");
			userInput = scanner.next();
			if (userInput.equalsIgnoreCase("y")) {
				System.out.println("You have chosen to leave the game.");
				System.out.print("\n\n");
				begin();
			} else if (userInput.equalsIgnoreCase("n")) {
				begin();
			}
			break;
		case 3:
			System.out.println("Your game has now ended. ");
			System.out.println("");
			break;
		default:
			System.out.println();		
		}

	}
	/**
	 * Calculates and displays final scores and declares winner of the game
	 */
	public static void endGame() {
		
		System.out.println("Here are the final results...");
		System.out.printf("\n\n");
		System.out.println("Scores are based on the following credits:");
		System.out.println("Junior Engineers = 10 credits;");
		System.out.println("Senior Engineers = 30 credits;");
		System.out.println("Lead Engineers = 70 credits;");
		System.out.println();
			
		
		ArrayList<Player> finalPlayers = new ArrayList<>();
		finalPlayers = PlayMenu.players;
		
		for (int loop=0; loop<finalPlayers.size(); loop++) {
			System.out.println(finalPlayers.get(loop).getName()+" - your final workforce is: ");
			System.out.println("Total juniors: "+finalPlayers.get(loop).getJuniorEng());
			System.out.println("Total seniors: "+finalPlayers.get(loop).getSeniorEng());
			System.out.println("Total leads: "+finalPlayers.get(loop).getLeadEng());
			System.out.println();
			System.out.println("TOTAL SCORE: "+finalPlayers.get(loop).totalScore());
			System.out.println();
		}			
			int highest = finalPlayers.get(0).totalScore();
			String winner = finalPlayers.get(0).getName();
	
			for(int count = 0; count < finalPlayers.size(); count++) {
				if(finalPlayers.get(count).totalScore() > highest) {
					highest = finalPlayers.get(count).totalScore();
					winner = finalPlayers.get(count).getName();
				} else {
			
				}
			}
			System.out.println("So the winner is: ");
			System.out.println("......"+ winner +"!!");
			System.out.println();
			System.out.println("Well Done! ");
			System.out.println("You have beat the competition, and become the most successful Software Company! ");
			System.out.print("\n\n");
			playAgain();
	}
	/**
	 * offers players a chance to start a new game
	 */
	public static void playAgain () {

		String userAnswer = "";
		Scanner scanner = MainMenu.getScanner();
		
		System.out.println("Would you like to start a new game? y/n");
		userAnswer = scanner.nextLine();
		if (userAnswer.equalsIgnoreCase("y")) {
			System.out.println("You will now be returned to the Main Menu...");
			begin();
		} else if (userAnswer.equalsIgnoreCase("n"));
			System.out.print("\t\t THE END \n\n");
			begin();
	}
	/**
	 * Scanner method implemented throughout the game
	 * @return
	 */
	public static Scanner getScanner() {
		if(scanner == null) 
			return new Scanner(System.in);
		else
			return scanner;
	}
}

