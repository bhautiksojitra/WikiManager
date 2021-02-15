/*----------------------------------------------------------------

    CLASS: Document
    NAME: BHAUTIK SOJITRA   (7900140)
    PURPOSE:   STORES ALL THE INFO ABOUT THE DOCUMENT

-----------------------------------------------------------------*/

public class Document extends DataType {

    private String title; // Title of the doc.
    private List lines; // List of lines in the Document
    private List history; //stores the commands applied on the document
    private List restoreList; //restores the old data

    //constructor->Initialize each variable
    public Document(String newTitle) {
        title = newTitle;
        history = new List();
        restoreList = new List();
        lines = new List();
    }

    //Add the data in the restoreList
    public void addRestoredVersion(DataType newVersion) {
        restoreList.add(newVersion);
    }

    //Retrieve the old data from the restoreList
    public Document retrieveOldVersion(int expectedTime) {
        //Iterate through the entire list.
        List.Iterator i = restoreList.iterator();
        while (i.hasNext()) {
            DataType dataInList = i.next();

            if (dataInList instanceof RestoredDoc) {
                //compare the time and return the data from the restoreList.
                if (((RestoredDoc) dataInList).getTime() == expectedTime) {
                    return ((RestoredDoc) dataInList).getDoc();
                }

            } else {
                System.out.println(dataInList.getId() + " is not an instance of restore class ! ");
            }
        }
        return null;
    }

    //Add history of the command in document
    public void addHistory(DataType newHistory) {
        history.add(newHistory);
    }

    public void printHistory() {
        System.out.println("---------------------- NEW DOCUMENT HISTORY--------------------------");
        System.out.println("Document Name: " + this.getId());
        history.printList();
        System.out.println("----------------------DOCUMENT HISTORY ENDS---------------------------");
    }

    //add the line in the document
    public void append(String inputLine) {
        //creating the new line using Content Object
        DataType newLine = new Content(inputLine);
        lines.add(newLine); //add the new Line at the end of the Document
    }

    //Will find the place and replace the line -> returns the old line
    public String replace(String inputLine, int lineNumber) {
        //If the doc isn't empty
        if (lines != null) {

            //Iterating through the Document.
            List.Iterator i = lines.iterator();
            int lineCount = 0; // to keep track of the line number

            while (i.hasNext()) {

                DataType lineData = i.next();
                //when we found the place to replace the line
                if (lineCount == lineNumber) {
                    //replacing the line
                    String oldLine = lineData.getId();
                    ((Content) lineData).setData(inputLine);
                    return oldLine;
                }
                lineCount++;

            }
            //check the doc if it doesn't have enough lines
            if (lineCount < lineNumber) {
                System.out.println("FAIL TO REPLACE: " + this.getId() + " Has NO CONTENT AT LINE: " + lineNumber);
                return null;
            }
        } else {
            System.out.println("FAIL TO REPLACE : " + this.getId() + " Has No Content !");
            return null;
        }
        return null;
    }

    //deletes the line and change all the pointers -> returns the old line
    public String delete(int lineNumber) {
        if (lines != null) {

            List.Iterator i = lines.iterator();
            //If the doc isn't empty

            int lineCount = 0; // to keep track of the line number
            while (i.hasNext()) {

                DataType line = i.next();
                if (lineCount == lineNumber) {

                    //replacing the data
                    String oldLine = line.getId();
                    lines.delete(line.getId());
                    return oldLine;
                }
                lineCount++;

            }
            //check the doc if it doesn't have enough lines
            if (lineCount < lineNumber) {
                System.out.println("FAIL TO DELETE : NO CONTENT AT LINE: " + lineNumber);
                return null;
            }
        } else {
            System.out.println("FAIL TO DELETE : " + this.getId() + " Has No Content !");
            return null;
        }
        return null;
    }

    //I write this method again here to print the lines with LINE NUMBERS.
    public String print() {
        System.out.println("Document Name: " + this.getId()); // prints the title

        String str = "";
        int lineNumber = 0;

        if (lines != null) {
            //Iterate through the entire list and store in all in a single string
            List.Iterator i = lines.iterator();
            while (i.hasNext()) {

                DataType lineData = i.next();
                str = str + lineNumber + "." + lineData.getId() + "\n";

                lineNumber++;
            }
        }
        return str;
    }

    //cloning of this object
    public Document deepCopy() {
        //creates new Doc with the same title
        Document newD = new Document(this.title);

        if (lines != null)
            newD.lines = this.lines.deepCopyList();

        //Copy of both list present in this Document
        newD.history = this.history.deepCopyList();
        newD.restoreList = this.restoreList.deepCopyList();

        return newD;

    }

    //restore the document to the old version. Changing the data fields with old version's fields
    public void restore(Document oldVersion) {
        this.title = oldVersion.title; //Changing the title
        if (oldVersion.lines != null)
            this.lines = oldVersion.lines.deepCopyList();//changing the lines
        else
            this.lines = null;
    }

    // get the line from the given line number
    public String getLine(int lineNumber)
    {
        //Iterate through the list
        List.Iterator i = lines.iterator();
        int lineCount = 0; // keep track of the line number

        while(i.hasNext())
        {
            DataType newLine = i.next();

            if(lineNumber == lineCount)
                return newLine.getId();

            lineCount++;
        }
        return null;
    }


    //return the title ->Overrides from the superclass
    public String getId() {
        return title;
    }

}
