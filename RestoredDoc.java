/*----------------------------------------------------

    CLASS : RestoredDoc
    AUTHOR : BHAUTIK SOJITRA (7900140)

    PURPOSE : HELPFUL TO STORE THE OLD DATA OF THE DOCUMENT WITH THE TIME
 -------------------------------------------------------*/
public class RestoredDoc extends DataType{

    private Document doc; //holds the ref of data
    private int time; // time to keep track of various old data

    public RestoredDoc(Document newDoc , int time)
    {
        doc = newDoc;
        this.time = time;
    }

    //return the time of the command execution on doc.
    public int getTime()
    {
        return time;
    }

    //returns the doc
    public Document getDoc()
    {
        return doc;
    }

    //returns unique id.
    public String getId() { return doc.getId(); }

}
