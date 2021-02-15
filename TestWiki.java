/*----------------------------------------------------

    Class: TestWiki
    Name: Bhautik Sojitra  (7900140)
    Purpose: Test the project using Junit Testing libraries

 ------------------------------------------------------*/

//Importing libraries of Junit testing
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Testing the Data structure and the Classes
public class TestWiki {

    @Test
    public void testAddInList() // Testing the add method of the List
    {
        List newList = new List(); //creating the list

        //checking if the list is created or not
        assertNotNull(newList);

        //creates some data of different data types
        DataType userData = new User("Mike");
        DataType docData = new Document("OOP");

        User newUser = new User("ALI");

        //Test if add method works or not.
        assertTrue(newList.add(userData));
        assertTrue(newList.add(docData));

    }

    @Test
    public void testSearch() //Testing search method in the ist
    {
        List newList = new List(); //new List created

        DataType data = new User("Mike"); //new data created
        newList.add(data); // data added in the list
        newList.add(new User("Ali"));

        //Testing search method
        assertTrue(newList.search("Mike"));
        assertTrue(newList.search("Ali"));

        assertFalse(newList.search("Bhautik"));

    }

    @Test
    public void testDelete() //test delete method in the list.
    {
        List newList = new List(); //newList created.

        newList.add(new User("A")); // data at the top
        newList.delete("A"); // data deleted from the top ->edge case
        assertFalse(newList.search("A")); // Tested deletion from the list

        newList.add(new Document("B"));
        newList.add(new Document("C"));

        //deleted all the data added before
        newList.delete("C");

        //Tested general case of delete method
        assertTrue(newList.search("B"));
        assertFalse(newList.search("C"));

    }

    @Test
    public void testRetrieveData() // testing retrieveData method in the list
    {
        List newList = new List(); // List created

        DataType data1 = new User("A"); // data Object created
        DataType data2 = new Content("B");

        newList.add(data1); // data added in the list
        newList.add(data2);

        //Compare the returned data from the method retrieveDataType method.
        assertEquals(data1 , newList.retrieveDataType("A"));
        assertNotEquals(data1 , newList.retrieveDataType("B"));
        assertEquals(data2 , newList.retrieveDataType("B"));

    }

    @Test
    public void testDeepCopy() // Testing the deep copy method of the list
    {
        List newList = new List(); // newList created

        // some data added in the list.
        newList.add(new User("B"));
        newList.add(new User("A"));
        newList.add(new User("C"));

        //created clone list using deep copy method of the list
        List cloneList = newList.deepCopyList();

        //Iterate through both the lists and compare the data in them
        List.Iterator orig = newList.iterator();
        List.Iterator clone = cloneList.iterator();

        while(orig.hasNext())
            assertEquals(orig.next().getId() , clone.next().getId()); // comparing the Id of the data type.

    }

    @Test
    public void testContent() // testing content class
    {
        //content created
        Content newLine = new Content("new Line");

        //Testing if the method works
        assertEquals("new Line" , newLine.getId());

        //setting the different line
        newLine.setData("second line");

        //Testing the method
        assertEquals("second line" , newLine.getId());
        assertNotEquals("new Line" , newLine.getId());

    }

    @Test
    public void testGetLine()// testing the get line method from the document
    {
        // documents created
        Document newDoc = new Document("Book");

        // lines appended
        newDoc.append("first line");
        newDoc.append("second line");

        //testing various cases of the method.
        assertEquals("first line" , newDoc.getLine(0));
        assertEquals("second line" , newDoc.getLine(1));
        assertNull(newDoc.getLine(2));

    }

    @Test
    public void testReplace() // Testing replace in the document.
    {
        Document newDoc = new Document("BOOK"); // new doc created

        newDoc.append("first line"); // new Line added in the doc.
        newDoc.append("second line");

        String oldLine = newDoc.replace("1st Line" , 0 );

        //replace returns old line
        assertEquals("first line" , oldLine );
        //Testing the new line
        assertEquals("1st Line" , newDoc.getLine(0));
        //comparing old line with the current line.
        assertNotEquals(oldLine , newDoc.getLine(0));
    }

    @Test
    public void testRestore() //Testing restore method in the list
    {
        //created one document
        Document newDoc = new Document("Book");
        newDoc.append("first line");
        newDoc.append("second line");

        //doc to restore
        Document toRestore = new Document("oldBook");

        newDoc.restore(toRestore); // old doc restored in the new one

        assertEquals(toRestore.getId() , newDoc.getId()); // checking if it is restored

    }

    @Test
    public void testDeleteInDoc()
    {
        //Document created
        Document newDoc = new Document("Book");

        // lines appended
        newDoc.append("first Line");
        newDoc.append("second Line");

        assertEquals("second Line" , newDoc.delete(1)); // Test deletion of line
        assertNull(newDoc.getLine(1)); // check if it's deleted or not

    }

}
