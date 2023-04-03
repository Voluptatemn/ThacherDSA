package Compressor;

import java.util.ArrayList;

public class PriorityQueue<T> {

    private ArrayList<Node> queue = new ArrayList<Node>(); 

    private class Node { // node class to store inside the priority que 

        T info;
        int priority;

        public Node(T info, int priority) {
            
            this.info = info;
            this.priority = priority; 

        }

        public String toString() {

            return info.toString(); 

        }


    }

    public void add(T data, int priority) { // adding an element, given the data and the priority 

        Node newNode = new Node(data, priority);

        if (queue.size() == 0) { // no stuff in the queue

            queue.add(newNode); // add it in 
        }

        else { 

            if (queue.get(queue.size() - 1).priority >= newNode.priority) { // check if the last one's priority 
                queue.add(newNode); 
            } else {
                for (int i = 0; i < queue.size(); i++) { // loop through the que 

                    if (queue.get(i).priority <= newNode.priority) {
                        queue.add(i, newNode); 
                        break;
                    } 
    
                }
            }

                
            }

    }

    public void binAdd (T data, int priority) {

        // binary insertion 
        // future advancements 
            
    }


    public T pop() {  // poping the last one out 

        return queue.remove(queue.size() - 1).info; 

    }

    public int frequency() { // getting the last frequency 
        return queue.get(queue.size() - 1).priority; 
    }

    public String toString() { // presenting the priority que
        return queue.toString();
    }

    public int size() { // the size of the priority que 
        return queue.size(); 
    }

    public static void main(String[] args) {
        

        // place to test the priority que 
        


    }



    

}
