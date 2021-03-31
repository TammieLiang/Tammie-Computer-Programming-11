public class Main {
    public static void main(String [] args){
        School school = new School("Gladstone Secondary School", 1950, "Vancouver");

        Student a = new Student("Bob", "Joe", 12);
        school.addStudent(a);
        school.addStudent(new Student("Billy", "Lee", 11));
        school.addStudent(new Student("John", "Nguyen", 11));
        school.addStudent(new Student("Mitchel", "Lin", 8));
        school.addStudent(new Student("Isabella", "Chang", 10));
        school.addStudent(new Student("Rachel", "White", 8));
        school.addStudent(new Student("Derrick", "Mohammed", 8));
        school.addStudent(new Student("Paul", "Chambers", 12));
        school.addStudent(new Student("Ryan", "Lighton", 10));
        school.addStudent(new Student("Ji", "Unki", 9));

        Teacher b = new Teacher("Kate", "Pineo", "Chemistry");
        school.addTeacher(new Teacher("Shirley", "Burdon", "AP Literature and Composition"));
        school.addTeacher(new Teacher("Colleen", "Marshall", "Art"));

        school.listAllStudents();
        System.out.println("---------------------------------------");
        school.listAllTeachers();
        System.out.println("---------------------------------------");

        school.deleteStudent(a);
        school.deleteTeacher(b);

        school.listAllStudents();
        System.out.println("---------------------------------------");
        school.listAllTeachers();
        System.out.println("---------------------------------------");

    }
}
