package org.studentList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AddStudent {
    public static void addStudent(String studentName, int studentId) {
        if(isExists(studentId)) {
            System.out.println("aynı id'ye sahip bir öğrenci mevcut, yanlış bir giriş yaptınız.");
            return;
        }
        Connection.openConnection("POST", 0);

        try {
            // JSON verisini gövdeye yazma
            Student student = new Student();
            student.setId(studentId);
            student.setName(studentName);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            String jsonInputString = objectMapper.writeValueAsString(student);

            try (OutputStream os = Connection.connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = Connection.connection.getResponseCode();
            if (responseCode < 300)
                System.out.println("öğrenci başarıyla eklendi.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        GetStudents.printStudents();
    }
    // Öğrenci ID'sinin var olup olmadığını kontrol eden metot
    private static boolean isExists(int studentId) {
        try {
            // 1. Mevcut öğrencileri al
            List<Student> students = GetStudents.getStudents();
            if (students == null) {
                System.out.println("Öğrenci listesi alınırken bir hata oluştu.");
                return false;
            }

            // 2. ID çakışmasını kontrol et
            return students.stream().anyMatch(student -> student.getId() == studentId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
