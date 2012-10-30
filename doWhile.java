public class doWhile {

	public static void main(String[] args){
		int input = 0;
		int count = 0;
		int distinct = 0;
		int pass = 0;
		int fail = 0;
		int invalid = 0;

		do{
			System.out.println("Enter a mark: ");
			count++;
			input = Integer.parseInt(System.console().readLine());
			if(input > 100 || input < 0)
			{
				invalid++;
			}
			else if(input >= 70 && input <= 100)
			{
				distinct++;
			}
			else if(input >= 50 && input < 70)
			{
				pass++;
			}
			else
			{
				fail++;
			}

		}
		while(input != -1);

		System.out.println("There are " + count + " students. " + distinct + " distinctions, " + pass + " pass, and " + fail + " failed. (" + invalid + " invalid).");

	}

}
