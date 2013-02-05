import static org.testng.AssertJUnit.assertSame;
import org.testng.annotations.Test;

public class CardTests {
	CardEnum ace;
	SuitEnum hearts;
	
	@Test
	public void createCard() {
		Card card = new Card(ace, hearts);
		String s = card.getCardInfo();
		int i = s.compareTo("hearts   ace");
		assertSame(i, 1);
	}
}
