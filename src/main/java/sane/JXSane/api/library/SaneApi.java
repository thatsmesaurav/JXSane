package sane.JXSane.api.library;

import java.util.List;

public interface SaneApi {
    public void initializeSane();
    public List getDevices();
    public void openDevice(String deviceName);
    public void closeDevice(String deviceName);
    public void getSaneParameters(String deviceName);
    public void exitSane();
    
    
}
