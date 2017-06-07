package sane.JXSane.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sane.JXSane.api.SaneDeviceParam;
import sane.JXSane.api.SaneDevicePool;
import sane.JXSane.api.library.SaneApi;
import sane.JXSane.api.library.SaneApiImpl;
import sane.JXSane.processor.image.ImageProcessor;
import sane.JXSane.processor.image.ImageProcessorImpl;

public class ApplicationController implements Initializable{
    
    @FXML
    private ComboBox<String> availableScanners;
    
    @FXML
    private ImageView previewImage;
    
    private SaneApi saneApi = null;
    private ImageProcessor processor= null;
    private String selectedDeviceName = null;
    
    public void initialize(URL fxmlFileLocation,ResourceBundle resources){

        saneApi = new SaneApiImpl();
        saneApi.initializeSane();
        
        //Initializing devicePool
        SaneDevicePool devicePool = SaneDevicePool.getInstance();
        devicePool.addSaneDevices(saneApi.getDevices());
        availableScanners.getItems().addAll(devicePool.getSaneDeviceNames());
        availableScanners.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                selectedDeviceName = t1;                
            }    
        });
    }
    
    @FXML
    protected void handleScanSubmitAction(ActionEvent event){
        processor = new ImageProcessorImpl();
        saneApi.openDevice(selectedDeviceName);
        SaneDeviceParam params = saneApi.getSaneParameters(selectedDeviceName);
        byte[] pixelBytes = saneApi.scanDocument(selectedDeviceName);
        saneApi.closeDevice(selectedDeviceName);
        processor.convertBytesToImage(pixelBytes, params);
        Image image = new Image("file:///home/saurav/wow.bmp");
        
        previewImage.setImage(image);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); 
        saneApi.exitSane();
    }
}
