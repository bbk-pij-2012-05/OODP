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
	
	public Airplane(int i, Flying fly) {
		// TODO Auto-generated constructor stub
		kind = i;
		hIF = fly;
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
		switch(kind){
		case 1: return "Vertically";
		case 2: return "I liftoff";
		case 3: return "Horizontally";
		default: return null;
		}
	}

}
