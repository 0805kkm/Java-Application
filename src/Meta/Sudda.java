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

public class Sudda extends JFrame {
	private int x=1200;
	private int y=800;
	private int nowbet=200;
	private int bet=0;
	private int playerBet=0;
	private int nowmoney=Meta_Frame.money;
	private int rand = new Random().nextInt(20);
	private int score = 0;
	private int playerScore=0;
	private int computerScore=0;
	private int callnum=0;
	private int half=0;

	
	private String whoswin="";
	private String playerResult = "";
	private String computerResult = "";
	private String playerAct="";
	private String computerAct="";
	
	private ArrayList<SuddaCard> allCards = new ArrayList<SuddaCard>();
	private ArrayList<SuddaCard> playerCards = new ArrayList<SuddaCard>();
	private ArrayList<SuddaCard> computerCards = new ArrayList<SuddaCard>();

	private Image cardImg [][] = new Image[10][2];
	private Image playerCardImage[] = new Image[2];
	private Image computerCardImage[] = new Image[2];
	private Image openCardImage[] = new Image[3];	
	private Color back = new Color(39,119,20);
	private Color ButtonColor = new Color(204,204,0);
	
	private Font ButtonFont = new Font("맑은 고딕",Font.BOLD,25);
	
	private JButton bHalf =  new JButton();
	private JButton bDeal =  new JButton();
	private JButton bDie =  new JButton();
	private JButton bCheck =  new JButton();
	private JButton bDraw =  new JButton();
	private JButton bAll = new JButton();
	private	JButton bYes =  new JButton();
	private	JButton bNo =  new JButton();
	
	private boolean playerTurn =true;
	private boolean playerFirst=true;
	private boolean lastBet=false;
	private boolean endGame = false;
	private boolean draw=false;
	private boolean die=false;
	private boolean allIn=false;
	
