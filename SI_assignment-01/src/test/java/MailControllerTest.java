import noInPuts.MailController;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MailControllerTest {
    // https://www.wpoven.com/tools/free-smtp-server-for-testing
    @Test
    public void testSendMail() {
        boolean result = MailController.sendMail("test@amsd.com", "Body Message", "Test Mail", "src/main/resources/yearly_report.pdf");
        assertTrue(result);
    }
}
