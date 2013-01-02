import org.junit.Test;
import static org.junit.Assert.*;

public class InitTest {
		@Test
		public void testName() {
		  Initials I = new Initials();
	      String input = "Brianne Leigh Murphy";
		  String output = I.getInitials(input);
		  String expected = "BLM";
		  assertEquals(output, expected);
		}


		@Test
				public void testNameBad() {
				  Initials I = new Initials();
			      String input = "Brianne Leigh  Murphy";
				  String output = I.getInitials(input);
				  String expected = "BLM";
				  assertEquals(output, expected);
		}
}
