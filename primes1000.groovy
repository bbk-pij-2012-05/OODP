boolean[] primeNumbers = new boolean[1001];
int[] primeNum = new int[1001];
 for(int i=2; i<1001; i++ ){primeNumbers[i]=true;}
for(int i=2; i < Math.sqrt(1001); i++)
{
	if(primeNumbers[i] == true)
	{
		for(int j = i+i; j < 1001; j=j+i) {
			primeNumbers[j] = false;
			primeNum[j] = j;
		}
	}
}
for(int i = 0; i < primeNumbers.length; i++) {
	if(primeNumbers[i] == true)
	{
	System.console().println(i);
}
}