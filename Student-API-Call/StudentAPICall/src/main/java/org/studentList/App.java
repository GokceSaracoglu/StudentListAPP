package org.studentList;

public class App {
    public static void run(){
        APIcall.ApiCaller();
        System.out.println("------------------");
        APIcall.addStudent(new Student("ahmet", 12));
        APIcall.ApiCaller();
        System.out.println("------------------");
        APIcall.deleteStudent(new Student("gökçe", 23));
        APIcall.ApiCaller();
        System.out.println("------------------");
    }
}
