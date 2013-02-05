/**
 * 
 */
package newPkg;

/**
 * @author Bri
 *
 */
public class Airplane {

	private int kind;
	private Flying hIF;
	private LiftOff fIL;
	
	public Airplane(int i, Flying fly, LiftOff lift) {
		// TODO Auto-generated constructor stub
		kind = i;
		hIF = fly;
		fIL = lift;
	}

	public String howDoYouFly() {
		// TODO Auto-generated method stub
		
		return hIF.howIFLly();
		
		/*switch(kind){
		case 1: return "Like a fighter jet";
		case 2: return "I don't fly";
		case 3: return "Like a passenger plane";
		default: return null;
		}*/
	}

	public String howDoYouLiftOff() {
		

		return fIL.howILiftOff();
	}

}
