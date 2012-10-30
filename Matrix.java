import java.util.Scanner;

public class Matrix {
	int[][] arr = new int[1][1];
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Matrix mat = new Matrix(6, 5);
		String newString = mat.toString();
		System.out.print("Your string is: " + newString);
		System.out.print("\n");
		mat.setElement(4,3);
		System.out.print("\n");
		System.out.print("\n");
		mat.prettyPrint();
		mat.setRow(2, "1,2,3,4,5");
		System.out.print("\n");
		System.out.print("\n");
		mat.prettyPrint();
		mat.setColumn(1, "9,8,7,6,5,4");
		System.out.print("\n");
		System.out.print("\n");
		mat.prettyPrint();
	}
	
	Matrix(int a, int b){
		this.arr = new int[a][b];
	}
	
	void setElement(int index1 ,int index2){
		System.out.print("\n" + "Please enter a number: ");
		int input = Integer.parseInt(sc.nextLine());
		try {
			this.arr[index1][index2] = input;
			System.out.print("\n" + "You set the index to " + this.arr[index1][index2]);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	void setRow(int row,String s){
		try{
			String[] sArray = s.split(",");
			
		for(int i = 0; i <= this.arr[row].length; i++)
		{
			this.arr[row][i] = Integer.parseInt(sArray[i]);
		}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	void setColumn(int col,String s){
		String[] sArray = s.split(",");
		try{
				for(int i = 0; i <= this.arr.length; i++)
				{
					this.arr[i][col] = Integer.parseInt(sArray[i]);
				}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	public String toString(){
		int len = this.arr.length;
		String s = "";
		s += "[";
		for(int i = 0; i < len; i++)
		{
			for(int j = 1; j < this.arr[i].length; j++)
			{
				s += this.arr[i][j] + ",";
			}
			
			s= s.substring(0, s.length() - 1) + ";";
		}
		s= s.substring(0, s.length() - 1) + "]";
		
		return s;
	}
	
	void prettyPrint(){
		 int rowSize = this.arr.length;
		 int colSize = this.arr[0].length;
		for(int i = 0; i < rowSize; i++)
		{
			for(int j = 0; j < colSize; j++)
			{
				System.out.print(this.arr[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
}
