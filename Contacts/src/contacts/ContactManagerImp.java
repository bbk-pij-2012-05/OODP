package contacts;

import java.io.BufferedReader;
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
	int contactCount = 0;
	int meetingCount = 0;

	public static void main(String[] args) {
		ContactManagerImp manage = new ContactManagerImp();
		manage.init();

	}

	public void init() {
		importData();

		addNewContact("Joe", "IB Tech");
		addNewContact("Liz", "IB Tech");
		addNewContact("Eoin", "IB Tech");
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 3, 22);
		Iterator<Contact> cIt = contactList.iterator();
		Set<Contact> attending = new HashSet<Contact>();
		while (cIt.hasNext()) {
			Contact c = cIt.next();
			System.out.print("\nContact:" + c.getName());
			System.out.print("\nContact ID:" + c.getId());
			attending.add(c);
		}
		addFutureMeeting(attending, cal);
		addNewPastMeeting(attending, cal, "Notes");
		addNewPastMeeting(attending, cal, "Notes2");
		addNewPastMeeting(attending, cal, "Notes3");
		addNewPastMeeting(attending, cal, "Notes4");
		Iterator<Meeting> mIt = meetingList.iterator();
		while (mIt.hasNext()) {
			Meeting m = mIt.next();
			System.out.println("Meeting: " + m.getId());
		}
		addMeetingNotes(1, "Notes go here");
		flush();
	}

	public void importData() {
		try {
			FileReader fr = new FileReader("data1.csv");
			BufferedReader br = new BufferedReader(fr);
			String lineIn = br.readLine();
			String[] readLine, contact, attendees;
			String contactId, name, notes, meetingId, date, meetingNotes;
			DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
			if (lineIn.equalsIgnoreCase("contacts")) {
				lineIn = br.readLine();
				while (!lineIn.equalsIgnoreCase("")
						&& !lineIn.equalsIgnoreCase("meetings")) {
					System.out.print("Read in  " + lineIn);
					readLine = lineIn.split("\\,");
					contactId = readLine[0];
					name = readLine[1];
					notes = readLine[2];
					ContactImp c = new ContactImp(name,
							Integer.parseInt(contactId));
					c.addNotes(notes);
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
					System.out.print(meetingId);
					attendees = readLine[1].split("\\:");
					int i = 0;
					while (i < attendees.length && !attendees[i].equalsIgnoreCase("")) {
							System.out.print(attendees[i]);

							contact = attendees[i].split("\\;");
							contactId = contact[0];
							name = contact[1];
							notes = contact[2];
							ContactImp ci = new ContactImp(name,
									Integer.parseInt(contactId));
							ci.addNotes(notes);
							meetingContacts.add(ci);
							i++;
						}
					date = readLine[2];
					try{
						meetingNotes = readLine[3];
					}catch(ArrayIndexOutOfBoundsException e){
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
					lineIn = br.readLine();
				}
			}
			br.close();
		} catch (IOException | ParseException IOE) {
			System.out.println("Oops!");
			IOE.printStackTrace();
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
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Meeting m = it.next();
			if (m.getId() == id) {
				Calendar c = Calendar.getInstance();
				if (m.getDate().after(c)) {
					throw new IllegalArgumentException();
				}
				return (PastMeeting) m;
			}
		}
		return null;
	}

	@Override
	public FutureMeeting getFutureMeeting(int id)
			throws IllegalArgumentException {
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Meeting m = it.next();
			if (m.getId() == id) {
				Calendar c = Calendar.getInstance();
				if (m.getDate().before(c)) {
					throw new IllegalArgumentException();
				}
				return (FutureMeeting) m;
			}
		}
		return null;
	}

	@Override
	public Meeting getMeeting(int id) throws IllegalArgumentException {
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Meeting m = it.next();
			if (m.getId() == id) {
				return m;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
		List<Meeting> futureMeetings = new ArrayList<Meeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Set<Contact> attendees = it.next().getContacts();
			Iterator<Contact> contactIt = attendees.iterator();
			while (contactIt.hasNext()) {
				if (contactIt.next().getId() == contact.getId()) {
					Calendar now = Calendar.getInstance();
					Meeting m = it.next();
					if (m.getDate().after(now)) {
						futureMeetings.add(m);
					}
				}
			}

		}
		return futureMeetings;
	}

	@Override
	public List<Meeting> getFutureMeetingList(Calendar date) {
		List<Meeting> futureMeetings = new ArrayList<Meeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Calendar now = Calendar.getInstance();
			Meeting m = it.next();
			if (m.getDate().after(now)) {
				futureMeetings.add(m);
			}
		}
		return futureMeetings;
	}

	@Override
	public List<PastMeeting> getPastMeetingList(Contact contact) {
		List<PastMeeting> pastMeetings = new ArrayList<PastMeeting>();
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Set<Contact> attendees = it.next().getContacts();
			Iterator<Contact> contactIt = attendees.iterator();
			while (contactIt.hasNext()) {
				if (contactIt.next().getId() == contact.getId()) {
					Calendar now = Calendar.getInstance();
					PastMeeting m = (PastMeeting) it.next();
					if (m.getDate().before(now)) {
						pastMeetings.add(m);
					}
				}
			}
		}
		return pastMeetings;
	}

	@Override
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date,
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
		MeetingImp newPastMeeting = new PastMeetingImp(++meetingCount, date,
				contacts, text);
		meetingList.add(newPastMeeting);
	}

	@Override
	public void addMeetingNotes(int id, String text) {
		Iterator<Meeting> it = meetingList.iterator();
		while (it.hasNext()) {
			Meeting m = it.next();
			if (m.getId() == id) {
				// change to past meeting and add notes
				System.out.print("\n" + m.getId());
			}
		}
	}

	@Override
	public void addNewContact(String name, String notes) {
		Contact newContact = new ContactImp(name, ++contactCount);
		newContact.addNotes(notes);
		contactList.add(newContact);
	}

	@Override
	public Set<Contact> getContacts(int id) {
		Iterator<Contact> it = contactList.iterator();
		Set<Contact> theContacts = new HashSet<Contact>();
		while (it.hasNext()) {
			Contact c = it.next();
			if (c.getId() == id) {
				theContacts.add(c);
			}
		}
		return theContacts;
	}

	@Override
	public Set<Contact> getContacts(String name) {
		Iterator<Contact> it = contactList.iterator();
		Set<Contact> theContacts = new HashSet<Contact>();
		while (it.hasNext()) {
			Contact c = it.next();
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
		Iterator<Contact> contactIt = contactList.iterator();
		Iterator<Meeting> meetingIt = meetingList.iterator();
		try {
			fw = new FileWriter("data.csv");
			PrintWriter pw = new PrintWriter(fw);
			pw.println("contacts");
			while (contactIt.hasNext()) {
				Contact c = contactIt.next();
				int idNum = c.getId();
				pw.write("" + idNum);
				pw.write(CSV_SEPARATOR);
				pw.write(c.getName());
				pw.write(CSV_SEPARATOR);
				pw.write(c.getNotes() + "\n");
			}
			pw.println("meetings");
			while (meetingIt.hasNext()) {
				Meeting m = meetingIt.next();
				Iterator<Contact> meetingContactsIt = m.getContacts()
						.iterator();
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
				DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
				Calendar cal = m.getDate();
				Date d = cal.getTime();
				pw.write(d.toString() + "\n");
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

}
