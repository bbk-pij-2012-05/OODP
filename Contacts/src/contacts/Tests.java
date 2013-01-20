package contacts;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Tests extends ContactManagerImp{
	Contact c;
	Contact c2;
	Set<Contact> conList;
	Calendar futCal;
	Calendar pastCal;
	PastMeeting pastMeeting;
	FutureMeeting futureMeeting;
	Date d;
	DateFormat dateFormat;
	
	
	@Before
	public void setUp() throws Exception {
		emptyLists();
		c = new ContactImp("Joe", 1);
		c2 = new ContactImp("Liz", 2);
		conList = new HashSet<Contact>();
		conList.add(c2);
		conList.add(c);
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
	
	//@Test
	public void fileTest() {
		fail("Not yet implemented");
	}
	@Test
	public void checkEmpty(){
		addNewContact("Joe", "notes");
		addNewContact("Liz", "Other notes");
		int[] ids = {1,2};
		Set<Contact> getContactsFrom = getContacts(ids);
		addNewPastMeeting(getContactsFrom, pastCal, "meeting");
		PastMeeting pm = getPastMeeting(1);
		addFutureMeeting(getContactsFrom, futCal);
		FutureMeeting fm = getFutureMeeting(2);
		emptyLists();
		Contact c = getContact("1");
		Meeting m = getMeeting(1);
		assertEquals(null, c);
		assertEquals(null, m);
	}
	
	@Test
	public void testAddFutureMeeting(){
		futCal.set(2013, 03, 22, 3, 30);
		pastCal.set(2011, 02, 20, 4, 15);
		int id = addFutureMeeting(conList, futCal);
		assertEquals(id, 1);
	}
	
	/*@Test
	public void testAddFutureMeetingPastMeeting(){
		int fail = addFutureMeeting(conList, pastCal);
		fail();
	}*/
	
	@Test
	public void testGetPastMeeting(){
		addNewContact("Joe", "notes");
		addNewContact("Liz", "Other notes");
		int[] ids = {1,2};
		Set<Contact> getContactsFrom = getContacts(ids);
		addNewPastMeeting(getContactsFrom, pastCal, "meeting");
		PastMeeting pm = getPastMeeting(1);
		assertEquals(pm.getId(), 1);
	}
	
	@Test
	public void testGetFutureMeeting(){
		emptyLists();
		addNewContact("Joe", "notes");
		addNewContact("Liz", "Other notes");
		int[] ids = {1,2};
		Set<Contact> getContactsFrom = getContacts(ids);
		addFutureMeeting(getContactsFrom, futCal);
		FutureMeeting fm = getFutureMeeting(1);
		assertEquals(fm.getId(), 1);
	}
	
	@Test
	public void getMeeting(){
		emptyLists();
		addNewContact("Joe", "notes");
		addNewContact("Liz", "Other notes");
		int[] ids = {1,2};
		Set<Contact> getContactsFrom = getContacts(ids);
		addFutureMeeting(getContactsFrom, futCal);
		Meeting m = getMeeting(1);
		assertEquals(m.getId(), 1);
	}
	
}
