public class HashUtilities {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Give me a number to hash: ");
		String s = System.console().readLine();
		int numToHash = s.hashCode();
		int hash = HashUtilities.shortHash(numToHash);
		System.out.println("0 < " + hash + " < 1000");
	}
	
	static public int shortHash(int num){
		if(num < 0){
			num = Math.abs(num);
		}
		
		int hash = (num % 1000);
		return hash;
	}

}
