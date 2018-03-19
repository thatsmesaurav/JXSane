package sane.JXSane.acquisitor.image;

import java.awt.image.BufferedImage;

public class GrayImageAcquisitor extends AbstractImageAcquisitor{

    @Override
    protected BufferedImage getBufferedImage(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    }
    
    @Override
    protected int[] convertBytesToInt(byte[] data) {
        int[] ints = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            ints[i] = (int) data[i] & 0xff;
        }
        return ints;
    }
    
}
