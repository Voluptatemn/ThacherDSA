package Compressor;

import java.util.HashMap;

public class BinaryBranch<T> {
    T info;
    BinaryBranch<T> left;
    BinaryBranch<T> right;
    boolean leaf; 
    HashMap<T, String> traverseCodes = new HashMap<T, String>(); 

    public BinaryBranch (T info) {
        this.info = info; 
        leaf = true;
    }

    public BinaryBranch (BinaryBranch<T> left, BinaryBranch<T> right) {
        this.left = left;
        this.right = right; 
        leaf = false; 
    }

    // trarse the tree to fill up the map with the code for each leaf, map is stored in the root
    public void traverseTree(BinaryBranch<T> root, String code) {
        
        if (root.leaf) {

            traverseCodes.put(root.info, code);

        } else {
            
            String code_left = code + '1'; 
            String code_right = code + '0'; 
            traverseTree(root.left, code_left);
            traverseTree(root.right, code_right); 

        }

    }
    

}


