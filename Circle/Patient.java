public class Patient {

	private String name;
	private int age;
	private Patient nextPatient;
	private Patient prevPatient;
	private Patient front = null;

	public Patient(String n, int a) {
		this.name = n;
		this.age = a;
		this.nextPatient = null;
		this.prevPatient = null;
	}

	public void addPatient(Patient p) {
		 if(front == null) {
			 front = this;
			 front.nextPatient = p;
			 front.prevPatient = p;
			 p.prevPatient = front;
			 p.nextPatient = front;
		 }
		 else{
			front.prevPatient.nextPatient = p;
			p.nextPatient = front;
			front.prevPatient = p;
		 }

	}

	public boolean deletePatient(Patient p) {
		if(this.nextPatient.name.equals(p.name)){
			System.out.println("Gone.");
			this.nextPatient = this.nextPatient.nextPatient;
			this.nextPatient.prevPatient = this;
			return true;
		} else if(this.nextPatient.name.equals(front.name)) {
			System.out.println("Not there.");
			return false;
		} else{
			return this.deletePatient(p.nextPatient);
		}


	}

	public void traverse() {
		System.out.println("Traverse: ");
		Patient p = this;
		do {
			System.out.println("Name: " + p.name + "  Age: " + p.age);
			p = p.nextPatient;
		} while (p!= null && p != front);
		System.out.println("\n");
	}


}
