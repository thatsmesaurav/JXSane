package sane.JXSane.acquisitor.image;

import java.awt.image.BufferedImage;

public class RGBImageAcquisitor extends AbstractImageAcquisitor {

    @Override
    protected BufferedImage getBufferedImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected int[] convertBytesToInt(byte[] bytes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
