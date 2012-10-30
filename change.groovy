import java.util.StringTokenizer;


System.console().println("How much was the purchase? ");
String str = System.console().readLine();
Double cost = Double.parseDouble(str);
System.console().println("How much was paid? ");
str = System.console().readLine();
Double paid = Double.parseDouble(str);
Double change = (paid-cost);
String strToToken = Double.toString(change);
StringTokenizer st = new StringTokenizer(strToToken, ".");
Double first = Double.parseDouble(st.nextElement());
if((first/50) >= 1)
{
	int fifties = first/50;
	first = (first-(fifties*50));
	System.console().println("50: " + fifties);
}

if((first/20) >= 1)
{
	int twenties = first/20;
	first = (first-(twenties*20));
	System.console().println("20: " + twenties);
}
if((first/10) >= 1)
{
	int tens = first/10;
	first = (first-(tens*10));
	System.console().println("10: " + tens);
}

if((first/5) >= 1)
{
	int fives = first/5;
	first = (first-(fives*5));
	System.console().println("5: " + fives);
}

Double second = Double.parseDouble(st.nextElement());
if(second.length() < 2)
{
	second = second + 0;
}


