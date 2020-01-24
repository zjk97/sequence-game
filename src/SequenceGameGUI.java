import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.Border;

public class SequenceGameGUI extends JFrame {
	
	JPanel boardPanel, handPanel, deckPanel, playerPanel;
	//matrix of card buttons in board order
	SButton[][] cardButtons = new SButton[10][10];
	//matrix of token buttons 
	SButton[][] tokenButtons = new SButton[10][10];
	//button for card deck
	JButton deckButton;
	//controlled by SequenceGame, set when a jack in hand is played 
	boolean twoEyedJackIsPlayed;
	int jackNumber;
	//controlled by SequenceGame, set when a card is recycled
	boolean isRecycled;
	ASequenceCard recycledCard;
	//controlled by SequenceGame, notify which card to remove from hand
	int removedHandCardIndex;
	
	ImageIcon redToken = new ImageIcon(getClass().getResource("tokens/redToken.png"));
	ImageIcon blueToken = new ImageIcon(getClass().getResource("tokens/blueToken.png"));
	ImageIcon greenToken = new ImageIcon(getClass().getResource("tokens/greenToken.png"));
			
		/* 
		   **NO LONGER USED**, Original button naming in order
		{
			{new JButton("TLCorner"), new JButton("c1"), new JButton("ck"), new JButton("cq"), new JButton("c10"), new JButton("c9"), new JButton("c8"), new JButton("c7"), new JButton("c6"), new JButton("TRCorner")},
			{new JButton("d1"), new JButton("s7"), new JButton("s8"), new JButton("s9"), new JButton("s10"), new JButton("sq"), new JButton("sk"), new JButton("s1"), new JButton("c5"), new JButton("s2")},
			{new JButton("dk"), new JButton("s6"), new JButton("C10"), new JButton("C9"), new JButton("C8"), new JButton("C7"), new JButton("C6"), new JButton("d2"), new JButton("c4"), new JButton("s3")}, 
			{new JButton("dq"), new JButton("s5"), new JButton("Cq"), new JButton("h8"), new JButton("h7"), new JButton("h6"), new JButton("C5"), new JButton("d3"), new JButton("c3"), new JButton("s4")},
			{new JButton("d10"), new JButton("S4"), new JButton("Ck"), new JButton("h9"), new JButton("h2"), new JButton("h5"), new JButton("C4"), new JButton("d4"), new JButton("c2"), new JButton("S5")},
			{new JButton("d9"), new JButton("S3"), new JButton("C1"), new JButton("h10"), new JButton("h3"), new JButton("h4"), new JButton("C3"), new JButton("d5"), new JButton("h1"), new JButton("S6")},
			{new JButton("d8"), new JButton("S2"), new JButton("D1"), new JButton("hq"), new JButton("hk"), new JButton("H1"), new JButton("C2"), new JButton("d6"), new JButton("Hk"), new JButton("S7")},
			{new JButton("d7"), new JButton("H2"), new JButton("Dk"), new JButton("Dq"), new JButton("D10"), new JButton("D9"), new JButton("D8"), new JButton("D7"), new JButton("Hq"), new JButton("S8")},
			{new JButton("D6"), new JButton("H3"), new JButton("H4"), new JButton("H5"), new JButton("H6"), new JButton("H7"), new JButton("H8"), new JButton("H9"), new JButton("H10"), new JButton("S9")},
			{new JButton("BLCorner"), new JButton("D5"), new JButton("D4"), new JButton("D3"), new JButton("D2"), new JButton("S1"), new JButton("Sk"), new JButton("Sq"), new JButton("S10"), new JButton("BRCorner")}
			};
			*/
	
