package noInPuts;

import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String csvFile = "src/main/resources/partners.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;

            // Header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                String name = fields[0];
                String email = fields[1];
                String ipAddress = fields[2];

                String location = GeoLocationController.getGeoLocationByIP(ipAddress);
                String gender = GenderController.getGenderByNameAndLocation(location, name);
                String invitationMessage = InvitationController.createInvitationForm(name, gender);
                MailController.sendMail(email, invitationMessage, "Invitation to Annual General Assembly", "src/main/resources/yearly_report.pdf");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}