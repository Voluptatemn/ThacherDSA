package Compressor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class Frequency {


    public static void main(String[] args) throws IOException {
        
        // pre-processing, adding a \n
        String path = "ExampleTest.txt"; 
        Writer modified_file;
        modified_file = new BufferedWriter(new FileWriter(path, true));
        modified_file.append("\n");
        modified_file.close();

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

        // converting the hashmap into a priority que with the frequencies as the priority
        PriorityQueue<BinaryBranch<Character>> pq = new PriorityQueue<BinaryBranch<Character>>(); 
        for (Character i: map.keySet()) {
            BinaryBranch<Character> leaf = new BinaryBranch<Character>(i);
            pq.add(leaf, map.get(i)); 
        }

        // converting the priority que into a binary tree 
        int count = 0; 
        int temp_frequency = 0;
        BinaryBranch<Character> temp = new BinaryBranch<Character>(null, null); 
        while (pq.size() != 1 || count == 1) {
            if (count == 0) {
                temp_frequency = pq.frequency(); 
                temp = pq.pop(); 
                count++;
            } else if (count == 1) {
                int frequency = pq.frequency(); 
                BinaryBranch<Character> branch = new BinaryBranch<Character>(temp, pq.pop()); 
                count = 0; 
                pq.add(branch, temp_frequency + frequency); 
            }
        }
        
        // traverse through the tree to get all codes for the characters
        BinaryBranch<Character> last = pq.pop();
        String code = "";
        last.traverseTree(last, code);
        HashMap<Character, String> codeMap = last.traverseCodes; 

        // 1: read the file and covert it into binary string
        // 2: change the binary string into characters and write the characters into a new file
        // step 1
        String binaryString = "";
        FileReader fileReader_bit = new FileReader(path);
        BufferedReader in_bit = new BufferedReader(fileReader_bit);

        while (in_bit.ready()) {
            char in_char = (char) in_bit.read();
            binaryString += codeMap.get(in_char); 
        }

        in_bit.close();

        // step 2
        // create the compressed file 
        String commpressedPathName = "compressed" + path;
        File myObj = new File(commpressedPathName);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
             System.out.println("File already exists.");
        }

        String ascii_decimal_char = ""; 

        // move the binary string by 8 bits 
        while (binaryString.length() > 0) {

            if (binaryString.length() >= 8) {
                String current = binaryString.substring(0, 8);
                ascii_decimal_char += (char) Integer.parseInt(current,2);  
                binaryString = binaryString.substring(8); 
            } else {
                ascii_decimal_char += (char) Integer.parseInt(binaryString,2);  
                break;
            }

        }

        // writing the compressed file 
        FileWriter myWriter = new FileWriter(commpressedPathName);
        myWriter.write(ascii_decimal_char);
        myWriter.close(); 
               
        // creating the dictionary file 
        String compressedPathCode = "decompressCodes" + path;
        File compressed_code = new File(compressedPathCode);
        if (compressed_code.createNewFile()) {
            System.out.println("File created: " + compressed_code.getName());
        } else {
             System.out.println("File already exists.");
        }

        String decompressCodeFile = "";
        for (Character i: last.traverseCodes.keySet()) {
            decompressCodeFile = decompressCodeFile + i + codeMap.get(i)  + "\n"; 
        }

        FileWriter codeWriter = new FileWriter(compressedPathCode);
        codeWriter.write(decompressCodeFile);
        codeWriter.close(); 

        
        

    


    }
}