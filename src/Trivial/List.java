package Trivial;
import java.util.ArrayList;

public class List {
	
	
	public static void main(String[] arg) {
		
/*		ArrayList<Integer> n = new ArrayList<Integer>();
		
		n.add(1);
		n.add(2);
		n.add(3);
		n.add(4);
		n.add(5);
		
		System.out.println(average(n)); 
		
		ArrayList<Integer> m = new ArrayList<Integer>();
		
		m.add(1);
		m.add(2);
		m.add(3);
		m.add(4);
		m.add(5);
		
		System.out.println(min(m)); 
		
		System.out.println(fibonacci(20)); 
		
		System.out.println(random(100, 10)); 
		
	 
		
		ArrayList<Integer> t = new ArrayList<Integer>();
		
		t.add(-1);
		t.add(1);
		t.add(-1);
		
		separate(t); 
		
		ArrayList<Double> test = new ArrayList<Double>();
		
		test.add(1.0);
		test.add(2.0);
		test.add(3.0);
		reverse(test);
		
		System.out.println(test); 
		
		System.out.println(factor(56)); */
		
		System.out.println(primeFactor(16)); 
		
	}
	
	public static int average(ArrayList<Integer> mylist) {
		
		int a = 0; 
		
		for (int i = 0; i < mylist.size(); i++) {
			a += mylist.get(i);
		}
		
		a /= (mylist.size()); 
		
		return a; 
	}
	
	
	public static int min(ArrayList<Integer> mylist) {
		
		int aI = 0;  
		
		for (int i = 1; i < mylist.size(); i++) {
			
			if (mylist.get(i) < mylist.get(aI)) {
				aI = i;
			}
			
		}
		
		return aI; 
		
	}
	
	public static ArrayList<Integer> fibonacci(int n) {
		
		ArrayList<Integer> m = new ArrayList<Integer>(); 
		
		for (int i = 0; i < n; i++) {
			
			if (i == 0) {
				m.add(0);
			} else if(i == 1) {
				m.add(1);
			} else if (i == 2){
				m.add(1);
			} else {
				int a = m.get(i-2) + m.get(i-1); 
				m.add(a);
			}
			
		}
		
		return m; 
		
	}
	
	public static ArrayList<Integer> random(int n, int x) {
		
		ArrayList<Integer> m = new ArrayList<Integer>();
		
		for (int i = 0; i < n; i++) {
			int min = -x; 
			int max = x; 
			int a = (int) Math.floor(Math.random() * (max-min+1) + min); 
			m.add(a); 
		}
		
		return m;
	}
	
	public static void separate(ArrayList<Integer> myList) {
		
		ArrayList<Integer> p = new ArrayList<Integer>();
		ArrayList<Integer> n = new ArrayList<Integer>();
		
		for (int i = 0; i < myList.size(); i++) {
			
			if(myList.get(i) < 0) {
				n.add(myList.get(i));
			} else {
				p.add(myList.get(i));
			}
			
		}
		
		System.out.println(p);
		System.out.println(n); 
	}
	
	
	public static void reverse(ArrayList<Double> myList) {
		
		for (int i = 0; i < myList.size()/2; i++) {
			double temp = myList.get(i);
			myList.set(i, myList.get(myList.size() - i - 1));
			myList.set(myList.size() - i - 1, temp);
		}
		
	}
	
	public static ArrayList<Integer> factor(int n) {
		
		ArrayList<Integer> f = new ArrayList<Integer>();
		
		for (int i = 0; i < n; i++) {
			if (n % (i + 1) == 0) {
				f.add(i+1); 
			}
		}
		
		
		return f; 
	}
	
	public static ArrayList<Integer> primeFactor(int n) {
		
		ArrayList<Integer> f = new ArrayList<Integer>();
		int a = 0; 
		
		for (int i = 1; i < n; i++) {
			if (n % (i+1) == 0) {
				n = n/(i+1);
				a++;
				i--;
			} else if (a != 0) {
				f.add(i + 1); 
				f.add(a); 
				a = 0; 			
			} else {
				a = 0; 
			}
			
		}
		
		
		return f; 
	}
	
	
}
