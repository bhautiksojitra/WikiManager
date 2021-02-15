/*----------------------------------------------------

    CLASS : List
    AUTHOR : BHAUTIK SOJITRA (7900140)

    PURPOSE : HOLDS THE VARIOUS KIND OF DATA. CREATES A DATA STRUCTURE. MANAGES THE DATA.
              ENTRY,DELETE,PRINT,ETC.
 -------------------------------------------------------*/

public class List {

    //pointer to the first data
    private Node head;
    Iterator i = new Iterator(); // Iterator to initialise through the entire list

    //Initialise the list.
    public List()
    {
        head = null;
    }

    //Add data in the list
    public boolean add(DataType newData)
    {
        //If the Input is valid
        if(newData != null) {

            //creates the new node for the input
            Node newNode = new Node(newData);

            //if the list is empty
            if(this.head == null)
            {
                this.head = newNode; //add the data top the top of the list
            }
            else
            {
                //if list isn't empty then add the data to the end of the list
                Node last = this.head;

                //To add data in this case we need to iterate through the list and reach to the end.
                while (last.getNext() != null)
                {
                    last = last.getNext();
                }
                last.setNext(newNode); //add the node with the data at the END.
            }
            return true;
        }
        else
        {
            System.out.println("Data to add Is NULL ! Check the values Again.");
            return false;
        }
    }

    //search the data by using its uniqueId
    public boolean search(String id)
    {
        if(head != null)
        {
            i.returnToTop(); // reset  the iterator

            //Iterate through the list and returns true if the data is found.
            while (i.hasNext())
            {
                if (i.next().getId().equals(id))
                    return true;

            }
        }

        return false;
    }

    //Get the reference of the data from the list to make changes in the data
    public DataType retrieveDataType(String id)
    {
        i.returnToTop(); // reset the iterator

        //Iterate through the list and return the reference
        while(i.hasNext())
        {
            DataType dataRef = i.next();
            if(dataRef.getId().equals(id))
            {
                return dataRef; //returns the data as a dataType
            }
        }
        return null; // returns null pointer

    }


    //print all the data
    public void printList()
    {
        i.returnToTop(); // reset the iterator

        while(i.hasNext())
            System.out.println(i.next().getId()); //prints the data

    }

    //delete the element of the given id from the list.
    public void delete(String id)
    {
        boolean isPresent = search(id);

        //if data is present
        if(isPresent)
        {
            //set two pointers to the list.
            Node current = this.head;
            Node previous = null;

            //if the element is at the top.
            if(current != null && current.getData().getId().equals(id))
            {
                this.head = current.getNext(); //set head as it points to the second data.
            }

            //else iterate through the list and finds the data to delete
            while (current != null && !current.getData().getId().equals(id))
            {
                previous = current;
                current = current.getNext();
            }

            //if the data is found change the pointers of previous to next.
            if (current != null && current.getData().getId().equals(id) && previous != null)
            {
                previous.setNext(current.getNext());
            }
        }
        else
        {
            System.out.println("FAIL TO DELETE: "  + id + " Not found !");
        }
    }

    //clone the list
    public List deepCopyList()
    {
        //create the new list.
        List copyList = new List();

        if(this.head != null && this.head.getData() != null) {

            //create the new data and node
            DataType copyData = this.head.getData().deepCopy();

            copyList.head = new Node(copyData);

            //Iterate through the list and clone all the data.
            Node temp = this.head.getNext();
            Node tempForCopy = copyList.head;

            while (temp != null) {

                DataType newData = (temp.getData()).deepCopy();
                Node newNode = new Node(newData);
                tempForCopy.setNext(newNode);


                temp = temp.getNext();
                tempForCopy = tempForCopy.getNext();
            }

            return copyList;
        }
        else
            return null;

    }

    //iterate through the list
    public Iterator iterator()
    {
        return new Iterator();
    }


    //helps to iterate through the list.
    public class Iterator {

        private Node temp;

        public Iterator( )
        {
            temp = head;
        } // Initialise the iterator

        public boolean hasNext()
        {
            return (temp != null);
        } // checks if it reaches to the end or not

        public void returnToTop()
        {
            temp = head;
        } // reset the Iterator

        //returns the data at the pointer of iterator and increments the pointer
        public DataType next()
        {
            DataType data = temp.getData();
            temp = temp.getNext();
            return data;
        }

    }

}
