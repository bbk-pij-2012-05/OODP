import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ContactManagerImp implements ContactManager {

	Set<Meeting> meetingList = new HashSet<Meeting>();
	Set<Contact> contactList = new HashSet<Contact>();
	// This would need to be changed if deletion of contacts and meetings were
	// added to the
	// implementation, as it sets the ID of the contact. But for now, it works.
	int contactCount = 0;
	int meetingCount = 0;

	public static void main(String[] args) {
		ContactManagerImp manage = new ContactManagerImp();
		manage.init();

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
				/*
				 * NOT WORKING......... Iterator<Contact> contactListIterator =
				 * contactList.iterator(); while (contactListIterator.hasNext())
				 * { System.out.print("Contact:  " +
				 * contactListIterator.next().getName()); }
				 */
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

	public void importData() {
		try {
			File f = new File("data.csv");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String lineIn = br.readLine();
			String[] readLine, contact, attendees;
			String contactId, name, notes, meetingId, date, meetingNotes;
			DateFormat dateFormat = new SimpleDateFormat(
					"EEE MMM dd kk:mm:ss zzz yyyy");
			if (lineIn.equalsIgnoreCase("contacts")) {
				lineIn = br.readLine();
				while (!lineIn.equalsIgnoreCase("")
						&& !lineIn.equalsIgnoreCase("meetings")) {
					readLine = lineIn.split("\\,");
					contactId = readLine[0];
					name = readLine[1];
					ContactImp c = new ContactImp(name,
							Integer.parseInt(contactId));
					try {
						notes = readLine[2];
						c.addNotes(notes);
					} catch (ArrayIndexOutOfBoundsException ae) {

					}
					contactList.add(c);
					contactCount++;
					lineIn = br.readLine();
				}
			}

			if (lineIn.equalsIgnoreCase("meetings")) {
				Set<Contact> meetingContacts = new HashSet<Contact>();
				lineIn = br.readLine();
				while (!lineIn.equalsIgnoreCase("")
						&& !lineIn.equalsIgnoreCase("\\")) {
					readLine = lineIn.split("\\,");
					meetingId = readLine[0];
					attendees = readLine[1].split("\\:");
					int i = 0;
					while (i < attendees.length
							&& !attendees[i].equalsIgnoreCase("")) {
						contact = attendees[i].split("\\;");
						contactId = contact[0];
						name = contact[1];
						ContactImp ci = new ContactImp(name,
								Integer.parseInt(contactId));
						try {
							notes = contact[2];
							ci.addNotes(notes);
						} catch (ArrayIndexOutOfBoundsException ae) {

						}
						meetingContacts.add(ci);
						i++;
					}
					date = readLine[2];
					try {
						meetingNotes = readLine[3];
					} catch (ArrayIndexOutOfBoundsException e) {
						meetingNotes = null;
					}
					Date d = dateFormat.parse(date);
					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					if (cal.after(Calendar.getInstance())) {
						Meeting futureMeeting = new FutureMeetingImp(
								Integer.parseInt(meetingId), cal,
								meetingContacts);
						meetingList.add(futureMeeting);
						meetingCount++;
					} else if (cal.before(Calendar.getInstance())) {
						Meeting pastMeet = new PastMeetingImp(
								Integer.parseInt(meetingId), cal,
								meetingContacts, meetingNotes);
						meetingList.add(pastMeet);
						meetingCount++;
					}

					Iterator<Meeting> meetingListIterator = meetingList
							.iterator();
					while (meetingListIterator.hasNext()) {
						Meeting m = meetingListIterator.next();
					}

					lineIn = br.readLine();
				}
			}
			f.delete();
			br.close();
		} catch (ArrayIndexOutOfBoundsException | IOException | ParseException IOE) {
			// IOE.printStackTrace();
		}
	}

	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date)
			throws IllegalArgumentException {
		Calendar now = Calendar.getInstance();
		if (date.compareTo(now) < 0) {
			throw new IllegalArgumentException("Error! Something went wrong.");
		}
		MeetingImp newMeeting = new MeetingImp(++meetingCount, date, contacts);
		meetingList.add(newMeeting);
		return newMeeting.getId();
	}

	@Override
	public PastMeeting getPastMeeting(int id) throws IllegalArgumentException {
		return (PastMeeting) getMeeting(id);
	}

	@Override
	public FutureMeeting getFutureMeeting(int id)
			throws IllegalArgumentException {
		return (FutureMeeting) getMeeting(id);
	}

	@Override
	public Meeting getMeeting(int id) throws IllegalArgumentException {
		Iterator<Meeting> meetingListIterator = meetingList.iterator();
		while (meetingListIterator.hasNext()) {
			Meeting m = meetingListIterator.next();
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		Iterator<Meeting> meetingListIterator = meetingList.iterator();
		List<Meeting> futureMeetings = new ArrayList<Meeting>();
		while (meetingListIterator.hasNext()) {
			Meeting m = meetingListIterator.next();
			Set<Contact> attendees = m.getContacts();
			Iterator<Contact> contactIt = attendees.iterator();
			while (contactIt.hasNext()) {
				if (contactIt.next().getId() == contact.getId()) {
					Calendar now = Calendar.getInstance();
					if (m.getDate().after(now)) {
						futureMeetings.add(m);
					}
				}
			}

		}
		if (futureMeetings != null) {
			return futureMeetings;
		} else
			return null;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		Iterator<Meeting> meetingListIterator = meetingList.iterator();
		List<Meeting> futureMeetings = new ArrayList<Meeting>();
		while (meetingListIterator.hasNext()) {
			Calendar now = Calendar.getInstance();
			Meeting m = meetingListIterator.next();
			if (m.getDate().after(now)) {
				futureMeetings.add(m);
			}
		}
		if (futureMeetings != null) {
			return futureMeetings;
		} else
			return null;
	}

	public List<Meeting> getPastMeetingList(Calendar date) {
		Iterator<Meeting> meetingListIterator = meetingList.iterator();
		List<Meeting> pastMeetings = new ArrayList<Meeting>();
		while (meetingListIterator.hasNext()) {
			Calendar now = Calendar.getInstance();
			Meeting m = meetingListIterator.next();
			if (m.getDate().before(now)) {
				pastMeetings.add(m);
			}
		}
		if (pastMeetings != null) {
			return pastMeetings;
		} else
			return null;

	}

	@Override
	public List<Meeting> getPastMeetingList(Contact contact) {
		Iterator<Meeting> meetingListIterator = meetingList.iterator();
		List<Meeting> pastMeetings = new ArrayList<Meeting>();
		while (meetingListIterator.hasNext()) {
			Meeting m = meetingListIterator.next();
			Set<Contact> attendees = m.getContacts();
			Iterator<Contact> contactIt = attendees.iterator();
			while (contactIt.hasNext()) {
				if (contactIt.next().getId() == contact.getId()) {
					Calendar now = Calendar.getInstance();
					if (m.getDate().before(now)) {
						pastMeetings.add(m);
					}
				}
			}
		}
		if (pastMeetings != null) {
			return pastMeetings;
		} else
			return null;
	}

	@Override
	public int addNewPastMeeting(Set<Contact> contacts, Calendar date,
			String text) {
		if (contacts == null || date == null || text == null) {
			throw new NullPointerException();
		}
		if (contacts.size() == 0) {
			throw new IllegalArgumentException();
		}
		Iterator<Contact> it = contacts.iterator();
		while (it.hasNext()) {
			if (!contactList.contains(it.next())) {
				throw new IllegalArgumentException();
			}
		}
		Calendar cal = Calendar.getInstance();
		if (date.compareTo(cal) > 0) {
			throw new IllegalStateException();
		}
		MeetingImp newPastMeeting = new PastMeetingImp(++meetingCount, date,
				contacts, text);
		meetingList.add(newPastMeeting);
		return newPastMeeting.ID;
	}

	@Override
	public void addMeetingNotes(int id, String text) {
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Meeting m = it.next();
			if (m.getId() == id) {
				PastMeeting pm = (PastMeeting) m;
				pm.addNotes(text);
				meetingList.remove(m);
				meetingList.add(pm);
			}
		}
	}

	@Override
	public void addNewContact(String name, String notes) {
		Contact newContact = new ContactImp(name, ++contactCount);
		newContact.addNotes(notes);
		contactList.add(newContact);
		System.out.println("Contact Added.");
	}

	public Set<Contact> getContacts(int... id) {
		Object[] people = contactList.toArray();

		Set<Contact> theContacts = new HashSet<Contact>();

		for (int num : id) {
			for (int i = 0; i < people.length; i++) {
				Contact c = (Contact) people[i];
				if (c.getId() == num) {
					theContacts.add(c);
					System.out.print(c.getId() + "  num:  " + num);
				}
			}
		}

		return theContacts;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		Iterator<Contact> contactListIterator = contactList.iterator();
		Set<Contact> theContacts = new HashSet<Contact>();
		while (contactListIterator.hasNext()) {
			Contact c = contactListIterator.next();
			if (c.getName() == name) {
				theContacts.add(c);
			}
		}
		return theContacts;
	}

	@Override
	public void flush() {
		String CSV_SEPARATOR = ",";
		FileWriter fw;
		Iterator<Contact> contactListIterator = contactList.iterator();
		Iterator<Meeting> meetingListIterator = meetingList.iterator();
		try {
			fw = new FileWriter("data.csv");
			PrintWriter pw = new PrintWriter(fw);
			pw.println("contacts");
			while (contactListIterator.hasNext()) {
				Contact c = contactListIterator.next();
				int idNum = c.getId();
				pw.write("" + idNum);
				pw.write(CSV_SEPARATOR);
				pw.write(c.getName());
				pw.write(CSV_SEPARATOR);
				pw.write(c.getNotes() + "\n");
			}
			pw.println("meetings");
			while (meetingListIterator.hasNext()) {
				Meeting m = meetingListIterator.next();
				Set<Contact> gotContacts = (Set<Contact>) m.getContacts();
				Iterator<Contact> meetingContactsIt = gotContacts.iterator();
				pw.write("" + m.getId());
				pw.write(CSV_SEPARATOR);
				while (meetingContactsIt.hasNext()) {
					Contact mContact = meetingContactsIt.next();
					int mId = mContact.getId();
					pw.write("" + mId);
					pw.write(";");
					pw.write(mContact.getName());
					pw.write(";");
					pw.write(mContact.getNotes() + ":");
				}
				pw.write(CSV_SEPARATOR);
				Calendar cal = m.getDate();
				Date d = cal.getTime();
				pw.write(d.toString() + "\n");
				pw.write(CSV_SEPARATOR);
				if (m instanceof PastMeeting) {
					pw.write(((PastMeeting) m).getNotes());
				}
			}
			pw.write("\\");
			pw.flush();
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Contact getContact(String name) {
		Contact temp;
		Iterator<Contact> it = contactList.iterator();
		while (it.hasNext()) {
			temp = it.next();
			if (isInt(name)) {
				int id = Integer.parseInt(name);
				if (temp.getId() == id) {
					return temp;
				}
			} else {
				if (temp.getName().equalsIgnoreCase(name)) {
					return temp;
				}
			}
		}

		return null;
	}

	public boolean isInt(String in) {
		try {
			Integer.parseInt(in);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public Calendar findTime(String time) throws ParseException {

		Date date;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		date = dateFormat.parse(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public void viewCalendar() {
		Boolean flag = true;
		while (flag) {
			System.out
					.println("Do you want to search by Contact(1), Meeting ID(2), or Date(3)? Enter 4 to return to the main menu. ");
			String meetingSearch = null;
			Meeting tempMeeting = null;
			List<Meeting> meetings = new ArrayList<Meeting>();
			meetingSearch = System.console().readLine();
			if (meetingSearch.equalsIgnoreCase("1")) {
				Contact found;
				System.out.println("Who do you want to search for?  ");
				String personToFind = System.console().readLine();
				found = getContact(personToFind);
				if (found != null) {
					List<Meeting> fmList = getFutureMeetingList(found);
					List<Meeting> pmList = getPastMeetingList(found);
					meetings.addAll(fmList);
					meetings.addAll(pmList);
					if (!meetings.isEmpty()) {
						System.out.println("Contact \"" + found.getName()
								+ "\" is part of these meetings: ");
						Iterator<Meeting> meetingListIterator = meetings
								.iterator();
						while (meetingListIterator.hasNext()) {
							tempMeeting = meetingListIterator.next();
							System.out.println(tempMeeting.getId());
							System.out.println(tempMeeting.getDate().getTime());
							ifIsPastMeeting(tempMeeting);
							printMeetingContacts(tempMeeting.getContacts());
						}
					}
				}
			} else if (meetingSearch.equalsIgnoreCase("2")) {
				System.out.println("Enter Meeting ID?");
				String meetingToFind = System.console().readLine();
				if (isInt(meetingToFind)) {
					tempMeeting = getMeeting(Integer.parseInt(meetingToFind));
					if (tempMeeting != null) {
						System.out.println(tempMeeting.getId());
						System.out.println(tempMeeting.getDate().getTime());
						ifIsPastMeeting(tempMeeting);
						printMeetingContacts(tempMeeting.getContacts());
					}
				}

			} else if (meetingSearch.equalsIgnoreCase("3")) {
				Boolean flag2 = false;
				while (!flag2) {
					System.out
							.println("What day this meeting taking place? DD/MM/YYYY  ");
					String time = System.console().readLine();
					System.out
							.println("What time is it taking place at? hh:mm  ");
					time += " " + System.console().readLine();
					try {
						Calendar cal = findTime(time);
						meetings.addAll(getFutureMeetingList(cal));
						meetings.addAll(getPastMeetingList(cal));
						Iterator<Meeting> meetingListIterator = meetings
								.iterator();

						while (meetingListIterator.hasNext()) {
							tempMeeting = meetingListIterator.next();
							System.out.println("Meeting ID: "
									+ tempMeeting.getId());
							System.out.println("Time: "
									+ tempMeeting.getDate().getTime());
							ifIsPastMeeting(tempMeeting);
							System.out.println("Contacts: ");
							printMeetingContacts(tempMeeting.getContacts());
						}
						flag2 = true;
					} catch (ParseException pe) {
						System.out.println("Try entering the date again.");
						flag2 = false;
					}
				}
			} else {

				flag = false;
			}

		}
	}

	public void findContactChoice() {
		Boolean findPerson = true;
		while (findPerson) {
			System.out.println("Who do you want to find?  ");
			String searchFor = System.console().readLine().trim();
			Contact c = getContact(searchFor);
			if (c != null) {
				System.out.println("ID: " + c.getId() + "  Name: "
						+ c.getName() + "  Notes:  " + c.getNotes());
			} else {
				System.out.println("That contact doesn't exist. ");
			}
			System.out.println("Do you want to find another contact? (y/n)");
			String reSearch = System.console().readLine();
			if (reSearch.equalsIgnoreCase("n") || reSearch.equals(null)) {
				findPerson = false;
			}
		}

	}

	public void addMeetingChoice() {
		ArrayList<Integer> contactsToFind = new ArrayList<Integer>();
		Set<Contact> people = new HashSet<Contact>();
		Boolean flag = false;
		System.out.println("Add Contacts");
		while (flag == false) {
			try {
				int id = 0;
				System.out.println("Contact ID: ");
				id = Integer.parseInt(System.console().readLine());
				contactsToFind.add(id);
				System.out
						.println("Do you want to add another person to this meeting?(y/n) ");
				if (System.console().readLine().equalsIgnoreCase("y")) {
					flag = false;
				} else
					flag = true;
			} catch (NumberFormatException e) {
				System.out.println("That's not a choice, try again");
				flag = false;
			}
		}

		int[] findContacts = new int[contactsToFind.size()];
		for (int i = 0; i < findContacts.length; i++) {
			findContacts[i] = contactsToFind.get(i).intValue();
		}
		people = getContacts(findContacts);
		flag = false;
		while (flag == false) {
			System.out
					.println("What day is this meeting taking place? DD/MM/YYYY  ");
			String time = System.console().readLine();
			System.out.print("What time is it taking place at? hh:mm  ");
			time += " " + System.console().readLine();
			try {
				Calendar cal = findTime(time);
				if (cal.after(Calendar.getInstance())) {
					int meetingID = addFutureMeeting(people, cal);
					System.out.print("Meeting " + meetingID + " was created.");
					flag = true;
				} else if (cal.before(Calendar.getInstance())) {
					System.out.print("Enter meeting notes: ");
					String meetingNotes = System.console().readLine();
					int test = addNewPastMeeting(people, cal, meetingNotes);
					System.out.println(getMeeting(test).getContacts());
					flag = true;
				} else {
					flag = false;
				}
			} catch (ParseException pe) {
				System.out.println("Try entering the date/time again.");
				flag = false;
			}
		}
	}

	public void printMeetingContacts(Set<Contact> contactSet) {
		Iterator<Contact> setIt = contactSet.iterator();
		System.out.println("Meeting Contacts: ");
		while (setIt.hasNext()) {
			Contact tempContact = setIt.next();
			System.out.println("ID: " + tempContact.getId());
			System.out.println("Name: " + tempContact.getName());
			System.out.println("Notes: " + tempContact.getNotes());
		}
	}

	public void editMeetingNotes() {
		Boolean flag = true;
		Iterator<Meeting> it = meetingList.iterator();
		while (flag) {
			System.out
					.println("Which meeting do you want to add notes for? (Type e to return to main menu)  ");
			String choice = System.console().readLine();
			if (choice.equalsIgnoreCase("e")) {
				flag = false;
				return;
			}
			try {
				int meetingChoice = Integer.parseInt(choice);
				while (it.hasNext()) {
					Meeting tempM = it.next();
					if (tempM.getId() == meetingChoice) {
						PastMeeting pm = (PastMeeting) tempM;
						String notes = pm.getNotes();
						System.out.println("Enter your notes: ");
						notes += System.console().readLine();
						addMeetingNotes(meetingChoice, notes);
						flag = false;
					}
				}
			} catch (ClassCastException | NumberFormatException ne) {
				// ne.printStackTrace();
				flag = true;
			}
		}
	}

	public void ifIsPastMeeting(Meeting m) {
		try {
			PastMeeting past = (PastMeeting) m;
			if (past.getNotes() != null)
				System.out.println(past.getNotes());

		} catch (ClassCastException ce) {
			ce.getStackTrace();
		}

	}

}
