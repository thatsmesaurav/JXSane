package sane.JXSane.processor.image;


public class ImageProcessorFactory {
    private final static int FORMAT_GRAY = 0;
    private final static int FORMAT_RGB = 1;
    private final static int FORMAT_RED = 2;
    private final static int FORMAT_GREEN = 3;
    private final static int FORMAT_BLUE = 4;
    
    public ImageProcessor getImageProcessor(int format){
        if(FORMAT_GRAY==format){
            return new GrayImageProcessor();
        }
        
        return null;
    }
}
