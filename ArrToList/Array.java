import java.util.Iterator;
import java.util.LinkedList;

public class Array {

	public static void main(String[] args) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		int[] arr = new int[15];
		for(int i = 0; i < 15; i++){
			arr[i] = i;
		}
		l = ListUtilities.convert(arr);
		Iterator<Integer> it = l.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next() + "  ");
		}
	}

}
