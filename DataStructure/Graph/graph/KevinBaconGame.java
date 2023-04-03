package graph;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class KevinBaconGame {

    public void StartGame() throws IOException {

        HashMap<String, String> actors = TxtProcess("graph/actors.txt");
        HashMap<String, String> movies = TxtProcess("graph/movies.txt"); 
        labeledGraph<String, String> graph = buildingGraph(actors, movies);
        initializeUi(graph);



    }
    
    public static HashMap<String, String> TxtProcess(String fileName) throws IOException {

        HashMap<String, String> actors = new HashMap<String, String>(); 
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream(file)));

        while (reader.ready()) {
            
            String line = reader.readLine(); 
            String code = "";
            String name = ""; 
            boolean coding = true;
            for (int i = 0; i < line.length(); i++) {
                if (coding) {
                    if (line.charAt(i) == '~') {
                        coding = false; 
                    } else {
                        code += line.charAt(i);
                    }
                } else {
                    name += line.charAt(i); 
                }
            }
            actors.put(code, name.toLowerCase()); 

        }

        reader.close();
        return actors;

    }

    public labeledGraph<String, String> buildingGraph(HashMap<String, String> actors, HashMap<String, String> movies) throws IOException {

        labeledGraph<String, String> graph = new labeledGraph<String, String>(); 
        for (String key: actors.keySet()) {
            String name = actors.get(key);
            graph.add(name); 
        }

        File file = new File("graph/movie-actors.txt");
        BufferedReader reader = new BufferedReader (new InputStreamReader(new FileInputStream(file)));

        HashMap<String, ArrayList<String>> movie_actor = new HashMap<String, ArrayList<String>>(); 

        while (reader.ready()) {

            String line = reader.readLine();
            String movie_code = "";
            String actor_code = "";
            boolean movie = true;
            for (int i = 0; i < line.length(); i++) {
                if (movie) {
                    if (line.charAt(i) == '~') {
                        movie = false; 
                    } else {
                        movie_code += line.charAt(i);
                    }
                } else {
                    actor_code += line.charAt(i); 
                }
            }

            String movie_name = movies.get(movie_code);
            String actor_name = actors.get(actor_code); 

            if (!movie_actor.containsKey(movie_name)) {
                ArrayList<String> actor_list = new ArrayList<String>(); 
                actor_list.add(actor_name);
                movie_actor.put(movie_name, actor_list);              
            } else {
                ArrayList<String> actor_list = movie_actor.get(movie_name); 
                actor_list.add(actor_name); 
                movie_actor.put(movie_name, actor_list);
            }
            
        }

        reader.close();
        
        for (String key: movie_actor.keySet()) {
            ArrayList<String> actor_list = movie_actor.get(key); 
            list_connect(graph, actor_list, key);
        }

        return graph; 


    }

    public void list_connect(labeledGraph<String, String> graph, ArrayList<String> actor_list, String movie_name) {

        for (int i = 0; i < actor_list.size(); i++) {
            if (i == actor_list.size() - 1) {
                break; 
            }
            for (int j = i + 1; j < actor_list.size(); j++) {
                graph.connect(actor_list.get(i), actor_list.get(j), movie_name);
            }
        }

    }

    public String getInput() {
        Console console = System.console(); 
        System.out.println("Enter selection: "); 
        String s = console.readLine();
        return s;
    }

    public void findTheTarget(labeledGraph<String, String> graph) {
        String start = getInput();
        String current = start; 
        String current_movie = ""; 
        String end = getInput();
        System.out.println("Your starting point is " + start + " and your ending point is " + end + "."); 
        ArrayList<String> ShortestPath = graph.search(start, end); 
        ArrayList<String> takenPath = new ArrayList<String>(); 
        int count = 0;

        while (!current.equals(end)) {
            takenPath.add(current); 
            ArrayList<String> movie_info = graph.showEdges(current);
            System.out.println("The movies you can choose from are: " + movie_info);  
            current_movie = getInput(); 
            takenPath.add(current_movie); 
            ArrayList<String> available_actor = graph.potential_neighbors(current_movie, current); 
            System.out.println("Avaiable actors are " + available_actor);
            current = getInput(); 
            count ++; 

        }

        takenPath.add(current); 
        System.out.println("Sucess");
        System.out.println(count); 
        System.out.println("The shortest path is " + ShortestPath + ". And your path is " + takenPath + "."); 
        
    }

    public void initialize_FindTargetText(labeledGraph<String, String> graph, String start, String end, JTextArea textBox, JTextArea selection_input, ArrayList<String> takenPath, int count, String current, String current_movie) {

        if (start.equals(end)) {
            String third = "You already won.";
            textBox.setText(textBox.getText() + "\n" + third);   
        }

        current = start; 
        System.out.println(current); 
        String reminder = "Your starting point is " + start + " and your ending point is " + end + "."; 
        textBox.setText(textBox.getText() + "\n" + reminder); 

        count = 0;
        takenPath.add(current); 
        ArrayList<String> movie_info = graph.showEdges(current);
        String first = "The movies you can choose from are: ";
        textBox.setText(textBox.getText() + "\n" + first + "\n" + movie_info);

        

    }

    public void FindTarget(labeledGraph<String, String> graph, String start, String end, JTextArea textBox, JTextArea selection_input, ArrayList<String> takenPath, String current, int count, int mode, String current_movie) {
        
        if (mode == 1) {
            mode = 0; 
            current_movie = selection_input.getText().toLowerCase().strip(); 
            takenPath.add(current_movie); 
            System.out.println(current);
            System.out.println(current_movie);  
            ArrayList<String> available_actor = graph.potential_neighbors(current_movie, current); 
            String second = "Avaiable actors are " + available_actor;
            textBox.setText(textBox.getText() + "\n" + second + "\n" + available_actor);   
        } else {
            ArrayList<String> ShortestPath = graph.search(start, end); 
            mode = 1; 
            current = selection_input.getText().toLowerCase().strip();
            takenPath.add(current);
            count ++; 
    
            if (current.equals(end)) {
                textBox.setText(textBox.getText() + "\n Success");  
                textBox.setText(textBox.getText() + "\n" + count);  
                String ending = "The shortest path is " + ShortestPath + ".";
                textBox.setText(textBox.getText() + "\n" + ending);
                textBox.setText(textBox.getText() + "\n" +  "And your path is " + takenPath + ".");
            } else {
                ArrayList<String> movie_info = graph.showEdges(current);
                String first = "The movies you can choose from are: ";
                textBox.setText(textBox.getText() + "\n" + first + "\n" + movie_info); 
            }
        

        

    }

    }


    public void initializeUi(labeledGraph<String, String> graph) {

        final int WIDTH = 1000;
        final int HEIGHT = 800;
        final int BUTTONHEIGHT = 20;
        String text = "";
        ArrayList<String> takenPath = new ArrayList<String>(); 
        int count = 0; 
        int mode = 1; 
        String current = ""; 
        String current_movie = ""; 

        JFrame frame = new JFrame("Kevin Bacon Game");
        frame.setSize(WIDTH, HEIGHT); 
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // text areas for entering the actors' names
        JTextArea actor1 = new JTextArea();
        actor1.setPreferredSize(new Dimension(WIDTH / 8, BUTTONHEIGHT));
        actor1.setText("Actor One: ");
        actor1.setEditable(false);

        JTextArea actor1_input = new JTextArea();
        actor1_input.setPreferredSize(new Dimension(WIDTH / 4, BUTTONHEIGHT));
        actor1_input.setEditable(true);
        
        JTextArea actor2 = new JTextArea();
        actor2.setPreferredSize(new Dimension(WIDTH / 8, BUTTONHEIGHT));
        actor2.setText("Actor Two: ");
        actor2.setEditable(false);
       
        JTextArea actor2_input = new JTextArea();
        actor2_input.setPreferredSize(new Dimension(WIDTH / 4, BUTTONHEIGHT));
        actor2_input.setEditable(true);

        JTextArea textBox = new JTextArea();
        textBox.setPreferredSize(new Dimension(WIDTH, HEIGHT -  2 * BUTTONHEIGHT));
        textBox.setText(text);
        textBox.setEditable(false);

        JTextArea selection_input = new JTextArea();
        selection_input.setPreferredSize(new Dimension(WIDTH / 5, BUTTONHEIGHT));
        selection_input.setEditable(true);
        

        JPanel buttonContainer = new JPanel();

        JButton Find = new JButton("Find Shortest Path"); // button for text
        Find.setPreferredSize(new Dimension(WIDTH / 5, BUTTONHEIGHT));
        Find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //to do 
                String actor1_text = actor1_input.getText().toLowerCase().strip(); 
                String actor2_text = actor2_input.getText().toLowerCase().strip();

                if (graph.isNotIn(actor1_text)) {
                    String output = "There is no " + actor1_text + " in the data base."; 
                    textBox.setText(textBox.getText() + "\n" + output); 
                } else if (graph.isNotIn(actor2_text)) {
                    String output = "There is no " + actor2_text + " in the data base."; 
                    textBox.setText(textBox.getText() + "\n" + output); 
                } else {
                    ArrayList<String> path = graph.search(actor1_text, actor2_text); 
                    String output = "The shortest path from " + actor1_text + " to " + actor2_text + " is: "; 
                    textBox.setText(textBox.getText() + "\n" + output + "\n" + path); 
                }

            
            }

            
        }); 

        JButton FindTarget = new JButton("Try Find the shortest path");
        FindTarget.setPreferredSize(new Dimension(WIDTH / 5, BUTTONHEIGHT));
        FindTarget.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //to do 
                String actor1_text = actor1_input.getText().toLowerCase().strip(); 
                String actor2_text = actor2_input.getText().toLowerCase().strip();

                if (graph.isNotIn(actor1_text)) {
                    String output = "There is no " + actor1_text + " in the data base."; 
                    textBox.setText(textBox.getText() + "\n" + output); 
                } else if (graph.isNotIn(actor2_text)) {
                    String output = "There is no " + actor2_text + " in the data base."; 
                    textBox.setText(textBox.getText() + "\n" + output); 
                } else {
                    initialize_FindTargetText(graph, actor1_text, actor2_text, textBox, selection_input, takenPath, count, current, current_movie); 
                }
             

            
            }

            
        }); 

        JButton select = new JButton("Select");
        select.setPreferredSize(new Dimension(WIDTH / 5, BUTTONHEIGHT));
        select.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //to do 
                String actor1_text = actor1_input.getText().toLowerCase().strip(); 
                String actor2_text = actor2_input.getText().toLowerCase().strip();

                if (graph.isNotIn(actor1_text)) {
                    String output = "There is no " + actor1_text + " in the data base."; 
                    textBox.setText(textBox.getText() + "\n" + output); 
                } else if (graph.isNotIn(actor2_text)) {
                    String output = "There is no " + actor2_text + " in the data base."; 
                    textBox.setText(textBox.getText() + "\n" + output); 
                } else {
                    FindTarget(graph, actor1_text, actor2_text, textBox, selection_input, takenPath, current, count, mode, current_movie); 
                }
             

            
            }

            
        }); 





        JPanel container = new JPanel();

        JPanel InPutcontainer = new JPanel();

        InPutcontainer.setPreferredSize(new Dimension(WIDTH, BUTTONHEIGHT));
        InPutcontainer.add(actor1);
        InPutcontainer.add(actor1_input);
        InPutcontainer.add(actor2);
        InPutcontainer.add(actor2_input);
        InPutcontainer.add(buttonContainer);

        buttonContainer.setPreferredSize(new Dimension(WIDTH, BUTTONHEIGHT));
        buttonContainer.add(Find);
        buttonContainer.add(FindTarget);
        buttonContainer.add(selection_input); 
        buttonContainer.add(select);
        
        container.add(InPutcontainer);
        container.add(textBox); 
        container.add(buttonContainer); 
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        

        frame.add(container);

        frame.setVisible(true);


    }

 
    public static void main(String[] args) throws IOException {
        KevinBaconGame KBC = new KevinBaconGame(); 
        KBC.StartGame(); 
    }

}
