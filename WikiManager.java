/*----------------------------------------------------

    CLASS : WikiManager
    AUTHOR : BHAUTIK SOJITRA (7900140)

    PURPOSE : MANAGES ALL THE COMMANDS APPLIED BY USERS AND CREATE REPORTS BASED ON THE COMMAND.
 -------------------------------------------------------*/
public class WikiManager {


    private List docList;  //Contains all the documents
    private List userList; // Contains all the registered Users
    
    private int time; //  To keep track of the command (Useful when restoring the documents)

    //Constructor - Initialize all the variables.
    public WikiManager() {

        docList = new List();
        userList = new List();
        time = 0;

    }

    //Will add the changes in restoreList in document without removing olData (useful to restore Data)
    public void addToRestoreList()
    {

        //Iterate through the entire list and add the changes in docs to restoreList
        if(docList != null)
        {
            List.Iterator i = docList.iterator();

            while(i.hasNext())
            {
                DataType dataInList = i.next();

                //create the copy of the old data and store that in the list.
                Document copiedDoc = ((Document)dataInList).deepCopy();
                RestoredDoc version = new RestoredDoc(copiedDoc , time);
                ((Document)dataInList).addRestoredVersion(version);
            }
        }
    }

    //Will add the user in userList.(Means registers the user with the id given as a parameter)
    public void user(String id) {

        //Checking condition to avoid duplicate entry.
        if (!userList.search(id)) {

            User newUser = new User(id);
            userList.add(newUser);

            System.out.println("SUCCESS: " + newUser.getId() + " Registered !");
        }
        else
        {
            System.out.println("FAIL TO REGISTER: " + id + " Is Already Registered !");
        }
        time++; // time increments by 1 which means One command is applied

        addToRestoreList(); // adding the changes in docList and time to the restoreList in specific document.
    }

    //Will Create the document with given title.
    public void create(String newTitle, String userId) {

        //only allowed if the user is registered
        if (userList.search(userId)) {
            //Avoid Duplicate Entry.
            if (!docList.search(newTitle)) {

                Document newDoc = new Document(newTitle);
                docList.add(newDoc);
                System.out.println("SUCCESS: " + newDoc.getId() + " Created By " + userId);


                DataType user = userList.retrieveDataType(userId); // get the Data from List to add into report

                //Create the report Using DataReport Class to save the history and userReport
                DataType report = new DataReport((User) user, newDoc, "CREATE", null, null, 0, time);
                // Adding the created report in specific user and document.
                ((User) user).addReport(report);
                newDoc.addHistory(report);

            }
            else
            {
                System.out.println("FAIL TO CREATE DOC: " + newTitle + " Has Already Been Created !");
            }
        }
        else
        {
            System.out.println("FAIL TO CREATE DOC: " + userId + " Is Not A Registered User !");
        }
        time++;
        //Saving the Changes to the restoreList in Document
        addToRestoreList();
    }

    //Will add the line in the given document
    public void append(String docName, String userId, String inputLine)
    {
        //Allowed only if registered User
        if (userList.search(userId)) {
            //Check if the Document is in docList or Not.
            if (docList.search(docName)) {

                //Get the data from the list to save changes in them.
                DataType doc = docList.retrieveDataType(docName);
                DataType user = userList.retrieveDataType(userId);

                //Making sure to cast the Document correctly
                if(doc instanceof Document) {

                    //Add the line at the bottom of the document
                    ((Document) doc).append(inputLine);
                    System.out.println("SUCCESS: " + inputLine + " Added in Document " + doc.getId() + " By " + userId);

                    //creating the report and adding it in the user and document.
                    DataType report = new DataReport((User) user, (Document) doc, "APPEND", null, inputLine, 0, time);
                    ((User) user).addReport(report);
                    ((Document) doc).addHistory(report);
                }
                else
                {
                    System.out.println(docName + " Is not a Document ! Check append Method");
                }
            } else {
                System.out.println("FAIL TO APPEND: " + docName + " Is Not Available !");
            }
        } else {
            System.out.println("FAIL TO APPEND: " + userId + " Is Not A Registered User !");
        }
        time++;
        addToRestoreList(); //save changes in the restoreList.
    }

