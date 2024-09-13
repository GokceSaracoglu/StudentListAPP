package org.studentList.app;

import org.studentList.AddStudent;
import org.studentList.DeleteStudent;
import org.studentList.GetStudents;

public class App {
    public static void main(String[] args) {
           run();
        }
    public static void run(){

        AddStudent.addStudent("yakup", 1);
        DeleteStudent.deleteStudent(25);



    }
}
