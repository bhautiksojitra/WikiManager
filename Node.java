/*----------------------------------------------------

    CLASS : Node
    AUTHOR : BHAUTIK SOJITRA (7900140)

    PURPOSE : CONTAINS THE DATA WITH THE POINTER TO THE NEXT OBJECT
 -------------------------------------------------------*/

public class Node{

    private DataType data; //holds data
    private Node next;  // pointer to the next object

    //adds new data
    public Node(DataType newData)
    {
        data = newData;
    }

    //retrieve the data
    public DataType getData()
    {
        return data;
    }

    //get next pointer
    public Node getNext()
    {
        return next;
    }

    //set new data
    public void setData(DataType newData)
    {
        data = newData;
    }

    //set new next pointer
    public void setNext(Node newNext)
    {
        next = newNext;
    }

}
