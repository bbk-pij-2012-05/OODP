Random generator = new Random();
int num = generator.nextInt(1000);
int choice = 0;
System.console().println("Please pick a number.");
while(true) {
String choiceIn = System.console().readLine();
choice = Integer.parseInt(choiceIn);
if(choice < num)
{
	System.console().println("Too low! Try again.");
}
else if(choice > num)
{
	System.console().println("Too high! Try again");
}
else
{
	System.console().println("Spot on. Good Choice!");
}}