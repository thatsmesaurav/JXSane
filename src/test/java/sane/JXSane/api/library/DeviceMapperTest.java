package sane.JXSane.api.library;

import com.sun.jna.Pointer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeviceMapperTest {

    private DeviceMapper mapper = null;

    @Before
    public void setUp() {
        mapper = DeviceMapper.instance();
    }

    @Test(expected = DeviceHandlerException.class)
    public void testAddDeviceStringException() throws DeviceHandlerException {
        mapper.addDeviceHandler("", new Pointer(0));
    }

    @Test(expected = DeviceHandlerException.class)
    public void testAddDevicePointerException() throws DeviceHandlerException {
        mapper.addDeviceHandler("Device", Pointer.NULL);
    }

    @Test
    public void testAddDevice() throws DeviceHandlerException {
        mapper.addDeviceHandler("Device", new Pointer(0));
    }

    @Test(expected = DeviceHandlerException.class)
    public void testGetDeviceException() throws DeviceHandlerException {
        mapper.getDeviceHandler("");
    }

    @Test
    public void testGetDevice() throws DeviceHandlerException {
        Pointer pointer = mapper.getDeviceHandler("Device");
        Assert.assertEquals(new Pointer(0), pointer );
    }
}
