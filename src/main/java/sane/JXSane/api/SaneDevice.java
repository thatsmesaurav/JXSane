
package sane.JXSane.api;

final public class SaneDevice {
    final private String name;
    final private String vendor;
    final private String model;
    final private String type;
    
    public SaneDevice(String name,String vendor, String model,String type){
        this.name = name;
        this.vendor = vendor;
        this.model = model;
        this.type = type;
    }
    
    final public String getName(){
        return name;
    }
    
    final public String getDeviceDetails(){
        String deviceDtls = "Device name --> " + name + "Device vendor --> " +vendor +"Device model --> " + model + "Device type --> " +type;
        return deviceDtls;
    }
    
    @Override
    public Object clone(){
        throw new UnsupportedOperationException();
    }
}
