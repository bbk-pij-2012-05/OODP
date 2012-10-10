System.console().println("Would you like to enter a binary or decimal number?");
System.console().println("1. Binary");
System.console().println("2. Decimal");
int choice = Integer.parseInt(System.console().readLine());
if(choice == 1)
{
	System.console().println("Please enter a binary number: ");
	String binary = System.console().readLine();
	binary2decimal(binary);
}
else if(choice == 2)
{
	System.console().println("Please enter a decimal number: ");
	int decimal = Integer.parseInt(System.console().readLine());
	decimal2binary(decimal);
}
else
{
	System.console().println("Try again");
}

void binary2decimal(String input){
	int num = Integer.parseInt(input ,2);
	System.console().println("Your number is " + num);
}

void decimal2binary(int numIn){
	System.console().println("Your binary is " + Integer.toBinaryString(numIn));
}