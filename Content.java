/*----------------------------------------------------

    CLASS : Content
    AUTHOR : BHAUTIK SOJITRA (7900140)

    PURPOSE : CONTAINS THE LINE AND REFERENCE TO THE NEXT LINE IN A DOCUMENT
 -------------------------------------------------------*/

public class Content extends DataType{

    private String line; // stores the line as a data. Works as a dataType for the list.

    public Content(String newLine)
    {
        line = newLine;
    }

    //returns the data in content
    public String getId()
    {
        return line;
    }

    public void setData(String newLine)
    {
        line = newLine;
    }

    //creates new content Object
    public DataType deepCopy()
    {
        return new Content(line);
    }



}
