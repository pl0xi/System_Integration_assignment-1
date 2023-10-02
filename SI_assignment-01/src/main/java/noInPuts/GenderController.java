package noInPuts;

import jdk.jshell.spi.ExecutionControl;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenderController {
    public static String getGenderByNameAndLocation(String location, String name) throws ExecutionControl.NotImplementedException {
        if (location.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("Missing argument");
        }

        try {
            URL url = new URL("https://gender-api.com/v2/gender");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer 60d2c304d78b5ebc92650c5e5a347cb1a091e9b7d0c053ad45834f89ef8d459f");
            connection.setDoOutput(true);

            String jsonPayload = String.format("{\"full_name\":\"%s\",\"country\":\"%s\"}", name, location);
            try (OutputStream os = connection.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")) {
                osw.write(jsonPayload);
                osw.flush();
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    JSONObject jsonResponse = new JSONObject(response.toString());

                    return jsonResponse.getString("gender");
                }
            }

            connection.disconnect();
            throw new IllegalArgumentException("Invalid arguments");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