	public Sudda(int m) {
		nowmoney-=100;
		this.setSize(x,y);
		this.setTitle("Sudda");
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board board = new Board();
		this.setContentPane(board);
		board.setLayout(null); 
	
		bHalf.setBounds(870, 500, 100, 100);
		bHalf.setBackground(ButtonColor);
		bHalf.setText("하프");
		bHalf.setFont(ButtonFont);
		bHalf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerAct="하프";
				if((nowbet/2)>=nowmoney) {
					bet=nowmoney;
					allIn=true;
				}
				else
					bet=(nowbet/2)-((nowbet/2)%50);
				nowbet+=bet;
				nowmoney-=bet;
				playerBet+=bet;
				playerTurn=false;
				playerFirst=false;
				if(lastBet) {
					result();
				}
			}});
		board.add(bHalf);
		
		bDeal.setBounds(980, 500, 100, 100);
		bDeal.setBackground(ButtonColor);
		bDeal.setText("콜");
		bDeal.setFont(ButtonFont);
		bDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerAct="콜";
				if(bet>=nowmoney) {
					bet=nowmoney;
					allIn=true;
				}
				nowbet+=bet;
				nowmoney-=bet;
				playerBet+=bet;
				playerCardImage[1]=openCardImage[0];
				if(lastBet) 
					result();
				lastBet=true;
				playerTurn=false;
			}});
		board.add(bDeal);
		
		bDie.setBounds(870, 610, 100, 100);
		bDie.setBackground(ButtonColor);
		bDie.setText("다이");
		bDie.setFont(ButtonFont);
		bDie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				die=true;
				endGame=true;
				playerFirst=false;
				playerTurn=false;
			}});
		board.add(bDie);
		
		bCheck.setBounds(980, 610, 100, 100);
		bCheck.setBackground(ButtonColor);
		bCheck.setText("체크");
		bCheck.setFont(ButtonFont);
		bCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerAct="체크";
				bet=0;
				playerBet+=bet;
				playerTurn = false;
				playerFirst=false;
				
			
			}});
		board.add(bCheck);
		
		bDraw.setBounds(930,580,100,100);
		bDraw.setBackground(ButtonColor);
		bDraw.setText("승부!");
		bDraw.setFont(ButtonFont);
		bDraw.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				while(whoswin.equals("무승부")||whoswin.equals("사구파토")) {
					spread();
					playerResult = suddaScore(playerCards);
					playerScore=score;
					computerResult = suddaScore(computerCards);
					computerScore=score;
					gameOver();
					if(whoswin.equals("나")) {
						nowmoney+=nowbet;
						playerTurn=true;
						playerFirst=true;
						endGame=true;
					}
					else if(whoswin.equals("상대")) {
						playerTurn=false;
						playerFirst=false;
						endGame=true;
					}
				}
				draw=false;
			}});
		board.add(bDraw);
		

		bAll.setBounds(930,580,100,100);
		bAll.setBackground(ButtonColor);
		bAll.setText("올인!");
		bAll.setFont(ButtonFont);
		bAll.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				result();
			}});
		board.add(bAll);
		
		bYes.setBounds(870,570,100,100);
		bYes.setBackground(ButtonColor);
		bYes.setText("네");
		bYes.setFont(ButtonFont);
		bYes.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				replay();
			}});
		board.add(bYes);
		
		bNo.setBounds(980,570,100,100);
		bNo.setBackground(ButtonColor);
		bNo.setText("아뇨");
		bNo.setFont(ButtonFont);
		bNo.addActionListener(new ActionListener() {            
			public void actionPerformed(ActionEvent e) {
				Meta_Frame.money=nowmoney;
				setVisible(false);
				Meta_Frame mf = new Meta_Frame(nowmoney);
			}});
		board.add(bNo);
		
		
		int idsetter = 0;
		for(int s=0;s<10;s++) {
			for(int n=0;n<2;n++ ) {
				if(n==0) {
					cardImg [s][n] = new ImageIcon("img/"+(s+1)+".png").getImage();
				}
				else if(n==1) {
					cardImg [s][n] = new ImageIcon("img/"+(s+1)+".5.png").getImage();
				}
				allCards.add(new SuddaCard(s+1,idsetter,cardImg[s][n]));
				idsetter++;
			}
		}
		spread();
		playerResult = suddaScore(playerCards);
		playerScore=score;
		computerResult = suddaScore(computerCards);
		computerScore=score;
		gameOver();
	}
	
	private void replay() {
		nowmoney-=100;
		nowbet=200;
		bet=0;
		half=0;
		playerBet=0;
		die=false;
		lastBet=false;
		endGame=false;
		allIn=false;
		computerAct="";
		playerAct="";
		spread();
		playerResult = suddaScore(playerCards);
		playerScore=score;
		computerResult = suddaScore(computerCards);
		computerScore=score;
		gameOver();
	
	}
	private void result() {
			if(whoswin.equals("나")) {
				nowmoney+=nowbet;
				endGame=true;
				playerTurn=true;
				playerFirst=true;
			}
			else if(whoswin.equals("상대")) {
				endGame=true;
				playerTurn=false;
				playerFirst=false;
			}
			else if(whoswin.equals("무승부")||whoswin.equals("사구파토")) {
				endGame=false;
				lastBet=false;
				draw=true;
			}
		
	}
	
	
	private void spread() {
		playerCards.clear();
		computerCards.clear();
		for(int i=0;i<20;i++) 
			allCards.get(i).cardUsed=false;
		
		for(int i=0;i<2;i++) {
			while(true) {
				while(allCards.get(rand).cardUsed) {
					rand = new Random().nextInt(20);
				}
				if(allCards.get(rand).cardUsed==false) {
					playerCards.add(allCards.get(rand));
					allCards.get(rand).cardUsed=true;
					playerCardImage[i] = allCards.get(rand).getImage();
					}
				while(allCards.get(rand).cardUsed) {
					rand = new Random().nextInt(20);
				}
				if(allCards.get(rand).cardUsed==false) {
						computerCards.add(allCards.get(rand));
						allCards.get(rand).cardUsed=true;
						computerCardImage[i] = allCards.get(rand).getImage();
				}
				rand = new Random().nextInt(20);
				openCardImage[i+1] = computerCardImage[i];
				computerCardImage[i] = new ImageIcon("img/pae.png").getImage();
				break;
			}
			openCardImage[0]=playerCardImage[1];
			playerCardImage[1] = new ImageIcon("img/pae.png").getImage();
			
		}
	}
	
	public void suddaButtonShow() {
		if(endGame) {
			bHalf.setVisible(false);
			bDie.setVisible(false);
			bCheck.setVisible(false);
			bDeal.setVisible(false);
			bYes.setVisible(true);
			bNo.setVisible(true);
			bDraw.setVisible(false);
			bAll.setVisible(false);
		}	
		else if(draw) {
			bHalf.setVisible(false);
			bDie.setVisible(false);
			bCheck.setVisible(false);
			bDeal.setVisible(false);
			bYes.setVisible(false);
			bNo.setVisible(false);
			bDraw.setVisible(true);
			bAll.setVisible(false);
		}
		else if(allIn) {
			bHalf.setVisible(false);
			bDie.setVisible(false);
			bCheck.setVisible(false);
			bDeal.setVisible(false);
			bYes.setVisible(false);
			bNo.setVisible(false);
			bDraw.setVisible(false);
			computer();
			bAll.setVisible(true);
		}
		else if(playerTurn) {
			if(playerFirst) {
			bHalf.setVisible(true);
			bDie.setVisible(true);
			bCheck.setVisible(true);
			bDeal.setVisible(false);
			bYes.setVisible(false);
			bNo.setVisible(false);
			bDraw.setVisible(false);
			bAll.setVisible(false);
			}
			else if(playerFirst==false){
				bHalf.setVisible(true);
				bDie.setVisible(true);
				bCheck.setVisible(false);
				bDeal.setVisible(true);
				bYes.setVisible(false);
				bNo.setVisible(false);
				bDraw.setVisible(false);
				bAll.setVisible(false);
			}
		}
		else if (playerTurn==false) {
			bHalf.setVisible(false);
			bDie.setVisible(false);
			bCheck.setVisible(false);
			bDeal.setVisible(false);
			bYes.setVisible(false);
			bNo.setVisible(false);
			bDraw.setVisible(false);
			bAll.setVisible(false);
			if(endGame==false)
				computer();
			else ;
			
		}
	}
	
	private void computer() {
		if(playerTurn==false) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			callnum=0;
			SuddaCard cf = computerCards.get(0);
			SuddaCard cs = computerCards.get(1);
			if(playerAct=="콜"||computerAct=="콜") {
				callnum++;
				lastBet=true;
			}
				if(callnum==0) {
					if(cf.month==5||cf.month==6||cf.month==2||cf.month==7||cf.month==8||cf.month==3) {
						if(playerAct.equals("")) {
							computerAct="체크";
							bet=0;
						}
						else {
							if(playerAct.equals("체크")) {
								computerAct="콜";
								playerFirst=true;
							}
							else if((cf.id==12||cf.id==14||cf.id==4)&&playerAct.equals("하프")) {
								computerAct="콜";
								nowbet+=bet;
								playerFirst=true;
								playerCardImage[1]=openCardImage[0];
							}
							else if(playerAct.equals("하프")) {
								computerAct="다이";
								whoswin="나";
								nowmoney+=nowbet;
								die=true;
								endGame=true;
								playerFirst=true;
							}
						}
						playerTurn=true;
					}
					else if(cf.month==10||cf.month==9||cf.month==4||cf.month==1) {
						if(playerAct.equals("")) {
							computerAct="하프";
							bet=(nowbet/2)-((nowbet/2)%50);
							nowbet+=bet;
						}
						else if(playerAct.equals("체크")) {
							computerAct="하프";
							bet=(nowbet/2)-((nowbet/2)%50);
							nowbet+=bet;
						}
						else if((cf.month==10||cf.month==9)&& playerAct.equals("하프")) {
							if(half<1) {
								computerAct="하프";
								bet=(nowbet/2)-((nowbet/2)%50);
								nowbet+=bet;
								half++;
								}
								else {
									computerAct="콜";
									nowbet+=bet;
									playerFirst=true;
									playerCardImage[1]=openCardImage[0];
								}
							}
						else if((cf.month==4)&& playerAct.equals("하프")) {
							if(allIn);
							if(half<1) {
							computerAct="하프";
							bet=(nowbet/2)-((nowbet/2)%50);
							nowbet+=bet;
							half++;
							}
							else {
								computerAct="콜";
								nowbet+=bet;
								playerFirst=true;
								playerCardImage[1]=openCardImage[0];
							}
						}
						else if((cf.month==1)&& playerAct.equals("하프")) {
							if(half<2) {
								computerAct="하프";
								bet=(nowbet/2)-((nowbet/2)%50);
								nowbet+=bet;
								half++;
								}
								else {
									computerAct="콜";
									nowbet+=bet;
									playerFirst=true;
									playerCardImage[1]=openCardImage[0];
								}
						}
						playerTurn=true;
					}}
				else if(callnum==1) {
						if(computerResult.equals("땡잡이")||computerResult.equals("암행어사")) {
							if(playerAct.equals("콜")) {
								if(nowbet==200) {
									computerAct="체크";
									nowbet+=bet;
									playerTurn=true;
								}
								else {
								computerAct="다이";
								whoswin="나";
								nowmoney+=nowbet;
								die=true;
								playerFirst=true;
								endGame=true;
								}
								playerTurn=true;
							}
							else if(playerAct.equals("하프")) {
								computerAct="콜";
								nowbet+=bet;
								result();
							}
							else if(playerAct.equals("체크")) {
								computerAct="다이";
								whoswin="나";
								nowmoney+=nowbet;
								die=true;
								playerTurn=true;
								playerFirst=true;
								endGame=true;
							}
								
						}
						else if(computerResult.equals("사구")||computerResult.equals("멍텅구리")) {
							if(playerAct.equals("콜")) {
								computerAct="체크";
								bet=0;
								playerTurn=true;
							}
							else if(computerAct.equals("콜")) {
								computerAct="콜";
								nowbet+=bet;
								result();
							}
						}
						else if(computerScore>=8) {
							if(playerAct.equals("콜")) {
								computerAct="하프";
								bet=(nowbet/2)-((nowbet/2)%50);
								nowbet+=bet;
								playerTurn=true;
							}
							else {
								computerAct="하프";
								bet=(nowbet/2)-((nowbet/2)%50);
								nowbet+=bet;
								result();
							}
						}
						else{
							if(nowbet==200&&playerAct.equals("콜")) {
								computerAct="체크";
								nowbet+=bet;
								playerTurn=true;
							}
							else if(nowbet==200) {
								computerAct="콜";
								nowbet+=bet;
								result();
							}
							else {
							computerAct="다이";
							whoswin="나";
							nowmoney+=nowbet;
							die=true;
							playerTurn=true;
							playerFirst=true;
							endGame=true;
							}
						}	
					}
				}
			
		}
	
	
	public void gameOver() {
		if(playerResult.equals("땡잡이")||computerResult.equals("땡잡이")) {
			if(playerScore>=16&&playerScore<=24)
				whoswin="상대";
			else if(computerScore>=16&&computerScore<=24)
				whoswin="나";
			else Count();
		}
		else if(playerResult.equals("암행어사")||computerResult.equals("암행어사")) {
			if(playerScore==26)
				whoswin="상대";
			else if(computerScore==26)
				whoswin="나";
			else Count();
		}
		else if(playerResult.equals("사구")) {
			if(computerScore<=15)
				whoswin="사구파토";
			else
				whoswin="상대";
		}
		else if(computerResult.equals("사구")) {
			if(playerScore<=15)
				whoswin="사구파토";
			else 
				whoswin="나";
		}
		else if(playerResult.equals("멍텅구리")) {
			if(computerScore<=24)
				whoswin="사구파토";
			else
				whoswin="상대";
		}
		else if(computerResult.equals("멍텅구리")) {
			if(playerScore<=24)
				whoswin="사구파토";
			else 
				whoswin="나";
		}
		else Count();
	}
	
	private void Count() {
		if(playerScore>computerScore) 
			whoswin="나";
		else if(playerScore==computerScore)
			whoswin="무승부";
		else if(playerScore<computerScore)
			whoswin="상대";
	}
	private String suddaScore(ArrayList<SuddaCard> cards) {
		int n=0; score=0; int firstm=0; int secondm=0; int firstid=0; int secondid=0; String result="";
		for( SuddaCard c :cards ) {
			if(n==0) {
				firstm=c.month;
				firstid=c.id;
			}
			else if(n==1) {
				secondm = c.month;
				secondid=c.id;
			}
			n++;
		}
		if((firstm==2&&secondm==8)||(firstm==8&&secondm==2)) {
			result="망통";
			score=0;
		}
		else if((firstm==4&&secondm==6)||(firstm==6&&secondm==4)) {
			result="세륙";
			score=10;
		}
		else if ((firstm==4&&secondm==10)||(firstm==10&&secondm==4)) {
			result="장사";
			score=11;
		}
		else if((firstm==1&&secondm==10)||(firstm==10&&secondm==1)) {
			result="장삥";
			score=12;
		}
		else if((firstm==1&&secondm==9)||(firstm==9&&secondm==1)) {
			result="구삥";
			score=13;
		}
		else if((firstm==1&&secondm==4)||(firstm==4&&secondm==1)) {
			result="독사";
			score=14;
		}
		else if((firstm==1&&secondm==2)||(firstm==2&&secondm==1)) {
			result="알리";
			score=15;
		}	
		else if(firstm==secondm) {
			if(firstm==10) {
				result="장땡";
				score=25;
			}
			else {
				result=firstm+"땡";
				score=firstm+15;
			}
		}
		else if(firstid==0&&(secondid==4||secondid==14)) {
			result="광땡";
			score=26;
		}
		else if(firstid==4&&secondid==14) {
			result="삼팔광땡";
			score=27;
		}
		else if((firstm==3&&secondm==7)||(firstm==7&&secondm==3)) {
			result="땡잡이";
			score=0;
		}
		else if((firstid==7&&secondid==16)||(firstid==7&&secondid==17)||(firstid==6&&secondid==17))
			result="사구";
		else if((firstid==6&&secondid==16))
			result="멍텅구리";
		else if(firstid==6&&secondid==12) {
			result="암행어사";
			score=1;
		}
		else {
			n=(firstm+secondm)%10;
			if(n==9) {
				result="갑오";
				score=9;
			}
			else 
				result=n+"끗";
			score=n;
		}
		return result;
	}
	
	private class Board extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(back);
			g.fillRect(0, 0, x, y);
			
			g.setColor(Color.black);
			g.setFont(ButtonFont);
			
			g.drawString("상대 패 : ", 300, 130);
			g.drawString("나의 패 : ", 300, 630);
			g.drawString("건 금액 : "+nowbet, 500, 380);
			g.drawString("보유 금액 : "+nowmoney, 850, 100);
			g.drawString("상대 턴 : "+computerAct,80, 130);
			if(computerAct.equals("콜")) {
				g.drawString(playerResult, 550, 500);
				playerCardImage[1]=openCardImage[0];
			}
			else {}
			
			if(lastBet) {
				g.drawString(playerResult, 550, 500);
				playerCardImage[1]=openCardImage[0];
			}
			
			else {}
			
			if(nowmoney==0)
				allIn=true;
			
			if(draw) {
				for(int i=0;i<2;i++) 
					computerCardImage[i] = openCardImage[i+1];
				playerCardImage[1]=openCardImage[0];
				g.drawString(computerResult, 550, 250);
				g.drawString(playerResult, 550, 500);
				g.drawString("한판 승부!",920 , 530);
			}
			else if(endGame) {
				if(die) {
					g.drawString(playerResult, 550, 500);
					g.drawString("한판 더?",920 , 530);
					playerCardImage[1]=openCardImage[0];
				}
				else {
				for(int i=0;i<2;i++) 
					computerCardImage[i] = openCardImage[i+1];
				playerCardImage[1]=openCardImage[0];
				g.drawString(computerResult, 550, 250);
				g.drawString(playerResult, 550, 500);
				g.drawString("한판 더?",920 , 530);
				}
			}
			int index=0;
			for( int i=0;i<2;i++) {
			g.drawImage(computerCardImage[index], 450+150*index,50,null,this);
			g.drawImage(playerCardImage[index], 450+150*index,550,null,this);
			index++;	
			}
		}
	}
}
