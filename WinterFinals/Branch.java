package WinterFinals;

import java.util.ArrayList;

public class Branch<T> {

    T info;
    ArrayList<Branch<T>> childrens = new ArrayList<Branch<T>>(); 
    boolean leaf; 

    public Branch (T info) {
        this.info = info; 
        leaf = true;
    }

    public Branch () {
        leaf = false; 
    }

    public void add (Branch<T> child) {
        childrens.add(child); 
    }

   
    

}


