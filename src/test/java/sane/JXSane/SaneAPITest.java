package sane.JXSane;

import org.junit.Before;
import org.junit.Test;
import sane.JXSane.api.library.SaneApi;
import sane.JXSane.api.library.SaneApiImpl;

public class SaneAPITest 
{
    SaneApi saneApi = null;
    
    @Before
    public void setup(){
        saneApi = new SaneApiImpl();
    }
    
    @Test
    public void testSaneLibInit(){
        try{
        saneApi.getDevices();
        }catch(Exception ex){
            
        }
    }
}
