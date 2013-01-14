

import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImp extends MeetingImp implements FutureMeeting {

	FutureMeetingImp(int mId, Calendar date, Set<Contact> partic){
		super(mId, date, partic);
	}
	
	/*@Override
	public int getId() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public Calendar getDate() {
		return scheduledDate;
	}

	@Override
	public Set<Contact> getContacts() {
		return participants;
	}*/

}
