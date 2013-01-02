public class PersonQueue {

	private Person front;
	public int count = 0;
	
	public PersonQueue(){
		front = null;
	}
	
	public void insert(Object in){
		Person old = front;
		front = new Person();
		front.item = in;
		front.next = old;
		count++;
	}
	
	public Object servePerson(){
		if(front == null){
			 throw new RuntimeException("Nobody to serve!");
		}
		else{
			Object removed = front.item;
			front = front.next;
			System.out.println("Person " + removed + " just got served!");
			count--;
			return removed;
		}
	}
	public void printQueue(){
		if(front != null){
		 for (Person n = front; n != null; n = n.next){
			 System.out.print(n.item + " ");
		 }
		}
		else{
			System.out.println("Nothing found!");
		}
	}
}
