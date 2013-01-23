

public class ContactImp implements Contact {

	int contactId;
	String contactName;
	String contactNotes = "";// = new ArrayList<String>();

	public ContactImp(String name, int id){
		contactName = name;
		contactId = id;
	}
	
	@Override
	public int getId() {
		return contactId;
	}

	@Override
	public String getName() {
		return contactName;
	}

	@Override
	public String getNotes() {
		return contactNotes.toString().replace("[", "").replace("]", "");
	}

	@Override
	public void addNotes(String note) {
		if(contactNotes.isEmpty()){
			contactNotes = note.trim();
		}else
			contactNotes += note.trim();
	}

}
