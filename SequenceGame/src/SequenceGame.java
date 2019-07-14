import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class SequenceGame {
	
	protected char[][] board;
	char currentPlayerColor = 'g'; 
	char gameResult = ' ';
	ASequencePlayer[] playerList;
	ASequencePlayer currentPlayer;
	CardDealer Chris;
	final char BLANK = ' ';
	SequenceGameGUI gui;
	SequenceGame thisGame = this;
	
	public SequenceGame(int numberOfPlayers) {
	
		long startTime = System.nanoTime();
		
		setBoard();
		createPlayers(numberOfPlayers);
		gui = new SequenceGameGUI(this);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dealCards(numberOfPlayers);
		
		gui.setVisible(true);

        long endTime = System.nanoTime();
        System.out.println(String.valueOf(endTime - startTime));
	}

	void setBoard() {
		
		board = new char[10][10];
		
		for(int r=0; r<10; r++)
			for(int c=0; c<10; c++)
				board[r][c] = BLANK;
		
	}
	
	void createPlayers(int n) {
		
		if(n<1)
			System.out.println("You need at least one player to play, dumb dumb");
		else if(n==1) {
			//single player mode, make a CPU player
			playerList = new ASequencePlayer[2];
			
			playerList[0] = new HumanSequencePlayer(0);
			playerList[1] = new CpuSequencePlayer(1);
		}
		else {
			//multi-player mode, no CPU player necessary
			playerList = new ASequencePlayer[n];
			
			for(int i=0; i<n; i++) {
				playerList[i] = new HumanSequencePlayer(i);
			}
				
		}
		
	}
	
	void dealCards(int numberOfPlayers) {
		//NEED WORK, SPECIFY CASES FOR 1,2,3 PLAYERS
		Chris = new CardDealer();
		//Chris.shuffleCards();//shuffle cards
		//Chris.shuffle(playerList);//shuffle player order
		
		//assign the first player to play
		currentPlayer = playerList[0];

		Border empty = BorderFactory.createEmptyBorder();
		
		switch(numberOfPlayers) {
			case 1:
				for(int i=0; i<6; i++)
					//one player + one cpu, everybody gets SIX cards!
					for(int j=0; j<2; j++) {
						//deal a card to each player
						ASequenceCard c = Chris.dealCard(playerList[j]);

						if(playerList[j] instanceof HumanSequencePlayer) {
							//add hand cards to GUI for player (the only player)
							JButton b = new JButton();
							
							//add the button for this card to player handMap
							((HumanSequencePlayer) playerList[j]).getHandMap()
							.put(new Integer(c.getCardNumber()), b);

							int x1 = c.getX1(), y1 = c.getY1();
							int x2 = c.getX2(), y2 = c.getY2();

							//set action when a hand card is selected
							b.setBorder(empty);
							b.setIcon(gui.handCardImages[x1][y1]);
							b.addActionListener(
									new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											//reset gui's jack fields
											gui.twoEyedJackIsPlayed = false;
											gui.oneEyedJackIsPlayed = false;
											
											if(!c.getIsTwoEyedJack()) {
												//a non-jack or one-eyed jack
												gui.makeAllDisabledCardsGrey(1);
												
												if(!c.getIsOneEyedJack()) {
													//a non-jack card
													//disable all the available token buttons
													for(int i=0; i<10; i++)
														for(int j=0; j<10; j++)
																gui.tokenButtons[i][j].setEnabled(false);
													
													SButton b1 = gui.cardButtons[x1][y1];
													SButton b2 = gui.cardButtons[x2][y2];
													//store the two positions of the same card
													b1.x1 = x1; b1.y1 = y1; b2.x1 = x1; b2.y1 = y1;
													b1.x2 = x2; b2.y2 = y2; b2.x2 = x2; b2.y2 = y2;
													//store the card number
													b1.cardNumber = c.getCardNumber();
													b2.cardNumber = c.getCardNumber();
													//highlight the cards on the board AND enable the buttons
													if(board[x1][y1]==' ') {
														b1.setIcon(gui.normalCardImages[x1][y1]);
														b1.setEnabled(true);
													}
													if(board[x2][y2]==' ') {
														b2.setIcon(gui.normalCardImages[x2][y2]);
														b2.setEnabled(true);
													}
												}
												else {
													//an one eyed jack
													//disable all the available token buttons
													for(int i=0; i<10; i++)
														for(int j=0; j<10; j++)
																gui.tokenButtons[i][j].setEnabled(false);
													
													gui.oneEyedJackIsPlayed = true;
													gui.jackNumber = c.getCardNumber();
													//enable all the available token buttons
													for(int i=0; i<10; i++)
														for(int j=0; j<10; j++)
															if(board[i][j] != ' ')
																gui.tokenButtons[i][j].setEnabled(true);
												}//end of inner else												
											}
											else {
												//a two eyed jack
												gui.twoEyedJackIsPlayed = true;
												gui.jackNumber = c.getCardNumber();
												gui.enableAllCards(thisGame, 1);
											}
										}
										
									});

							gui.handPanel.add(b);
						}
						
					}
				
				break;
			case 2:
				//NEED WORK, DOES NOT SUPPORT MULTI PLAYER YET
				for(int i=0; i<6; i++)
					//deal six cards if TWO players
					for(int j=0; j<numberOfPlayers; j++)
						Chris.dealCard(playerList[j]);
				break;
			case 3:
				//NEED WORK, DOES NOT SUPPORT MULTI PLAYER YET
				for(int i=0; i<7; i++)
					//deal seven cards if THREE players
					for(int j=0; j<numberOfPlayers; j++)
						Chris.dealCard(playerList[j]);
				break;
			default: 
				System.out.println("The game only supports up to three players.");
		}			
		
	}//end of deal cards
	
	boolean isGameOver() {
		//for each card, check 8 ways (left, right, up, down, and FOUR diagonals)
		
		return false;
	}

	public static void main(String[] args) {
		
		SequenceGame g = new SequenceGame(1);
		
		ASequencePlayer[] l = g.playerList;
		
		System.out.println("Players:");
		for(int i=0; i<l.length;i++) {
			System.out.print("\tPlayer " + l[i].playerNumber + String.valueOf(l[i].getClass()) + ":\n\t\tHand: ");
			l[i].printHand();
		}

	}

}
