package Meta;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Meta {
	public static void main(String[] args){
		Meta_Frame mf = new Meta_Frame(10000);
	}
}

class Meta_Frame extends JFrame implements Runnable,KeyListener{
	public static int money =10000;
	boolean keyUp = false;
	boolean keyDown = false;
	boolean keyLeft = false;
	boolean keyRight = false;
	boolean characterMove = false;
 
	Image img = new ImageIcon("img/icon.png").getImage();
	Image trumpCard  =  new ImageIcon("img/back.png").getImage();
	Image hwatu  =  new ImageIcon("img/Pae.png").getImage();
	Image question  =  new ImageIcon("img/p.png").getImage();
	Image buffimg;
	Graphics gc;
	Thread th;

	int x, y; 
	int cnt; 
	int lookWhere; 
 
	Meta_Frame(int money){
		this.money=money;
		setTitle("Meta");
		setSize(800, 600);
		x = 375;
		y = 100;
		lookWhere = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		th = new Thread(this);
		th.start();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public void run(){ 
		while(true){
			try{
				keyProcess();
				repaint();
				th.sleep(30);
				cnt++;
			}
			catch(Exception e){}
		}
	}
 
	public void paint(Graphics g){
		File file = new File("img/ss.png");
		try {
			buffimg = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc = buffimg.getGraphics();
		DrawImg();
		g.drawImage(buffimg, 0, 0, this);
	}
	
	public int Position() {
		int p = 0;
		if(75<=x&&x<=200&&250<=y&&y<=400) p=1;
		if(575<=x&&x<=700&&250<=y&&y<=400)	p=2;
		if(315<=x&&x<=415&&120<=y&&y<=240) 	p=3;	
		if(x>770) x=770;
		if(y>570) y=570;
		if(x<0)	x=0;
		if(y<0)	y=0;
		return p;
	}

	public void DrawImg(){
		gc.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		Position();
		gc.drawString("Press X for interaction", 300, 50);
		if(Position()==1) 
			gc.drawString("BlackJack", 110, 500);
		if(Position()==1&&money<100) 
			gc.drawString ("Not enough money",90,290);
		if(Position()==2) 
			gc.drawString("Sudda", 623, 500);
		if(Position()==2&&money<1000) 
			gc.drawString ("Not enough money",587,290);
		if(Position()==3) 
			gc.drawString("how to play", 340, 190);
		gc.drawString("You have : "+Integer.toString(money), 600, 50);
		gc.drawImage(trumpCard,100,300,this);
		gc.drawImage(hwatu,600,300,this);
		gc.drawImage(question,375,200,this);
		MoveImage(img, x, y, 32, 48);
	}
 
	public void MoveImage(Image img, int x, int y, int width,int height){
		gc.setClip(x, y, width, height);
		if( characterMove ){ 
			if ( cnt / 5 % 4 == 0 )
				gc.drawImage(img, x - ( width * 0 ), y - ( height * lookWhere ), this);
			else  if( cnt / 5 % 4 == 1 )
				gc.drawImage(img, x - ( width * 1 ), y - ( height * lookWhere ), this);
			else  if( cnt / 5 % 4 == 2 )
				gc.drawImage(img, x - ( width * 2 ), y - ( height * lookWhere ), this);
			else  if( cnt / 5 % 4 == 3 )
				gc.drawImage(img, x - ( width * 3 ), y - ( height * lookWhere ), this);
		}
		else 
			gc.drawImage(img, x - ( width * 0 ),y - ( height * lookWhere ), this);
	}

	public void playblackjack() {
		dispose();
		Thread t = new Thread(new BlackJackThread(money));
		t.start();
	}
	
	public void playSudda() {
		dispose();
		Thread s = new Thread(new SuddaThread(money));
		s.start();
	}
	
	public void howToPlay() {
		HowToPlay h = new HowToPlay(); 
	}
 
	public void keyProcess(){
		characterMove = false;

		if ( keyUp ){
			characterMove = true;
			y -= 8;
			lookWhere = 3;
		}
		if ( keyDown){
			y += 8;
			lookWhere = 0;
			characterMove = true;
		}
		if ( keyLeft){
			x -= 8;
			lookWhere = 1;
			characterMove = true;
		}
		if ( keyRight){
			x += 8;
			lookWhere = 2;
			characterMove = true;
		}
	}
 
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT :
			keyLeft = true;
			break;
		case KeyEvent.VK_RIGHT :
			keyRight = true;
			break;
		case KeyEvent.VK_UP :
			keyUp = true;
			break;
		case KeyEvent.VK_DOWN :
			keyDown = true;
			break;
		}

		if(Position()==1&&keycode==88) {
			if(money>=100)
				playblackjack();

		}
		if(Position()==2&&keycode==88) {
			if(money>1000)
				playSudda();
		}
		if(Position()==3&&keycode==88) {
			howToPlay();
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT :
			keyLeft = false;
			break;
		case KeyEvent.VK_RIGHT :
			keyRight = false;
			break;
		case KeyEvent.VK_UP :
			keyUp = false;
			break;
		case KeyEvent.VK_DOWN :
			keyDown = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {}

	}