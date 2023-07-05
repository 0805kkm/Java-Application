package Meta;

public class BlackJackThread implements Runnable{
	private int money;
	
	public BlackJackThread(int m) {
		this.money=m;
	}

	public void run() {
		BlackJack bj= new BlackJack(money);	
		while(true) {
			try {
			bj.blackjackscore();
			bj.blackjackbuttonShow();
			bj.repaint();
		}
			catch(Exception e){}
		}
		
	}
	
}
