System.console().println("Please enter a number");
String str = System.console().readLine();
int num1 = Integer.parseInt(str);
System.console().println("Please enter a number");
String str2 = System.console().readLine();
int num2 = Integer.parseInt(str2);
System.console().println("Please make a selection");
System.console().println("1. Addition");
System.console().println("2. Subtraction");
System.console().println("3. Multiplication");
System.console().println("4. Division");
String num = System.console().readLine();
double choice = Double.parseDouble(num);
if(choice == 1)
{
	System.console().println("Your result is: " + (num1+num2));
}
else if(choice == 2)
{
	System.console().println("Your result is: " + (num1-num2));
}
else if(choice == 3)
{
	System.console().println("Your result is: " + (num1*num2));
}
else if(choice == 4)
{
	System.console().println("Your result is: " + (num1/num2));
}
else
{
	System.console().println("Oops! Can't do that.");
}