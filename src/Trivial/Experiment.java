package Trivial;
import java.util.ArrayList;

public class Experiment {
	
	
	public static void main(String[] args) {
		
/*		ArrayList<Character> testList = new ArrayList<Character>();
		testList.add('f');
		testList.add('s');
		testList.add('f');
		testList.add('s');
		testList.add('f');
		testList.add('s');
		testList.add('f');
		System.out.println(removeEverySecond(testList)); 
		
		System.out.println(arrayToList(new String [] {"sd", "ff", "dd"})); 
		
		ArrayList<Double> testList = new ArrayList<Double>();
		testList.add(1.0);
		testList.add(1.0);
		testList.add(2.0);
		testList.add(2.0);
		testList.add(3.0);
		testList.add(3.0);
		testList.add(4.0);
		System.out.println(doubleTimes(testList)); */
		
		System.out.println(letters(26)); 
		
	}
	
	public static ArrayList<Character> removeEverySecond(ArrayList<Character> mylist) {
		
		
		for (int i = 1; i < mylist.size(); i++) { 
			
			mylist.remove(i);
		}
		
		
		return mylist;
		
	}
	
	public static ArrayList<String> arrayToList(String[] arr) {
		
		ArrayList<String> newlist = new ArrayList<String>(); 
		
		for (int i = arr.length - 1; i >= 0; i--) {
			
			newlist.add(arr[i]); 
			
			
		}
		
		return newlist; 
	}
	
	public static ArrayList<Double> doubleTimes(ArrayList<Double> d) {
		
		for (int i = 0; i < d.size(); i++) {
			double q = d.get(i);
			q = q * 2;
			d.set(i, q); 
		}
		
		return d; 
	}

	public static ArrayList<Character> letters(int n) {
		
		ArrayList<Character> newList = new ArrayList<Character>();
		
		Character[] Alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's','t','u','v','w','x','y','z'}; 
		
		if (n > 26) {
			System.out.println("Outofbound"); 
		} else {
		for (int i = 0; i < n; i++ ) {
			if (i % 2 == 0) {
				newList.add(0, Alphabet[i]); 
			} else {
				newList.add(newList.size(), Alphabet[i]); 
			}
		}
		}
		
		return newList; 
	}

}