    //Will Replace one line with another
    public void replace(String docTitle, String userId, int lineNumber, String inputLine) {
        //Only allowed for registered Users
        if (userList.search(userId)) {
            //Check if the document is available to edit.
            if (docList.search(docTitle)) {

                //Get data to make changes in it
                DataType newDoc = docList.retrieveDataType(docTitle);
                DataType newUser = userList.retrieveDataType(userId);

                //Replacing the old line by using replace command in Document class.
                String oldLine = ((Document) newDoc).replace(inputLine, lineNumber);

                //Only add in the report if the replace command worked successfully above this line.
                if (oldLine != null) {

                    System.out.println("SUCCESS: " + inputLine + " Replaced in Document: " + newDoc.getId() + " at Line " + lineNumber + " By " + userId);

                    //Creating and adding the report in user and document.
                    DataType report = new DataReport((User) newUser, (Document) newDoc, "REPLACE", oldLine, inputLine, lineNumber, time);
                    ((User) newUser).addReport(report);
                    ((Document) newDoc).addHistory(report);
                }

            } else {
                System.out.println("FAIL TO REPLACE: " + docTitle + " Document Not Found!");
            }
        } else {
            System.out.println("FAIL TO REPLACE: " + userId + " Is Not A Registered User !");
        }

        time++;
        addToRestoreList();
    }

    //Delete the line from the document from given line number
    public void delete(String docTitle, String userId, int lineNumber) {

        //CHECK ALL THE REQUIREMENTS
        if (userList.search(userId)) {

            if (docList.search(docTitle)) {

                DataType newDoc = docList.retrieveDataType(docTitle);
                DataType newUser = userList.retrieveDataType(userId);

                //implementing the delete command in Document class
                String deletedLine = ((Document) newDoc).delete(lineNumber);

                if (deletedLine != null) {
                    System.out.println("SUCCESS: line " + lineNumber + " has been Deleted in Document: " + newDoc.getId() + " By " + userId);

                    DataType report = new DataReport((User) newUser, (Document) newDoc, "REPLACE", deletedLine, null, lineNumber, time);
                    ((User) newUser).addReport(report);
                    ((Document) newDoc).addHistory(report);
                }

            }
            else
            {
                System.out.println("FAIL TO DELETE: " + docTitle + " Document Not Found!");
            }
        }
        else
        {
            System.out.println("FAIL TO DELETE: " + userId + " Is Not A Registered User !");
        }

        time++;
        addToRestoreList();
    }


    //Will print all the data from the given document
    public void print(String docTitle) {
        //If the doc is available to print
        if (docList.search(docTitle))
        {
            //uses the print method from the class Document
            System.out.println("--------------------------------------------------------------------");
            DataType doc = docList.retrieveDataType(docTitle);
            System.out.println(((Document) doc).print());
            System.out.println("--------------------------------------------------------------------");
        }
        else
        {
            System.out.println("FAIL TO PRINT DOC: " + docTitle + " NOT FOUND !");
        }
        time++;
        addToRestoreList();
    }

    //printing the history of commands applied on the given document
    public void history(String docName) {

        //Search the Doc and then retrieve the data and then print using the command printHistory in document.
        if (docList.search(docName))
        {
            DataType newDoc = docList.retrieveDataType(docName);
            ((Document) newDoc).printHistory();
        }
        else
        {
            System.out.println("FAIL: " + docName + " Document Does not Exist !");
        }

        time++;
        addToRestoreList();
    }

    //Similar as history. Just print the history of user.
    public void userReport(String userId) {

        if (userList.search(userId)) {

            DataType newUser = userList.retrieveDataType(userId);
            ((User) newUser).printUserReport();
        }
        else
        {
            System.out.println("FAIL: " + userId + " NOT FOUND !");
        }

        time++;
        addToRestoreList();
    }

    //Will restore the data of the document to the specific time.
    public void restore(String userId, String docName, int expectedTime)
    {
        //Only allowed to registered users
        if (userList.search(userId)) {

            //Finds the document
            if (docList.search(docName)) {

                //get the data to save changes in it.
                DataType doc = docList.retrieveDataType(docName);
                DataType newUser = userList.retrieveDataType(userId);

                //Get the old data that we want to restore from the restoreList of the document.
                Document oldDoc = ((Document)doc).retrieveOldVersion(expectedTime);

                if(oldDoc != null) {

                    //changes the data in the document using restore command
                    ((Document)doc).restore(oldDoc);
                    System.out.println("SUCCESS: " + docName + " Is Restored to Time " + expectedTime + " By " + userId );

                    DataType report = new DataReport((User) newUser, (Document) doc, "RESTORE", null, null, 0, time);
                    ((User) newUser).addReport(report);
                    ((Document) doc).addHistory(report);
                }
                else
                {
                    System.out.println( "FAIL TO RESTORE : " + docName + " Was Not Created at Time " + expectedTime );
                }
            }
            else
            {
                System.out.println("FAIL TO RESTORE: " + docName + " Document Not Found!");
            }
        }
        else
        {
            System.out.println("FAIL TO RESTORE: " + userId + " Is Not Registered !");
        }

        time++;
        addToRestoreList();
    }
    
}