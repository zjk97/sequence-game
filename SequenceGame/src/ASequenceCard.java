
public class ASequenceCard {
	
	private int cardNumber;
	private int x1, y1, x2, y2;
	private boolean isOneEyedJack = false, isTwoEyedJack = false, isJS, isJH, isJD, isJC;
	private String imageFileName;
	
	//getters
	int getX1() {
		return x1;
	}
	
	int getX2() {
		return x2;
	}
	
	int getY1() {
		return y1;
	}
	
	int getY2() {
		return y2;
	}
	
	boolean getIsOneEyedJack() {
		return isOneEyedJack;
	}
	
	boolean getIsTwoEyedJack() {
		return isTwoEyedJack;
	}
	
	boolean getIsJS() {
		return isJS;
	}
	
	boolean getIsJH() {
		return isJH;
	}
	
	boolean getIsJD() {
		return isJD;
	}
	
	boolean getIsJC() {
		return isJC;
	}
	
	String getImageFileName() {
		return imageFileName;
	}

	public int getCardNumber() {
		return cardNumber;
	}
	
	//constructor
	public ASequenceCard(int c) {
		
		cardNumber = c;
		
		switch(c) {
		
		/*
		 * in RANK-major order
		 * spades, hearts, diamonds, clubs
		 * BOARD direction: upper left corner cards are 1 of clubs and 1 of diamonds
		 * Unicode Version 12.1
		 */
		case 1:
		case 53:
			//A of S
			x1 = 1; y1 = 7; x2 = 9; y2 = 5;

			imageFileName = "handCards/AS.png";
			break;
		case 2:
		case 54:
			//A of H
			x1 = 5; y1 = 8; x2 = 6; y2 = 5;

			imageFileName = "handCards/AH.png";
			break;
		case 3:
		case 55:
			//A of D
			x1 = 1; y1 = 0; x2 = 6; y2 = 2;

			imageFileName = "handCards/AD.png";
			break;
		case 4:
		case 56:
			//A of C
			x1 = 0; y1 = 1; x2 = 5; y2 = 2;

			imageFileName = "handCards/AC.png";
			break;
		case 5:
		case 57:
			//2 of S
			x1 = 1; y1 = 9; x2 = 6; y2 = 1;

			imageFileName = "handCards/2S.png";
			break;
		case 6:
		case 58:
			//2 of H
			x1 = 4; y1 = 4; x2 = 7; y2 = 1;

			imageFileName = "handCards/2H.png";
			break;
		case 7:
		case 59:
			//2 of D
			x1 = 2; y1 = 7; x2 = 9; y2 = 4;

			imageFileName = "handCards/2D.png";
			break;
		case 8:
		case 60:
			//2 of C
			x1 = 4; y1 = 8; x2 = 6; y2 = 6;

			imageFileName = "handCards/2C.png";
			break;
		case 9:
		case 61:
			//3 of S
			x1 = 2; y1 = 9; x2 = 5; y2 = 1;

			imageFileName = "handCards/3S.png";
			break;
		case 10:
		case 62:
			//3 of H
			x1 = 5; y1 = 4; x2 = 8; y2 = 1;

			imageFileName = "handCards/3H.png";
			break;
		case 11:
		case 63:
			//3 of D
			x1 = 3; y1 = 7; x2 = 9; y2 = 3;

			imageFileName = "handCards/3D.png";
			break;
		case 12:
		case 64:
			//3 of C
			x1 = 3; y1 = 8; x2 = 5; y2 = 6;

			imageFileName = "handCards/3C.png";
			break;
		case 13:
		case 65:
			//4 of S
			x1 = 3; y1 = 9; x2 = 4; y2 = 1;

			imageFileName = "handCards/4S.png";
			break;
		case 14:
		case 66:
			//4 of H
			x1 = 5; y1 = 5; x2 = 8; y2 = 2;

			imageFileName = "handCards/4H.png";
			break;
		case 15:
		case 67:
			//4 of D
			x1 = 4; y1 = 7; x2 = 9; y2 = 2;

			imageFileName = "handCards/4D.png";
			break;
		case 16:
		case 68:
			//4 of C
			x1 = 2; y1 = 8; x2 = 4; y2 = 6;

			imageFileName = "handCards/4C.png";
			break;
		case 17:
		case 69:
			//5 of S
			x1 = 3; y1 = 1; x2 = 4; y2 = 9;

			imageFileName = "handCards/5S.png";
			break;
		case 18:
		case 70:
			//5 of H
			x1 = 4; y1 = 5; x2 = 8; y2 = 3;

			imageFileName = "handCards/5H.png";
			break;
		case 19:
		case 71:
			//5 of D
			x1 = 5; y1 = 7; x2 = 9; y2 = 1;

			imageFileName = "handCards/5D.png";
			break;
		case 20:
		case 72:
			//5 of C
			x1 = 1; y1 = 8; x2 = 3; y2 = 6;

			imageFileName = "handCards/5C.png";
			break;
		case 21:
		case 73:
			//6 of S
			x1 = 2; y1 = 1; x2 = 5; y2 = 9;

			imageFileName = "handCards/6S.png";
			break;
		case 22:
		case 74:
			//6 of H
			x1 = 3; y1 = 5; x2 = 8; y2 = 4;

			imageFileName = "handCards/6H.png";
			break;
		case 23:
		case 75:
			//6 of D
			x1 = 6; y1 = 7; x2 = 8; y2 = 0;

			imageFileName = "handCards/6D.png";
			break;
		case 24:
		case 76:
			//6 of C
			x1 = 0; y1 = 8; x2 = 2; y2 = 6;

			imageFileName = "handCards/6C.png";
			break;
		case 25:
		case 77:
			//7 of S
			x1 = 1; y1 = 1; x2 = 6; y2 = 9;

			imageFileName = "handCards/7S.png";
			break;
		case 26:
		case 78:
			//7 of H
			x1 = 3; y1 = 4; x2 = 8; y2 = 5;

			imageFileName = "handCards/7H.png";
			break;
		case 27:
		case 79:
			//7 of D
			x1 = 7; y1 = 0; x2 = 7; y2 = 7;

			imageFileName = "handCards/7D.png";
			break;
		case 28:
		case 80:
			//7 of C
			x1 = 0; y1 = 7; x2 = 2; y2 = 5;

			imageFileName = "handCards/7C.png";
			break;
		case 29:
		case 81:
			//8 of S
			x1 = 1; y1 = 2; x2 = 7; y2 = 9;

			imageFileName = "handCards/8S.png";
			break;
		case 30:
		case 82:
			//8 of H
			x1 = 3; y1 = 3; x2 = 8; y2 = 6;

			imageFileName = "handCards/8H.png";
			break;
		case 31:
		case 83:
			//8 of D
			x1 = 6; y1 = 0; x2 = 7; y2 = 6;

			imageFileName = "handCards/8D.png";
			break;
		case 32:
		case 84:
			//8 of C
			x1 = 0; y1 = 6; x2 = 2; y2 = 4;

			imageFileName = "handCards/8C.png";
			break;
		case 33:
		case 85:
			//9 of S
			x1 = 1; y1 = 3; x2 = 8; y2 = 9;

			imageFileName = "handCards/9S.png";
			break;
		case 34:
		case 86:
			//9 of H
			x1 = 4; y1 = 3; x2 = 8; y2 = 7;

			imageFileName = "handCards/9H.png";
			break;
		case 35:
		case 87:
			//9 of D
			x1 = 5; y1 = 0; x2 = 7; y2 = 5;

			imageFileName = "handCards/9D.png";
			break;
		case 36:
		case 88:
			//9 of C
			x1 = 0; y1 = 5; x2 = 2; y2 = 3;

			imageFileName = "handCards/9C.png";
			break;
		case 37:
		case 89:
			//10 of S
			x1 = 1; y1 = 4; x2 = 9; y2 = 8;

			imageFileName = "handCards/10S.png";
			break;
		case 38:
		case 90:
			//10 of H
			x1 = 5; y1 = 3; x2 = 8; y2 = 8;

			imageFileName = "handCards/10H.png";
			break;
		case 39:
		case 91:
			//10 of D
			x1 = 4; y1 = 0; x2 = 7; y2 = 4;

			imageFileName = "handCards/10D.png";
			break;
		case 40:
		case 92:
			//10 of C
			x1 = 0; y1 = 4; x2 = 2; y2 = 2;

			imageFileName = "handCards/10C.png";
			break;
		case 41:
		case 93:
			//J of S
			x1 = 0; y1 = 0;
			isOneEyedJack = true;
			
			imageFileName = "handCards/JS.png";
			break;
		case 42:
		case 94:
			//J of H
			x1 = 0; y1 = 9;
			isOneEyedJack = true;
			
			imageFileName = "handCards/JH.png";
			break;
		case 43:
		case 95:
			//J of D
			x1 = 9; y1 = 0;
			isTwoEyedJack = true;
			
			imageFileName = "handCards/JD.png";
			break;
		case 44:
		case 96:
			//J of C
			x1 = 9; y1 = 9;
			isTwoEyedJack = true;
			
			imageFileName = "handCards/JC.png";
			break;
		case 45:
		case 97:
			//Q of S
			x1 = 1; y1 = 5; x2 = 9; y2 = 7;

			imageFileName = "handCards/QS.png";
			break;
		case 46:
		case 98:
			//Q of H
			x1 = 6; y1 = 3; x2 = 7; y2 = 8;

			imageFileName = "handCards/QH.png";
			break;
		case 47:
		case 99:
			//Q of D
			x1 = 3; y1 = 0; x2 = 7; y2 = 3;

			imageFileName = "handCards/QD.png";
			break;
		case 48:
		case 100:
			//Q of C
			x1 = 0; y1 = 3; x2 = 3; y2 = 2;

			imageFileName = "handCards/QC.png";
			break;
		case 49:
		case 101:
			//K of S
			x1 = 1; y1 = 6; x2 = 9; y2 = 6;

			imageFileName = "handCards/KS.png";
			break;
		case 50:
		case 102:
			//K of H
			x1 = 6; y1 = 4; x2 = 6; y2 = 8;

			imageFileName = "handCards/KH.png";
			break;
		case 51:
		case 103:
			//K of D
			x1 = 2; y1 = 0; x2 = 7; y2 = 2;

			imageFileName = "handCards/KD.png";
			break;
		case 52:
		case 104:
			//K of C
			x1 = 0; y1 = 2; x2 = 4; y2 = 2;

			imageFileName = "handCards/KC.png";
			break;


		
		}//end of switch
	}//end of constructor


}//end of class