	ImageIcon[][] normalCardImages = {
			{new ImageIcon(getClass().getResource("normalCards/corner.png")), new ImageIcon(getClass().getResource("normalCards/AC.png")), new ImageIcon(getClass().getResource("normalCards/KC.png")), new ImageIcon(getClass().getResource("normalCards/QC.png")), new ImageIcon(getClass().getResource("normalCards/10C.png")), new ImageIcon(getClass().getResource("normalCards/9C.png")), new ImageIcon(getClass().getResource("normalCards/8C.png")), new ImageIcon(getClass().getResource("normalCards/7C.png")), new ImageIcon(getClass().getResource("normalCards/6C.png")), new ImageIcon(getClass().getResource("normalCards/corner.png"))},
			{new ImageIcon(getClass().getResource("normalCards/AD.png")), new ImageIcon(getClass().getResource("normalCards/7S.png")), new ImageIcon(getClass().getResource("normalCards/8S.png")), new ImageIcon(getClass().getResource("normalCards/9S.png")), new ImageIcon(getClass().getResource("normalCards/10S.png")), new ImageIcon(getClass().getResource("normalCards/QS.png")), new ImageIcon(getClass().getResource("normalCards/KS.png")), new ImageIcon(getClass().getResource("normalCards/AS.png")), new ImageIcon(getClass().getResource("normalCards/5C.png")), new ImageIcon(getClass().getResource("normalCards/2S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/KD.png")), new ImageIcon(getClass().getResource("normalCards/6S.png")), new ImageIcon(getClass().getResource("normalCards/10C.png")), new ImageIcon(getClass().getResource("normalCards/9C.png")), new ImageIcon(getClass().getResource("normalCards/8C.png")), new ImageIcon(getClass().getResource("normalCards/7C.png")), new ImageIcon(getClass().getResource("normalCards/6C.png")), new ImageIcon(getClass().getResource("normalCards/2D.png")), new ImageIcon(getClass().getResource("normalCards/4C.png")), new ImageIcon(getClass().getResource("normalCards/3S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/QD.png")), new ImageIcon(getClass().getResource("normalCards/5S.png")), new ImageIcon(getClass().getResource("normalCards/QC.png")), new ImageIcon(getClass().getResource("normalCards/8H.png")), new ImageIcon(getClass().getResource("normalCards/7H.png")), new ImageIcon(getClass().getResource("normalCards/6H.png")), new ImageIcon(getClass().getResource("normalCards/5C.png")), new ImageIcon(getClass().getResource("normalCards/3D.png")), new ImageIcon(getClass().getResource("normalCards/3C.png")), new ImageIcon(getClass().getResource("normalCards/4S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/10D.png")), new ImageIcon(getClass().getResource("normalCards/4S.png")), new ImageIcon(getClass().getResource("normalCards/KC.png")), new ImageIcon(getClass().getResource("normalCards/9H.png")), new ImageIcon(getClass().getResource("normalCards/2H.png")), new ImageIcon(getClass().getResource("normalCards/5H.png")), new ImageIcon(getClass().getResource("normalCards/4C.png")), new ImageIcon(getClass().getResource("normalCards/4D.png")), new ImageIcon(getClass().getResource("normalCards/2C.png")), new ImageIcon(getClass().getResource("normalCards/5S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/9D.png")), new ImageIcon(getClass().getResource("normalCards/3S.png")), new ImageIcon(getClass().getResource("normalCards/AC.png")), new ImageIcon(getClass().getResource("normalCards/10H.png")), new ImageIcon(getClass().getResource("normalCards/3H.png")), new ImageIcon(getClass().getResource("normalCards/4H.png")), new ImageIcon(getClass().getResource("normalCards/3C.png")), new ImageIcon(getClass().getResource("normalCards/5D.png")), new ImageIcon(getClass().getResource("normalCards/AH.png")), new ImageIcon(getClass().getResource("normalCards/6S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/8D.png")), new ImageIcon(getClass().getResource("normalCards/2S.png")), new ImageIcon(getClass().getResource("normalCards/AD.png")), new ImageIcon(getClass().getResource("normalCards/QH.png")), new ImageIcon(getClass().getResource("normalCards/KH.png")), new ImageIcon(getClass().getResource("normalCards/AH.png")), new ImageIcon(getClass().getResource("normalCards/2C.png")), new ImageIcon(getClass().getResource("normalCards/6D.png")), new ImageIcon(getClass().getResource("normalCards/KH.png")), new ImageIcon(getClass().getResource("normalCards/7S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/7D.png")), new ImageIcon(getClass().getResource("normalCards/2H.png")), new ImageIcon(getClass().getResource("normalCards/KD.png")), new ImageIcon(getClass().getResource("normalCards/QD.png")), new ImageIcon(getClass().getResource("normalCards/10D.png")), new ImageIcon(getClass().getResource("normalCards/9D.png")), new ImageIcon(getClass().getResource("normalCards/8D.png")), new ImageIcon(getClass().getResource("normalCards/7D.png")), new ImageIcon(getClass().getResource("normalCards/QH.png")), new ImageIcon(getClass().getResource("normalCards/8S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/6D.png")), new ImageIcon(getClass().getResource("normalCards/3H.png")), new ImageIcon(getClass().getResource("normalCards/4H.png")), new ImageIcon(getClass().getResource("normalCards/5H.png")), new ImageIcon(getClass().getResource("normalCards/6H.png")), new ImageIcon(getClass().getResource("normalCards/7H.png")), new ImageIcon(getClass().getResource("normalCards/8H.png")), new ImageIcon(getClass().getResource("normalCards/9H.png")), new ImageIcon(getClass().getResource("normalCards/10H.png")), new ImageIcon(getClass().getResource("normalCards/9S.png"))},
			{new ImageIcon(getClass().getResource("normalCards/corner.png")), new ImageIcon(getClass().getResource("normalCards/5D.png")), new ImageIcon(getClass().getResource("normalCards/4D.png")), new ImageIcon(getClass().getResource("normalCards/3D.png")), new ImageIcon(getClass().getResource("normalCards/2D.png")), new ImageIcon(getClass().getResource("normalCards/AS.png")), new ImageIcon(getClass().getResource("normalCards/KS.png")), new ImageIcon(getClass().getResource("normalCards/QS.png")), new ImageIcon(getClass().getResource("normalCards/10S.png")), new ImageIcon(getClass().getResource("normalCards/corner.png"))}

			};
	
