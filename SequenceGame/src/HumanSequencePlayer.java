import java.util.HashMap;

import javax.swing.JButton;

public class HumanSequencePlayer extends ASequencePlayer {
	
	private HashMap<Integer, JButton> handMap;
	
	public HumanSequencePlayer(int number) {
		
		super(number);
		
		handMap = new HashMap<Integer, JButton>();
		
	}
	
	void setColor(String color) {
		playerColor = color;
	}
	
	HashMap<Integer, JButton> getHandMap() {
		return handMap;
	}

}
