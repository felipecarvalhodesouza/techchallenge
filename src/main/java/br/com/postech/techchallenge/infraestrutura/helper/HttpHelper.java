package br.com.postech.techchallenge.infraestrutura.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

    private String API_URL = "https://7yy2pse5ii.execute-api.us-east-1.amazonaws.com/techchallenge/auth";
    
    public String sendPostRequest(String requestBody) throws IOException {
    	return sendPostRequest(requestBody, (HttpURLConnection) new URL(API_URL).openConnection());
    }
    
    public String sendPostRequest(String requestBody, URL url) throws IOException {
    	return sendPostRequest(requestBody, (HttpURLConnection) url.openConnection());
    }

    public String sendPostRequest(String requestBody, HttpURLConnection connection) throws IOException {
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
