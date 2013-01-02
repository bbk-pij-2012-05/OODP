import java.util.ArrayList;

public class Entry {
//	public int key;
	public ArrayList<String> name;
	
	Entry(String n){
		name = new ArrayList<String>();
		add(n);
	}
	
	public boolean contains(String s){
		return name.contains(s);
	}
	
	public void add(String newName){
		name.add(newName);
	}
	
	public String[] get(int key){
		String[] newString = new String[name.size()];
		for(int i = 0; i < name.size(); i++){
			newString[i] = name.get(i);
		}
		return newString;
	}
	
	public void remove(String s){
		if(contains(s) == false){
			System.out.println("Not there to remove!");
			return;
		}
		else{
			int index = name.indexOf(s);
			name.remove(index);
			return;
		}
	}
	
	public boolean isEmpty(){
		return name.isEmpty();		
	}
	
}
