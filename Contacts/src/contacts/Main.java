public class Main extends ContactManagerImp{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		m.init();

	}
	
	public void init() {
		
		while (true) {
			importData();

			System.out.println("What would you like to do?");
			System.out.println("1) Add Contact");
			System.out.println("2) Get Contact");
			System.out.println("3) Add New Meeting");
			System.out.println("4) View Meeting");
			System.out.println("5) Edit Meeting Notes");
			System.out.println("6) Exit");

			String name;

			int in = 0;
			boolean choiceFlag = true;
			while ((in == 0 || in < 0 || in > 5) && choiceFlag == true) {
				String choice = System.console().readLine();
				if (isInt(choice)) {
					in = Integer.parseInt(choice);
					choiceFlag = false;
				}
			}
			if (in == 1) {
				System.out.println("Name: ");
				name = System.console().readLine();
				System.out.println("Notes: ");
				String notes = System.console().readLine();
				addNewContact(name, notes);
				
			} else if (in == 2) {
				findContactChoice();
			} else if (in == 3) {
				addMeetingChoice();

			} else if (in == 4) {
				viewCalendar();
			} else if (in == 5) {
				editMeetingNotes();
			} else if (in == 6) {
				flush();
				System.exit(0);
			}
		}
	}
	
}
