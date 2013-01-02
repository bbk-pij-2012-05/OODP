import java.util.Scanner;

public class ArrayCopier {

	static int[] src;
	static int[] dst = new int[10];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayCopier ac = new ArrayCopier();
		System.out.println("Enter some numbers.");	
		String s = sc.nextLine();
		int len = s.length();
		src = new int[len];
		for(int i = 0; i < len; i++)
		{
			src[i] = Character.digit(s.charAt(i), 10);
		}

		ac.Copy(src, dst);
	}

	void Copy(int a[], int b[]){

		if(a.length == b.length || a.length > b.length)
		{
			for(int i = 0; i < b.length; i++)
			{
				
				b[i] = a[i];
			}
		}
		else if(a.length < b.length)
		{
			for(int i = 0; i < b.length; i++)
			{
				b[i] = 0;
			}
		}

		System.out.print("Your new array contains: ");
		for(int i = 0; i < b.length; i++)
		{
			System.out.print(b[i]);
		}
		
	}
	
}
