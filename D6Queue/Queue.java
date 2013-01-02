public class Queue {
	private Node front;
	public int count = 0;
	
	private class Node {
        private Object item;
        private Node next;
    }

	public Queue(){
		front = null;
	}
	
	public void insert(Object in){
		Node old = front;
		front = new Node();
		front.item = in;
		front.next = old;
		count++;
	}
	
	public Object retrieve(){
		if(front == null){
			 throw new RuntimeException("Nothing in the Queue to remove!");
		}
		else{
			Object removed = front.item;
			front = front.next;
			count--;
			return removed;
		}
	}
	public void printQueue(){
		if(front != null){
		 for (Node n = front; n != null; n = n.next){
			 System.out.print(n.item + " ");
		 }
		}
		else{
			System.out.print("Nothing found!");
		}
	}
}
