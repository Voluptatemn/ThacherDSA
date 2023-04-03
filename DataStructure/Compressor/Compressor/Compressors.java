package Compressor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Compressors {

    public Compressors() throws IOException {

        String path = choosingFile(); 
        long startTime = System.nanoTime();
        HashMap<Character, Integer> FrequencyMap = Frequency(path);
        HashMap<Character, String> codeMap = treeConstruction(FrequencyMap); 
        codeFile(codeMap, path);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println("The duration to create the code map is: " + duration + " miliseconds."); 

        startTime = System.nanoTime();
        compressedFile(path);
        System.out.println("Writing the compressed file ... "); 
        // BitWriter(path, codeMap);
        ascii_String(codeMap, path);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;

        System.out.println("The duration to create the compressed file is: " + duration + " miliseconds."); 

        

    }

    public HashMap<Character, Integer> Frequency(String path) throws IOException { // anaylze the frequency of the file 
        
        // creating a hashmap for the priorities of the characters
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); 
        FileReader fileReader = new FileReader(path);
        BufferedReader in = new BufferedReader(fileReader);

        while (in.ready()) {
            char in_char = (char) in.read();
            if (map.containsKey(in_char)) {
                map.put(in_char, map.get(in_char) + 1);
            } else {
                map.put(in_char, 1); 
            }
        }

        in.close();

        return map; 
    }

    public HashMap<Character, String> treeConstruction(HashMap<Character, Integer> map) { // creating the tree and return the root 

        // converting the hashmap into a priority que with the frequencies as the priority
        PriorityQueue<BinaryBranch<Character>> pq = new PriorityQueue<BinaryBranch<Character>>(); 
        for (Character i: map.keySet()) {
            BinaryBranch<Character> leaf = new BinaryBranch<Character>(i);
            pq.add(leaf, map.get(i)); 
        }

        // converting the priority que into a binary tree 
        while (pq.size() > 1) {  
            int temp_frequency = pq.frequency(); 
            BinaryBranch<Character> temp = pq.pop();
            int frequency = pq.frequency(); 
            BinaryBranch<Character> branch = new BinaryBranch<Character>(temp, pq.pop()); 
            pq.add(branch, temp_frequency + frequency); 
        }
        
        // traverse through the tree to get all codes for the characters
        BinaryBranch<Character> last = pq.pop();
        String code = "";
        last.traverseTree(last, code);
        HashMap<Character, String> codeMap = last.traverseCodes; 
        
        return codeMap; 
    }

    public void codeFile(HashMap<Character, String> codeMap, String path) throws IOException { // creating the code map file and wirter the code map 

        // creating the dictionary file 
        String compressedPathCode = "decompressCodes" + path;
        File compressed_code = new File(compressedPathCode);
        if (compressed_code.createNewFile()) {
            System.out.println("File created: " + compressed_code.getName());
        } else {
             System.out.println("File already exists.");
        }


        // writer the code map 
        String decompressCodeFile = "";
        for (Character i: codeMap.keySet()) {
            decompressCodeFile = decompressCodeFile + i + codeMap.get(i)  + "\n"; 
        }

        FileWriter codeWriter = new FileWriter(compressedPathCode);
        codeWriter.write(decompressCodeFile);
        codeWriter.close(); 


    }

    public void ascii_String (HashMap<Character, String> codeMap, String path) throws NumberFormatException, IOException { // compressing the file not using bit reader

        int parse = 8; 
        String binaryString = "";
        FileReader fileReader_bit = new FileReader(path);
        BufferedReader in_bit = new BufferedReader(fileReader_bit);
        while (in_bit.ready()) {
            char in_char = (char) in_bit.read();
            binaryString += codeMap.get(in_char); 
            if (binaryString.length() >= parse) {
                String current = binaryString.substring(0, parse);
                writer((char) Integer.parseInt(current,2), path);  
                binaryString = binaryString.substring(parse); 
            }
        }

        // addressing the remainings 
        if (binaryString.length() > 0) {
            int zeros = parse - binaryString.length(); 
            for (int i = 0; i < zeros; i++) {
                binaryString += '0'; 
            }
            writer((char) Integer.parseInt(binaryString,2), path); 
            String indicator = Integer.toBinaryString(zeros); 
            writer((char) Integer.parseInt(indicator,2), path);             
        } else {
            writer((char) 0, path); 
        }

        in_bit.close();


    }

    public void BitWriter(String path, HashMap<Character, String> codeMap) throws IOException {  // the method to compress using friedman's code

        // problem to solve: leaving the last few blanking 
        // creating the reader to read the file
        String commpressedPathName = "compressed" + path;
        FileReader fileReader_bit = new FileReader(path);
        BufferedReader in_bit = new BufferedReader(fileReader_bit);

        // creating the bit writer using friedman's code to compress
        BufferedBitWriter writers = new BufferedBitWriter(commpressedPathName); 
        while (in_bit.ready()) {
            // read the character and convert it into a binary string
            char in_char = (char) in_bit.read();
            String binaryString = codeMap.get(in_char); 

            // using friedman's code to write the bits into the new file 
            for (int i = 0; i < binaryString.length(); i++) {
                if (binaryString.charAt(i) == '1') {
                    writers.writeBit(true);
                } else {
                    writers.writeBit(false);
                }
            }
        }

        // important closing statements
        writers.close();
        in_bit.close();


    }

    public void compressedFile(String path) throws IOException { // creating the file 

        // create the compressed file 
        String commpressedPathName = "compressed" + path;
        File myObj = new File(commpressedPathName);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
             System.out.println("File already exists.");
        }

    }

    public void writer(Character ascii_decimal_char, String path) throws IOException { // the writer for the ascii_String compression 
        
        // writing the compressed file 
        String commpressedPathName = "compressed" + path;
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(commpressedPathName, true));
        out.write(ascii_decimal_char);
        out.close(); 
               
    }

    public String choosingFile() { // choosing the file to compress

        try (Scanner myObj = new Scanner(System.in)) {
            System.out.println("Enter file relative path to compress: ");
            String path = myObj.nextLine();  // Read user input
            return path;
        } 
    }

    public static void main(String[] args) throws IOException {

        new Compressors(); 
    


    }
}