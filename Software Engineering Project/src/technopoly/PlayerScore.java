package technopoly;

public class PlayerScore {

	private int finalTotal;
	private String playerName;
	
	public PlayerScore (){
		
	}
	
	public PlayerScore(int finalTotal, String playerName) {
		super();
		this.finalTotal = finalTotal;
		this.playerName = playerName;
	}

	public int getFinalTotal() {
		return finalTotal;
	}
	public void setFinalTotal(int finalTotal) {
		this.finalTotal = finalTotal;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
