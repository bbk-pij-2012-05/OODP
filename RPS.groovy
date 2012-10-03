int firstP = 0;
int secondP = 0;
while(firstP != 3 || secondP != 3) {
System.console().println("Please enter your set: ");
String input = System.console().readLine().toUpperCase();
if(input.equals("PR"))
{
	firstP++;
}
else if(input.equals("PS"))
{
	secondP++;
}
else if(input.equals("RP"))
{
	secondP++;
}
else if(input.equals("RS"))
{
	firstP++;
}
else if(input.equals("SP"))
{
	firstP++;
}
else if(input.equals("PR"))
{
	secondP++;
}
else
{
	System.console().println("Either you put in the wrong set, or you tied. Try again.");
}

if(firstP == 3 && secondP != 3)
{
	System.console().println("Person 1 wins!");
	break;
}
if(secondP == 3 && firstP != 3)
{
	System.console().println("Person 2 wins!");
	break;
}
}