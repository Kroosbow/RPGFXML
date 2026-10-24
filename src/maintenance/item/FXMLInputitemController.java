/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import maintenance.FXMLDocumentController;
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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLInputitemController implements Initializable {

    boolean editdata = false;
    
    @FXML
    private ImageView imgitm;
    @FXML
    private TextField txtNm;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtTf;
    @FXML
    private TextField txtStk;
    @FXML
    private TextArea txtdesc;
    @FXML
    private Button back;
    @FXML
    private Button clear;
    @FXML
    private Button submit;
    @FXML
    private Button addimg;
    @FXML
    private TextField txtimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void execute(ItemModel d) {
        if (!d.getItem_id().isEmpty()) {
            editdata = true;
            txtId.setText(d.getItem_id());
            txtNm.setText(d.getNm_brg());
            txtTf.setText(String.valueOf(d.getTarif()));
            txtStk.setText(String.valueOf(d.getStok()));
            txtdesc.setText(d.getDesc());
            txtimg.setText(d.getImg_brg());
            txtId.setEditable(false);
            txtNm.requestFocus();
            showgambar();
        }
    }

    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }

    @FXML
    private void clearclick(ActionEvent event) {
        txtId.setText("");
        txtNm.setText("");
        txtStk.setText("");
        txtTf.setText("");
        txtdesc.setText("");
        txtimg.setText("");
        txtId.requestFocus();
        imgitm.setImage(null);
    }

    @FXML
    private void submitclick(ActionEvent event) {
        ItemModel n = new ItemModel();
        n.setItem_id(txtId.getText());
        n.setNm_brg(txtNm.getText());
        n.setTarif(Double.parseDouble(txtTf.getText()));
        n.setStok(Integer.parseInt(txtStk.getText()));
        n.setDesc(txtdesc.getText());
        n.setImg_brg(txtimg.getText());
        FXMLDocumentController.dtdbitem.setItemModel(n);
        if (editdata) {
            //biar tahu apakah berhasil atau tidak?
            if (FXMLDocumentController.dtdbitem.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtId.setEditable(true);
                clearclick(event);
                
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtdbitem.validasi(n.getItem_id()) <= 0) {
            if (FXMLDocumentController.dtdbitem.insert()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil disimpan", ButtonType.OK);
                a.showAndWait();
                clearclick(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal disimpan", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data sudah ada", ButtonType.OK);
            a.showAndWait();
            txtId.requestFocus();
        }

    }
    
    public void showgambar() {
        String path = txtimg.getText();
        if (path == null || path.isEmpty()) {
            imgitm.setImage(null);
            return;
        }
        try {
            Image gambar = new Image(new FileInputStream(path));
            imgitm.setImage(gambar);
        } catch (FileNotFoundException ex) {

            imgitm.setImage(null);

        }
    }
    @FXML
    private void addimgclick(ActionEvent event) {
        // Membuat objek FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File");

        // Mendapatkan window utama (Stage) dari TextField atau komponen lain
        Stage stage = (Stage) txtimg.getScene().getWindow();
        // Menampilkan dialog open file
        File selectedFile = fileChooser.showOpenDialog(stage);
        // Jika pengguna memilih file
        if (selectedFile != null) {
            // Menampilkan NAMA FILE saja ke dalam TextField
            txtimg.setText("src\\img\\" + selectedFile.getName());
            showgambar();
        }
    }
    
}
