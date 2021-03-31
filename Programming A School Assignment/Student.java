/* The student class instructs how a student is constructed--in other words, the blue print of a student.
Information of the student includes full name, grade, and unique student ID number.
 */
public class Student {

    private String firstName;
    private String lastName;
    private int grade;
    private static int uniqueNumber = 1;
    private int studentNumber;

    public Student(String firstName, String lastName, int grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        studentNumber = uniqueNumber;
        uniqueNumber++;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    //this method prints out the student's full information (
    public String toString(){
        return "Name: " + this.firstName + " Grade: " + this.grade;
    }
}
