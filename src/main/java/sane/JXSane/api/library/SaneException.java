
package sane.JXSane.api.library;

public class SaneException extends RuntimeException{
    
    public SaneException(String exceptionMessage){
        super(exceptionMessage);
    }
    
    public SaneException(Exception ex){
        super(ex);
    }
}
