/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.bestiaries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import maintenance.FXMLDocumentController;
import maintenance.item.ItemModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLInputraceController implements Initializable {
    
    boolean editdata =false;
    @FXML
    private TextField idrc;
    @FXML
    private TextField rcnm;
    @FXML
    private TextField rclog;
    @FXML
    private TextArea rdesc;
    @FXML
    private TextArea edesc;
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
    public void execute(RaceModel d) {
        if (!d.getId_rc().isEmpty()) {
            editdata = true;
            idrc.setText(d.getId_rc());
            rcnm.setText(d.getRace_nm());
            rdesc.setText(d.getDescription());
            edesc.setText(d.getEffect_desc());
            rclog.setText(d.getImg_rc());
            idrc.setEditable(false);
            rcnm.requestFocus();
        
        }
    }
    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }

    @FXML
    private void cancelclick(ActionEvent event) {
        idrc.setText("");
        rcnm.setText("");
        rclog.setText("");
        rdesc.setText("");
        edesc.setText("");
        idrc.requestFocus();
    }

    @FXML
    private void saveclick(ActionEvent event) {
        RaceModel n = new RaceModel();
        n.setId_rc(idrc.getText());
        n.setRace_nm(rcnm.getText());
        n.setImg_rc(rclog.getText());
        n.setDescription(rdesc.getText());
        n.setEffect_desc(edesc.getText());
        FXMLDocumentController.dtdbrace.setRaceModel(n);
        if (editdata) {
            //biar tahu apakah berhasil atau tidak?
            if (FXMLDocumentController.dtdbrace.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                idrc.setEditable(true);
                cancelclick(event);

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtdbrace.validasi(n.getId_rc()) <= 0) {
            if (FXMLDocumentController.dtdbrace.insert()) {
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
            idrc.requestFocus();
        }

    }

    @FXML
    private void img_search(ActionEvent event) {
        // Membuat objek FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File");

        // Mendapatkan window utama (Stage) dari TextField atau komponen lain
        Stage stage = (Stage) rclog.getScene().getWindow();
        // Menampilkan dialog open file
        File selectedFile = fileChooser.showOpenDialog(stage);
        // Jika pengguna memilih file
        if (selectedFile != null) {
            // Menampilkan NAMA FILE saja ke dalam TextField
            rclog.setText("src\\img\\" + selectedFile.getName());
        }
    }
}
