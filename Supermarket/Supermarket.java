public class Supermarket {
	PersonQueue pq = new PersonQueue();
	
	public static void main(String[] args) {
		Supermarket s = new Supermarket();
		s.init();
	}
	
	public void init(){
		System.out.println("We're creating random people and adding them to the queue.... \n");
		Person p1 = new Person();
		p1.item = "james";
		Person p2 = new Person();
		addPerson(p1);
		
	}
	
	public void addPerson(Person p){
		pq.insert(p);
		pq.printQueue();
	}
	
	public void servePerson(){
		pq.servePerson();
		pq.printQueue();
	}

}
