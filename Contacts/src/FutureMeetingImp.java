import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImp extends MeetingImp implements FutureMeeting {

	FutureMeetingImp(int mId, Calendar date, Set<Contact> partic){
		super(mId, date, partic);
	}

}
