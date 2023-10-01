import jdk.jshell.spi.ExecutionControl;
import noInPuts.InvitationController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvitationControllerTest {

    @Test
    public void testGetGeoLocationByIP() throws ExecutionControl.NotImplementedException {
        InvitationController invitationController = new InvitationController();
        String result = invitationController.getGeoLocationByIP("195.47.247.8");
        assertEquals("DK", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGeoLocationByIPWithEmptyString() throws ExecutionControl.NotImplementedException {
        InvitationController invitationController = new InvitationController();
        invitationController.getGeoLocationByIP("");
    }
}
