package sane.JXSane.acquisitor.image;


public final class ImageAcquisitorFactory {
    
    private final static int FORMAT_GRAY = 0;
    private final static int FORMAT_RGB = 1;
    private final static int FORMAT_RED = 2;
    private final static int FORMAT_GREEN = 3;
    private final static int FORMAT_BLUE = 4;
    
    private ImageAcquisitorFactory(){
        
    }
    
    public static ImageAcquisitor getImageProcessor(int format){
        if(FORMAT_GRAY==format){
            return new GrayImageAcquisitor();
        }
        
        return null;
    }
}
