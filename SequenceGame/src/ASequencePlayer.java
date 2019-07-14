import java.util.LinkedList;

public class ASequencePlayer {
	
	protected int playerNumber;
	protected String playerColor;
	LinkedList<ASequenceCard> hand = new LinkedList<ASequenceCard>();

	public ASequencePlayer(int number) {
		
		playerNumber = number;
		
	}
	
	void printHand() {
		for(ASequenceCard i : hand)
			System.out.print(i.getImageFileName() + " ");
		System.out.println("\n");
	}
	
}
