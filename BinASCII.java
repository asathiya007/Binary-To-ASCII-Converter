import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class representing a binary to ASCII converter
 *
 * @author Akshay Sathiya
 * @version 1.2
 */
public class BinASCII {

    /**
     * Convert a binary number to ASCII
     *
     * @param binNum a String that represents a binary number
     * @return the ASCII character represented by the binary number
     */
    public static String binToASCII(String binNum) {
        // Initialize variables
        String strASCII = "";
        String[] binNumArray = new String[binNum.length()];
        int decNum = 0;
        String lineNum = "";
        String line = "";
        int lineIndex = 0;
        boolean isBinary = true;

        // Store the binary number in a char array and verify the digits
        for (int i = 0; i < binNum.length(); i++) {
            binNumArray[i] = binNum.substring(i, i + 1);
            if (!binNumArray[i].equals("1") && !binNumArray[i].equals("0")) {
                strASCII = "None - input is not in binary form.";
                isBinary = false;
            }
        }

        // Conduct conversion if number is binary
        if (isBinary) {
            try {
                // Instantiate File, FileReader, and BufferedReader objects
                File file = new File("decASCII.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // Convert binary to decimal
                for (int i = binNum.length() - 1, j = 1; i >= 0; i--) {
                    decNum += (Integer.parseInt(binNumArray[i]) * j);
                    j *= 2;
                }

                // Look up ASCII equivalent of decimal
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.substring(1, 2).equals("\t")) {
                        continue;
                    }
                    lineNum = line.substring(1, line.indexOf("\t"));
                    if (lineNum.equals(Integer.toString(decNum))) {
                        strASCII = line.substring(line.indexOf("\t") + 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // If conversion done but no binary-ASCII association
        if (strASCII.equals("")) {
            strASCII = "None - input is out of range.";
        }

        // Return the result of the conversion
        return strASCII;
    }

    /**
     * Executes the binary to ASCII conversion
     *
     * @param args the array of Strings used to hold command line args
     */
    public static void main(String[] args) {

        // Initialize variables
        String binaryNum = "";
        int binDigit = 0;


        // Instantiate the Scanner object
        Scanner scan = new Scanner(System.in);

        // Loop
        while (!binaryNum.equalsIgnoreCase("quit")) {
            // Accept user input
            System.out.print("Binary number ['quit' to quit]: ");
            binaryNum = scan.nextLine();

            // Check the input for the quit code
            if (binaryNum.equalsIgnoreCase("quit")) {
                continue;
            }

            // Convert the binary number into ASCII and report
            System.out.println("ASCII Character: " + binToASCII(binaryNum));
            System.out.println();
        }
    }
}
