package sane.JXSane.processor.image;

import sane.JXSane.api.SaneDeviceParam;

public interface ImageProcessor {

    public void convertBytesToImage(byte[] bytes, SaneDeviceParam params);

}
