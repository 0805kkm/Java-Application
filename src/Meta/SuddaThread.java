package Meta;

public class SuddaThread implements Runnable {
	private int money;
	
	public static void main(String args[]) {
		Thread t = new Thread(new SuddaThread(10000));
		t.start();
	
	}
	public SuddaThread(int m) {
		this.money=m;
	}

	public void run() {
		Sudda s = new Sudda(money);
		while(true) {
			try {
				s.repaint();
				s.suddaButtonShow();
				
				}
			catch(Exception e){}
		}
		
	}
	
}
