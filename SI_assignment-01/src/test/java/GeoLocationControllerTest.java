import jdk.jshell.spi.ExecutionControl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeoLocationControllerTest {
    @Test
    public void testGetGeoLocationByIP() throws ExecutionControl.NotImplementedException {
        String result = noInPuts.GeoLocationController.getGeoLocationByIP("195.47.247.8");
        assertEquals("DK", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGeoLocationByIPWithEmptyString() throws ExecutionControl.NotImplementedException {
        noInPuts.GeoLocationController.getGeoLocationByIP("");
    }
}
