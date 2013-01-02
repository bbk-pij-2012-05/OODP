public class OldPhone implements Phone{

	private String brand = null;

	public void call(String number) {
		// TODO Auto-generated method stub
		System.out.println("Calling " + number);
	}

	public OldPhone(String brand) {
		this.brand = brand;
		}

	public String getBrand(){
		return brand;
	}

}
