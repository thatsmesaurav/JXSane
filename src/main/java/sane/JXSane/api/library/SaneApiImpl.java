package sane.JXSane.api.library;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sane.JXSane.api.SaneDevice;
import sane.JXSane.api.SaneDeviceParam;

public class SaneApiImpl implements SaneApi {

    private final Logger logger = LoggerFactory.getLogger(SaneApiImpl.class);
    private final int MAX_IMAGE_SIZE = 20971520;

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

        SaneLibrary.INSTANCE.sane_open(deviceName, pointer);
        DeviceMapper.instance().addDeviceHandler(deviceName, pointer.getValue());
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

    /**
     * Scans a document and returns the read bytes.
     *
     * @param deviceName
     * @return
     */
    public byte[] scanDocument(String deviceName) {

        byte[] bytes = null;

        try {
            Pointer ptr = DeviceMapper.instance().getDeviceHandler(deviceName);
            SaneLibrary.INSTANCE.sane_start(ptr);

            IntByReference len = new IntByReference();
            Pointer buff = new Memory(MAX_IMAGE_SIZE);
            SaneLibrary.INSTANCE.sane_read(ptr, buff, MAX_IMAGE_SIZE, len);

            bytes = buff.getByteArray(0, len.getValue());
        } catch (Exception ex) {
            throw new SaneException(ex);
        }
        return bytes;
    }

    /**
     * 
     * @param deviceName 
     * @return SaneDeviceParam
     * 
     */
    public SaneDeviceParam getSaneParameters(String deviceName) {
        if (deviceName == null || deviceName.isEmpty()) {
            throw new SaneException("Device name is null. Please check");
        }
        NativeDeviceParams deviceParams = new NativeDeviceParams();

        try {

            
            int status = SaneLibrary.INSTANCE.sane_get_parameters(DeviceMapper.instance().getDeviceHandler(deviceName), deviceParams);

            if (status != 0) {

                String errorMsg = SaneLibrary.INSTANCE.sane_strstatus(status);
                logger.error(errorMsg);
                throw new SaneException(errorMsg);
            }

        } catch (Exception ex) {
            throw new SaneException(ex);
        }
        
        return new SaneDeviceParam(deviceParams.frameFormat, deviceParams.lastFrame, deviceParams.bytesPerLine,deviceParams.pixelsPerLine,deviceParams.lines,deviceParams.depth);
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

        public int sane_open(String name, PointerByReference handle);

        public void sane_close(Pointer handle);

        public int sane_get_parameters(Pointer handle, NativeDeviceParams deviceParams);

        public String sane_strstatus(int status);

        public int sane_start(Pointer handle);

        public int sane_read(Pointer handle, Pointer buff,
                int maxlen, IntByReference len);

        public void sane_exit();
    }

}
