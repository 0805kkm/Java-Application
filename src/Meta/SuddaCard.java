package Meta;
import java.awt.Image;

public class SuddaCard {
	
	int month;
	boolean cardUsed;
	boolean light;
	int id;
	int number;
	Image cardImg;
	
	public SuddaCard(int m,int id,Image cardImg) {
		this.month=m;
		this.id=id;
		this.cardImg = cardImg;
	
	}
	
	public Image getImage() {
		return cardImg;
	}	
}
