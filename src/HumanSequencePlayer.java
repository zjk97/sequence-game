import java.util.HashMap;

import javax.swing.JButton;

public class HumanSequencePlayer extends ASequencePlayer {
	
	private HashMap<Integer, JButton> handMap;
	
	public HumanSequencePlayer(int number) {
		
		super(number);
		
		handMap = new HashMap<Integer, JButton>();
		
	}
	
	HashMap<Integer, JButton> getHandMap() {
		return handMap;
	}
	
	void enableAllHandCards() {
		for (HashMap.Entry<Integer, JButton> mapElement : handMap.entrySet()) { 
			mapElement.getValue().setEnabled(true);
		}
	}

}
