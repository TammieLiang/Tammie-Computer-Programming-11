/* The teacher class contains instructions on how a teacher is made--in other words, the blue print of a teacher.
Information includes the teacher's full name and subject they're teaching.
 */
public class Teacher {
    private String firstName;
    private String lastName;
    private String subject;

    public Teacher(String firstName, String lastName, String subject){
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getSubject(){
        return this.subject;
    }

    public String toString(){
        return "Name: " + this.firstName + " Subject: " + this.subject;
    }
}
