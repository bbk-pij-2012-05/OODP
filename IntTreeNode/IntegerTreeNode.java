public class IntegerTreeNode {
	int value;
	IntegerTreeNode left;
	IntegerTreeNode right;

	IntegerTreeNode(int num) {
		value = num;
		right = null;
		left = null;
	}

	public void add(int newNumber) {
		if (newNumber > this.value) {
			if (right == null) {
				right = new IntegerTreeNode(newNumber);
			} else {
				right.add(newNumber);
			}
		} else {
			if (left == null) {
				left = new IntegerTreeNode(newNumber);
			} else {
				left.add(newNumber);
			}
		}
	}

	public boolean contains(int num){
		if(this.value == num){
			return true;
		}
		else if(num > this.value)
			return right.contains(num);
		else if(num < this.value)
			return left.contains(num);
		else
			return false;
	}

	public int getMax() {
		if(right != null){
			return right.getMax();
		}
		else
			return this.value;
	}

	public int getMin() {
		if(left != null){
			return left.getMin();
		}
		else
			return this.value;
	}

}
