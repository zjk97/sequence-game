import java.util.Random;

public class CardDealer {
	
	Random rand = new Random();
	private int nextCard = 0;
	
	 int[] gameDeck = {
			//the original deck with 104 cards
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 
			11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 
			21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 
			31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 
			41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 
			51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 
			61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 
			71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 
			81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 
			91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 
			101, 102, 103, 104};
	 
	public CardDealer() {
		/*CardDealer constructor*/
	}
	
	void shuffleCards() {

		for(int i=0; i<104; i++) {
			int r = rand.nextInt(104);

			/* **If case to detect when a card's new position matches its current position**
				if(i == r) {
					System.out.println("Lucky duck occurred at " + i);
				}
				else {
			 */
			int temp = gameDeck[i];
			gameDeck[i] = gameDeck[r];
			gameDeck[r] = temp;
			//}
		}

		//print out the shuffled deck
		/*
		System.out.println("The shuffled deck is: ");
		for (int i = 0; i < 104; i ++) 
			System.out.print(new ASequenceCard(gameDeck[i]).getImageFileName() + " ");
		*/
	}// end of shuffle
	
	
	
	void shuffle(Object[] o) {

		//used to shuffle players
		int n = o.length;

		for(int i=0; i<n; i++) {
			int r = rand.nextInt(n);

			/* **If case to detect when a card's new position matches its current position**
				if(i == r) {
					System.out.println("Lucky duck occurred at " + i);
				}
				else {
			 */
			Object temp = o[i];
			o[i] = o[r];
			o[r] = temp;
			//}
		}
	}// end of shuffle(Object o)
	
	

	ASequenceCard dealCard(ASequencePlayer p) {
		
		//deal a card to player p
		ASequenceCard c = new ASequenceCard(gameDeck[nextCard]);
		p.hand.add(c);
		
		//update the next card in deck
		if(nextCard == 103)
			nextCard = 0;
		else
			nextCard++;
		
		return c;
	}
}//end of class
