package graph; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class labeledGraph<E, T> {
    

    public class Vertex {

        E info;
        HashSet<Edge> edges;

        public Vertex (E info) {
            this.info = info;
            edges = new HashSet<Edge>();
        }

        public String toString() {

            return info.toString(); 

        }

    }

    public class Edge {

        T info;
        Vertex v1, v2; 

        public Edge (T info, Vertex v1, Vertex v2) {
            this.info = info; 
            this.v1 = v1;
            this.v2 = v2; 
        }

        public Vertex getOtherVertex(Vertex current) {
           
            if (!v1.equals(current)) {
                return v1; 
            } else {
                return v2;

            }

        }

        public String toString() {

            return info.toString(); 
        }


    }

    HashMap<E, Vertex> vertices = new HashMap<E, Vertex>();



    public void add(E info1) {

        Vertex v = new Vertex(info1); 

        if(vertices.containsKey(info1)) {
            // System.out.println("this " + info1 + " is already stored"); 
        } else {
            vertices.put(info1, v); 
        }


    }

    public void connect(E info1, E info2, T info3) {

        Vertex v1 = vertices.get(info1);
        Vertex v2 = vertices.get(info2);
        Edge e = new Edge(info3, v1, v2); 
    
        if (v1 == null) {
            System.out.println("Data: " + info1.toString() + "does not exist in graph");
        } 
        else if (v2 == null) {
            System.out.println("Data: " + info2.toString() + "does not exist in graph");
        }
        else {
             
            v1.edges.add(e);
            v2.edges.add(e); 

        }
    
    }



    public ArrayList<String> search(E startingPoint, E endPoint) {

        if (startingPoint.equals(endPoint)) {
            return null; 
        } else if (vertices.get(startingPoint) == null || vertices.get(endPoint) == null) {
            return null;
        }
           


        HashMap<Vertex, Edge> ledTo = new HashMap<Vertex, Edge>();
        Vertex current = vertices.get(startingPoint);
        ArrayList<Vertex> toVisit = new ArrayList<Vertex>(); 
        
        ledTo.put(current, null); 
        toVisit.add(current); 

        while (toVisit.size() != 0) {

            current = toVisit.remove(0);  
            for (Edge edge: current.edges) {

                Vertex otherVertex = edge.getOtherVertex(current); 
                
                if (otherVertex.info.equals(endPoint)) {

                    ledTo.put(otherVertex, edge); 
                    return BackTrace(endPoint, ledTo); 

                } else if (!ledTo.containsKey(otherVertex)) {
 
                    toVisit.add(otherVertex); 
                    ledTo.put(otherVertex, edge);  

                } 

            }

        }

        return null;

    }

    public ArrayList<String> BackTrace(E endPoint, HashMap<Vertex, Edge> ledTo) {

        Vertex current = vertices.get(endPoint); 
        Edge currentEdge = ledTo.get(current);  
        ArrayList<String> path = new ArrayList<String>(); 

        while (currentEdge != null) {
            path.add(0, current.toString()); 
            path.add(0, currentEdge.toString());
            current = currentEdge.getOtherVertex(current);  
            currentEdge = ledTo.get(current); 
        }
        path.add(0, current.toString());
        return path; 

    }


    HashMap<T, ArrayList<Edge>> available_edges = new HashMap<T, ArrayList<Edge>>(); 
    public ArrayList<String> showEdges(E info) {

        Vertex current = vertices.get(info); 
        available_edges = new HashMap<T, ArrayList<Edge>>(); 
        ArrayList<String> edge_info = new ArrayList<String>(); 
        for (Edge e: current.edges) {
            if (!available_edges.containsKey(e.info)) {
                edge_info.add(e.toString()); 
                ArrayList<Edge> edges = new ArrayList<Edge>();
                edges.add(e); 
                available_edges.put(e.info, edges);
            } else {
                ArrayList<Edge> edges = available_edges.get(e.info);
                edges.add(e);
                available_edges.put(e.info, edges);
            }
        }
        return edge_info; 

    }

    public ArrayList<String> potential_neighbors(T choice, E info) {

        Vertex current = vertices.get(info); 
        ArrayList<String> available_choices = new ArrayList<String>();
        ArrayList<Edge> selection = available_edges.get(choice); 
        for (Edge e: selection) {
            String otherInfo = e.getOtherVertex(current).toString(); 
            available_choices.add(otherInfo);
        }

        return available_choices; 


    }

    


    public ArrayList<String> findNeighbors(E info) {
        Vertex current = vertices.get(info); 
        ArrayList<String> neighbor_info = new  ArrayList<String>(); 
        for (Edge e: current.edges) {
            neighbor_info.add(e.getOtherVertex(current).toString()); 
        }
        return neighbor_info; 
    }

    public boolean isNotIn(E info) {
        Vertex current = vertices.get(info);
        if (current == null) {
            return true;
        } else {
            return false; 
        }
    }

    public int averageConnectivity() {

        // count average connectivity 

        return 1; 


    }

    public static void main(String[] args) {


        labeledGraph<String, String> graph = new labeledGraph<String, String>();

        graph.add("Chris Evans");
        graph.add("Black Widow"); 
        graph.add("Robert de Niro");
        graph.add("Jack Nicholson");
        graph.add("Marlon Brando"); 
        graph.connect("Chris Evans", "Black Widow", "Avengers");
        graph.connect("Robert de Niro", "Jack Nicholson", "asian");
        graph.connect("Marlon Brando", "Black Widow", "black"); 

        ArrayList<String> path = graph.search("Marlon Brando", "Chris Evans"); 
        System.out.println(path); 

    }




    


}
