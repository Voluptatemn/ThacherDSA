package Compressor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Decompressor {
    

    public static void main(String[] args) throws IOException {

        // reading the code map file and rebuild a hash map for the keys and the characters 
        HashMap<String, Character> codeMap = new HashMap<String, Character>(); 
        String path = "decompressCodesExampleTest.txt"; 
        FileReader codeReader = new FileReader(path);
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

        // translate the file into binary 

        String compressedFilePath = "compressedExampleTest.txt";
        String bits = "";
        
        FileReader compressedFileReader = new FileReader(compressedFilePath);
        BufferedReader compressed_in = new BufferedReader(compressedFileReader); 

        FileInputStream fis = new FileInputStream("compressedExampleTest.txt");
        DataInputStream dis = new DataInputStream(fis);
        int length = dis.available();
        dis.skipBytes(length - 1);
        int lastChar = dis.readByte();
        dis.close();
        fis.close();

        while (compressed_in.ready()) {
            int ascii_decimal = compressed_in.read(); 
            String bit = DecimaltoBinary.decToBinary(ascii_decimal); 
            if (bit.length() < 8 && ascii_decimal != lastChar) {
                String zeros = "";
                for (int i = 0; i < 8 - bit.length(); i++) {
                    zeros += "0";
                }
                bit = zeros + bit; 
            }
            bits += bit; 
            
        }
        compressed_in.close(); 


        // check the dictionary for the code 

        String temp_key = ""; 
        String decompressedFile = ""; 

        for (int i = 0; i < bits.length(); i++) {
            temp_key += bits.charAt(i);
            if (codeMap.containsKey(temp_key)) {
                decompressedFile += codeMap.get(temp_key); 
                temp_key = "";
            } 


        }

        System.out.println(decompressedFile); 
        

    }
}
