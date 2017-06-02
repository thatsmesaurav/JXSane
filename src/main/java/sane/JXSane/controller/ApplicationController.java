package sane.JXSane.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import sane.JXSane.api.SaneDevicePool;
import sane.JXSane.api.library.SaneApi;
import sane.JXSane.api.library.SaneApiImpl;

public class ApplicationController implements Initializable{
    
    @FXML
    private ComboBox<String> availableScanners;
    
    private SaneApi saneApi = null;
    
    public void initialize(URL fxmlFileLocation,ResourceBundle resources){

        saneApi = new SaneApiImpl();
        saneApi.initializeSane();
        SaneDevicePool devicePool = SaneDevicePool.getInstance();
        
        devicePool.addSaneDevices(saneApi.getDevices());
        availableScanners.getItems().addAll(devicePool.getSaneDeviceNames());
        saneApi.openDevice(devicePool.getSaneDeviceNames().get(1));
        saneApi.getSaneParameters(devicePool.getSaneDeviceNames().get(1));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); 
        saneApi.exitSane();
    }
}
