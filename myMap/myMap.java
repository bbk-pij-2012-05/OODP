public class myMap implements SimpleMap{

	String[] arr = new String[100];
	

	public myMap(){
		
	}
	
	public static void main(String[] args) {
		myMap map = new myMap();
		map.put(1, "Hello");
		map.put(2, "how");
		map.put(3, "are");
		map.put(4, "you");
		map.put(5, "doing?");
		
		map.get(3);
		map.remove(3);
		System.out.print(map.get(3));
		
	}

	@Override
	public String put(int key, String name) {
		int loc = (key % 100);
		if(arr[loc] != null)
			return null;
		else{
			arr[loc] = name;
			return name;
		}
	}

	@Override
	public String get(int key) {
		if(arr[(key % 100)] == null){
			return "Not there!";
		}
		else
			return arr[(key % 100)];
	}

	@Override
	public void remove(int key) {
		arr[(key % 100)] = null;
	}

	@Override
	public boolean isEmpty() {
		if(arr.length == 0)
			return true;
		else
			return false;
	}
}
