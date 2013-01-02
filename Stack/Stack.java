public class Stack {

	private Node front;

	public Stack(){
		this.front = null;
	}

	public void push(int n){
		Node newNode = new Node(n);
		Node oldFront = front;
		front = newNode;
		front.next = oldFront;

	}

	public String pop(){
		if(front == null){
			System.out.print("Nothing in the stack to pop.");
			return null;
		}
		String s = Integer.toString(front.num);
		front = front.next;
		return s;
	}

	public boolean empty(){
		if(front == null){
			System.out.print("Yep, it's empty.");
			return true;
		}
		else{
			System.out.println("There's still stuff in the stack.");
			return false;
		}
	}
}
