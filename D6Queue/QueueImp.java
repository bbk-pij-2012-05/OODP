public class QueueImp {

    public static void main(String[] args) {
    	Queue q = new Queue();
    	System.out.println("There are " + q.count + " requests in the queue.");
    	System.out.println("Inserting request 5...");
    	q.insert(5);
    	System.out.println("Inserting request 10...");
    	q.insert(10);
    	System.out.println("Inserting request 15...");
    	q.insert(15);
    	System.out.println("Inserting request 20...");
    	q.insert(20);
    	System.out.println("There are " + q.count + " requests in the queue.");
    	System.out.println("Retrieving request 20...");
    	q.retrieve();
    	System.out.println("Retrieving request 15...");
    	q.retrieve();
    	System.out.println("There are " + q.count + " requests in the queue.");
    	System.out.println("Inserting request 25...");
    	q.insert(25);
    	System.out.println("There are " + q.count + " requests in the queue.");
    	q.printQueue();
    }
	
}