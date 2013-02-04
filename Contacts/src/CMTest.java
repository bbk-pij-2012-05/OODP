import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CMTest extends ContactManagerImp {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	Contact c;
	Contact c2;
	Set<Contact> conList;
	Calendar futCal;
	Calendar pastCal;
	PastMeeting pastMeeting;
	FutureMeeting futureMeeting;
	Date d;
	DateFormat dateFormat;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
		d = new Date();
		String pastTime = "13/02/2011 2:00";
		String futureTime = "10/05/2013 4:30";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		d = dateFormat.parse(pastTime);
		pastCal = Calendar.getInstance();
		pastCal.setTime(d);
		d = dateFormat.parse(futureTime);
		futCal = Calendar.getInstance();
		futCal.setTime(d);
		pastMeeting = new PastMeetingImp(1, pastCal, conList, "Notes");
		futureMeeting = new FutureMeetingImp(2, futCal, conList);
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	    System.setErr(null);
	    contactList.clear();
	    meetingList.clear();
	}

	// @Test
	public void testImportData() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddFutureMeeting() {
		futCal.set(2013, 03, 22, 3, 30);
		pastCal.set(2011, 02, 20, 4, 15);
		int id = addFutureMeeting(conList, futCal);
		assertEquals(id, 1);
	}

	@Test
	public void testGetPastMeeting() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		int[] ids = { 1, 2 };
		Set<Contact> getContactsFrom = getContacts(ids);
		addNewPastMeeting(getContactsFrom, pastCal, "meeting");
		PastMeeting pm = getPastMeeting(1);
		assertEquals(pm.getId(), 1);
	}

	@Test
	public void testGetFutureMeeting() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		int[] ids = { 1, 2 };
		Set<Contact> getContactsFrom = getContacts(ids);
		addFutureMeeting(getContactsFrom, futCal);
		FutureMeeting fm = getFutureMeeting(1);
		assertEquals(1, fm.getId());
		Boolean check = false;
		if (futCal.compareTo(fm.getDate()) == 0) {
			check = true;
		}
		assertTrue(check);
	}

	@Test
	public void testGetMeeting() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		int[] ids = { 1, 2 };
		Set<Contact> getContactsFrom = getContacts(ids);
		addFutureMeeting(getContactsFrom, futCal);
		Meeting m = getMeeting(1);
		assertEquals(m.getId(), 1);
	}

	@Test
	public void testGetFutureMeetingListContact() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		addFutureMeeting(contactList, futCal);
		Iterator<Contact> it = contactList.iterator();
		Contact c = it.next();
		List<Meeting> meetings = getFutureMeetingList(c);
		Iterator<Meeting> mIt = meetings.iterator();
		Meeting m = mIt.next();
		assertEquals(1, m.getId());
	}

	@Test
	public void testGetFutureMeetingListCalendar() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		addFutureMeeting(contactList, futCal);
		List<Meeting> meetings = getFutureMeetingList(futCal);
		Iterator<Meeting> mIt = meetings.iterator();
		Meeting m = mIt.next();
		assertEquals(1, m.getId());
	}

	@Test
	public void testGetPastMeetingListCalendar() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		addNewPastMeeting(contactList, pastCal, "notes");
		List<Meeting> meetings = getPastMeetingList(futCal);
		Iterator<Meeting> mIt = meetings.iterator();
		Meeting m = mIt.next();
		assertEquals(1, m.getId());
	}

	@Test
	public void testGetPastMeetingListContact() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		addNewPastMeeting(contactList, pastCal, "notes");
		Iterator<Contact> it = contactList.iterator();
		Contact c = it.next();
		List<PastMeeting> meetings = getPastMeetingList(c);
		Iterator<PastMeeting> mIt = meetings.iterator();
		Meeting m = mIt.next();
		assertEquals(1, m.getId());
	}

	@Test
	public void testAddNewPastMeeting() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		addNewPastMeeting(contactList, pastCal, "notes");
		Meeting m = getMeeting(1);
		Boolean check = m.getDate().equals(pastCal);
		assertEquals(1, m.getId());
		assertTrue(check);
	}

	@Test
	public void testAddMeetingNotes() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		addNewPastMeeting(contactList, pastCal, "");
		addMeetingNotes(1, "New Notes");
		PastMeeting m = getPastMeeting(1);
		Boolean check = m.getNotes().equalsIgnoreCase("New Notes");
		assertTrue(check);
	}

	@Test
	public void testAddNewContact() {
		addNewContact("Joe", "notes");
		Iterator<Contact> it = contactList.iterator();
		Contact c = it.next();
		assertEquals("Joe", c.getName());
		assertEquals("notes", c.getNotes());
	}

	@Test
	public void testGetContactsIntArray() {
		addNewContact("Joe", "notes");
		addNewContact("Liz", "more notes");
		int nums[] = { 1, 2 };
		Set<Contact> con = getContacts(nums);
		Boolean checks[] = new Boolean[2];
		int i = 0;
		Iterator<Contact> it = con.iterator();
		while (it.hasNext()) {
			Contact c = it.next();
				if (contactList.contains(c)) {
					checks[i] = true;
				} else
					checks[i] = false;
				i++;
		}
		assertEquals(true, checks[0]);
		assertEquals(true, checks[1]);
	}

	
	 @Test 
	 public void testGetContactsString() { 
	addNewContact("Joe", "notes");
	addNewContact("Joe", "Another Joe");
	Set<Contact> con = getContacts("Joe");
	Boolean checks[] = new Boolean[2];
	int i = 0;
	Iterator<Contact> it = con.iterator();
	while (it.hasNext()) {
		Contact c = it.next();
			if (c.getName().equalsIgnoreCase("Joe")) {
				checks[i] = true;
			} else
				checks[i] = false;
			i++;
	}
	assertEquals(true, checks[0]);
	assertEquals(true, checks[1]);
 }
	 /* 
	 @Test 
	 public void testFlush() { 
	 
	 }
	 */
	 @Test 
	 public void testGetContact() { 
		 addNewContact("Joe", "notes");
			addNewContact("Liz", "more notes");
			Contact c = getContact("Joe");
			assertEquals("Joe", c.getName());
	 }
	 
	 @Test 
	 public void testIsInt() {
		 int i = 3;
		 String s = "name";
		 assertTrue(isInt(Integer.toString(i)));
		 assertFalse(isInt(s));
	 }
	 
	 @Test 
	 public void testFindTime() {
		Date date;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		Calendar pre = Calendar.getInstance();
		Calendar post = Calendar.getInstance();
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		try{
		date = dateFormat.parse("13/02/2011 2:00");
		pre.setTime(date);
		date = dateFormat.parse("10/05/2013 4:30");
		post.setTime(date);
		before = findTime("13/02/2011 2:00");
		after = findTime("10/05/2013 4:30");
		}catch(ParseException pe){
			pe.printStackTrace();
		}
		assertEquals(0, before.compareTo(pre));
		assertEquals(0, after.compareTo(post));
	 }
	 
	@Test 
	 public void testPrintMeetingContacts() {
		addNewContact("Joe", "notes");
		printMeetingContacts(contactList);
		String s = outContent.toString();
		assertTrue(s.contains("ID: 1"));
		assertTrue(s.contains("Name: Joe"));
		assertTrue(s.contains("Notes: notes"));
	}
	 

}
