public class Node implements IntSet {

	private int number;
	private Node next;
	private Node prev;

	public Node(int n) {
		this.number = n;
		this.next = null;
		this.prev = null;
	}

	public void add(int num) {
		Node newNode = new Node(num);
		if(this.number == num){
			return;
		}
		else if (this.next == null) {
			this.next = newNode;
			newNode.prev = this;
			newNode.next = null;
		} else {
			this.next.add(newNode.number);
		}
	}

	public boolean containsVerbose(int num) {
		int x = this.number;
		System.out.println("Contains: " + x);
		if(x == num){
			return true;
		}
		else
			if(this.next == null){
				return false;
			}
			else
				return this.next.containsVerbose(num);
	}


	public boolean contains(int num){
		int y = this.number;
		if(y == num){
			return true;
		}
		else
			if(this.next == null){
				return false;
			}
			else
				return this.next.contains(num);
	}



}
