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


    public boolean hasTargetLeaf (T targert) {

        if (leaf) {
            if (info == targert) {
                return true;
            } else {
                return false; 
            }
        }

        if (left.hasTargetLeaf(targert)) { 
            return true;
        } else if (right.hasTargetLeaf(targert)) {
            return true;
        } else {
            return false; 
        }

    }

    public String TargetLeafPath (T target) {
        String code = "";


        if (!leaf) {
            if (left.hasTargetLeaf(target)) {
                code += "1"; 
                code += left.TargetLeafPath(target); 
                return code; 
            } else if (right.hasTargetLeaf(target)) {
                code += "0"; 
                code += right.TargetLeafPath(target);
                return code; 
            } 
        }
        return code; 


    }


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


