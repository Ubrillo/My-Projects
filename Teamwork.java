package cwk4; 


/**
 * Details of your team
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "54";
        
        details[1] = "Lay";
        details[2] = "Matthew";
        details[3] = "21045748";

        details[4] = "Udoette";
        details[5] = "Ubong";
        details[6] = "21085920";

        details[7] = "Nahian";
        details[8] = "Saad";
        details[9] = "21067680";


        details[10] = "Karanja";
        details[11] = "James";
        details[12] = "20073213";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
