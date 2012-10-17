public class spy {
	static int spyCount;
	int spyID;
	
	spy(int a){
		this.spyID = a;
		spy.spyCount++;
	}
	
	void die(){
		System.out.println("Spy " + this.spyID + " has been detected and eliminated.");
		System.out.println("Total spies: " + spy.spyCount);
	}
	
	public static void main(String[] args) {
		spy firstSpy = new spy(1);
		firstSpy.die();
		spy secondSpy = new spy(2);
		spy thirdSpy = new spy(3);
		secondSpy.die();
		spy fourthSpy = new spy(4);
		thirdSpy.die();
		fourthSpy.die();
		
	}
	
}