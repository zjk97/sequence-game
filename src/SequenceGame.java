import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class SequenceGame {
	
	protected char[][] board;
	char currentPlayerColor; 
	char gameResult = ' ';
	ASequencePlayer[] playerList;
	ASequencePlayer currentPlayer;
	String winner;
	CardDealer Chris;
	final char BLANK = ' ', CORNER = 'C';
	SequenceGameGUI gui;
	SequenceGame thisGame = this;
	Border empty = BorderFactory.createEmptyBorder();
	boolean oneEyedJackIsPlayed = false;
	int isStartingNewGame = JOptionPane.YES_OPTION;
	// set by SequenceGameGUI:
		//x and y coordinate of the card most recently played
		int lastPlayedX = -1, lastPlayedY = -1;
		ASequenceCard lastPlayedCard;
	//for testing purposes
		String startUpTime, winBy;
		int turn = 0;
		SequenceLog log;
	
	public SequenceGame(int numberOfPlayers) throws InterruptedException {
	
		long startTime = System.nanoTime();
		
		setBoard();
		createPlayers(numberOfPlayers);
		gui = new SequenceGameGUI(this);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dealCards(numberOfPlayers);
		
		gui.setVisible(true);

        long endTime = System.nanoTime();
        startUpTime = String.valueOf(endTime - startTime);
        System.out.println(startUpTime);
        
        startGame(numberOfPlayers);
        isStartingNewGame = JOptionPane.showConfirmDialog(gui, "Waste your life on this game", 
        		"Play again?", JOptionPane.YES_NO_OPTION);
        gui.dispose();
	}


	void setBoard() {
		
		board = new char[10][10];
		
		//make every slot of the board BLANK
		for(int r=0; r<10; r++)
			for(int c=0; c<10; c++)
				board[r][c] = BLANK;
		
		//fill the four corners
		board[0][0] = CORNER; board[0][9] = CORNER;
		board[9][0] = CORNER; board[9][0] = CORNER;
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
		Chris.shuffleCards();//shuffle cards
		Chris.shuffle(playerList);//shuffle player order
		
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
		 * determine if any player has won
		 */
		
		//game just started, game must NOT be over
		if(lastPlayedX == -1)
			return false;
		
		//if one-eyed jack played, game must NOT be over
		if(oneEyedJackIsPlayed) {
			oneEyedJackIsPlayed = false;
			return false;
		}
		
		//check corners
		//Top-left
		if(board[0][1]!=' ' && board[0][1]==board[0][2] && board[0][2]==board[0][3] && board[0][3]==board[0][4]) {
			gameResult = board[0][1];
			winner = currentPlayer.playerName;
			winBy = "Top-Left Corner (0)";
			return true;
		}
		if(board[1][0]!=' ' && board[1][0]==board[2][0] && board[2][0]==board[3][0] && board[3][0]==board[4][0]) {
			gameResult = board[1][0];
			winner = currentPlayer.playerName;
			winBy = "Top-Left Corner (1)";
			return true;
		}
		if(board[1][1]!=' ' && board[1][1]==board[2][2] && board[2][2]==board[3][3] && board[3][3]==board[4][4]) {
			gameResult = board[1][1];
			winner = currentPlayer.playerName;
			winBy = "Top-Left Corner (2)";
			return true;
		}
		
		//Top-right
		if(board[0][5]!=' ' && board[0][5]==board[0][6] && board[0][6]==board[0][7] && board[0][7]==board[0][8]) {
			gameResult = board[0][5];
			winner = currentPlayer.playerName;
			winBy = "Top-Right Corner (0)";
			return true;
		}
		if(board[1][9]!=' ' && board[1][9]==board[2][9] && board[2][9]==board[3][9] && board[3][9]==board[4][9]) {
			gameResult = board[1][1];
			winner = currentPlayer.playerName;
			winBy = "Top-Right Corner (1)";
			return true;
		}
		if(board[1][8]!=' ' && board[1][8]==board[2][7] && board[2][7]==board[3][6] && board[3][6]==board[4][5]) {
			gameResult = board[1][8];
			winner = currentPlayer.playerName;
			winBy = "Top-Right Corner (2)";
			return true;
		}
		
		//Bottom-left
		if(board[0][8]!=' ' && board[0][8]==board[0][7] && board[0][7]==board[0][6] && board[0][6]==board[0][5]) {
			gameResult = board[0][8];
			winner = currentPlayer.playerName;
			winBy = "Bottom-Left Corner (0)";
			return true;
		}
		if(board[9][1]!=' ' && board[9][1]==board[9][2] && board[9][2]==board[9][3] && board[9][3]==board[9][4]) {
			gameResult = board[9][1];
			winner = currentPlayer.playerName;
			winBy = "Bottom-Left Corner (1)";
			return true;
		}
		if(board[8][1]!=' ' && board[8][1]==board[7][2] && board[7][2]==board[6][3] && board[6][3]==board[5][4]) {
			gameResult = board[8][1];
			winner = currentPlayer.playerName;
			winBy = "Bottom-Left Corner (2)";
			return true;
		}
		
		//Bottom-right
		if(board[9][8]!=' ' && board[9][8]==board[9][7] && board[9][7]==board[9][6] && board[9][6]==board[9][5]) {
			gameResult = board[9][8];
			winner = currentPlayer.playerName;
			winBy = "Bottom-Right Corner (0)";
			return true;
		}
		if(board[8][9]!=' ' && board[8][9]==board[7][9] && board[7][9]==board[6][9] && board[6][9]==board[5][9]) {
			gameResult = board[8][9];
			winner = currentPlayer.playerName;
			winBy = "Bottom-Right Corner (1)";
			return true;
		}
		if(board[8][8]!=' ' && board[8][8]==board[7][7] && board[7][7]==board[6][6] && board[6][6]==board[5][5]) {
			gameResult = board[8][8];
			winner = currentPlayer.playerName;
			winBy = "Bottom-Right Corner (2)";
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
			winner = currentPlayer.playerName;
			winBy = "Left-Right Streak: " + sum;
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
			winner = currentPlayer.playerName;
			winBy = "Up-Down Streak: " + sum;
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
			winner = currentPlayer.playerName;
			winBy = "LeftUp + RightDown Diagonal: " + sum;
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
			winner = currentPlayer.playerName;
			winBy = "LeftDown + RightUp Diagonal: " + sum;
			return true;
		}

		
		//if board is filled, but nobody has won then it's a tie
		boolean hasEmpty = false;
		outerloop:
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) 
				if(board[i][j]==' ') {
					hasEmpty = true;
					break outerloop;
				}
		
		if(!hasEmpty) {
			//game ends as a tie
			gameResult = 'T';
			winner = "TIE";
			winBy = "Tied: full board";
			return true;
		}
		
		//if none of the above returns true, then return false
		return false;
	}//end of isGameOver

	
	
	void displayNewHandCard(ASequenceCard c, HumanSequencePlayer player) {
		JButton b = new JButton();
		//immediately disable this button
		b.setEnabled(false);
		

		int x1 = c.getX1(), y1 = c.getY1();
		int x2 = c.getX2(), y2 = c.getY2();
		int cardNumber = c.getCardNumber();

		//add the button for this card to player handMap
		player.getHandMap().put(new Integer(cardNumber), b);
		
		//set action when a hand card is selected
		b.setBorder(empty);
		b.setIcon(gui.handCardImages[x1][y1]);
		b.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//reset gui's jack fields
						gui.twoEyedJackIsPlayed = false;
						oneEyedJackIsPlayed = false;
						
						//reset recycle fields
						gui.isRecycled = false;
						gui.deckButton.setEnabled(false);
						
						//set index of this card in hand if it is removed
						gui.removedHandCardIndex = currentPlayer.hand.indexOf(c);
						
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
								b1.cardNumber = cardNumber;
								b2.cardNumber = cardNumber;
								//highlight the cards on the board AND enable the buttons
								if(board[x1][y1]==' ') {
									b1.setIcon(gui.normalCardImages[x1][y1]);
									b1.setEnabled(true);
								}
								if(board[x2][y2]==' ') {
									b2.setIcon(gui.normalCardImages[x2][y2]);
									b2.setEnabled(true);
								}
								if(board[x1][y1]!=' ' && board[x2][y2]!=' ') {
									//both spots are taken, recycle
									gui.isRecycled = true;
									gui.recycledCard = c;
									gui.removedHandCardIndex = currentPlayer.hand.indexOf(c);
									gui.deckButton.setEnabled(true);
								}
							}
							else {
								//an one eyed jack
								//disable all the available token buttons
								for(int i=0; i<10; i++)
									for(int j=0; j<10; j++)
											gui.tokenButtons[i][j].setEnabled(false);
								
								oneEyedJackIsPlayed = true;
								gui.jackNumber = cardNumber;
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
							gui.jackNumber = cardNumber;
							gui.enableAllCards(thisGame, 1);
						}
					}
					
				});

		gui.handPanel.add(b);
		gui.handPanel.revalidate();
	}

	void startGame(int numberOfPlayers) throws InterruptedException {
		
		log = new SequenceLog();
		log.updateLog("StartUp Time: " + startUpTime + "\n");
		
		switch(numberOfPlayers) {
		case 1:
			//single player mode
			ASequencePlayer p0 = playerList[0];
			HumanSequencePlayer human;
			CpuSequencePlayer cpu;
			
			if(p0 instanceof HumanSequencePlayer) {
				human = (HumanSequencePlayer) p0;
				cpu = (CpuSequencePlayer) playerList[1];
			}
			else {
				cpu = (CpuSequencePlayer) p0;
				human = (HumanSequencePlayer) playerList[1];
			}
			
			//assign player name
			String name = JOptionPane.showInputDialog(gui, "Please enter your name: ", "Rotty Coyo");

			//check if name is all whitespace
			boolean isBlank = true;
			if(name!=null) {
				int len = name.length();
				for(int i=0; i<len; i++)
					if(name.charAt(i)!= ' ') {
						isBlank = false;
						break;
					}
			}
					
			if(name=="" || isBlank) {
				//set default name
				human.playerName = "Rotty Coyo";
				//notify user
				JOptionPane.showMessageDialog(gui, "Your default name is: Rotty Coyo", "Gave up the chance to name yourself?",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				human.playerName = name;
			}
			
			//assign player color
			JList<ImageIcon> list = new JList<ImageIcon>(new ImageIcon[] {gui.redToken, gui.blueToken, gui.greenToken});
			JOptionPane.showMessageDialog(
			  gui, list, "Please choose your color", JOptionPane.PLAIN_MESSAGE);
			
			//player did NOT select a color
			while(list.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(gui, "No, you MUST select a color", "We can do this all day", JOptionPane.ERROR_MESSAGE);
				
				JOptionPane.showMessageDialog(
						  gui, list, "Please choose your color", JOptionPane.PLAIN_MESSAGE);
			}
			
			switch(list.getSelectedIndex()) {
				case 0: 
					human.playerColor = 'r';
					cpu.selectColor(0);
				break;
				case 1: human.playerColor = 'b'; 
					cpu.selectColor(1);
				break;
				case 2: human.playerColor = 'g'; 
					cpu.selectColor(2);
				break;
			}
			//log players' info
			String playerInfo = "User's name: " + human.playerName + "(" + human.playerColor + ")\n"
					+ "CPU chose color: " + cpu.playerColor + "\n";
			log.updateLog(playerInfo);
			
			//let the game begin
			if(p0 instanceof HumanSequencePlayer) {
				//human starts first
				while(true) {
					if(isGameOver(lastPlayedX, lastPlayedY)) {
						//game is over
						endGame();
						break;
					}
					else {
						currentPlayer = human;
						currentPlayerColor = human.playerColor;
						human.enableAllHandCards();
						
						//log human's hand
						log.updateLog("Human turn " + turn + ":"
								+ "\n\tCurrent hand: " + currentPlayer.getHand());
						
						//wait until human player makes a move
						synchronized(this) {
							wait();
						} 
						//log human's move
						log.updateLog("\tMove: " + lastPlayedCard.getCardName() +
								" at " + "(" + lastPlayedX + ", " + lastPlayedY + ")");
						
						//check if the move wins
						if(isGameOver(lastPlayedX, lastPlayedY)) {
							//game is over
							endGame();
							break;
						}
						synchronized(this) {
							wait();
						} 
					}

					//computer's turn
					currentPlayer = cpu;
					currentPlayerColor = cpu.playerColor;

					//log cpu's hand
					log.updateLog("CPU turn " + turn + ":"
							+ "\n\tCurrent hand: " + currentPlayer.getHand());
					
					//computer makes a move
					cpu.makeAMove(thisGame);
					//log cpu's move
					log.updateLog("\tMove: " + lastPlayedCard.getCardName() +
							" at " + "(" + lastPlayedX + ", " + lastPlayedY + ")");
					
					//update turn
					turn++;
					
				}//outer while
			}//end of if
			else {
				//cpu starts first
				while(true) {
					//computer's turn
					currentPlayer = cpu;
					currentPlayerColor = cpu.playerColor;
					//log cpu's hand
					log.updateLog("CPU turn " + turn + ":"
							+ "\n\tCurrent hand: " + currentPlayer.getHand());
					
					//computer makes a move
					cpu.makeAMove(thisGame);
					//log cpu's move
					log.updateLog("\tMove: " + lastPlayedCard.getCardName() +
							" at " + "(" + lastPlayedX + ", " + lastPlayedY + ")");

					if(isGameOver(lastPlayedX, lastPlayedY)) {
						//game is over
						endGame();
						break;
					}
					else {
						currentPlayer = human;
						currentPlayerColor = human.playerColor;
						
						//log human's hand
						log.updateLog("Human turn " + turn + ":"
								+ "\n\tCurrent hand: " + currentPlayer.getHand());

						human.enableAllHandCards();
						//wait until human player makes a move
						synchronized(this) {
							wait();
						}
						//log human's move
						log.updateLog("\tMove: " + lastPlayedCard.getCardName() +
								" at " + "(" + lastPlayedX + ", " + lastPlayedY + ")");
						
						if(isGameOver(lastPlayedX, lastPlayedY)) {
							//game is over
							endGame();
							break;
						}
						synchronized(this) {
							wait();
						}
					}
					
					//update turn
					turn++;
					
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
	
	void endGame() {
		//game is over
		JOptionPane.showMessageDialog(null,"Winner is: " + winner,
				"GAME OVER",JOptionPane.ERROR_MESSAGE);
		//log endgame
		log.updateLog("\tMove: " + lastPlayedCard.getCardName() +
				" at " + "(" + lastPlayedX + ", " + lastPlayedY + ")");
		log.updateLog("\nGame ended at turn " + turn + "\nWinner is: " + winner
				+ ", by " + winBy);
	}
	
	public static void main(String[] args) throws InterruptedException {

		while(true) {
			SequenceGame g = new SequenceGame(1);

	        //give notice while starting a new game
			if(g.isStartingNewGame == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null,"Loading a new game...",
						"Just a second",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null,"Bye :(",
						"Play again!",JOptionPane.INFORMATION_MESSAGE);
				
				break;
			}

			/*
			ASequencePlayer[] l = g.playerList;

			System.out.println("Players:");
			for(int i=0; i<l.length;i++) {
				System.out.print("\tPlayer " + l[i].playerNumber + String.valueOf(l[i].getClass()) + ":\n\t\tHand: ");
				l[i].printHand();
			}
				*/
		}//end of while

	}//end of main method

}//end of class
