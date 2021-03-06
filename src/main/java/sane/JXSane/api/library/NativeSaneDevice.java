package sane.JXSane.api.library;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;


public class NativeSaneDevice extends Structure{
    
    public NativeSaneDevice(Pointer ptr){
        super(ptr);
        read();
    }
    
    public String name;
    public String vendor;
    public String model;
    public String type;
    
    @Override
    public List getFieldOrder(){
        return Arrays.asList("name","vendor","model","type");
    }
}
