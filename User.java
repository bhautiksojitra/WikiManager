public class User extends DataType
{
    private String uniqueId;
    private List userReport;

    public User(String userId)
    {
        uniqueId = userId;
        userReport = new List();
    }

    public void addReport(DataType newReport)
    {
        userReport.add(newReport);
    }

    public String getId()
    {
        return uniqueId;
    }

    public boolean equals(String id)
    {
        if(getId().equals(id))
        {
            return true;
        }
        return false;
    }

    public void printUserReport()
    {

        System.out.println("-------------------NEW USER REPORT----------------------------");
        System.out.println("User Name: " + this.getId());
        userReport.printList();
        System.out.println("-------------------USER REPORT ENDS-----------------------");
    }





}
