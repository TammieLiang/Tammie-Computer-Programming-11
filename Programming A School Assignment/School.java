/*
This is where a school is built--the blue print of a school. A school consists of a list of teachers,
a list of students, a list of courses taught, the school name, the date the school was built in, and what
city the school is located. There are various functions of the school to manage its students and teachers.
 */
import java.util.ArrayList;

public class School {
    private ArrayList <Teacher> teacherList = new ArrayList<>();
    private ArrayList <Student> studentList = new ArrayList<>();
    private ArrayList <String> courses = new ArrayList<>();
    private String schoolName;
    private int schoolBuiltDate;
    private String city;

    public School(String schoolName, int builtDate, String city){
        this.teacherList = new ArrayList<>();
        this.studentList = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.schoolName = schoolName;
        this.schoolBuiltDate = builtDate;
        this.city = city;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolBuiltDate() {
        return schoolBuiltDate;
    }

    public void setSchoolBuiltDate(int schoolBuiltDate) {
        this.schoolBuiltDate = schoolBuiltDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //this method adds a specific teacher to the school's list of teachers
    public void addTeacher(Teacher teacher){
        this.teacherList.add(teacher);
    }

    //this method adds a specific student to the school's list of students
    public void addStudent(Student student){
        this.studentList.add(student);
    }

    //this method removes a specific teacher from the school's list of teachers
    public void deleteTeacher (Teacher teacher){
        this.teacherList.remove(teacher);
    }

    //this method removes a student from a specific index position
    public void removeStudentBasedOnListNum (int number){
        if (number <= this.studentList.size()-1 && number >= 0){
            this.studentList.remove(this.studentList.get(number));
        } else System.out.println("This student does not exist, or they have already been removed.");
    }

    //this adds all the unique courses taught by all the teachers in the school onto the course list.
    //there will be no duplicate courses in the course list.
    public void setCourseList(){
        for (int i = 0; i < teacherList.size(); i++){
            if (!this.courses.contains(teacherList.get(i).getSubject())){
                this.courses.add(teacherList.get(i).getSubject());
            }
        }
    }

    //this lists all the unique courses taught by all the teachers in the school
    public void listAllCourses(){
        for (String course: courses){
            System.out.println(course);
        }
    }
    //this method displays each teacher's first name, last name, and subject taught from the list of teachers
    public void listAllTeachers(){
        for (Teacher teacher: teacherList){
            System.out.println(teacher);
        }
    }

    //this method displays each student's first name, last name, and grade from the list of students
    public void listAllStudents(){
        for (Student student: studentList){
            System.out.println(student);
        }
    }

    //this method lists all the students' student numbers from the list of students
    public void listAllStudentNum(){
        for (Student student: studentList){
            System.out.println(student.getStudentNumber());
        }
    }
}
