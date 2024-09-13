package org.studentList;
import java.util.List;
public class DeleteStudent {
    public static void deleteStudent(int studentId) {
        try {
            List<Student> students = GetStudents.getStudents();
            if (students == null) {
                System.out.println("Öğrenci listesi alınırken bir hata oluştu.");
                return;
            }

            //Öğrenci ID'sini kontrol et
            boolean studentExists = students.stream().anyMatch(student -> student.getId() == studentId);

            if (studentExists) {
                // Öğrenci varsa, DELETE isteği gönder
                Connection.openConnection("DELETE", studentId);
                int responseCode = Connection.connection.getResponseCode();
                if (responseCode < 300) {
                    System.out.println("Öğrenci başarıyla silindi.");
                } else {
                    System.out.println("Öğrenci silinirken bir hata oluştu");
                }
            } else {
                System.out.println("Öğrenci bulunamadı.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        GetStudents.printStudents();
    }
}
