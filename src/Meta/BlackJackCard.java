package Meta;

import java.awt.Image;

public class BlackJackCard {
	
	int number;
	boolean cardUsed;
	String name;
	int id;
	Image cardImg;
	int value;
	
	public BlackJackCard(int n,int id,Image cardImg) {
		this.number=n;
		this.id=id;
		this.cardImg = cardImg;
		
		if(number<11) {
			name= Integer.toString(number);
			value = number;
		}
		
		else if(number==11){
			name = "Jack";
			value = 10;
		}
		
		else if (number == 12) {
			name ="Queen";
			value = 10;
		}
		
		else if(number==13) {
			name = "King";
			value = 10;
		}
		
		else {
			name= "Ace";
			value = 1;
		}
	}
	
	public Image getImage() {
		return cardImg;
	}

}