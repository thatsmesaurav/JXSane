package sane.JXSane.acquisitor.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sane.JXSane.api.SaneDeviceParam;

public abstract class AbstractImageAcquisitor implements ImageAcquisitor {

    private final Logger logger = LoggerFactory.getLogger(AbstractImageAcquisitor.class);

    public BufferedImage convertBytesToImage(byte[] bytes, SaneDeviceParam params) {

        BufferedImage image = getBufferedImage(params.getWidth(),params.getHeight());
        try {
            

            WritableRaster raster = (WritableRaster) image.getData();
            raster.setPixels(0, 0, params.getWidth(), params.getHeight(), convertBytesToInt(bytes));
            image.setData(raster);
            
            File file = new File("/home/saurav/wow.bmp");
            ImageIO.write(image, "bmp", file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return image;
    }

    
    protected abstract BufferedImage getBufferedImage(int width, int height);
    protected abstract int[] convertBytesToInt(byte[] bytes);
}
