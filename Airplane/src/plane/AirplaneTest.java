package plane;

import static org.junit.Assert.*;

import newPkg.Airplane;
import newPkg.FFJ;
import newPkg.Flying;

import org.junit.Test;

public class AirplaneTest {

	private FlyingFactory flyingFactory;

	@Test
	public void test(){
		
		Flying fly = flyingFactory.createFlying("Fighter Jet");
		
		String expected = "Like a fighter jet";
		String returned = null;
		
		Airplane classUnderTest = new Airplane(1, fly);
		
		returned = classUnderTest.howDoYouFly();
		
		assertEquals("Wrong Answer!", returned, expected);
	}
	
	@Test
	public void test2(){
		
		String expected = "Vertically";
		String returned = null;
		
		Flying fly = new FFJ();
		
		Airplane classUnderTest = new Airplane(1, fly);
		returned = classUnderTest.howDoYouLiftOff();
		
		assertEquals("Wrong Answer!", returned, expected);
	}
	
	@Test
	public void test3(){
		
		String expected = "I don't fly";
		String returned = null;
		
		Flying fly = new ModelAirPlane();
		
		Airplane classUnderTest = new Airplane(1, fly);
		returned = classUnderTest.howDoYouFly();
		
		assertEquals("Wrong Answer!", returned, expected);
		
	}
}
