package technopoly;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main body of the game
 * @author Lorna
 */
public class PlayMenu {

	public static ArrayList<Player> players = new ArrayList<Player>();
	
	public static final int GAME_ROUND = 12;
	public static final int MAX_PLAYERS = 4;
		
	public static void main(String[] args) {
		start();
		
	}
	/**
	 * Starts the game by asking for number of players
	 */
	public static void start() {

		int numPlayers;
		Scanner scanner = MainMenu.getScanner();
		
		System.out.println("How many players are joining the game?");
		numPlayers = scanner.nextInt();
		if (numPlayers <= 3) {
			System.out
			.println("Sorry you don't have enough players...you need at least 4 players - go find some mates!");
			start();
		} else if (numPlayers > 4) {
			System.out.println(
					"Sorry you have too many players! Only 4 players can play Technopoly....maybe draw straws??");
			start();
		} else {
			System.out.println("Great! You can now add your player names to the game.");
			PlayMenu.addPlayers();
		}
	}
	/**
	 * Adds players to the Players arraylist
	 */
	public static void addPlayers() {
		
		String userName;
		Scanner scanner = MainMenu.getScanner();
		System.out.println("Please enter player name (max 10 characters): ");
		
		userName = scanner.nextLine();
						
		Player p = new Player(userName, 0, 50); 
		
		players.add(p);
		System.out.println("Player number "+players.size()+" is "+p.getName());
		
		int numPlayers = players.size();
		if(numPlayers==MAX_PLAYERS){
			System.out.print("Great! All your players have been added. Let's start the game...\n\n");
			playGame();
		} else if (numPlayers!=MAX_PLAYERS) {
			addPlayers();
		}
	}
	/**
	 * Game round begins, players asked to roll dice 
	 */
	public static void playGame() {
		
		String answer = "";
		Scanner scanner = MainMenu.getScanner();
		
		for (int turns = 1; turns <= GAME_ROUND; turns++) {
			
			for (int loop = 0; loop < players.size(); loop++) {
				Player p = players.get(loop);
				
				System.out.println();
				System.out.println(p.getName() + ", would you like to roll the dice? y/n ");
				answer = scanner.nextLine();
				if (answer.equalsIgnoreCase("y")) {
					System.out.println("Rolling the dice...");
					move(p);
				} else if (answer.equalsIgnoreCase("n")) {
					System.out.println("Do you want to leave the game?");
					answer = scanner.nextLine();
					if (answer.equalsIgnoreCase("y")) {
						System.out.println("Sorry to hear! As a player has chosen to leave, the game must now end. ");
						MainMenu.endGame();
					}
				} else {
					System.out.println("Oops! You must enter either y or n! Next player's turn...");
				}
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("After "+GAME_ROUND+" rounds, the game is now over!");
		System.out.println();
		System.out.println("<<<GAME OVER>>>");
		System.out.println();
		MainMenu.endGame();
	}
	/**
	 * Rolls the dice and moves the player to a new position on the Board
	 * @param p - the current Player
	 */
	public static void move(Player p) {

		System.out.println(p.getName() + ", your current square is : Square " + p.getCurrentSquare().getSquareID()
				+ " - " + p.getCurrentSquare().getArea());
		System.out.println("Your current workforce is... ");
		System.out.printf("Junior Engineers: %d \n", p.getJuniorEng());
		System.out.printf("Senior Engineers: %d \n", p.getSeniorEng());
		System.out.printf("Lead Engineers: %d \n", p.getLeadEng());
		System.out.println();

		int currentSquareID = p.getCurrentSquare().getSquareID();
		p.setCurrentSquare(Board.gameSquares.get(Board.setPosition(Dice.rollDice(), currentSquareID)));
		
		
		int newSquare = p.getCurrentSquare().getSquareID();
		
		System.out.print("Moving....\n\n");
		
			if(newSquare<=currentSquareID) {
				System.out.println("You've passed Recruitment! You've employed 10 new Junior Engineers!");
				p.setJuniorEng(p.getJuniorEng() + 10);
				p.workforce();
				System.out.print("\n");
			}
				
		System.out.println("You have landed on: Square " + p.getCurrentSquare().getSquareID() + " - "
				+ p.getCurrentSquare().getArea());
		squareType(p);
	}
	/**
	 * Gives player the properties of the square they have landed on, with options for that square
	 * @param p - the current Player
	 */
	public static void squareType(Player p) {
				
		// Recruitment square
		if (p.getCurrentSquare().getSquareID() == 0) {
			System.out.println("Prepare for the new graduate intake! ");
			
		// Work From Home
		} else if (p.getCurrentSquare().getSquareID() == 6) {
			System.out.print("Put your feet up! \n\n");
		}
		
		// landing on an occupied square
		if (p.getCurrentSquare().getOwner() != null) {
			p.getCurrentSquare().getOwner();
			if (p.getCurrentSquare().getOwner().equals(p)) {
				System.out.println("You occupy this area, so there is no change to your workforce! ");
			} else {
				System.out.println(p.getCurrentSquare().getArea() + " is occupied by "
						+ p.getCurrentSquare().getOwner().getName());
				System.out.println("You must transfer " + p.getCurrentSquare().getCost() + " junior engineers to "
						+ p.getCurrentSquare().getOwner().getName());
				if (p.getJuniorEng() < p.getCurrentSquare().getCost()) {
					System.out.println(
							"Oh no! You have no junior engineers left to transfer...Unfortunately you are out of the game... \n\n");
					System.out.println("The game is now over");
				MainMenu.endGame();
				} else {
					p.setJuniorEng(p.getJuniorEng() - p.getCurrentSquare().getCost());
					System.out.println("Your new junior workforce is: " + p.getJuniorEng());					
					
					p.getCurrentSquare().getOwner().setJuniorEng(
							p.getCurrentSquare().getOwner().getJuniorEng() + p.getCurrentSquare().getCost());
					System.out.println(p.getCurrentSquare().getOwner().getName() + "'s new junior workforce is: "
							+ p.getCurrentSquare().getOwner().getJuniorEng());
				}
			}
		// if square is not occupied
		} else if ((p.getCurrentSquare().getSquareID() != 6) && (p.getCurrentSquare().getSquareID() != 0)) {
			System.out.println(
					"Would you like to send " + p.getCurrentSquare().getCost() + " junior engineers on training? y/n");
			Scanner sc = MainMenu.getScanner();
			String answer = sc.nextLine();

			if (answer.equalsIgnoreCase("y")) {
				p.setJuniorEng(p.getJuniorEng() - p.getCurrentSquare().getCost());
				if (p.getJuniorEng() <= 0) {
					System.out.print(
							"\n Oh no! You have no junior engineers left...Unfortunately you are out of the game... \n\n");
					System.out.println("That means the game is now over! ");
					MainMenu.endGame();
				} else {
					System.out.println("Your new junior workforce is: " + p.getJuniorEng());
					System.out.print("You now occupy " + p.getCurrentSquare().getArea()
							+ " and will gain junior employees when other players land on this square. \n");
					p.getCurrentSquare().setOwner(p);
					System.out.println("Press enter to continue...");
					upskill(p);
					upskillLead(p);
				}
			} else if (answer.equalsIgnoreCase("n")) {
				System.out.println("Your junior workforce remains the same at: " + p.getJuniorEng());
				System.out.print("Next player's turn...\n\n");
			} else {
				System.out.println("Oops! You must enter y or n");
				answer = sc.nextLine();
			}
		}
	}
	/**
	 * Checks if player owns all Areas within a Field
	 * @param p - the current player
	 * @param field - the Field of current square
	 * @return
	 */
	public static boolean ownsField(Player p, String field) {
		for(int loop=0; loop < Board.gameSquares.size(); loop++) {
			if(Board.gameSquares.get(loop).getField().equals(field)) {
				if(Board.gameSquares.get(loop).getOwner() == null) {
					return false;
				}
				
				if(Board.gameSquares.get(loop).getOwner() != null && !Board.gameSquares.get(loop).getOwner().equals(p)) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * If player owns a Field, they are given the option to upskill to Senior Engineers
	 * @param p
	 */
	public static void upskill(Player p) {
		
		Scanner scanner = MainMenu.getScanner();
		String answerFields = scanner.nextLine();
	
		if (ownsField(p, p.getCurrentSquare().getField())) {
			
			switch (p.getCurrentSquare().getSquareID()) {
				case 1: 
				case 2:	
						System.out.println("You have now gained enough expertise to occupy a Field of Software Development: "
								+ p.getCurrentSquare().getField());
						System.out.println("You can now develop your team. Would you like to upskill some junior employees in one of the Areas of this Field?");
						answerFields = scanner.nextLine();
						
						if (answerFields.equalsIgnoreCase("y")) {
							System.out.println("Which area would you like to develop? Please enter square ID: 1 or 2 ");{
								answerFields = scanner.nextLine();
								if (answerFields.equalsIgnoreCase("1")) {
									System.out.println("You have chosen to develop: Maintenance.");
									p.setJuniorEng(p.getJuniorEng() - 5);
									p.setSeniorEng(p.getSeniorEng() + 3);
									p.workforce();
								} else if (answerFields.equalsIgnoreCase("2")) {
									System.out.println("You have chosen to develop: Consulting.");
									p.setJuniorEng(p.getJuniorEng() - 5);
									p.setSeniorEng(p.getSeniorEng() + 3);
									p.workforce();
								}
							}
						} else if (answerFields.equalsIgnoreCase("n")) {
						System.out.print("Ok, your workforce remains unchanged. \n\n"); 
						}
						break;	
				case 3:
				case 4:
				case 5:
					System.out.println("You now occupy a Field of Software Development: " +
							p.getCurrentSquare().getField()); System.out.println(
									"You can now develop your team. Would you like to upskill some junior employees in one of the Areas of this Field?"
									);
							answerFields = scanner.nextLine();
							if (answerFields.equalsIgnoreCase("y")) { 
								System.out.println("Which area would you like to develop? Please enter square ID: 3, 4 or 5 "); 
											answerFields = scanner.nextLine();
											if (answerFields.equalsIgnoreCase("3")) {
													System.out.println("You have chosen to develop: Python.");
													p.setJuniorEng(p.getJuniorEng() - 10); 
													p.setSeniorEng(p.getSeniorEng() + 3);
													p.workforce();
											} else if (answerFields.equalsIgnoreCase("4")) {
														System.out.println("You have chosen to develop: Java.");
														p.setJuniorEng(p.getJuniorEng() - 10);
														p.setSeniorEng(p.getSeniorEng() + 3);
														p.workforce();
											} else if (answerFields.equalsIgnoreCase("5")) {
															System.out.println("You have chosen to develop: C#.");
															p.setJuniorEng(p.getJuniorEng() - 10);
															p.setSeniorEng(p.getSeniorEng() + 3);
															p.workforce();
											}
									} else if (answerFields.equalsIgnoreCase("n")) {
									System.out.print("Ok, your workforce remains unchanged. \n\n"); 
									}
									break;
					case 7:
					case 8:
					case 9:
						System.out.println("You now occupy a Field of Software Development: " +p.getCurrentSquare().getField()); System.out.println(
										"You can now develop your team. Would you like to upskill some junior employees in one of the Areas of this Field?"); 
								answerFields = scanner.nextLine();
								if (answerFields.equalsIgnoreCase("y")) { 
									System.out.println("Which area would you like to develop? Please enter square ID: 7, 8 or 9 "); {
												answerFields = scanner.nextLine();
												if (answerFields.equalsIgnoreCase("7")) {
														System.out.println("You have chosen to develop: Software.");
														p.setJuniorEng(p.getJuniorEng() - 20);
														p.setSeniorEng(p.getSeniorEng() + 3);
														p.workforce(); 
												} else if (answerFields.equalsIgnoreCase("8")) {
															System.out.println("You have chosen to develop: Infrastructure.");
															p.setJuniorEng(p.getJuniorEng() - 20);
															p.setSeniorEng(p.getSeniorEng() + 3);
															p.workforce(); 
												} else if (answerFields.equalsIgnoreCase("9")) {
																System.out.println("You have chosen to develop: Platform.");
																p.setJuniorEng(p.getJuniorEng() - 20);
																p.setSeniorEng(p.getSeniorEng() + 3);
																p.workforce();
																}
												}
									} else if (answerFields.equalsIgnoreCase("n")) {
											System.out.print("Ok, your workforce remains unchanged. \n\n");
									}
									break;
					case 10:
					case 11:
						System.out.println("You now occupy a Field of Software Development: " +	p.getCurrentSquare().getField());
						System.out.println("You can now develop your team. Would you like to upskill some junior employees in one of the Areas of this Field? y/n"); 
								answerFields = scanner.nextLine();
								if (answerFields.equalsIgnoreCase("y")) {
									System.out.println("Which area would you like to develop? Please enter square ID: 10 or 11 "); 
										answerFields = scanner.nextLine();		
										if (answerFields.equalsIgnoreCase("10")) {
											System.out.println("You have chosen to develop: Data Integrity.");
											p.setJuniorEng(p.getJuniorEng() - 30);
											p.setSeniorEng(p.getSeniorEng() + 3);
											p.workforce(); 
										} else if (answerFields.equalsIgnoreCase("11")) {
												System.out.println("You have chosen to develop: Threat Intelligence.");
												p.setJuniorEng(p.getJuniorEng() - 30); 
												p.setSeniorEng(p.getSeniorEng() + 3);
												p.workforce();
												}
								} else if (answerFields.equalsIgnoreCase("n")) {
									System.out.print("Ok, your workforce remains unchanged. \n\n");
								}
								break;
			}														
		}
	}
	/**
	 * If player owns a Field and has upskilled to Senior, they are given the option of upskilling to Lead Engineer
	 * @param p - the current player
	 */
	public static void upskillLead (Player p) {
		
		Scanner scanner = MainMenu.getScanner();
		String answer = scanner.nextLine();
		
		if(ownsField(p, p.getCurrentSquare().getField()) && (p.getSeniorEng()!=0)){
			System.out.println("You can now upskill your workforce and create a Lead Engineer!");
			System.out.println("Would you like to upskill "+p.getCurrentSquare().getCost()+" Juniors and 1 Senior to employ a Lead in this Area? y/n");
			answer = scanner.nextLine();
			if(answer.equalsIgnoreCase("y")){
				p.setJuniorEng(p.getJuniorEng()- p.getCurrentSquare().getCost());
				p.setSeniorEng(p.getSeniorEng() - 1);
				p.setLeadEng(p.getLeadEng() + 1);
				p.workforce();
			} else if (answer.contains("n")) {
				System.out.print("Ok, your workforce remains unchanged. \n\n");
			}
		}		 
		
	}
}
