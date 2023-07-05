package Meta;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BlackJack extends JFrame {
	
	private int x=1200;
	private int y=800;
	private int cardBoxX=50;
	private int cardBoxY=50;
	private int cardBoxW=800;
	private int cardBoxH=400;
	private int informBoxX = cardBoxX+cardBoxW+50;
	private int informBoxY = cardBoxY;
	private int informBoxW = 250;
	private int informBoxH = 450;
	private int buttonBoxX = informBoxX;
	private int buttonBoxY = informBoxY+informBoxH+50;
	private	int buttonBoxW = informBoxW;
	private int buttonBoxH = 200;
	private int cardspace = 10;
	private int cardW=cardBoxW/6;
	private int hitCard =0;
	private int playermax=0;
	private int playermin=0;
	private int dealerScore=0;
	private int playerscore;
	private int bet=100;
	private int nowmoney=Meta_Frame.money;
	private int rand = new Random().nextInt(52);

	private String whoswin="";
	private	String playmoreq = "Play More?";
		
	private	boolean hitdoublestay=true;
	private	boolean hitstay=false;
	private	boolean stay=false;
	private	boolean playmore=false;
	private	boolean nothavemoney=false;
		
	private	ArrayList<BlackJackCard> allCards = new ArrayList<BlackJackCard>();
	private	ArrayList<BlackJackCard> playerCards = new ArrayList<BlackJackCard>();
	private	ArrayList<BlackJackCard> dealerCards = new ArrayList<BlackJackCard>();
		
	private	Image cardImg [][] = new Image[4][13];
	private	Image playerCardImage[] = new Image[10];
	private	Image dealerCardImage[] = new Image[10];
	private	Image OpenCardImage;
		
	private Color back = new Color(39,119,20);
	private Color ButtonColor = new Color(204,204,0);
		
	private	Font ButtonFont = new Font("Times new Roman",Font.PLAIN,30);
		
	private	JButton bHit =  new JButton();
	private	JButton bdouble =  new JButton();
	private	JButton bStay =  new JButton();
	private	JButton bYes =  new JButton();
	private	JButton bNo =  new JButton();
		
		public BlackJack(int m) {
			if(nowmoney<200) {
				hitdoublestay=false;
				hitstay=true;
			}
			nowmoney-=100;
			this.setSize(x,y);
			this.setTitle("BlackJack");
			this.setVisible(true);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Board board = new Board();
			this.setContentPane(board);
			board.setLayout(null); 
			
			bHit.setBounds(informBoxX+65, informBoxY+40, 120, 80);
			bHit.setBackground(ButtonColor);
			bHit.setText("HIT");
			bHit.setFont(ButtonFont);
			bHit.addActionListener(new ActionListener() {            
				public void actionPerformed(ActionEvent e) {
					while(true) {
						rand = new Random().nextInt(52);
						while(allCards.get(rand).cardUsed) {
						rand = new Random().nextInt(52);
						}
						if(allCards.get(rand).cardUsed==false) {
							playerCards.add(allCards.get(rand));
							allCards.get(rand).cardUsed=true;
							playerCardImage[hitCard+2] = allCards.get(rand).getImage();
							hitCard++;
							break;
						}
					}
					hitdoublestay=false;
					hitstay=true;
				}});
			board.add(bHit);
			
			bdouble.setBounds(informBoxX+65, informBoxY+180, 120, 80);
			bdouble.setBackground(ButtonColor);
			bdouble.setText("x2");
			bdouble.setFont(ButtonFont);
			bdouble.addActionListener(new ActionListener() {            
				public void actionPerformed(ActionEvent e) {
					bet=200;
					nowmoney-=100;
					while(true) {
						rand = new Random().nextInt(52);
						while(allCards.get(rand).cardUsed) {
						rand = new Random().nextInt(52);
						}
						if(allCards.get(rand).cardUsed==false) {
							playerCards.add(allCards.get(rand));
							allCards.get(rand).cardUsed=true;
							playerCardImage[2] = allCards.get(rand).getImage();
							break;
						}
					}
					hitdoublestay=false;
					stay=true;
				}});
			board.add(bdouble);
			
			bStay.setBounds(informBoxX+65, informBoxY+300, 120, 80);
			bStay.setBackground(ButtonColor);
			bStay.setText("STAY");
			bStay.setFont(ButtonFont);
			bStay.addActionListener(new ActionListener() {            
				public void actionPerformed(ActionEvent e) {
					dealerCardImage[1] = OpenCardImage;
					int i=0;
					while(dealerScore<17) {
						rand = new Random().nextInt(52);
						while(allCards.get(rand).cardUsed==true) {
							rand = new Random().nextInt(52);
						}
							if(allCards.get(rand).cardUsed==false) {
								dealerCards.add(allCards.get(rand));
								allCards.get(rand).cardUsed=true;
								dealerCardImage[i+2] = allCards.get(rand).getImage();
								i++;
							}
							blackjackscore();
						}
						gameOver();	
					if(bet==100) {
					if(whoswin.equals("player"))
						nowmoney+=200;
					else if(whoswin.equals("draw"))
						nowmoney+=100;
					}
					else if(bet==200) {
					if(whoswin.equals("player"))
						nowmoney+=400;
					else if(whoswin.equals("draw"))
						nowmoney+=200;
					}

					hitdoublestay=false;
					hitstay=false;
					stay=false;
					playmore=true;
					if(nowmoney<=50) {
						playmore=false;
						nothavemoney=true;
						bNo.setText("Okay");
					}
				}});
			board.add(bStay);
			
			bYes.setBounds(buttonBoxX+65, buttonBoxY+10, 120, 80);
			bYes.setBackground(ButtonColor);
			bYes.setText("YES");
			bYes.setFont(ButtonFont);
			bYes.addActionListener(new ActionListener() {            
				public void actionPerformed(ActionEvent e) {
					playmore=false;
					hitdoublestay=true;
					whoswin="";
					hitCard=0;
					spread();
					bet=100;
					nowmoney-=100;
					if(nowmoney<200) {
						hitdoublestay=false;
						hitstay=true;
					}
				}});
			board.add(bYes);
			
			bNo.setBounds(buttonBoxX+65, buttonBoxY+100, 120, 80);
			bNo.setBackground(ButtonColor);
			bNo.setText("NO");
			bNo.setFont(ButtonFont);
			bNo.addActionListener(new ActionListener() {            
				public void actionPerformed(ActionEvent e) {
					Meta_Frame.money=nowmoney;
					setVisible(false);
					Meta_Frame mf = new Meta_Frame(nowmoney);
				}});
			board.add(bNo);
			
			int idsetter = 0;
			for(int s=0;s<4;s++) {
				for(int n=0;n<13;n++ ) {
					if(s==0) {
						cardImg [s][n] = new ImageIcon("img/clubs-"+(n+2)+".png").getImage();
					}
					else if(s==1) {
						cardImg [s][n] = new ImageIcon("img/hearts-"+(n+2)+".png").getImage();
					}
					else if(s==2) {
						cardImg [s][n] = new ImageIcon("img/diamonds-"+(n+2)+".png").getImage();
					}
					else {
						cardImg [s][n] = new ImageIcon("img/spades-"+(n+2)+".png").getImage();
					}
					allCards.add(new BlackJackCard(n+2,idsetter,cardImg[s][n]));
					idsetter++;
				}
			}
			spread();
	}
		
		public void spread() {
			playerCards.clear();
			dealerCards.clear();
			
			for(int i=0;i<52;i++) 
				allCards.get(i).cardUsed=false;
			
			for(int i=0;i<2;) {
				while(true) {
					while(allCards.get(rand).cardUsed) {
						rand = new Random().nextInt(52);
					}
					if(allCards.get(rand).cardUsed==false) {
						playerCards.add(allCards.get(rand));
						allCards.get(rand).cardUsed=true;
						playerCardImage[i] = allCards.get(rand).getImage();
						}
					rand = new Random().nextInt(52);
					while(allCards.get(rand).cardUsed) {
						rand = new Random().nextInt(52);
					}
					if(allCards.get(rand).cardUsed==false) {
							dealerCards.add(allCards.get(rand));
							allCards.get(rand).cardUsed=true;
							dealerCardImage[i] = allCards.get(rand).getImage();
					}
					rand = new Random().nextInt(52);
					i++;
					break;
			}}
			
			OpenCardImage = dealerCardImage[1];
			dealerCardImage[1] = new ImageIcon("img/back.png").getImage();

		}
		
		public void blackjackscore() {
			int total=0;
			boolean Ace =false;
			for(BlackJackCard c : playerCards) {
				if(c.name.equals("Ace")&&total<=10) 
					Ace=true;
				total+=c.value;
			}
			playermax= total;
			playermin = total;
			if(Ace==true) 
				playermax+=10;
			
			total=0;
			Ace =false;
			for(BlackJackCard c : dealerCards) {
				if(c.name.equals("Ace")&&total<=10) 
					total+=10;
				total+=c.value;
			}
			dealerScore= total;
		}
		
		private void gameOver() {
			if(playermax>21) {
				playerscore = playermin;
				if(playerscore>21&&dealerScore>21)
					whoswin="draw";
				else if(playerscore>21)
					whoswin="dealer";
				else if(dealerScore<22&&dealerScore>playerscore)
					whoswin="dealer";
				else if(dealerScore==playerscore)
					whoswin="draw";
				else 
					whoswin="player";
				}
			
			else if(playermax<21) {
				playerscore=playermax;
				if(playerscore>21&&dealerScore>21)
					whoswin="draw";
				else if(dealerScore>21)
					whoswin="player";
				else if(dealerScore<22&&dealerScore>playerscore)
					whoswin="dealer";
				else if(dealerScore==playerscore)
					whoswin="draw";
				else
					whoswin="player";
			}
			
			else if(playermax==21){ 
				playerscore = playermax;
				if(dealerScore==playerscore)
					whoswin="draw";
				else 
					whoswin="player";
			}
		}
		
		public void blackjackbuttonShow() {
			
			if(hitdoublestay==true&&nowmoney>=200) {
				bHit.setVisible(true);
				bStay.setVisible(true);
				bdouble.setVisible(true);
				bYes.setVisible(false);
				bNo.setVisible(false);
				
			}
			else if(hitstay == true) {
				bHit.setVisible(true);
				bStay.setVisible(true);
				bdouble.setVisible(false);
				bYes.setVisible(false);
				bNo.setVisible(false);
			}
			else if (stay==true) {
				bHit.setVisible(false);
				bStay.setVisible(true);
				bdouble.setVisible(false);
				bYes.setVisible(false);
				bNo.setVisible(false);
			}
			else if(playmore==true) {
				bHit.setVisible(false);
				bStay.setVisible(false);
				bdouble.setVisible(false);
				bYes.setVisible(true);
				bNo.setVisible(true);
			}
			else if (nothavemoney==true) {
				bHit.setVisible(false);
				bStay.setVisible(false);
				bdouble.setVisible(false);
				bYes.setVisible(false);
				bNo.setVisible(true);
			}
			
		}

		private class Board extends JPanel {
			
			public void paintComponent(Graphics g) {
			
				g.setColor(back);
				g.fillRect(0, 0, x, y);
				
				g.setColor(Color.black);
				g.drawRect(cardBoxX, cardBoxY, cardBoxW, cardBoxH);
				g.drawRect(cardBoxX, cardBoxY+cardBoxH+100, cardBoxW, cardBoxH-200);
				g.drawRect(informBoxX, informBoxY, informBoxW, informBoxH);
				g.drawRect(buttonBoxX, buttonBoxY, buttonBoxW, buttonBoxH);
				g.setFont(ButtonFont);
				
				if(playmore==true) {
					g.drawString(playmoreq, buttonBoxX+60, buttonBoxY-10);
					g.drawString(Integer.toString(dealerScore), cardBoxX+250, cardBoxY+cardBoxH+170);
				}
				if(nothavemoney==true) {
					g.drawString(Integer.toString(dealerScore), cardBoxX+250, cardBoxY+cardBoxH+170);
					g.drawString("Get Out!", buttonBoxX+60, buttonBoxY-10);
				}
				g.drawString("Now Bet: "+Integer.toString(bet), cardBoxX+50, cardBoxY+cardBoxH+50);
				g.drawString("Now Money: "+Integer.toString(nowmoney), cardBoxX+450,  cardBoxY+cardBoxH+50);
				g.drawString("Dealer Card: ", cardBoxX+50, cardBoxY+cardBoxH+170);
				g.drawString("Your Card(min/max): ", cardBoxX+50, cardBoxY+cardBoxH+250);
				g.drawString(Integer.toString(playermin)+" / "+Integer.toString(playermax), cardBoxX+350, cardBoxY+cardBoxH+250);
				g.drawString("The winner is?  "+whoswin, cardBoxX+500, cardBoxY+cardBoxH+210);
				

				int index=0;
				for(BlackJackCard c:playerCards) {
					g.drawImage(playerCardImage[index], cardBoxX+index*cardW+cardspace, cardBoxY+cardspace+200, null, this);
					index++;
				}
				index=0;
				for(BlackJackCard c : dealerCards) {
					g.drawImage(dealerCardImage[index], cardBoxX+index*cardW+cardspace, cardBoxY+cardspace, null, this);
					index++;
				}
			}
		}
}