public class TreeIntSet implements IntSet{

	int value;
	TreeIntSet left;
	TreeIntSet right;

	TreeIntSet(int num) {
		value = num;
		right = null;
		left = null;
	}


	public static void main(String[] args) {
		TreeIntSet node = new TreeIntSet(25);
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

		if(node.containsVerbose(70)){
			System.out.println("True");
		}
		else
			System.out.println("False");

		String s = node.toString();
		System.out.println(s);

	}

	@Override
	public void add(int num) {
		if (num > this.value) {
			if (right == null) {
				right = new TreeIntSet(num);
			} else {
				right.add(num);
			}
		} else {
			if (left == null) {
				left = new TreeIntSet(num);
			} else {
				left.add(num);
			}
		}
	}

	@Override
	public boolean contains(int num) {
		if(this.value == num){
			return true;
		}
		else if(num > this.value  && right != null)
			return right.contains(num);
		else if(num < this.value && left != null)
			return left.contains(num);
		else
			return false;
	}

	@Override
	public boolean containsVerbose(int num) {
		System.out.println(this.value);
		if(this.value == num){
			System.out.println("End verbose");
			return true;
		}
		else if(num > this.value && right != null){
			return right.containsVerbose(num);
		}
		else if(num < this.value && left != null){
			return left.containsVerbose(num);
		}

		else{
			return false;
		}
	}

	public String toString(){
		String s = Integer.toString(this.value);
		s = s + ", ";
		if(left != null){
			s = (s + left.toString());
		}
		if(right != null){
			s = (s + right.toString());
		}

		return s.substring(0, s.length()-1);
	}

}
