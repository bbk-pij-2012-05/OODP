public class treeImp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IntegerTreeNode node = new IntegerTreeNode(25);
		node.add(45);
		node.add(5);
		node.add(15);
		node.add(43);
		node.add(100);
		node.add(52);
		node.add(1);
		node.add(22);
		node.add(67);
		node.add(86);

		if(node.contains(5)){
			System.out.println("True");
		}
		else
			System.out.println("False");

		System.out.println("The max is " + node.getMax());
		System.out.println("The min is " + node.getMin());
		String s = node.toString();
		System.out.println(s);
		int depth = node.depth();
		System.out.println("\n" + depth);
	}

}
