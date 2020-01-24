import java.util.Random;

public class CpuSequencePlayer extends ASequencePlayer {
	Random r = new Random();
	char[] colorSelection = {'r', 'b', 'g'};

	public CpuSequencePlayer(int number) {

		super(number);
		playerName = "CPU Overlord";

	}

	void makeAMove(SequenceGame game) {
		boolean isPlayed = false;
		SequenceGameGUI gui = game.gui;

		do {
			int len = hand.size();
			int index = r.nextInt(len);
			ASequenceCard c = hand.get(index);
			game.lastPlayedCard = c;
			
			if(c.getIsOneEyedJack()) {
				//one eyed jack

				outerloop:
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++)
						if(game.board[i][j]!=' ' && game.board[i][j]!='C'
							&& game.board[i][j]!=playerColor && r.nextInt(10)==5) {
							//a token found at this location, 1/10 chance to be selected
							game.board[i][j] = ' ';
							gui.tokenButtons[i][j].setIcon(null);
							hand.remove(index);
							game.oneEyedJackIsPlayed = true;
							game.lastPlayedX = i;
							game.lastPlayedY = j;

							//System.out.println("CPU's move: " + i + ", " + j);
							isPlayed = true;
							break outerloop;
						}
			}	
			else if(c.getIsTwoEyedJack()) {
				outerloop:
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++)
						if(game.board[i][j]==' ' && r.nextInt(80)==5) {
							//no token found at this location, 1/80 chance to be selected
							game.board[i][j] = playerColor;
							switch(playerColor) {
							case 'r': gui.tokenButtons[i][j].setIcon(gui.redToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.redToken); 
									  break;
							case 'b': gui.tokenButtons[i][j].setIcon(gui.blueToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.blueToken); 
									  break;
							case 'g': gui.tokenButtons[i][j].setIcon(gui.greenToken); 
									  gui.tokenButtons[i][j].setDisabledIcon(gui.greenToken);
							}
							hand.remove(index);
							game.lastPlayedX = i;
							game.lastPlayedY = j;

							//System.out.println("CPU's move: " + i + ", " + j);
							isPlayed = true;
							break outerloop;
						}
			}
			else {
				//non-jack card
				int x1 = c.getX1(), y1 = c.getY1();
				int x2 = c.getX2(), y2 = c.getY2();
				
				if(game.board[x1][y1]==' ' && game.board[x2][y2]==' ') {
					int choice = r.nextInt(1);
					if(choice==0) {
						game.board[x1][y1] = playerColor;
						switch(playerColor) {
						case 'r': gui.tokenButtons[x1][y1].setIcon(gui.redToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.redToken); 
								  break;
						case 'b': gui.tokenButtons[x1][y1].setIcon(gui.blueToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.blueToken); 
								  break;
						case 'g': gui.tokenButtons[x1][y1].setIcon(gui.greenToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.greenToken);
						}//switch
						
						game.lastPlayedX = x1;
						game.lastPlayedY = y1;
						
						//System.out.println("CPU's move: " + x1 + ", " + y1);
					}//inner if
					else {
						game.board[x2][y2] = playerColor;
						switch(playerColor) {
						case 'r': gui.tokenButtons[x2][y2].setIcon(gui.redToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.redToken); 
								  break;
						case 'b': gui.tokenButtons[x2][y2].setIcon(gui.blueToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.blueToken); 
								  break;
						case 'g': gui.tokenButtons[x2][y2].setIcon(gui.greenToken); 
								  gui.tokenButtons[x2][y2].setDisabledIcon(gui.greenToken);
						}//switch

						game.lastPlayedX = x2;
						game.lastPlayedY = y2;

						//System.out.println("CPU's move: " + x2 + ", " + y2);
					}//inner else
					
					hand.remove(index);
					isPlayed = true;
				}//outer if
				else if(game.board[x1][y1]==' ') {
						game.board[x1][y1] = playerColor;
						switch(playerColor) {
						case 'r': gui.tokenButtons[x1][y1].setIcon(gui.redToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.redToken); 
								  break;
						case 'b': gui.tokenButtons[x1][y1].setIcon(gui.blueToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.blueToken); 
								  break;
						case 'g': gui.tokenButtons[x1][y1].setIcon(gui.greenToken); 
								  gui.tokenButtons[x1][y1].setDisabledIcon(gui.greenToken);
						}//switch

						game.lastPlayedX = x1;
						game.lastPlayedY = y1;
						
						hand.remove(index);
						isPlayed = true;
						
						//System.out.println("CPU's move: " + x1 + ", " + y1);
				}
				else if(game.board[x2][y2]==' ') {
					game.board[x2][y2] = playerColor;
					switch(playerColor) {
					case 'r': gui.tokenButtons[x2][y2].setIcon(gui.redToken); 
							  gui.tokenButtons[x2][y2].setDisabledIcon(gui.redToken); 
							  break;
					case 'b': gui.tokenButtons[x2][y2].setIcon(gui.blueToken); 
							  gui.tokenButtons[x2][y2].setDisabledIcon(gui.blueToken); 
							  break;
					case 'g': gui.tokenButtons[x2][y2].setIcon(gui.greenToken); 
							  gui.tokenButtons[x2][y2].setDisabledIcon(gui.greenToken);
					}//switch

					game.lastPlayedX = x2;
					game.lastPlayedY = y2;
					
					hand.remove(index);
					isPlayed = true;

					//System.out.println("CPU's move: " + x2 + ", " + y2);
				}
				else {
					//recycle card and play a new card
					//***isPlayed should be left as FALSE
					ASequenceCard temp = hand.remove(index);
					
					game.log.updateLog("CPU recycled a card: " + temp.getCardName());
					
					game.Chris.dealCard(this);

					//System.out.println("CPU's move: Recycled " + c.getCardName());
				}

			}
		}while(!isPlayed);
		
		game.Chris.dealCard(this);

		//System.out.println("CPU ended turn");
	}//end of makeAMove
	
	void selectColor(int i) {
		int s;
		do {
			s = r.nextInt(3);
		}while(s==i);
		
		playerColor = colorSelection[s];
	}
}//end of class
