package Compressor;

public class DecimaltoBinary {
    static String decToBinary(int n)
    {
        // array to store binary number
        int[] binaryNum = new int[1000];
   
        // counter for binary array
        int i = 0;
        while (n > 0) 
        {
            // storing remainder in binary array
            binaryNum[i] = n % 2;
            n = n / 2;
            i++;
        }
   
        // printing binary array in reverse order
        String binaryCode = "";
        for (int j = i - 1; j >= 0; j--)
            binaryCode += binaryNum[j];

        return binaryCode; 
    }
}
