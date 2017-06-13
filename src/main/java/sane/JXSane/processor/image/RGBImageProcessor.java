package sane.JXSane.processor.image;

import java.awt.image.BufferedImage;

public class RGBImageProcessor extends AbstractImageProcessor {

    @Override
    protected BufferedImage getBufferedImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected int[] convertBytesToInt(byte[] bytes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