	ImageIcon[][] greyCardImages = {
			{new ImageIcon(getClass().getResource("greyCards/corner.png")), new ImageIcon(getClass().getResource("greyCards/AC.png")), new ImageIcon(getClass().getResource("greyCards/KC.png")), new ImageIcon(getClass().getResource("greyCards/QC.png")), new ImageIcon(getClass().getResource("greyCards/10C.png")), new ImageIcon(getClass().getResource("greyCards/9C.png")), new ImageIcon(getClass().getResource("greyCards/8C.png")), new ImageIcon(getClass().getResource("greyCards/7C.png")), new ImageIcon(getClass().getResource("greyCards/6C.png")), new ImageIcon(getClass().getResource("greyCards/corner.png"))},
			{new ImageIcon(getClass().getResource("greyCards/AD.png")), new ImageIcon(getClass().getResource("greyCards/7S.png")), new ImageIcon(getClass().getResource("greyCards/8S.png")), new ImageIcon(getClass().getResource("greyCards/9S.png")), new ImageIcon(getClass().getResource("greyCards/10S.png")), new ImageIcon(getClass().getResource("greyCards/QS.png")), new ImageIcon(getClass().getResource("greyCards/KS.png")), new ImageIcon(getClass().getResource("greyCards/AS.png")), new ImageIcon(getClass().getResource("greyCards/5C.png")), new ImageIcon(getClass().getResource("greyCards/2S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/KD.png")), new ImageIcon(getClass().getResource("greyCards/6S.png")), new ImageIcon(getClass().getResource("greyCards/10C.png")), new ImageIcon(getClass().getResource("greyCards/9C.png")), new ImageIcon(getClass().getResource("greyCards/8C.png")), new ImageIcon(getClass().getResource("greyCards/7C.png")), new ImageIcon(getClass().getResource("greyCards/6C.png")), new ImageIcon(getClass().getResource("greyCards/2D.png")), new ImageIcon(getClass().getResource("greyCards/4C.png")), new ImageIcon(getClass().getResource("greyCards/3S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/QD.png")), new ImageIcon(getClass().getResource("greyCards/5S.png")), new ImageIcon(getClass().getResource("greyCards/QC.png")), new ImageIcon(getClass().getResource("greyCards/8H.png")), new ImageIcon(getClass().getResource("greyCards/7H.png")), new ImageIcon(getClass().getResource("greyCards/6H.png")), new ImageIcon(getClass().getResource("greyCards/5C.png")), new ImageIcon(getClass().getResource("greyCards/3D.png")), new ImageIcon(getClass().getResource("greyCards/3C.png")), new ImageIcon(getClass().getResource("greyCards/4S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/10D.png")), new ImageIcon(getClass().getResource("greyCards/4S.png")), new ImageIcon(getClass().getResource("greyCards/KC.png")), new ImageIcon(getClass().getResource("greyCards/9H.png")), new ImageIcon(getClass().getResource("greyCards/2H.png")), new ImageIcon(getClass().getResource("greyCards/5H.png")), new ImageIcon(getClass().getResource("greyCards/4C.png")), new ImageIcon(getClass().getResource("greyCards/4D.png")), new ImageIcon(getClass().getResource("greyCards/2C.png")), new ImageIcon(getClass().getResource("greyCards/5S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/9D.png")), new ImageIcon(getClass().getResource("greyCards/3S.png")), new ImageIcon(getClass().getResource("greyCards/AC.png")), new ImageIcon(getClass().getResource("greyCards/10H.png")), new ImageIcon(getClass().getResource("greyCards/3H.png")), new ImageIcon(getClass().getResource("greyCards/4H.png")), new ImageIcon(getClass().getResource("greyCards/3C.png")), new ImageIcon(getClass().getResource("greyCards/5D.png")), new ImageIcon(getClass().getResource("greyCards/AH.png")), new ImageIcon(getClass().getResource("greyCards/6S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/8D.png")), new ImageIcon(getClass().getResource("greyCards/2S.png")), new ImageIcon(getClass().getResource("greyCards/AD.png")), new ImageIcon(getClass().getResource("greyCards/QH.png")), new ImageIcon(getClass().getResource("greyCards/KH.png")), new ImageIcon(getClass().getResource("greyCards/AH.png")), new ImageIcon(getClass().getResource("greyCards/2C.png")), new ImageIcon(getClass().getResource("greyCards/6D.png")), new ImageIcon(getClass().getResource("greyCards/KH.png")), new ImageIcon(getClass().getResource("greyCards/7S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/7D.png")), new ImageIcon(getClass().getResource("greyCards/2H.png")), new ImageIcon(getClass().getResource("greyCards/KD.png")), new ImageIcon(getClass().getResource("greyCards/QD.png")), new ImageIcon(getClass().getResource("greyCards/10D.png")), new ImageIcon(getClass().getResource("greyCards/9D.png")), new ImageIcon(getClass().getResource("greyCards/8D.png")), new ImageIcon(getClass().getResource("greyCards/7D.png")), new ImageIcon(getClass().getResource("greyCards/QH.png")), new ImageIcon(getClass().getResource("greyCards/8S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/6D.png")), new ImageIcon(getClass().getResource("greyCards/3H.png")), new ImageIcon(getClass().getResource("greyCards/4H.png")), new ImageIcon(getClass().getResource("greyCards/5H.png")), new ImageIcon(getClass().getResource("greyCards/6H.png")), new ImageIcon(getClass().getResource("greyCards/7H.png")), new ImageIcon(getClass().getResource("greyCards/8H.png")), new ImageIcon(getClass().getResource("greyCards/9H.png")), new ImageIcon(getClass().getResource("greyCards/10H.png")), new ImageIcon(getClass().getResource("greyCards/9S.png"))},
			{new ImageIcon(getClass().getResource("greyCards/corner.png")), new ImageIcon(getClass().getResource("greyCards/5D.png")), new ImageIcon(getClass().getResource("greyCards/4D.png")), new ImageIcon(getClass().getResource("greyCards/3D.png")), new ImageIcon(getClass().getResource("greyCards/2D.png")), new ImageIcon(getClass().getResource("greyCards/AS.png")), new ImageIcon(getClass().getResource("greyCards/KS.png")), new ImageIcon(getClass().getResource("greyCards/QS.png")), new ImageIcon(getClass().getResource("greyCards/10S.png")), new ImageIcon(getClass().getResource("greyCards/corner.png"))}


};

