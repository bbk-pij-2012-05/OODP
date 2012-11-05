public class SmartPhone extends MobilePhone{

	public SmartPhone(String brand) {
		super(brand);
		// TODO Auto-generated constructor stub
	}

	public void call(String number){
		if(number.startsWith("00"))
		{
			System.out.println("Calling " + number + " through the internet to save money.");
		}
		else{
		super.call(number);
		}
	}

	public void browseWeb(String s){

	}

	public void findPosition(){

	}
}
