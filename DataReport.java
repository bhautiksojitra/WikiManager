/*----------------------------------------------------

    CLASS : Data Report
    AUTHOR : BHAUTIK SOJITRA (7900140)

    PURPOSE : CREATES REPORT FOR EACH COMMAND EXECUTED
 -------------------------------------------------------*/
public class DataReport extends DataType {

    //All the required data to print enough information about the command executed
    private User user;
    private Document document;
    private String commandName;
    private String line;
    private String oldLine;
    private int lineNumber;
    private int time;

    //constructor ->set all the values based on the command
    public DataReport(User newUser, Document newDocument, String newCommand,String oldLine , String newLine , int newLineNumber , int newTime) {
        user = newUser;
        document = newDocument;
        commandName = newCommand;
        line = newLine;
        this.oldLine = oldLine;
        lineNumber = newLineNumber;
        time = newTime;
    }

    //deep copy methods which overrides the methods in abstract class
    public DataType deepCopy()
    {
        DataType newData = new DataReport(user,document,commandName,line,oldLine,lineNumber,time);

        return newData;
    }

    //Return all the information when the command executes and store it in the list.
    public String getId()
    {
        String str = "";

        //Various printing style based on the command executed
        if(commandName.equals("CREATE"))
        {
            str = str + "Created By: " + user.getId() +
                    " | Document Name: " + document.getId() +
                    " | Command Executed: " + commandName;
        }
        else if(commandName.equals("APPEND"))
        {
            str = str + "Edited By: " + user.getId() +
                    " | DocumentName: " + document.getId() +
                    " | Command Executed: " + commandName +
                    " | Line Appended: " + line;
        }
        else if(commandName.equals("REPLACE"))
        {
            str = str + "Edited By: " + user.getId() +
                    "  | DocumentName: " + document.getId() +
                    "  | Command Executed: " + commandName +
                    "  | New Line Replaced: " + line +
                    "  | Old Line: " + oldLine +
                    "  | Line number: " + lineNumber;
        }
        else if(commandName.equals("DELETE"))
        {

            str = str + "Edited By: " + user.getId() +
                    "  | DocumentName: " + document.getId() +
                    "  | Command Executed: " + commandName +
                    "  | Line Deleted: " + oldLine +
                    "  | Line number: " + lineNumber;


        }
        else if(commandName.equals("RESTORE"))
        {
            str = str + "Edited By: " + user.getId() +
                    " | Document: " + document.getId() +
                    " | Command Executed: " + commandName +
                    " | Time: " + time;

        }
        else
        {
            str = str + " Edited By: " + user.getId() +
                        " | DocumentName: " + document.getId() +
                        " | Command Executed: " + commandName +
                        " | line :" + line +
                        " | Line number: " + lineNumber;

        }

        return str;
    }

}
