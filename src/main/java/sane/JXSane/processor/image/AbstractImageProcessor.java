package sane.JXSane.processor.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sane.JXSane.api.SaneDeviceParam;

public abstract class AbstractImageProcessor implements ImageProcessor {

    private final Logger logger = LoggerFactory.getLogger(AbstractImageProcessor.class);

    public void convertBytesToImage(byte[] bytes, SaneDeviceParam params) {

        try {
            BufferedImage image = getBufferedImage(params.getWidth(),params.getHeight());

            WritableRaster raster = (WritableRaster) image.getData();
            raster.setPixels(0, 0, params.getWidth(), params.getHeight(), convertBytesToInt(bytes));
            image.setData(raster);
            getBufferedImageToBmpByteArray(image);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    private byte[] getBufferedImageToBmpByteArray(BufferedImage image) {
        byte[] imageData = null;
        try {
            File file = new File("/home/saurav/wow.bmp");
            ImageIO.write(image, "bmp", file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageData;
    }

    
    protected abstract BufferedImage getBufferedImage(int width, int height);
    protected abstract int[] convertBytesToInt(byte[] bytes);
}
