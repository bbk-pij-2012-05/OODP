import java.util.LinkedList;

public class ListUtilities {

	public static LinkedList<Integer> convert(int a[]){
		LinkedList<Integer> theList = new LinkedList<Integer>();

		for(int i = 0; i < a.length; i++){
			theList.add(a[i]);
		}
		return theList;
	}
}