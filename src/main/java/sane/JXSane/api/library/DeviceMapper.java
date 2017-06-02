/**
 * Sane device singleton Mapper. This class maps the sane device names with the Pointers.
 */
package sane.JXSane.api.library;

import com.sun.jna.Pointer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeviceMapper {

    private final Object lock = new Object();
    private final Map<String, Pointer> deviceMap = new ConcurrentHashMap<String, Pointer>();
    private static final DeviceMapper INSTANCE = new DeviceMapper();

    private DeviceMapper() {
    }

    
    public static DeviceMapper instance() {
        return INSTANCE;
    }

    public Pointer getDeviceHandler(String deviceName) {
        Pointer pointer = null;
        if (deviceName == null || deviceName.isEmpty()) {
            throw new DeviceHandlerException("Device Name is empty, please try with a valid name");
        }

        synchronized (lock) {
            pointer = deviceMap.get(deviceName);
        }

        if (pointer == null) {
            throw new DeviceHandlerException("Device Handler Not Found, May be it was never added.");
        }
        return pointer;
    }

    public void addDeviceHandler(String deviceName, Pointer handler) {
        if (deviceName == null || deviceName.isEmpty()) {
            throw new DeviceHandlerException("Device name is not valid. Cannot add.");
        }

        if (handler == null) {
            throw new DeviceHandlerException("Handler is null. Cannot add. Please provide a valid handler.");
        }
        synchronized (lock) {
            deviceMap.put(deviceName, handler);
        }
    }
    
    public void removeDeviceHandler(String deviceName){
        if(deviceName == null || deviceName.isEmpty()){
            throw new DeviceHandlerException("Device name is not valid. Cannot remove");
        }
        
        synchronized(lock){
            deviceMap.remove(deviceName);
        }
    }
}
