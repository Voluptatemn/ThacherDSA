package WinterFinals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FinalProject_Sam {
	
	// holds the branches before tree construction
	ArrayList<Branch<Integer>> branches = new ArrayList<Branch<Integer>>();
	
	// holds the numbers generated from traversing your tree
	ArrayList<Integer> soln = new ArrayList<Integer>();

	
	// calls each step of the process
	public FinalProject_Sam() throws IOException {
		buildList();
		buildTree();
		getNums(branches.remove(0), 0);
		
		// converts each number in your solution to its character equivalent
		for (int n : soln)
			System.out.print((char)n);
	}
	
	// generates a list of branches from a provided text file. Each number should be turned into a leaf,
	// each line of the text file should be turned into a parent branch, with each of these 
	// leaves as children
	public void buildList() throws IOException {
		// your code here
		String path = "WinterFinals/FinalCodes.txt"; 
		FileReader fileReader = new FileReader(path);
        BufferedReader in = new BufferedReader(fileReader);
		
        while (in.ready()) {

			String line = in.readLine();
			String number = "";
			Branch<Integer> parent = new Branch<Integer>(); 
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == ',') {
					Branch<Integer> leaf = new Branch<Integer>(Integer.parseInt(number));
					parent.add(leaf); 
					number = "";
				} else {
					number += line.charAt(i);
				}
			}
			Branch<Integer> leaf = new Branch<Integer>(Integer.parseInt(number));
			parent.add(leaf); 
			branches.add(parent); 

        }

        in.close();
	}
	
	// builds a tree by removing the first 5 branches from the list then creating a new parent branch
	// with these 5 branches as its children. Repeat until there is only 1 branch remaining in the list
	public void buildTree() {
		
		// your code here
		while (branches.size() > 1) {
			Branch<Integer> parent = new Branch<Integer>();
			for (int i = 0; i < 5; i++) {
				parent.add(branches.remove(0)); 
			}
			branches.add(parent); 
			
		}
	}
	
	// traverses the tree to generate our solution numbers. The number for the root of the tree is 0. 
	// Any other branch's number is equal to its parent's number + the index of this branch in its parent's
	// children list
	public void getNums(Branch<Integer> b, int currNum) {

		// your code here
		if (b.leaf) {
			soln.add(currNum + b.info);
		}

		for (int i = 0; i < b.childrens.size(); i++){
			getNums(b.childrens.get(i), i + currNum);
		}

	}

	
	
	public static void main(String[] args) throws IOException {
		new FinalProject_Sam();
	}
}