	//four jacks are placed in the four corners since
	//corners are not real cards
	ImageIcon[][] handCardImages = {
			{new ImageIcon(getClass().getResource("handCards/JS.png")), new ImageIcon(getClass().getResource("handCards/AC.png")), new ImageIcon(getClass().getResource("handCards/KC.png")), new ImageIcon(getClass().getResource("handCards/QC.png")), new ImageIcon(getClass().getResource("handCards/10C.png")), new ImageIcon(getClass().getResource("handCards/9C.png")), new ImageIcon(getClass().getResource("handCards/8C.png")), new ImageIcon(getClass().getResource("handCards/7C.png")), new ImageIcon(getClass().getResource("handCards/6C.png")), new ImageIcon(getClass().getResource("handCards/JH.png"))},
			{new ImageIcon(getClass().getResource("handCards/AD.png")), new ImageIcon(getClass().getResource("handCards/7S.png")), new ImageIcon(getClass().getResource("handCards/8S.png")), new ImageIcon(getClass().getResource("handCards/9S.png")), new ImageIcon(getClass().getResource("handCards/10S.png")), new ImageIcon(getClass().getResource("handCards/QS.png")), new ImageIcon(getClass().getResource("handCards/KS.png")), new ImageIcon(getClass().getResource("handCards/AS.png")), new ImageIcon(getClass().getResource("handCards/5C.png")), new ImageIcon(getClass().getResource("handCards/2S.png"))},
			{new ImageIcon(getClass().getResource("handCards/KD.png")), new ImageIcon(getClass().getResource("handCards/6S.png")), new ImageIcon(getClass().getResource("handCards/10C.png")), new ImageIcon(getClass().getResource("handCards/9C.png")), new ImageIcon(getClass().getResource("handCards/8C.png")), new ImageIcon(getClass().getResource("handCards/7C.png")), new ImageIcon(getClass().getResource("handCards/6C.png")), new ImageIcon(getClass().getResource("handCards/2D.png")), new ImageIcon(getClass().getResource("handCards/4C.png")), new ImageIcon(getClass().getResource("handCards/3S.png"))},
			{new ImageIcon(getClass().getResource("handCards/QD.png")), new ImageIcon(getClass().getResource("handCards/5S.png")), new ImageIcon(getClass().getResource("handCards/QC.png")), new ImageIcon(getClass().getResource("handCards/8H.png")), new ImageIcon(getClass().getResource("handCards/7H.png")), new ImageIcon(getClass().getResource("handCards/6H.png")), new ImageIcon(getClass().getResource("handCards/5C.png")), new ImageIcon(getClass().getResource("handCards/3D.png")), new ImageIcon(getClass().getResource("handCards/3C.png")), new ImageIcon(getClass().getResource("handCards/4S.png"))},
			{new ImageIcon(getClass().getResource("handCards/10D.png")), new ImageIcon(getClass().getResource("handCards/4S.png")), new ImageIcon(getClass().getResource("handCards/KC.png")), new ImageIcon(getClass().getResource("handCards/9H.png")), new ImageIcon(getClass().getResource("handCards/2H.png")), new ImageIcon(getClass().getResource("handCards/5H.png")), new ImageIcon(getClass().getResource("handCards/4C.png")), new ImageIcon(getClass().getResource("handCards/4D.png")), new ImageIcon(getClass().getResource("handCards/2C.png")), new ImageIcon(getClass().getResource("handCards/5S.png"))},
			{new ImageIcon(getClass().getResource("handCards/9D.png")), new ImageIcon(getClass().getResource("handCards/3S.png")), new ImageIcon(getClass().getResource("handCards/AC.png")), new ImageIcon(getClass().getResource("handCards/10H.png")), new ImageIcon(getClass().getResource("handCards/3H.png")), new ImageIcon(getClass().getResource("handCards/4H.png")), new ImageIcon(getClass().getResource("handCards/3C.png")), new ImageIcon(getClass().getResource("handCards/5D.png")), new ImageIcon(getClass().getResource("handCards/AH.png")), new ImageIcon(getClass().getResource("handCards/6S.png"))},
			{new ImageIcon(getClass().getResource("handCards/8D.png")), new ImageIcon(getClass().getResource("handCards/2S.png")), new ImageIcon(getClass().getResource("handCards/AD.png")), new ImageIcon(getClass().getResource("handCards/QH.png")), new ImageIcon(getClass().getResource("handCards/KH.png")), new ImageIcon(getClass().getResource("handCards/AH.png")), new ImageIcon(getClass().getResource("handCards/2C.png")), new ImageIcon(getClass().getResource("handCards/6D.png")), new ImageIcon(getClass().getResource("handCards/KH.png")), new ImageIcon(getClass().getResource("handCards/7S.png"))},
			{new ImageIcon(getClass().getResource("handCards/7D.png")), new ImageIcon(getClass().getResource("handCards/2H.png")), new ImageIcon(getClass().getResource("handCards/KD.png")), new ImageIcon(getClass().getResource("handCards/QD.png")), new ImageIcon(getClass().getResource("handCards/10D.png")), new ImageIcon(getClass().getResource("handCards/9D.png")), new ImageIcon(getClass().getResource("handCards/8D.png")), new ImageIcon(getClass().getResource("handCards/7D.png")), new ImageIcon(getClass().getResource("handCards/QH.png")), new ImageIcon(getClass().getResource("handCards/8S.png"))},
			{new ImageIcon(getClass().getResource("handCards/6D.png")), new ImageIcon(getClass().getResource("handCards/3H.png")), new ImageIcon(getClass().getResource("handCards/4H.png")), new ImageIcon(getClass().getResource("handCards/5H.png")), new ImageIcon(getClass().getResource("handCards/6H.png")), new ImageIcon(getClass().getResource("handCards/7H.png")), new ImageIcon(getClass().getResource("handCards/8H.png")), new ImageIcon(getClass().getResource("handCards/9H.png")), new ImageIcon(getClass().getResource("handCards/10H.png")), new ImageIcon(getClass().getResource("handCards/9S.png"))},
			{new ImageIcon(getClass().getResource("handCards/JD.png")), new ImageIcon(getClass().getResource("handCards/5D.png")), new ImageIcon(getClass().getResource("handCards/4D.png")), new ImageIcon(getClass().getResource("handCards/3D.png")), new ImageIcon(getClass().getResource("handCards/2D.png")), new ImageIcon(getClass().getResource("handCards/AS.png")), new ImageIcon(getClass().getResource("handCards/KS.png")), new ImageIcon(getClass().getResource("handCards/QS.png")), new ImageIcon(getClass().getResource("handCards/10S.png")), new ImageIcon(getClass().getResource("handCards/JC.png"))}

	};
	
