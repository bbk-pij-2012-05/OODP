
public class Card {

	private CardEnum card;
	private SuitEnum suit;
	
	Card(CardEnum c, SuitEnum s) {
		this.suit = s;
		this.card = c;
	}
	
	Card getCard(){
		return this;
	}
	
	String getCardInfo(){
		return card.getName() + "  " + suit.getSuit();
	}
	
}
