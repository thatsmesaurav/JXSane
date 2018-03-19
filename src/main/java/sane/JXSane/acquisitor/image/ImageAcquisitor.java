package sane.JXSane.acquisitor.image;

import java.awt.image.BufferedImage;
import sane.JXSane.api.SaneDeviceParam;

public interface ImageAcquisitor {

    public BufferedImage convertBytesToImage(byte[] bytes, SaneDeviceParam params);

}