	public SequenceGameGUI(SequenceGame game) {
		
		Container contentPane = getContentPane();
        contentPane.setLayout(null);
        
        //get window size 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        
        //create a border style
        Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK);
        
        
        //set panels
        int x1 = (int)(screenWidth*0.05), y1 = (int)(screenHeight*0.01), w1 = (int)(screenWidth*0.7), h1 = (int)(screenHeight*0.8);
        int y2 = (int)(screenHeight*0.03) + (int)(screenHeight*0.8), h2 = (int)(screenHeight*0.15);
        
        boardPanel = new JPanel(new GridLayout(10, 10));
        boardPanel.setBounds(x1, y1, w1, h1);
        boardPanel.setBorder(blackLineBorder);
        contentPane.add(boardPanel);
        
        handPanel = new JPanel(new FlowLayout());
        handPanel.setBounds(x1, y2, w1, h2);
        handPanel.setBorder(blackLineBorder);
        contentPane.add(handPanel);
        
        playerPanel = new JPanel();
        playerPanel.setBounds((int)(screenWidth*0.07) + (int)(screenWidth*0.70), (int)(screenHeight*0.01), (int)(screenWidth*0.15), (int)(screenHeight*0.8));
        playerPanel.setBorder(blackLineBorder);
        contentPane.add(playerPanel);
        
