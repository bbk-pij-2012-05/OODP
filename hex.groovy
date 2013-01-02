System.console().println("Would you like to enter a hex or decimal number?");
System.console().println("1. Hex");
System.console().println("2. Decimal");
int choice = Integer.parseInt(System.console().readLine());
if(choice == 1)
{
	System.console().println("Please enter a hex number: ");
	String hex = System.console().readLine();
	hex2decimal(hex);
}
else if(choice == 2)
{
	System.console().println("Please enter a decimal number: ");
	int decimal = Integer.parseInt(System.console().readLine());
	decimal2hex(decimal);
}
else
{
	System.console().println("Try again");
}

void hex2decimal(String input){
	if(input.contains("0x"))
	{
	input = input.substring(2);
	}
	int num = Integer.parseInt(input ,16);
	System.console().println("Your decimal number is " + num);
}

void decimal2hex(int numIn){
	System.console().println("Your hex is 0x" + Integer.toHexString(numIn));
}