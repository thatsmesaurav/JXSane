package sane.JXSane;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import sane.JXSane.api.SaneDevice;
import sane.JXSane.api.SaneDeviceParam;
import sane.JXSane.api.library.SaneApi;
import sane.JXSane.api.library.SaneApiImpl;
import sane.JXSane.acquisitor.image.ImageAcquisitor;


/**
 * To test this class either you need to create a mock object or connect
 * scanners. Hence the tests are not completed deliberately.
 *
 */
public class SaneAPITest {

    SaneApi saneApi = null;
    ImageAcquisitor imagePrssr = null;

    @Before
    public void setup() {
        saneApi = new SaneApiImpl();
        
    }

    @Test
    public void testSaneLib() {
        try {
            
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
