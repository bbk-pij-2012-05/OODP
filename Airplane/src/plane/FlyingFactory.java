package plane;

import newPkg.Flying;

public class FlyingFactory {

	public Flying createFlying(String string) {
		if(string.equals("Fighter Jet")){
			return new IFlyLikeFJ();
		}
		return null;
	}

}
