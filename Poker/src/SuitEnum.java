
public enum SuitEnum {

	Heart("H"), Club("C"), Diamond("D"), Spade("S");
	
	 private String suit;
	 
	 SuitEnum(String name){
		 this.suit = name;
	 }
	 
	 String getSuit(){
		 return this.suit;
	 }
	
}
