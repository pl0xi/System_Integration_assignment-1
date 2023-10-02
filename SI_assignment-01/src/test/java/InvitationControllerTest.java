import jdk.jshell.spi.ExecutionControl;
import noInPuts.InvitationController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvitationControllerTest {

    @Test
    public void testCreateInvitationForm() throws ExecutionControl.NotImplementedException {
        String result = InvitationController.createInvitationForm("Henrik Kristensen", "male");
        assertEquals("Dear Mr. Henrik Kristensen.\n\nI hope this email finds you in good health and high spirits. " +
                "We are excited to announce our upcoming Annual General Assembly (GA) meeting, " +
                "and we cordially invite you to join us for this important event.\n\n" +
                "The event is set to be at 03-11-2023 at 14:30 CEST +2 at the company Zoom meeting room.\n\n" +
                "In order to provide you with the necessary information and materials, " +
                "please find attached a copy of our company's Yearly Report for your review.\n\n" +
                "Best regards, \n\n" +
                "The Company", result);
    }

}
