package Compressor;

import java.util.ArrayList;

public class PriorityQueue<T> {

    private ArrayList<Node> queue = new ArrayList<Node>(); 

    private class Node {

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

    public void add(T data, int priority) {

        Node newNode = new Node(data, priority);

        if (queue.size() == 0) { // no stuff in the queue

            queue.add(newNode); // add it in 
        }

        else { 

            if (queue.get(queue.size() - 1).priority >= newNode.priority) { // check if the last one's priority 
                queue.add(newNode); 
            } else {
                for (int i = 0; i < queue.size(); i++) {

                    if (queue.get(i).priority <= newNode.priority) {
                        queue.add(i, newNode); 
                        break;
                    } 
    
                }
            }

                
            }

    }

    public void binAdd (T data, int priority) {
        Node newNode = new Node(data, priority);

        if (queue.size() == 0) {
            queue.add(newNode); 
        } else if (queue.get(queue.size() - 1).priority >= newNode.priority) {
            queue.add(newNode); 
        } else if (queue.get(0).priority <= newNode.priority) {
            queue.add(0, newNode); 
        } else if (queue.size() == 2) {
            queue.add(1, newNode); 
        } else {
            int start = 0; 
            int end = queue.size(); 
            while (start <= end) {
                int i = (start + end) / 2 - 1;
                if (queue.get(i).priority == newNode.priority) {
                    queue.add(i, newNode); 
                    break;
                } else if (queue.get(i).priority < newNode.priority) {
                    System.out.println("bigger");
                    end = i; 
                    if (start >= end) {
                        queue.add(i, newNode);
                        break;
                    }
                } else {
                    start = i; 
                    System.out.println("smaller");
                    if (start >= end) {
                        queue.add(i, newNode);
                        break; 
                    }
                }
            }

        }
    }


    public T pop() { 

        return queue.remove(queue.size() - 1).info; 

    }

    public int frequency() {
        return queue.get(queue.size() - 1).priority; 
    }

    public String toString() {
        return queue.toString();
    }

    public int size() {
        return queue.size(); 
    }

    public static void main(String[] args) {
        
        PriorityQueue<Character> queue = new PriorityQueue<Character>(); 

        queue.binAdd('g', 1);
        queue.binAdd('h', 20);
        queue.binAdd('c', 4);
        queue.binAdd('j', 7);
        System.out.println(queue.toString());

        


    }



    

}
