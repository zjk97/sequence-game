
public class SequencePrinter {
	
	//card image names in board order
	static String[][] cardImages = {
			{"corner.png", "AC.png", "KC.png", "QC.png", "10C.png", "9C.png", "8C.png", "7C.png", "6C.png", "corner.png"},
			{"AD.png", "7S.png", "8S.png", "9S.png", "10S.png", "QS.png", "KS.png", "AS.png", "5C.png", "2S.png"},
			{"KD.png", "6S.png", "10C.png", "9C.png", "8C.png", "7C.png", "6C.png", "2D.png", "4C.png", "3S.png"},
			{"QD.png", "5S.png", "QC.png", "8H.png", "7H.png", "6H.png", "5C.png", "3D.png", "3C.png", "4S.png"},
			{"10D.png", "4S.png", "KC.png", "9H.png", "2H.png", "5H.png", "4C.png", "4D.png", "2C.png", "5S.png"},
			{"9D.png", "3S.png", "AC.png", "10H.png", "3H.png", "4H.png", "3C.png", "5D.png", "AH.png", "6S.png"},
			{"8D.png", "2S.png", "AD.png", "QH.png", "KH.png", "AH.png", "2C.png", "6D.png", "KH.png", "7S.png"},
			{"7D.png", "2H.png", "KD.png", "QD.png", "10D.png", "9D.png", "8D.png", "7D.png", "QH.png", "8S.png"},
			{"6D.png", "3H.png", "4H.png", "5H.png", "6H.png", "7H.png", "8H.png", "9H.png", "10H.png", "9S.png"},
			{"corner.png", "5D.png", "4D.png", "3D.png", "2D.png", "AS.png", "KS.png", "QS.png", "10S.png", "corner.png"},
			};
	
	//card names in board order
	static String[][] cards = {
			{"corner", "AC", "KC", "QC", "10C", "9C", "8C", "7C", "6C", "corner"},
			{"AD", "7S", "8S", "9S", "10S", "QS", "KS", "AS", "5C", "2S"},
			{"KD", "6S", "10C", "9C", "8C", "7C", "6C", "2D", "4C", "3S"},
			{"QD", "5S", "QC", "8H", "7H", "6H", "5C", "3D", "3C", "4S"},
			{"10D", "4S", "KC", "9H", "2H", "5H", "4C", "4D", "2C", "5S"},
			{"9D", "3S", "AC", "10H", "3H", "4H", "3C", "5D", "AH", "6S"},
			{"8D", "2S", "AD", "QH", "KH", "AH", "2C", "6D", "KH", "7S"},
			{"7D", "2H", "KD", "QD", "10D", "9D", "8D", "7D", "QH", "8S"},
			{"6D", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "9S"},
			{"corner", "5D", "4D", "3D", "2D", "AS", "KS", "QS", "10S", "corner"}
			};
	
	public SequencePrinter() {
		
		
	}
	
	static void findACard(String s) {
		//find the card represented by String s 
		//and print out its two locations on the board

		boolean duplicate = false;
		
		System.out.print("//");
		
		if(s.length()==2)
			System.out.println(String.valueOf(s.charAt(0)) + " of " + String.valueOf(s.charAt(1)));
		else if(s.length()==3)
			System.out.println(String.valueOf(s.charAt(0)) + String.valueOf(s.charAt(1)) + " of " + String.valueOf(s.charAt(2)));
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(s.equals(cards[i][j])) {
					if(duplicate)
						System.out.printf("x2 = %s; y2 = %s;\n", i, j);
					else {
						System.out.printf("\tx1 = %s; y1 = %s; ", i, j);
						duplicate = true;
					}	
				}
			}
		}
	}
	
	static void cardsInNaturalOrder() {
		//print out each rank of the cards in four suits
		//used to create array
		for(int i=1; i<14; i++) {
			String s;
			
			switch (i) {
				case 1: s = "A"; break;
				case 11: s = "J"; break;
				case 12: s = "Q"; break;
				case 13: s = "K"; break;
				default: s = String.valueOf(i);
			}
			
			System.out.printf("\"%sS\", \"%sH\", \"%sD\", \"%sC\",\n", s, s, s, s);
		}		
	}

	static void ASequenceCardMethodBuilder(String[] c) {
		int counter = 0;
		
		for(int i=1; i<53; i++) {
			System.out.printf("case %d:\ncase %d:\n\t", i, i+52);
			findACard(c[counter]);
			System.out.printf("\n\tcardName = \"%s\";\n", c[counter]);
			System.out.println("\tbreak;");

			counter++;
		}//end of out most loop
	}
	
	
	public static void main(String[] args) {
		
		String[] c = {"AS", "AH", "AD", "AC",
				"2S", "2H", "2D", "2C",
				"3S", "3H", "3D", "3C",
				"4S", "4H", "4D", "4C",
				"5S", "5H", "5D", "5C",
				"6S", "6H", "6D", "6C",
				"7S", "7H", "7D", "7C",
				"8S", "8H", "8D", "8C",
				"9S", "9H", "9D", "9C",
				"10S", "10H", "10D", "10C",
				"JS", "JH", "JD", "JC",
				"QS", "QH", "QD", "QC",
				"KS", "KH", "KD", "KC"};
		
		ASequenceCardMethodBuilder(c);
		
		/* printing an array of ImageIcons
		String[][] cardImages = {
				{"corner.png", "AC.png", "KC.png", "QC.png", "10C.png", "9C.png", "8C.png", "7C.png", "6C.png", "corner.png"},
				{"AD.png", "7S.png", "8S.png", "9S.png", "10S.png", "QS.png", "KS.png", "AS.png", "5C.png", "2S.png"},
				{"KD.png", "6S.png", "10C.png", "9C.png", "8C.png", "7C.png", "6C.png", "2D.png", "4C.png", "3S.png"},
				{"QD.png", "5S.png", "QC.png", "8H.png", "7H.png", "6H.png", "5C.png", "3D.png", "3C.png", "4S.png"},
				{"10D.png", "4S.png", "KC.png", "9H.png", "2H.png", "5H.png", "4C.png", "4D.png", "2C.png", "5S.png"},
				{"9D.png", "3S.png", "AC.png", "10H.png", "3H.png", "4H.png", "3C.png", "5D.png", "AH.png", "6S.png"},
				{"8D.png", "2S.png", "AD.png", "QH.png", "KH.png", "AH.png", "2C.png", "6D.png", "KH.png", "7S.png"},
				{"7D.png", "2H.png", "KD.png", "QD.png", "10D.png", "9D.png", "8D.png", "7D.png", "QH.png", "8S.png"},
				{"6D.png", "3H.png", "4H.png", "5H.png", "6H.png", "7H.png", "8H.png", "9H.png", "10H.png", "9S.png"},
				{"corner.png", "5D.png", "4D.png", "3D.png", "2D.png", "AS.png", "KS.png", "QS.png", "10S.png", "corner.png"},
				};
		for(int i=0; i<10; i++) {
			System.out.print("{");
			for(int j=0; j<10; j++)
				System.out.printf("new ImageIcon(getClass().getResource(\"greyCards/%s\")), ", cardImages[i][j]);
			System.out.println("}");
		}
			*/
		
	}

}