        deckPanel = new JPanel();
        deckPanel.setBounds((int)(screenWidth*0.07) + (int)(screenWidth*0.70), (int)(screenHeight*0.03) + (int)(screenHeight*0.8), (int)(screenWidth*0.15), (int)(screenHeight*0.15));
        deckPanel.setBorder(blackLineBorder);
        deckPanel.setLayout(new OverlayLayout(deckPanel));
        contentPane.add(deckPanel);
        
        //calculate dimension for each card and token
        int bw = boardPanel.getWidth()/10;
        int bh = boardPanel.getHeight()/10;
        int tbw = (int)(bw/1.2);
        int tbh = (int)(bh/1.2);
        
        //set tokens' size
        Image Rimg = redToken.getImage();
		Image RnewImg = Rimg.getScaledInstance(tbw, tbh, java.awt.Image.SCALE_SMOOTH);
		redToken = new ImageIcon(RnewImg);

        Image Bimg = blueToken.getImage();
		Image BnewImg = Bimg.getScaledInstance(tbw, tbh, java.awt.Image.SCALE_SMOOTH);
		blueToken = new ImageIcon(BnewImg);
		
        Image Gimg = greenToken.getImage();
		Image GnewImg = Gimg.getScaledInstance(tbw, tbh, java.awt.Image.SCALE_SMOOTH);
		greenToken = new ImageIcon(GnewImg);
        
