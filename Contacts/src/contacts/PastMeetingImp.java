package contacts;

import java.util.Calendar;
import java.util.Set;

public class PastMeetingImp extends MeetingImp implements PastMeeting, Meeting{
	
	String notes;
	
	PastMeetingImp(int mId, Calendar date, Set<Contact> partic, String meetingNotes){
		super(mId, date, partic);
		notes = meetingNotes;
	}
	
	@Override
	public int getId() {
		return ID;
	}

	@Override
	public Calendar getDate() {
		return scheduledDate;
	}

	@Override
	public Set<Contact> getContacts() {
		return participants;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public void addNotes(String newNotes) {
		notes = newNotes;
	}

}
