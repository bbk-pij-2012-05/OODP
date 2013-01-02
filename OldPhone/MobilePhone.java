public class MobilePhone extends OldPhone {

	public MobilePhone(String brand) {
		super(brand);
	 	// TODO Auto-generated constructor stub
	}

	private String[] lastTen = new String[10];

	public void call(String s) {
		super.call(s);
		if (lastTen[9] != null) {
			for (int i = 0; i < lastTen.length - 1; i++) {
				lastTen[i] = lastTen[i + 1];
			}
			lastTen[9] = s;
		} else {
			for (int i = 0; i < lastTen.length; i++) {
				if (lastTen[i] == null) {
					lastTen[i] = s;
					break;
				}
			}
		}
	}

	public void ringAlarm(String s) {
		System.out.println("Ringing alarm " + s);
	}

	protected void playGame(String s) {
		System.out.println("Opening game " + s);
	}

	public void printLastNumbers() {
		for (int i = 0; i < lastTen.length; i++) {
			if (lastTen[i] != null) {
				System.out.println("Number " + i + ": " + lastTen[i]);
			}
		}
	}
}
