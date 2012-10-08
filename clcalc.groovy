System.console().println("Please enter an equation");
String str = System.console().readLine();
for(int i = 0; i < str.length(); i++)
{
	if(str[i] == "*")
	{
		Double first = Double.parseDouble(str.substring(0, (i)));
		Double second = Double.parseDouble(str.substring(i+1, str.length()));
		Double total = (first * second);
		System.console().println(total);
	}
	else if(str[i] == "+")
	{
		Double first = Double.parseDouble(str.substring(0, (i)));
		Double second = Double.parseDouble(str.substring(i+1, str.length()));
		Double total = (first + second);
		System.console().println(total);
	}
	else if(str[i] == "/")
		{
			Double first = Double.parseDouble(str.substring(0, (i)));
			Double second = Double.parseDouble(str.substring(i+1, str.length()));
			Double total = (first / second);
			System.console().println(total);
	}
	else if(str[i] == "-")
		{
			Double first = Double.parseDouble(str.substring(0, (i)));
			Double second = Double.parseDouble(str.substring(i+1, str.length()));
			Double total = (first - second);
			System.console().println(total);
			break;
	}
}