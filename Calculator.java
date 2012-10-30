public class Calculator {
	
	public static void main(String[] args) {
		Calculator c = new Calculator();
			c.add(3,9);
			c.subtract(3,9);
			c.multiply(3,9);
			c.divide(3,9);
			c.modulus(3,9);
			
	}

	public void add(int a, int b) {
		System.out.println((a+b));
	}
	
	public void subtract(int a, int b) {
		System.out.println((a-b));
	}
	
	public void multiply(int a, int b) {
		System.out.println((a*b));
	}
	
	public void divide(int a, int b) {
		System.out.println((((double) a)/((double) b)));
	}
	
	public void modulus(int a, int b) {
		System.out.println((a%b));
	}
}
