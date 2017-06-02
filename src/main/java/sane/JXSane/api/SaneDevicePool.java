
package sane.JXSane.api;

import java.util.ArrayList;
import java.util.List;

public class SaneDevicePool {
    private static final SaneDevicePool INSTANCE = new SaneDevicePool();
    //This can be an concurrent list
    private final List<SaneDevice> saneDeviceList = new ArrayList<SaneDevice>();
    
    private SaneDevicePool(){
        
    }
    
    public static SaneDevicePool getInstance(){
        return INSTANCE;
    }
    
    public void addDevice(SaneDevice saneDevice){
        saneDeviceList.add(saneDevice);
    }
    
    public SaneDevice getDeviceByName(String deviceName){
        if(deviceName == null || deviceName.isEmpty()){
            throw new NullPointerException("device name null");
        }

        for (SaneDevice saneDevice : saneDeviceList) {
            if(deviceName.equalsIgnoreCase(saneDevice.getName()))return saneDevice;
        }
        
        return null;
    }
    
    public List<String> getSaneDeviceNames(){
        List<String> deviceNames = new ArrayList<String>();
        
        for(SaneDevice device:saneDeviceList){
            deviceNames.add(device.getName());
        }
        
        return deviceNames;
    }
    
    public void clearDevices(){
        saneDeviceList.clear();
    }
    
    public void addSaneDevices(List<SaneDevice> saneDevices){
        saneDeviceList.addAll(saneDevices);
    }
}
