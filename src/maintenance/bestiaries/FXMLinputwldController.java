/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.bestiaries;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import maintenance.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLinputwldController implements Initializable {

    boolean editdata = false;
    @FXML
    private TextField wldid;
    @FXML
    private TextField wldreg;
    @FXML
    private TextField wldimg;
    @FXML
    private TextArea wlddis;
    @FXML
    private Button back;
    @FXML
    private Button cancel;
    @FXML
    private Button save;
    @FXML
    private Button imgs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void execute(WldModel d) {
        if (!d.getId_chkpoint().isEmpty()) {
            editdata = true;
            wldid.setText(d.getId_chkpoint());
            wldreg.setText(d.getChk_name());
            wlddis.setText(String.valueOf(d.getDistance()));
            wldimg.setText(d.getMap_img());
            wldid.setEditable(false);
            wldreg.requestFocus();
        
        }
    
}

    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }

    @FXML
    private void cancelclick(ActionEvent event) {
        wldid.setText("");
        wldreg.setText("");
        wlddis.setText("");
        wldimg.setText("");
        wldid.requestFocus();
    }

    @FXML
    private void saveclick(ActionEvent event) {
        WldModel n = new WldModel();
        n.setId_chkpoint(wldid.getText());
        n.setChk_name(wldreg.getText());
        n.setDistance(Double.parseDouble(wlddis.getText()));
        n.setMap_img(wldimg.getText());
        
        FXMLDocumentController.dtdbwld.setWldModel(n);
        if (editdata) {
            //biar tahu apakah berhasil atau tidak?
            if (FXMLDocumentController.dtdbwld.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Succesfuly Changed", ButtonType.OK);
                a.showAndWait();
                wldid.setEditable(true);
                cancelclick(event);

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtdbwld.validasi(n.getId_chkpoint()) <= 0) {
            if (FXMLDocumentController.dtdbwld.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Saved Succesfully", ButtonType.OK);
                a.showAndWait();
                cancelclick(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Save Data", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data already Exists", ButtonType.OK);
            a.showAndWait();
            wldid.requestFocus();
        }

    }

    @FXML
    private void img_search(ActionEvent event) {
        // Membuat objek FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File");

        // Mendapatkan window utama (Stage) dari TextField atau komponen lain
        Stage stage = (Stage) wldimg.getScene().getWindow();
        // Menampilkan dialog open file
        File selectedFile = fileChooser.showOpenDialog(stage);
        // Jika pengguna memilih file
        if (selectedFile != null) {
            // Menampilkan NAMA FILE saja ke dalam TextField
            wldimg.setText("src\\img\\" + selectedFile.getName());
        }
    }
    
}
