boolean[] primeNumbers = new boolean[10001];
 for(int i=2; i<10001; i++ ){primeNumbers[i]=true;}
for(int i=2; i < Math.sqrt(10001); i++)
{
	if(primeNumbers[i] == true)
	{
		for(int j = i+i; j < 10001; j=j+i) {
			primeNumbers[j] = false;
		}
	}
}
for(int i = 0; i < 10001; i++) {
	if(primeNumbers[i] == true)
	{
	System.console().println(i);
}
}