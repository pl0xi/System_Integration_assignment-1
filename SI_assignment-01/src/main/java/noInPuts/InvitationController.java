package noInPuts;

import jdk.jshell.spi.ExecutionControl;

public class InvitationController {
    public static void getGeoLocationByIP(String ip) throws ExecutionControl.NotImplementedException {
        if(ip.isEmpty()) {
            throw new IllegalArgumentException("Missing argument");
        }

        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

    public static void getGenderByNameAndLocation(String location, String name) throws ExecutionControl.NotImplementedException {
        if(location.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("Missing argument");
        }

        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

}
