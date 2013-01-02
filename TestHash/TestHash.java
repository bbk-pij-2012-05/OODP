import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class TestHash {
	@Test
	public void testName() {
		MyHashMap hm = new MyHashMap();
		Random ranGen = new Random();

		for(int i = 0; i < 2000; i++){
			int random = ranGen.nextInt(1000);
			try{
				String in = "name" + i;
				hm.put(random, in);
			}
			catch(Exception e){
				fail();
			}
		}
}

	@Test
	public void testNameFail() {
		MyHashMap hm = new MyHashMap();
		Random ranGen = new Random();

		for(int i = 0; i < 2000; i++){
			int random = ranGen.nextInt(2000);
			try{
				String in = "name" + i;
				hm.put(random, in);
			}
			catch(Exception e){
				fail();
			}
		}
}

}
