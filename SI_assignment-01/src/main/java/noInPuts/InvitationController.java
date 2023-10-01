package noInPuts;

import jdk.jshell.spi.ExecutionControl;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class InvitationController {
    public static String getGeoLocationByIP(String ip) throws ExecutionControl.NotImplementedException {
        if (ip.isEmpty()) {
            throw new IllegalArgumentException("Missing argument");
        }

        try {
            URL url = new URL("http://wsgeoip.lavasoft.com/ipservice.asmx");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setDoOutput(true);

            String xmlPayload = String.format("<?xml version='1.0' encoding='utf-8' ?>" +
                    "<soap12:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soap12='http://www.w3.org/2003/05/soap-envelope'>" +
                    "<soap12:Body>" +
                    "<GetIpLocation xmlns='http://lavasoft.com/'>" +
                    "<sIp>%s</sIp>" +
                    "</GetIpLocation>" +
                    "</soap12:Body>" +
                    "</soap12:Envelope>", ip);

            try (OutputStream os = connection.getOutputStream();
                 OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8")) {
                osw.write(xmlPayload);
                osw.flush();
            }

            int responseCode = connection.getResponseCode();


            if (responseCode == HttpURLConnection.HTTP_OK) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(connection.getInputStream());

                NodeList nodeList = doc.getElementsByTagName("GetIpLocationResult");
                if (nodeList.getLength() > 0) {
                    String xmlResult = nodeList.item(0).getTextContent();
                    Document resultDoc = dBuilder.parse(new java.io.ByteArrayInputStream(xmlResult.getBytes("UTF-8")));
                    connection.disconnect();
                    return resultDoc.getElementsByTagName("Country").item(0).getTextContent();
                }
            }

            connection.disconnect();
            throw new IllegalArgumentException("Invalid IP address");
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

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