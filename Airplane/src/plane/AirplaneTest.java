package plane;

import static org.junit.Assert.*;

import newPkg.Airplane;
import newPkg.FFJ;
import newPkg.Flying;
import newPkg.LiftOff;

import org.junit.BeforeClass;
import org.junit.Test;

public class AirplaneTest {

	private static FlyingFactory flyingFactory;

	@BeforeClass
	public static void onlyOnce(){
		flyingFactory = new FlyingFactory();
	}
	
	
	@Test
	public void test(){
		String expected = "Like a fighter jet";
		String returned = null;
		
		Flying fly = harrierFactory.createFlying();
		
		LiftOff liftoff = harrierFactory.createLiftOff();
		
		
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
