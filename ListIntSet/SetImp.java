public class SetImp {

	private Node listSet = null;

	public static void main(String[] args) {
		SetImp hm = new SetImp();
		hm.init();
	}
	void init(){
		listSet = new Node(33);
		listSet.add(55);
		listSet.add(23);
		listSet.add(4);
		listSet.add(64);
		listSet.add(55);
		listSet.add(24);
		listSet.add(89);
		listSet.add(46);
		boolean found = listSet.contains(46);
		System.out.println("Was 46 found? " + found);
		boolean found2 = listSet.containsVerbose(53);
		System.out.println("Was 53 found? " + found2);
	}

}
