/**
 * 
 */
package technopoly;

/** Properties of square on the Board
 * @author Lorna
 *
 */
public class Square {

	private int squareID;
	private String area;
	private String field;
	private boolean occupied;
	private Player owner;
	private int cost;
	/**
	 * default constructor
	 */
	public Square() {
		
	}
	/**
	 * constructor with args
	 * @param squareID - index in ArrayList
	 * @param area - the Area of the square
	 * @param field - the Field of the square
	 * @param occupied - if square is occupied by a player
	 * @param cost - number of junior engineers required if player lands on square
	 */
	public Square(int squareID, String area, String field, boolean occupied, int cost) {
		super();
		this.squareID = squareID;
		this.area = area;
		this.field = field;
		this.occupied = occupied;
		this.owner = null;
		this.cost = cost;
	}
	
	/**
	 * gets index number of square in Board ArrayList
	 * @return
	 */
	public int getSquareID() {
		return squareID;
	}
	/**
	 * sets the index number of square in Board ArrayList
	 * @param squareID
	 */
	public void setSquareID(int squareID) {
		this.squareID = squareID;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	/**
	 * returns if square is occupied by a player
	 * @return
	 */
	public boolean isOccupied() {
		return occupied;
	}
	/**
	 * sets if the square is owned
	 * @param occupied
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	/**
	 * gets the player that occupies that area/square
	 * @return
	 */
	public Player getOwner() {
		return owner;
	}
	/**
	 * sets the occupier of a square 
	 * @param owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
}
