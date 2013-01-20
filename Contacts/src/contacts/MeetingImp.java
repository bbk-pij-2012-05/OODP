import java.util.Calendar;
import java.util.Set;

public class MeetingImp implements Meeting{
	
	int ID;
	Calendar scheduledDate;
	Set<Contact> participants;
	
	MeetingImp(int mId, Calendar date, Set<Contact> partic){
		participants = partic;
		scheduledDate = date;
		ID = mId;
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

}
