package Compressor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Decompressor {

    public Decompressor() throws IOException {
        
        String path = choosingFile(); 
        path = path.substring(10); 
        long startTime = System.nanoTime();
        String decrompressCodePath = "decompressCodes" + path; 
        HashMap<String, Character> codeMap = codeMap(decrompressCodePath); 
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;

        System.out.println("The duration for creating the decompressed Code is: " + duration + " miliseconds."); 

        startTime = System.nanoTime();
        decompressedFile(path);
        System.out.println("Do not open the file that is being written. Decompressing ..."); 
        WritingdecompressedStrings(codeMap, path); 
        // reading(codeMap, path);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;

        System.out.println("The duration for creating the decompressed file is: " + duration + " miliseconds."); 
        
        

    }

    public HashMap<String, Character> codeMap(String decrompressCodePath) throws IOException {
        
        // reading the code map file and rebuild a hash map for the keys and the characters 
        // reading the file 
        HashMap<String, Character> codeMap = new HashMap<String, Character>(); 
        FileReader codeReader = new FileReader(decrompressCodePath);
        BufferedReader in = new BufferedReader(codeReader);
        Character temp_info = null; 
        while (in.ready()) {
            String line = in.readLine();
            if (line.length() == 0) {
                temp_info = '\n'; 
            } else {
                if (temp_info == null) {
                    String key = line.substring(1);
                    Character info = line.charAt(0);
                    codeMap.put(key, info); 
                } else {
                    codeMap.put(line, temp_info); 
                    temp_info = null; 
                }
            }
            
        }

        in.close();

        return codeMap; 
    }

    public void WritingdecompressedStrings(HashMap<String, Character> codeMap, String path) throws IOException { // using the Sam's way to decompress, not using bit manipulation 

        // translate the file into binary 
        String bits = "";
        String compressedFilePath = "compressed" + path; 

        // create the buffered input stream 
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(compressedFilePath));

        // initialize the reading, read the first three bytes 
        int current = in.read();
		if(current == -1) {
            System.out.println("File did not have two bytes"); 
        }

		int next = in.read();
		if(next == -1) {
            System.out.println("File did not have two bytes");
        }

		int afterNext = in.read();


        // if there is only 2 bytes in the file 
        if (afterNext == -1) {

            // converting the current into bits 
            String bit = creatingBit(current); 
            bit = bit.substring(0, 8 - next); 
            bits += bit;

            // convert the binary code to the characters 
            bits = writeChar(codeMap, bits, path);     


        }

        // loop through the file if there is not only two bytes 
        while (afterNext != -1) { 

            // converting the current into bits 
            String bit = creatingBit(current); 
            bits += bit;
            
            // writing binary code into char 
            bits = writeChar(codeMap, bits, path);

            current = next;
            next = afterNext;
            afterNext = in.read(); 

            // if there is not a third character, then count the amount of the second character and minus that amount of zeros for the current 
            if (afterNext == -1) {

                // binary code 
                bit = creatingBit(current); 
                bit = bit.substring(0, 8 - next); 
                bits += bit;

                // writing character 
                bits = writeChar(codeMap, bits, path);      
            
            }
        }

        in.close(); 
    }

    public String creatingBit(int current) { // changing the btye to binary code and fixing it 
        String bit = DecimaltoBinary.decToBinary(current); 
        int zeros = 8 - bit.length(); 
        if (bit.length() < 8) {
                for (int i = 0; i < zeros; i++) {
                    bit = '0' + bit; 
                }
        }
        return bit; 
    }   

    public String writeChar(HashMap<String, Character> codeMap, String bits, String path) throws IOException { // writing the bits into char into the new file  
        String temp_key = ""; 
        for (int i = 0; i < bits.length(); i++) {
            temp_key += bits.charAt(i);
            if (codeMap.containsKey(temp_key)) {
                writer(codeMap.get(temp_key), path);
                bits = bits.substring(temp_key.length()); 
                temp_key = "";
                i = -1;
            } else if (i == bits.length() - 1 && temp_key.length() > 0) {
                temp_key = ""; 
            }
        }  
        return bits; 
    }



    public void reading(HashMap<String, Character> codeMap, String path) throws IOException { // using Friedman's code to decompress and read the compressed file 

        // reading the compressed file 
        String bits = "";
        String compressedFilePath = "compressed" + path; 
        BufferedBitReader reader = new BufferedBitReader(compressedFilePath);
        while (reader.hasNext()) {

            // appending the boolean, and then check if the map contains such code 
            boolean bit = reader.readBit(); 

            if (bit) {
                bits += '1';
            } else {
                bits += '0'; 
            }

            if (codeMap.containsKey(bits)) {
                Character temp = codeMap.get(bits); 
                writer(temp, path);
                bits = ""; 
            }
        }

        // closing statement
        reader.close();
       

    }

    public void decompressedFile(String path) throws IOException { // creating the decompressed file 
        // creating the decompressed file 

        String DecommpressedPathName = "Decompressed" + path;
        File decompressedFile = new File(DecommpressedPathName);
        if (decompressedFile.createNewFile()) {
            System.out.println("File created: " + decompressedFile.getName());
        } else {
            System.out.println("File already exists.");
        }
 
    }

    public void writer(Character decompressedString, String path) throws IOException { // writes the decompressed chars into the decompress file 
 
        // writing the string 
        String DecommpressedPathName = "Decompressed" + path;

        FileOutputStream myWriter = new FileOutputStream(DecommpressedPathName, true);
        myWriter.write(decompressedString);
        myWriter.close(); 
    }

    public String choosingFile() { // choosing the compressed file that is going to get decompressed 

        try (Scanner myObj = new Scanner(System.in)) {
            System.out.println("Enter the compressed file relative path to decompress: ");
            String path = myObj.nextLine();  // Read user input
            return path;
        } 
    }

    public static void main(String[] args) throws IOException {

        new Decompressor(); 
    }
}
