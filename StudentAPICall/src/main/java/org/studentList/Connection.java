package org.studentList;

import java.net.HttpURLConnection;
import java.net.URI;

public class Connection {
    private static final String baseURL;
    public static HttpURLConnection connection;

    static {
        // Config sınıfını kullanarak baseURL'i al
        Config config = new Config();
        baseURL = config.getBaseURL();
    }


    public static void openConnection(String method, int studentId){
        try {
            URI uri = new URI(baseURL);
            if("DELETE".equals(method))
                uri = new URI(baseURL.concat("/").concat(String.valueOf(studentId)));

            connection = (HttpURLConnection)uri.toURL().openConnection();
            connection.setRequestMethod(method);

            if ("POST".equals(method)) {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
