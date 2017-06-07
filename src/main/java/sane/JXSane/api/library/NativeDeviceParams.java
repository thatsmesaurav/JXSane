package sane.JXSane.api.library;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class NativeDeviceParams extends Structure {
    
    public int frameFormat;
    public boolean lastFrame;
    public int bytesPerLine;
    public int pixelsPerLine;
    public int lines;
    public int depth;
    
    @Override
    public List getFieldOrder(){
        return Arrays.asList("frameFormat","lastFrame","bytesPerLine","pixelsPerLine","lines","depth");
    }
}
