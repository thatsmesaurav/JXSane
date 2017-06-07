package sane.JXSane.processor.image;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sane.JXSane.api.SaneDeviceParam;

public class ImageProcessorImpl implements ImageProcessor {

    private final Logger logger = LoggerFactory.getLogger(ImageProcessorImpl.class);

    public void convertBytesToImage(byte[] bytes, SaneDeviceParam params) {
        int width = params.getWidth();
        int height = params.getHeight();

        try {
            DataBuffer buffer = new DataBufferByte(bytes, bytes.length);

            int[] pixels = byteToInt(bytes);

            BufferedImage image = null;
            if (params.getDepth() == 8) {
                image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            } else if (params.getDepth() == 24) {
                image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            }

            WritableRaster raster = (WritableRaster) image.getData();
            raster.setPixels(0, 0, width, height, pixels);
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

    private int[] byteToInt(byte[] data) {
        int[] ints = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            ints[i] = (int) data[i] & 0xff;
        }
        return ints;
    }
}
