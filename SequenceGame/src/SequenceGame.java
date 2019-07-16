import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class SequenceGame {
	
	protected char[][] board;
	char currentPlayerColor; 
	char gameResult = ' ';
	ASequencePlayer[] playerList;
	ASequencePlayer currentPlayer;
	CardDealer Chris;
	final char BLANK = ' ';
	SequenceGameGUI gui;
	SequenceGame thisGame = this;
	Border empty = BorderFactory.createEmptyBorder();
	//set by SequenceGameGUI:
		//x and y coordinate of the card most recently played
		int lastPlayedX = -1, lastPlayedY = -1;
	
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
        
        //startGame(numberOfPlayers);
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
			playerList[0].playerColor = 'r';
			playerList[1] = new CpuSequencePlayer(1);
			playerList[1].playerColor = 'b';
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
		Chris.shuffleCards();//shuffle cards
		Chris.shuffle(playerList);//shuffle player order
		
		//assign the first player to play
		currentPlayer = playerList[0];
		currentPlayerColor = playerList[0].playerColor;
		
		switch(numberOfPlayers) {
			case 1:
				for(int i=0; i<6; i++)
					//one player + one cpu, everybody gets SIX cards!
					for(int j=0; j<2; j++) {
						//deal a card to each player
						ASequenceCard c = Chris.dealCard(playerList[j]);

						//add hand cards to GUI for player (the only player)
						if(playerList[j] instanceof HumanSequencePlayer) 
							displayNewHandCard(c, (HumanSequencePlayer)playerList[j]);
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
	
	boolean isGameOver(int x, int y) {
		/*
		 * input x, y coordinate of the token just played
		 * ***(if a remove card is played, DO NOT call this method)
		 * determine if any player has won
		 */
		
		//if one-eyed jack played, game must NOT be over
		if(x==-1)
			return false;
		
		//check corners
		//Top-left
		if(board[0][1]!=' ' && board[0][1]==board[0][2] && board[0][2]==board[0][3] && board[0][3]==board[0][4]) {
			gameResult = board[0][1];
			System.out.println("Top-Left Win");
			return true;
		}
		if(board[1][0]!=' ' && board[1][0]==board[2][0] && board[2][0]==board[3][0] && board[3][0]==board[4][0]) {
			gameResult = board[1][0];
			System.out.println("Top-Left Win");
			return true;
		}
		if(board[1][1]!=' ' && board[1][1]==board[2][2] && board[2][2]==board[3][3] && board[3][3]==board[4][4]) {
			gameResult = board[1][1];
			System.out.println("Top-Left Win");
			return true;
		}
		
		//Top-right
		if(board[0][4]!=' ' && board[0][4]==board[0][5] && board[0][6]==board[0][7] && board[0][7]==board[0][8]) {
			gameResult = board[0][4];
			System.out.println("Top-Right Win");
			return true;
		}
		if(board[1][9]!=' ' && board[1][9]==board[2][9] && board[2][9]==board[3][9] && board[3][9]==board[4][9]) {
			gameResult = board[1][1];
			System.out.println("Top-Right Win");
			return true;
		}
		if(board[1][8]!=' ' && board[1][8]==board[2][7] && board[2][7]==board[3][6] && board[3][6]==board[4][5]) {
			gameResult = board[1][8];
			System.out.println("Top-Right Win");
			return true;
		}
		
		//Bottom-left
		if(board[0][8]!=' ' && board[0][8]==board[0][7] && board[0][7]==board[0][6] && board[0][6]==board[0][5]) {
			gameResult = board[0][8];
			System.out.println("Bottom-Left Win");
			return true;
		}
		if(board[9][1]!=' ' && board[9][1]==board[9][2] && board[9][2]==board[9][3] && board[9][3]==board[9][4]) {
			gameResult = board[9][1];
			System.out.println("Bottom-Left Win");
			return true;
		}
		if(board[8][1]!=' ' && board[8][1]==board[7][2] && board[7][2]==board[6][3] && board[6][3]==board[5][4]) {
			gameResult = board[8][1];
			System.out.println("Bottom-Left Win");
			return true;
		}
		
		//Bottom-right
		if(board[9][8]!=' ' && board[9][8]==board[9][7] && board[9][7]==board[9][6] && board[9][6]==board[9][5]) {
			gameResult = board[9][8];
			System.out.println("Bottom-Right Win");
			return true;
		}
		if(board[8][9]!=' ' && board[8][9]==board[7][9] && board[7][9]==board[6][9] && board[6][9]==board[5][9]) {
			gameResult = board[8][9];
			System.out.println("Bottom-Right Win");
			return true;
		}
		if(board[8][8]!=' ' && board[8][8]==board[7][7] && board[7][7]==board[6][6] && board[6][6]==board[5][5]) {
			gameResult = board[8][8];
			System.out.println("Bottom-Right Win");
			return true;
		}
		
		
		
		/*for each token placed, check 8 ways
		 * if:
		 *  left + right >= 4 or
		 *  up + down >= 4 or
		 *  LeftUp + RightDown >= 4 or
		 *  LeftDown + RightUp >= 4 
		 * then return true
		 */

		//sum = total number of consecutive and same color tokens
		int sum = 0;

		//left+right
		//left
		for(int i=y; i>0; i--) {
			if(board[x][i]==board[x][i-1])
				sum++;
			else
				break;
		}
		//right
		for(int i=y; i<9; i++) {
			if(board[x][i]==board[x][i+1])
				sum++;
			else
				break;
		}
		if(sum>=4) {
			gameResult = board[x][y];
			System.out.println("Left-Right Win " + sum);
			return true;
		}
		else
			sum = 0;
		
		//up+down
		//up
		for(int i=x; i>0; i--) {
			if(board[i][y]==board[i-1][y])
				sum++;
			else
				break;
		}
		//down
		for(int i=x; i<9; i++) {
			if(board[i][y]==board[i+1][y])
				sum++;
			else
				break;
		}
		if(sum>=4) {
			gameResult = board[x][y];
			System.out.println("Up-Down Win " + sum);
			return true;
		}
		else
			sum = 0;
		
		//LeftUp + RightDown
		//LeftUp
		for(int i=x, j=y; i>0&&j>0; i--, j--) {
			if(board[i][j]==board[i-1][j-1]) {
				System.out.println("Left-up: " +i + ", " +j);
				sum++;
			}
			else
				break;
		}
		//RightDown
		for(int i=x, j=y; i<9&&j<9; i++, j++) {
			if(board[i][j]==board[i+1][j+1]) {
				System.out.println("Left-up: " +i + ", " +j);
				sum++;
			}
			else
				break;
		}
		if(sum>=4) {
			gameResult = board[x][y];
			System.out.println("Left-Up + Right-down Win " + sum);
			return true;
		}
		else
			sum = 0;
		
		//LeftDown + RightUp
		//LeftDown
		for(int i=x, j=y; i>0&&j<9; i--, j++) {
			if(board[i][j]==board[i-1][j+1])
				sum++;
			else
				break;
		}
		//RightUp
		for(int i=x, j=y; i<9&&j>0; i++, j--) {
			if(board[i][j]==board[i+1][j-1])
				sum++;
			else
				break;
		}
		if(sum>=4) {
			gameResult = board[x][y];
			System.out.println("Left-down + Right-up Win "+ sum);
			return true;
		}
		
		//if none of the above returns true, then return false
		return false;
	}//end of isGameOver

	void displayNewHandCard(ASequenceCard c, HumanSequencePlayer player) {
		JButton b = new JButton();
		//immediately disable this button
		b.setEnabled(false);
		
		//add the button for this card to player handMap
		player.getHandMap().put(new Integer(c.getCardNumber()), b);

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
		gui.handPanel.revalidate();
	}

	void startGame(int numberOfPlayers) throws InterruptedException {
		switch(numberOfPlayers) {
		case 1:
			ASequencePlayer p0 = playerList[0];
			ASequencePlayer p1 = playerList[1];
			
			if(p0 instanceof HumanSequencePlayer) {
				//human starts first
				while(true) {
					if(isGameOver(lastPlayedX, lastPlayedY)) {
						//game is over
						JOptionPane.showMessageDialog(null,"Winner is:" + currentPlayer,
								"GAME OVER",JOptionPane.ERROR_MESSAGE);
						break;
					}
					else {
						currentPlayer = p0;
						currentPlayerColor = p0.playerColor;
						((HumanSequencePlayer) p0).enableAllHandCards();
						System.out.println("Human player's turn has started...");
						//wait until human player makes a move
						synchronized(this) {
							wait();
						} 
						//check if the move wins
						if(isGameOver(lastPlayedX, lastPlayedY)) {
							//game is over
							JOptionPane.showMessageDialog(null,"Winner is:" + currentPlayer,
									"GAME OVER",JOptionPane.ERROR_MESSAGE);
							break;
						}
						synchronized(this) {
							wait();
						} 
					}

					//computer's turn
					currentPlayer = p1;
					currentPlayerColor = p1.playerColor;

					System.out.println("CPU player's turn has started...");
					//computer makes a move
					((CpuSequencePlayer)(p1)).makeAMove(thisGame);
				}//outer while
			}//end of if
			else {
				
				//cpu starts first
				while(true) {
					//computer's turn
					System.out.println("CPU player's turn has started...");
					currentPlayer = p0;
					currentPlayerColor = p0.playerColor;
					
					
					//computer makes a move
					((CpuSequencePlayer)(p0)).makeAMove(thisGame);

					if(isGameOver(lastPlayedX, lastPlayedY)) {
						//game is over
						JOptionPane.showMessageDialog(null,"Winner is:" + currentPlayer,
								"GAME OVER",JOptionPane.ERROR_MESSAGE);
						break;
					}
					else {
						currentPlayer = p1;
						currentPlayerColor = p1.playerColor;

						((HumanSequencePlayer) p1).enableAllHandCards();
						System.out.println("Human player's turn has started...");
						//wait until human player makes a move
						synchronized(this) {
							wait();
						}
						if(isGameOver(lastPlayedX, lastPlayedY)) {
							//game is over
							JOptionPane.showMessageDialog(null,"Winner is:" + currentPlayer,
									"GAME OVER",JOptionPane.ERROR_MESSAGE);
							break;
						}
						synchronized(this) {
							wait();
						}
					}
				}//outer while
			}//end of else	
		case 2:
			break;
		case 3:
		}//switch
	}
	
	void resume() {
		synchronized(this) {
			notify();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		SequenceGame g = new SequenceGame(1);
		
		ASequencePlayer[] l = g.playerList;
		
		System.out.println("Players:");
		for(int i=0; i<l.length;i++) {
			System.out.print("\tPlayer " + l[i].playerNumber + String.valueOf(l[i].getClass()) + ":\n\t\tHand: ");
			l[i].printHand();
		}

		g.startGame(1);
	}

}
