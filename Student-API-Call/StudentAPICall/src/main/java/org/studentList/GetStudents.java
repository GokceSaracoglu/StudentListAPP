package org.studentList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.sql.SQLOutput;
import java.util.List;

public class GetStudents {

    public static void getStudents(){
    try {
        Config config = new Config();
        String baseURL = config.getBaseURL();
        URI uri = new URI(baseURL);
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            JsonNode jsonNode = objectMapper.readTree(response.toString());
            List<Student> students =objectMapper.readValue(response.toString(),new TypeReference<List<Student>>() {});

            for (Student student : students) {
                System.out.printf("Student name: %s, student id: %d%n", student.getName(), student.getId());
            }

            System.out.printf("%d öğrenci var", students.size());
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
