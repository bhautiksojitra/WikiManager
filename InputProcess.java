
/*----------------------------------------------------

    CLASS : INPUT PROCESS
    AUTHOR : BHAUTIK SOJITRA (7900140)

    PURPOSE : READ THE TXT FILE AND PROCESS THE DATA IN IT. AND EXECUTE THE COMMANDS.
 -------------------------------------------------------*/
import java.io.*;

//Process the file and execute the commands based on the file input
public class InputProcess {

    // Constructor - takes the file name and process the file
    public InputProcess(String fileName) {
        // also handles the exception
        try {
            fileRead(fileName); // process the file
        } catch (ArithmeticException ae) {
            System.out.println("MATH ERROR!");
            ae.printStackTrace();
        } catch (FileNotFoundException fe) {
            System.out.println("FILE NOT FOUND!");
            fe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something Went Wrong !");
            e.printStackTrace();
        }
    }

    // reads each line of the line and process the commands
    public void fileRead(String inputFile) throws Exception {

        // initialise the objects for fileReading
        FileReader fRead = new FileReader(inputFile);
        BufferedReader bRead = new BufferedReader(fRead);

        String line = bRead.readLine(); // storing the first line in string

        WikiManager c1 = new WikiManager(); // initializing WikiManager to execute commands

        // loop runs until we reach to the end of the file
        while (line != null) {

            line = line.trim();

            String[] array = line.split(" ", 10);

            // Manage the comments in the text file
            if (line.startsWith("#")) {
                line = bRead.readLine(); // skip the line and reads the next line
            } else {
                // the program executes various commands based on the what it reads from the
                // file
                if (array[0].equals("USER")) {
                    c1.user(array[1]);
                } else if (array[0].equals("CREATE")) {
                    c1.create(array[1], array[2]);
                } else if (array[0].equals("APPEND")) {
                    String str = "";
                    for (int i = 3; i < array.length; i++) {
                        str = str + " " + array[i];
                    }
                    c1.append(array[1], array[2], str.trim());
                } else if (array[0].equals("DELETE")) {
                    c1.delete(array[1], array[2], Integer.parseInt(array[3].trim()));
                } else if (array[0].equals("REPLACE")) {
                    String str = "";

                    for (int i = 4; i < array.length; i++) {
                        str = str + " " + array[i];
                    }

                    c1.replace(array[1], array[2], Integer.parseInt(array[3].trim()), str.trim());
                } else if (array[0].equals("RESTORE")) {
                    c1.restore(array[1], array[2], Integer.parseInt(array[3].trim()));
                } else if (array[0].equals("USERREPORT")) {
                    c1.userReport(array[1]);
                } else if (array[0].equals("PRINT")) {
                    c1.print(array[1]);
                } else if (array[0].equals("HISTORY")) {
                    c1.history(array[1]);
                } else if (array[0].equals("QUIT")) {
                    System.exit(0);
                } else {
                    System.out.println("UnKnown Command Applied ! Check It Again");
                }
                line = bRead.readLine();
            }

            fRead.close();
        }
    }
}
