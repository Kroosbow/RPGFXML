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
public class FXMLInputclsController implements Initializable {
    
    boolean editdata = false;
    @FXML
    private TextField clsid;
    @FXML
    private TextField clsnm;
    @FXML
    private TextField clslog;
    @FXML
    private TextArea clsdesc;
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
    public void execute(ClsModel d) {
        if (!d.getId_cls().isEmpty()) {
            editdata = true;
            clsid.setText(d.getId_cls());
            clsnm.setText(d.getCls_nm());
            clsdesc.setText(d.getDescription());
            clslog.setText(d.getImg_cls());
            clsid.setEditable(false);
            clsnm.requestFocus();
        
        }
    
}

    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }

    @FXML
    private void cancelclick(ActionEvent event) {
        clsid.setText("");
        clsdesc.setText("");
        clslog.setText("");
        clsnm.setText("");
        clsid.requestFocus();
    }

    @FXML
    private void saveclick(ActionEvent event) {
        ClsModel n = new ClsModel();
        n.setId_cls(clsid.getText());
        n.setCls_nm(clsnm.getText());
        n.setDescription(clsdesc.getText());
        n.setImg_cls(clslog.getText());
        
        FXMLDocumentController.dtdbcls.setClsModel(n);
        if (editdata) {
            //biar tahu apakah berhasil atau tidak?
            if (FXMLDocumentController.dtdbcls.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data Succesfuly Changed", ButtonType.OK);
                a.showAndWait();
                clsid.setEditable(true);
                cancelclick(event);

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed to Change Data", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtdbcls.validasi(n.getId_cls()) <= 0) {
            if (FXMLDocumentController.dtdbcls.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                cancelclick(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            clsid.requestFocus();
        }

    }

    @FXML
    private void img_search(ActionEvent event) {
    // Membuat objek FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File");

        // Mendapatkan window utama (Stage) dari TextField atau komponen lain
        Stage stage = (Stage) clslog.getScene().getWindow();
        // Menampilkan dialog open file
        File selectedFile = fileChooser.showOpenDialog(stage);
        // Jika pengguna memilih file
        if (selectedFile != null) {
            // Menampilkan NAMA FILE saja ke dalam TextField
            clslog.setText("src\\img\\" + selectedFile.getName());
        }
    }
}
