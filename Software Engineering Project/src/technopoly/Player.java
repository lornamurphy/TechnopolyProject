package technopoly;
/**
 * Sets the properties of a Player
 * @author Lorna
 *
 */
public class Player {

	private String name;
	private int playerID;
	private int juniorEng = 50;
	private int seniorEng;
	private int leadEng;
		
	public static final int JUNIOR_CREDIT = 10;
	public static final int SENIOR_CREDIT = 30;
	public static final int LEAD_CREDIT = 70;
	
	private Square currentSquare;
	
	private Square ownSquare;

	/**
	 * default
	 */
	public Player() {

	}

	/**
	 * constructor with args
	 * @param name - implements business rule from setter
	 * @param playerID - arrayList index
	 * @param juniorEng - starting balance
	 */
	public Player(String name, int playerID, int juniorEng) {
		super();
		this.setName(name);
		this.playerID = playerID;
		this.juniorEng = juniorEng;
							
		setCurrentSquare(Board.gameSquares.get(0));
		setOwnSquare(ownSquare);
		
	}

	public String getName() {
		return name;
	}
	/**
	 * Sets player name
	 * @param name - must be between 3 and 10 characters, cannot be empty
	 */
	public void setName(String name) {
		
		if((name.length() >= 3) && (name.length()<=10)){
			this.name = name;	
		} else if ((name.length() < 3) || (name.length()>10)){
			System.out.println("Oops! Player names must be between 3 and 10 characters in length.");
			PlayMenu.start();
		} else if (name.isEmpty()) {
			System.out.println("Sorry - you need to enter a name! ");
			PlayMenu.start();
		}
	}

	public int getPlayerID() {
		return playerID;
	}
	/**
	 * sets ID from index number in ArrayList
	 * @param playerID - int
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	/**
	 * 
	 * @return - total junior engineers (int)
	 */
	public int getJuniorEng() {
		return juniorEng;
	}
	/**
	 * Sets number of junior engineers according to square they land on
	 */
	public void setJuniorEng(int juniorEng) {
		this.juniorEng = juniorEng;
	}
	/**
	 * returns current square for Player
	 * @return
	 */
	public Square getCurrentSquare() {
		return currentSquare;
	}
	/**
	 * sets current square according to dice roll
	 * @param currentSquare
	 */
	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}

	public int getSeniorEng() {
		return seniorEng;
	}
	/**
	 * Sets number of Senior engineers if upskilled
	 * @param seniorEng
	 */
	public void setSeniorEng(int seniorEng) {
		this.seniorEng = seniorEng;
	}

	public int getLeadEng() {
		return leadEng;
	}
	/**
	 * Sets number of Lead engineers if upskilled 
	 * @param leadEng
	 */
	public void setLeadEng(int leadEng) {
		this.leadEng = leadEng;
	}
	
	public Square getOwnSquare() {
		return ownSquare;
	}
	
	public void setOwnSquare(Square ownSquare) {
		this.ownSquare = ownSquare;
	}
	
	/**
	 * Calculates the total score per player by converting workforce to credits
	 * @return - total score (int)
	 */
	public int totalScore() {
		int total = 0;
		int juniorCred = getJuniorEng()*JUNIOR_CREDIT;
		int seniorCred = getSeniorEng()*SENIOR_CREDIT;
		int leadCred = getLeadEng()*LEAD_CREDIT;
		
		total = juniorCred + seniorCred + leadCred;
		return total;
	}
	/**
	 * Prints to screen players total workforce
	 */
	public void workforce() {
		System.out.println("Your new workforce is: ");
		System.out.printf("Junior Engineers: %d \n", getJuniorEng());
		System.out.printf("Senior Engineers: %d \n", getSeniorEng());
		System.out.printf("Lead Engineers: %d \n", getLeadEng());
		
	}

}