		//add cards to the board 
        for(int i=0; i<10; i++)
        	for(int j=0; j<10; j++) {
        		//create a panel for both card and token (to be added to boardPanel)
        		JPanel grid = new JPanel();
        		grid.setLayout(new OverlayLayout(grid));
        		
        		//create a button for each card on the board
        		SButton b = cardButtons[i][j] = new SButton();
        		
        		//create a button for each token on the board
        		SButton t = tokenButtons[i][j] = new SButton();
        		
        		
        		//***TOKEN BUTTON
        		//assign the unique i and j value (board position) to each button
        		t.i = i; t.j = j;
        		//set action for TOKEN button
        		t.addActionListener(
        				new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						//if ONE-eyed jack is played
        						//0(a). disable all cards in hand
        						for (HashMap.Entry<Integer, JButton> mapElement : ((HumanSequencePlayer) (game.currentPlayer)).getHandMap().entrySet()) { 
        							mapElement.getValue().setEnabled(false);
        						}
        						//0(b). enable card deck for drawing a new card
        						deckButton.setEnabled(true);
        						//1. reset all disabled card icons
        						makeAllDisabledCardsNormal();
        						//2. set token icon to null AND update board[][]
        						t.setIcon(null);
        						game.board[t.i][t.j] = ' ';
        						//3. disable all token buttons
								for(int i=0; i<10; i++)
									for(int j=0; j<10; j++)
											tokenButtons[i][j].setEnabled(false);
								//4. remove one eyed jack from hand
        						ASequenceCard card = new ASequenceCard(jackNumber);
        						game.currentPlayer.hand.remove(removedHandCardIndex);
        						if(game.currentPlayer instanceof HumanSequencePlayer) {
	        						handPanel.remove(
	        								((HumanSequencePlayer) game
	        										.currentPlayer)
	        											.getHandMap().get(card.getCardNumber()));
	        						handPanel.repaint();
        						}//end of inner if
        						//5. update SequenceGame
        						game.lastPlayedX = t.i;
        						game.lastPlayedY = t.j;
        						game.lastPlayedCard = card;
        						game.resume();
        					}
        				});
        		t.setEnabled(false);
        		
        		
        		//***CARD BUTTON
        		//assign the unique i and j value (board position) to each button
        		b.i = i; b.j = j;
        		
        		//retrieve the ImageIcon from array and create new Image based on resolutions
        		Image img = normalCardImages[i][j].getImage();
        		Image newImg = img.getScaledInstance(bw, bh, java.awt.Image.SCALE_SMOOTH);
        		ImageIcon newIcon = new ImageIcon(newImg);
        		b.setIcon(newIcon);
        		b.setDisabledIcon(newIcon);
        		
        		//set action for CARD button
        		b.addActionListener(
        				new ActionListener() {
        					public void actionPerformed(ActionEvent e) {
        						//when an eligible card is played,
        						//0(a). disable all cards in hand
        						for (HashMap.Entry<Integer, JButton> mapElement : ((HumanSequencePlayer) (game.currentPlayer)).getHandMap().entrySet()) { 
        							mapElement.getValue().setEnabled(false);
        						}
        						//0(b). enable card deck for drawing a new card
        						deckButton.setEnabled(true);
        						//1. update board[][]
        						char color = game.currentPlayerColor; 
        						game.board[b.i][b.j] = color;
        						//2. display player token
        						switch(color) {
        							case 'r': t.setIcon(redToken); t.setDisabledIcon(redToken); break;
        							case 'b': t.setIcon(blueToken); t.setDisabledIcon(blueToken); break;
        							case 'g': t.setIcon(greenToken); t.setDisabledIcon(greenToken); break;
        						}
        						//3. reset all disabled icons
        						makeAllDisabledCardsNormal();
        						//n. do different things based on whether wild jack is played
        						if(twoEyedJackIsPlayed) {
        							//4. disable all cards
        							disableAllCards();
        							//5. remove wild jack from hand
            						ASequenceCard card = new ASequenceCard(jackNumber);
            						game.currentPlayer.hand.remove(removedHandCardIndex);
            						if(game.currentPlayer instanceof HumanSequencePlayer) {
    	        						handPanel.remove(
    	        								((HumanSequencePlayer) game
    	        										.currentPlayer)
    	        											.getHandMap().get(card.getCardNumber()));
    	        						handPanel.repaint();
            						}//end of inner if
            						//6. reset jack fields
            						twoEyedJackIsPlayed = false;
            						//7. update lastPlayedCard
            						game.lastPlayedCard = card;
        						}
        						else {
        							//a non-jack card is played
        							//4. disable the two option cards
            						cardButtons[b.x1][b.y1].setEnabled(false);
            						cardButtons[b.x2][b.y2].setEnabled(false);
            						//5. remove the card from hand
            						ASequenceCard card = new ASequenceCard(b.cardNumber);
            						game.currentPlayer.hand.remove(removedHandCardIndex);
            						if(game.currentPlayer instanceof HumanSequencePlayer) {
    	        						handPanel.remove(
    	        								((HumanSequencePlayer) game
    	        										.currentPlayer)
    	        											.getHandMap().get(card.getCardNumber()));
    	        						handPanel.repaint();
            						}//end of inner if statement
            						//6. update lastPlayedCard
            						game.lastPlayedCard = card;
        						}//end of else
        						
        						game.lastPlayedX = b.i;
        						game.lastPlayedY = b.j;
        						game.resume();
        					}//end of actionPerformed
        				});
        		b.setEnabled(false);
        		
        		//update the array with new card images for this resolution
        		normalCardImages[i][j] = newIcon;
        		

        		//add this token button to the grid panel
        		t.setBorder(null);
        		t.setContentAreaFilled(false);
        		t.setAlignmentX(CENTER_ALIGNMENT);
        		t.setAlignmentY(CENTER_ALIGNMENT);
        		grid.add(t);
        		
        		//add this card button to the panel specific to this grid
        		b.setOpaque(false);
        		b.setAlignmentX(CENTER_ALIGNMENT);
        		b.setAlignmentY(CENTER_ALIGNMENT);
        		grid.add(b);
        		
        		//add this grid panel to the board panel
        		boardPanel.add(grid);
        		
        		//resize the grey card images for future use
        		Image greyImg = greyCardImages[i][j].getImage();
        		Image newGreyImg = greyImg.getScaledInstance(bw, bh, java.awt.Image.SCALE_SMOOTH);
        		ImageIcon newGreyIcon = new ImageIcon(newGreyImg);
        		greyCardImages[i][j]= newGreyIcon;

        		//resize the hand card images for future use
        		Image handImg = handCardImages[i][j].getImage();
        		Image newHandImg = handImg.getScaledInstance(bh, bw, java.awt.Image.SCALE_SMOOTH);
        		ImageIcon newHandIcon = new ImageIcon(newHandImg);
        		handCardImages[i][j]= newHandIcon;
        	}
        
        //disable corners
      		cardButtons[0][0].setEnabled(false);
      		cardButtons[0][9].setEnabled(false);
      		cardButtons[9][0].setEnabled(false);
      		cardButtons[9][9].setEnabled(false);
        
        //set buttons
      	//quit
        JButton quit = new JButton("Quit");
        quit.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				System.exit(0);
        			}
        		});
        quit.setSize(100, 100);
        playerPanel.add(quit);
        
        //deck button
        //resize icon
        ImageIcon deckIcon = new ImageIcon(getClass().getResource("cardBack.png"));
		Image deckImg = deckIcon.getImage();
		Image newDeckImg = deckImg.getScaledInstance(bw, bh, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newDeckIcon = new ImageIcon(newDeckImg);
		deckButton = new JButton(newDeckIcon);
		deckButton.setBorder(BorderFactory.createEmptyBorder());
		deckButton.setContentAreaFilled(false);
		deckButton.setAlignmentX(CENTER_ALIGNMENT);
		deckButton.setAlignmentY(CENTER_ALIGNMENT);
        deckButton.addActionListener(
        		new ActionListener() {
        			public void actionPerformed(ActionEvent e) {
        				//disable to avoid drawing again
        				deckButton.setEnabled(false);
        				
        				ASequenceCard c;
        				
        				if(isRecycled) {
        					//do NOT end turn
            				//draw a new card to add to player's hand (LinkedList)
            				c = game.Chris.dealCard(game.currentPlayer);
            				//display the new card
            				game.displayNewHandCard(c, (HumanSequencePlayer)game.currentPlayer);
            				//remove recycle card
        					game.currentPlayer.hand.remove(recycledCard);
        					handPanel.remove(
    								((HumanSequencePlayer) game
    										.currentPlayer)
    											.getHandMap().get(recycledCard.getCardNumber()));
        					((HumanSequencePlayer) game
        									.currentPlayer)
        										.getHandMap()
        											.get(c.getCardNumber())
        												.setEnabled(true);
    						handPanel.repaint();
    						
    						//isRecycled = false;
        				}
        				else {
	        				//end turn
            				
            				//draw a new card to add to player's hand (LinkedList)
            				c = game.Chris.dealCard(game.currentPlayer);
            				//display the new card
            				game.displayNewHandCard(c, (HumanSequencePlayer)game.currentPlayer);
            				
							game.resume();
        				}
        			}//end of actionPerformed
        		});
        deckButton.setEnabled(false);
        deckPanel.add(deckButton);
        
        
        	
        //set size and name
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setTitle("Sequence Game");        
        
	}//end of constructor
	
	
	void makeAllDisabledCardsNormal() {
		//make all card buttons' icon normal
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
				cardButtons[i][j].setDisabledIcon(normalCardImages[i][j]);
	}
	
	void makeAllDisabledCardsGrey(int option) {
		switch(option) {
			case 0:
				//make all card buttons' DisabledIcon grey
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++) {
						cardButtons[i][j].setDisabledIcon(greyCardImages[i][j]);
					}
				break;
			case 1:
				//disable all cards and make all card buttons' icon grey
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++) {
						cardButtons[i][j].setDisabledIcon(greyCardImages[i][j]);
						cardButtons[i][j].setEnabled(false);
					}
				break;
		}
	}
	
	void enableAllCards(SequenceGame game, int option) {
		switch(option) {
			case 0:
				//enable all cards on the board
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++) {
						cardButtons[i][j].setEnabled(true);
					}
				break;
			case 1:
				//enable all AVAILABLE cards on the board
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++) {
						if(game.board[i][j]==' ')
							cardButtons[i][j].setEnabled(true);
						else
							cardButtons[i][j].setDisabledIcon(greyCardImages[i][j]);
					}
				break;
		}

		//disable corners
		cardButtons[0][0].setEnabled(false);
		cardButtons[0][9].setEnabled(false);
		cardButtons[9][0].setEnabled(false);
		cardButtons[9][9].setEnabled(false);
	}
	
	void disableAllCards() {
		//disable all cards on the board
				for(int i=0; i<10; i++)
					for(int j=0; j<10; j++) {
						cardButtons[i][j].setEnabled(false);
					}
	}



}//end of class
