
/*----------------------------------------------------

    Class: Main
    Name: Bhautik Sojitra (7900140)
    Purpose: Get Input from the User and Call the file processor.

 ------------------------------------------------------*/
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter File Name with extention: ");

        Scanner scanFileName = new Scanner(System.in);
        String fileName = scanFileName.next();

        // Initialising the file reader
        InputProcess i = new InputProcess(fileName);

        scanFileName.close();
    }
}
