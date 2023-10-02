import jdk.jshell.spi.ExecutionControl;
import noInPuts.GenderController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenderControllerTest {
    @Test
    public void testgetGenderByNameAndLocation() throws ExecutionControl.NotImplementedException {
        String result = GenderController.getGenderByNameAndLocation("DK", "Jens Lauge");
        assertEquals("male", result);
    }
}
