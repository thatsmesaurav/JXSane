package sane.JXSane.api.library;

import java.util.List;
import sane.JXSane.api.SaneDeviceParam;

public interface SaneApi {
    public void initializeSane();
    public List getDevices();
    public void openDevice(String deviceName);
    public void closeDevice(String deviceName);
    public SaneDeviceParam getSaneParameters(String deviceName);
    public byte[] scanDocument(String deviceName);
    public void exitSane();
    
    
}
