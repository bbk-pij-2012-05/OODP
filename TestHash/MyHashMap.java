public class MyHashMap implements SimpleMap {

	// ArrayList<Entry> myArrayList;
	Entry[] arr;
	int count = 0;

	MyHashMap() {
		// myArrayList = new ArrayList<Entry>();
		arr = new Entry[1000];
	}

	public static void main(String[] args) {
		MyHashMap hashing = new MyHashMap();

		hashing.put(5, "Joe");
		hashing.put(6, "Liz");
		hashing.put(1, "Pam");
		hashing.put(19, "Brooklyn");
		hashing.put(56, "Glenn");
		hashing.put(24, "Enrique");

		hashing.isEmpty();

		hashing.remove(24, "Enrique");
		hashing.remove(5, "Joe");
		hashing.remove(6, "Liz");
		hashing.remove(56, "Glenn");
		hashing.remove(1, "Pam");
		hashing.remove(19, "Brooklyn");

		hashing.isEmpty();
	}

	@Override
	public String put(int key, String name) {
		if (arr[key] == null) {
			arr[key] = new Entry(name);
			return name;
		} else {
			if (arr[key].contains(name)) {
				System.out.print("Oops");
				return null;
			} else {
				arr[key].add(name);
				return name;
			}
		}
	}

	@Override
	public String[] get(int key) {
		return arr[key].get(key);
	}

	@Override
	public void remove(int key, String name) {
		arr[key].remove(name);
		if (arr[key].contains(null)) {
			arr[key] = null;
		}
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < arr.length; i++) {
		 if (arr[i] != null && !arr[i].isEmpty()) {
				System.out.println("It's not empty.");
				return false;
			}
		}
		System.out.println("It's empty.");
		return true;
	}
}