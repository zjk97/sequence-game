import java.util.LinkedList;

public class ASequencePlayer {
	
	protected int playerNumber;
	protected char playerColor;
	String playerName;
	LinkedList<ASequenceCard> hand = new LinkedList<ASequenceCard>();

	public ASequencePlayer(int number) {
		
		playerNumber = number;
		
	}
	
	void printHand() {
		for(ASequenceCard i : hand)
			System.out.print(i.getCardName() + " ");
		System.out.println("\n");
	}
	
	String getHand() {
		String s = "";
		for(ASequenceCard i : hand)
			s+=i.getCardName() + ", ";
		return s;
	}
	
}
