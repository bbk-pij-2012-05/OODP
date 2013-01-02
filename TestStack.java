import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestStack {
	static StringStack ss;

	@BeforeClass
	public static void onlyOnce() {
		ss = new ArrayStringStack();
	}

	@Test
	public void testPush() {
		for (int i = 0; i < 10; i++) {
			ss.push("Info" + i);
		}
	}

	@Test
	public void testPop(){
		ss.push("Jim");
		String s = ss.pop();
		String expected = "Jim";
		assertEquals(s, expected);
	}

	@Test
	public void testPeekNull(){
			ss = new ArrayStringStack();
			String response = ss.peek();
			if(response == null){
				assertTrue(true);
			}
		}

	@Test
	public void testPeekLastElem(){
			ss = new ArrayStringStack();
			ss.push("Joe");
			String response = ss.peek();
			assertEquals(response, "Joe");
	}

		@Test
		public void testEmpty(){
			ss = new ArrayStringStack();
			boolean response = ss.isEmpty();
			assertTrue(response);
	}
}
