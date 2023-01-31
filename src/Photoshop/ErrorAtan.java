package Photoshop;

import java.util.ArrayList; 

public class ErrorAtan {

	public static void main (String[] args) {
		
		double a = 20.5;
		double sigma_a = 0.1;
		double b = 26.6;
		double sigma_b = 0.1; 
		System.out.println(atanError(a, b, sigma_a, sigma_b)); 
		
		ArrayList<Double> test = new ArrayList<Double>();
		test.add(37.3170);
		test.add(38.3180);
		test.add(37.8600);
		test.add(37.6206);
		test.add(37.6206);
		test.add(38.2621);
		
		double h = average(test);
		h = h - 1.0;
		System.out.println(h);
		
		System.out.println(average(test));
		
		ArrayList<Double> errorTest = new ArrayList<Double>();
		errorTest.add(0.00058); 
		errorTest.add(0.00048); 
		errorTest.add(0.00051); 
		errorTest.add(0.00051); 
		errorTest.add(0.00051); 
		errorTest.add(0.00049); 
		System.out.println(averageError(errorTest));
		
		ArrayList<Double> addtest = new ArrayList<Double>();
		addtest.add(0.00021);
		addtest.add(0.3);
		System.out.println(addingError(addtest));
		
	}
	
	public static ArrayList<Double> atanError(double a, double b, double sigma_a, double sigma_b ) {
		
		ArrayList<Double> m = new ArrayList<Double>();
		
		double storage = Math.cos(182.59287262);
		double convert = 180 / Math.PI;
		double temp1 = convert * storage / (b + storage * a * storage * a / b) * sigma_a;
		double temp2 = convert * storage / (b * b + storage * a * storage * a) * sigma_b; 
		
		double x = Math.sqrt(temp1*temp1 + temp2*temp2); 
		double temp3 = a * storage / b; 
		double y = Math.atan(temp3) * 180 / Math.PI -1.12317908;
		m.add(x);
		m.add(y);
		
		
		
		return m; 
		
		
	}
	
	
	public static double average(ArrayList<Double> mylist) {
		
		double a = 0; 
		
		for (int i = 0; i < mylist.size(); i++) {
			a += mylist.get(i);
		}
		
		a /= (mylist.size()); 
		
		return a; 
	}
		
	
	public static double averageError(ArrayList<Double> l) {
		
		double x = 0; 
		
		for (int i = 0; i < l.size(); i++) {
			double n = l.size();
			double sigma = l.get(i);
			double temp = (1/n * sigma) * (1/n * sigma);
			x += temp; 
		}
		
		x = Math.sqrt(x);
		
		return x; 
		
	}
	
	public static double addingError(ArrayList<Double> l) {
		double x = 0;
		
		for (int i = 0; i < l.size(); i++) {
			double sigma = l.get(i);
			double temp = sigma * sigma;
			x += temp;
		}
		
		return Math.sqrt(x);
	}
	
	
		
		
	
}
