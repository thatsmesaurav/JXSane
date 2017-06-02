package sane.JXSane.api;

import org.junit.Before;

public class SaneDevicePoolTest {
    SaneDevicePool devicePool = null;
    
    @Before
    public void setup(){
        devicePool = SaneDevicePool.getInstance();
    }
}
