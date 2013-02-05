package plane;

import static org.junit.Assert.*;

import newPkg.Airplane;
import newPkg.FFJ;
import newPkg.Flying;
import newPkg.LiftOff;

import org.junit.BeforeClass;
import org.junit.Test;

public class AirplaneTest implements AirPlaneFactory {

	private static FlyingFactory flyingFactory;
	private static LiftOffFactory liftOffFactory;

	@BeforeClass
	public static void onlyOnce(){
		flyingFactory = new FlyingFactory();
		liftOffFactory = new LiftOffFactory();
	}
	
	
	/* (non-Javadoc)
	 * @see plane.AirPlaneFactory#test()
	 */
	@Override
	@Test
	public void test(){
		String expected = "Like a fighter jet";
		String returned = null;
		
		HarrierFactory harrierFactory = new HarrierFactory();
		
		Flying fly = harrierFactory.createFlying();
		
		LiftOff liftOff = harrierFactory.createLiftOff();
		
		
		Airplane classUnderTest = new Airplane(1, fly, liftOff);
		
		returned = classUnderTest.howDoYouFly();
		
		assertEquals("Wrong Answer!", returned, expected);
	}
	
	/* (non-Javadoc)
	 * @see plane.AirPlaneFactory#test2()
	 */
	@Override
	@Test
	public void test2(){
		
		String expected = "Vertically";
		String returned = null;
		HarrierFactory harrierFactory = new HarrierFactory();
		
		LiftOff liftOff = harrierFactory.createLiftOff();
		Flying fly = new FFJ();
		
		Airplane classUnderTest = new Airplane(1, fly, liftOff);
		returned = classUnderTest.howDoYouLiftOff();
		
		assertEquals("Wrong Answer!", returned, expected);
	}
	
	/* (non-Javadoc)
	 * @see plane.AirPlaneFactory#test3()
	 */
	@Override
	@Test
	public void test3(){
		
		String expected = "I don't fly";
		String returned = null;
		
HarrierFactory harrierFactory = new HarrierFactory();
		
		LiftOff liftOff = harrierFactory.createLiftOff();
		Flying fly = new ModelAirPlane();
		
		Airplane classUnderTest = new Airplane(1, fly, liftOff);
		returned = classUnderTest.howDoYouFly();
		
		assertEquals("Wrong Answer!", returned, expected);
		
	}
}
