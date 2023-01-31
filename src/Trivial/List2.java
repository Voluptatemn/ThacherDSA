package Trivial;
import java.util.ArrayList; 


public class List2 {
	
	public static void main(String[] arg) {
//		System.out.println(perfectSquare(5));
		
/*		ArrayList<String> myList = new ArrayList<String>();
		myList.add("Justin");
		myList.add("PRob");
		System.out.println(java.util.Arrays.toString(characterCount(myList)));  
		ArrayList<Character> myList = new ArrayList<Character>();
		myList.add('a');
		myList.add('n'); 
		System.out.println(concatenated(myList)); */
		ArrayList<Double> myList = new ArrayList<Double>();
		myList.add(3.0);
		myList.add(6.0);
		myList.add(1.0);
		myList.add(8.0);
		myList.add(19.0);
		System.out.println(myList);
		allAverage(myList); 
		System.out.println(myList);
		
	}
	
	
	public static ArrayList<Integer> perfectSquare(int n) {
		ArrayList<Integer> myList = new ArrayList<Integer>();
		for (int i = 0; i < n + 1; i++) {
			int a = i * i;
			myList.add(a); 
		}
		return myList;
	}
	
	public static int[] characterCount(ArrayList<String> n) {
			int [] freqs = new int[26];
			for (String s : n) {
				s = s.toLowerCase();
				for (int i = 0; i < s.length(); i++) {
					freqs[s.charAt(i) - 97] ++; 
				
				}
		}
		
		return freqs;
		
	}
	
	public static String concatenated(ArrayList<Character> n) {
		
		String output = ""; 
		for (Character s : n) {
			output += s; 
		}
		
		return output; 
	}
	
	public static void allAverage(ArrayList<Double> n) {
		double average = 0; 
		for (int i = 0; i < n.size(); i++) {
			for (int j = 0; j < n.size(); j++) {
				average += n.get(j);
			}
			average /= n.size(); 
			n.set(i, average);
			average = 0; 
		}
	}
	
	

}
