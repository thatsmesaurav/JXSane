package sane.JXSane.api.library;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.ArrayList;
import java.util.List;
import sane.JXSane.api.SaneDevice;

public class SaneApiImpl implements SaneApi {

    /**
     * Initialize the sane backend -- Must be the first call before calling any
     * other sane API
     */
    public void initializeSane() {
        String[] auth = {};
        IntByReference version = new IntByReference();
        SaneLibrary.INSTANCE.sane_init(version, auth);
    }

    /**
     * Get all the sane devices in the form of a list connected to the computer
     *
     * @return List
     * @throws SaneException
     */
    public List getDevices() {
        List<SaneDevice> saneDevices = null;
        PointerByReference saneDeviceListPointer;

        try {
            saneDevices = new ArrayList<SaneDevice>();
            saneDeviceListPointer = new PointerByReference();

            SaneLibrary.INSTANCE.sane_get_devices(saneDeviceListPointer, true);

            for (Pointer ptr : saneDeviceListPointer.getValue().getPointerArray(0)) {
                NativeSaneDevice nativeSaneDevice = new NativeSaneDevice(ptr);
                saneDevices.add(new SaneDevice(nativeSaneDevice.name, nativeSaneDevice.vendor, nativeSaneDevice.model, nativeSaneDevice.type));
            }
        } catch (Exception ex) {
            throw new SaneException(ex);
        }
        return saneDevices;
    }

    /**
     * Opens a device and returns in a opaque pointer handler.
     *
     * @throws SaneException
     * @param deviceName
     */
    public void openDevice(String deviceName) {
        PointerByReference pointer = new PointerByReference();

        if (deviceName == null || deviceName.isEmpty()) {
            throw new SaneException("Device name is null, Please check");
        }

        SaneLibrary.INSTANCE.sane_open(deviceName, pointer.getPointer());
        DeviceMapper.instance().addDeviceHandler(deviceName, pointer.getPointer());
    }

    /**
     * Closes a device given a pointer handler
     *
     * @throws SaneException
     * @param deviceName
     */
    public void closeDevice(String deviceName) {

        if (deviceName == null || deviceName.isEmpty()) {
            throw new SaneException("Device name is null. Please check");
        }
        SaneLibrary.INSTANCE.sane_close(DeviceMapper.instance().getDeviceHandler(deviceName));
        DeviceMapper.instance().removeDeviceHandler(deviceName);
    }

    public void getSaneParameters(String deviceName) {
        if (deviceName == null || deviceName.isEmpty()) {
            throw new SaneException("Device name is null. Please check");
        }

        try {

            NativeDeviceParams params = new NativeDeviceParams();
            int i = SaneLibrary.INSTANCE.sane_get_parameters(DeviceMapper.instance().getDeviceHandler(deviceName), params);
            System.out.println("ststus hai " + i);
            System.out.println("sane.JXSane.api.library.SaneApiImpl.getSaneParameters()");

        } catch (Exception ex) {
            throw new SaneException(ex);
        }
    }

    /**
     * Exits all the sane backend. Must be called at the end, while closing the
     * application to release all the resources
     */
    public void exitSane() {
        SaneLibrary.INSTANCE.sane_exit();
    }

    /**
     * Sane Library Interface mapped to the native library
     */
    public interface SaneLibrary extends Library {

        public SaneLibrary INSTANCE = (SaneLibrary) Native.loadLibrary("sane", SaneLibrary.class);

        public int sane_init(IntByReference version_code, Object callback);

        public int sane_get_devices(PointerByReference pointerToDeviceList, boolean localOnly);

        public int sane_open(String name, Pointer handle);

        public void sane_close(Pointer handle);

        public int sane_get_parameters(Pointer handle, NativeDeviceParams params);

        public void sane_exit();
    }

}
