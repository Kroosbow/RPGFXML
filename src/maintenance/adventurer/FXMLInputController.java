/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.adventurer;

import maintenance.Maintenance;
import maintenance.FXMLDocumentController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class FXMLInputController implements Initializable {

    boolean editdata = false;

    @FXML
    private TextField txtThn;
    @FXML
    private ComboBox<String> classcombo;
    @FXML
    private TextField txtSub;
    @FXML
    private ComboBox<String> ordercombo;
    @FXML
    private ImageView pro_pic;
    @FXML
    private ComboBox<String> racecombo;
    @FXML
    private TextField txtNo;
    @FXML
    private TextField txtName;
    @FXML
    private TextArea desc;
    @FXML
    private Button findprofil;
    @FXML
    private Button submit;
    @FXML
    private Button clear;
    @FXML
    private Button back;
    @FXML
    private TextField txtpropic;
    @FXML
    private TextField txtrc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        classcombo.setItems(FXCollections.observableArrayList(
                "Duelist", "Magi", "Ranged", "Support", "Healer", "Defender", "Swiftstrike"));
        classcombo.setPromptText("Class");

        ordercombo.setItems(FXCollections.observableArrayList(
                "01: Hero", "02: Rescue", "03: Explorer", "04: Field", "05: Pathfinder", "06: SkySailor", "07: Receptionist", "08: Merchant", "09: Aegis", "9B: Ashen Aegis"));
        ordercombo.setPromptText("Order");

        racecombo.setItems(FXCollections.observableArrayList(
                "Dragon", "Elf", "Tssiar", "Human", "Homiconnee", "Homicanid", "Mimic", "Other"));
        racecombo.setPromptText("Races");

        //ini bagian pk yang gaboleh diubah saat edit
        txtThn.setDisable(false);
        txtNo.setDisable(false);
        classcombo.setDisable(false);
        ordercombo.setDisable(false);

        //race other
        txtrc.setVisible(false);
        txtrc.setManaged(false);
        // TODO
    }

    public void execute(HeroModel d) {

        if (!d.getId_guild().isEmpty()) {
            editdata = true;
            txtThn.setText((d.getId_guild()).substring(2, 6));
            txtName.setText(d.getNm());
            txtNo.setText((d.getId_guild()).substring(8, 10));
            txtSub.setText(d.getSubclass());
            txtpropic.setText(d.getProfile_pic());
            showgambar();

            switch ((d.getId_guild()).substring(6, 8)) {
                case "01":
                    ordercombo.getSelectionModel().select(0);
                    ;
                    break;
                case "02":
                    ordercombo.getSelectionModel().select(1);
                    ;
                    break;
                case "03":
                    ordercombo.getSelectionModel().select(2);
                    ;
                    break;
                case "04":
                    ordercombo.getSelectionModel().select(3);
                    ;
                    break;
                case "05":
                    ordercombo.getSelectionModel().select(4);
                    ;
                    break;
                case "06":
                    ordercombo.getSelectionModel().select(5);
                    ;
                    break;
                case "07":
                    ordercombo.getSelectionModel().select(6);
                    ;
                    break;
                case "08":
                    ordercombo.getSelectionModel().select(7);
                    ;
                    break;
                case "09":
                    ordercombo.getSelectionModel().select(8);
                    ;
                    break;
                case "9B":
                    ordercombo.getSelectionModel().select(9);
                    ;
                    break;
                default:
                    ordercombo.getSelectionModel().select(0);
                    ;
                    break;
            }
            switch ((d.getId_guild()).substring(0, 2)) {
                case "DL":
                    classcombo.getSelectionModel().select(0);
                    ;
                    break;
                case "MG":
                    classcombo.getSelectionModel().select(1);
                    ;
                    break;
                case "RG":
                    classcombo.getSelectionModel().select(2);
                    ;
                    break;
                case "SP":
                    classcombo.getSelectionModel().select(3);
                    ;
                    break;
                case "HL":
                    classcombo.getSelectionModel().select(4);
                    ;
                    break;
                case "DF":
                    classcombo.getSelectionModel().select(5);
                    ;
                    break;
                case "SS":
                    classcombo.getSelectionModel().select(6);
                    ;
                    break;
                default:
                    classcombo.getSelectionModel().select(0);
                    ;
                    break;
            }

        }
        //race other
        String race = d.getRace();
        if (racecombo.getItems().contains(race)) {
            racecombo.setValue(race);
            txtrc.setVisible(false);
            txtrc.setManaged(false);
            txtrc.clear();
        } else {
            racecombo.setValue("Other");
            txtrc.setText(race);
            txtrc.setVisible(true);
            txtrc.setManaged(true);
        }
        txtThn.setDisable(true);
        txtNo.setDisable(true);
        classcombo.setDisable(true);
        ordercombo.setDisable(true);

    }

    @FXML
    private void inputThn(ActionEvent event) {

    }

    @FXML
    private void inputSub(ActionEvent event) {

    }

    @FXML
    private void inputNo(ActionEvent event) {

    }

    @FXML
    private void inputName(ActionEvent event) {
    }

    @FXML
    private void findprofilclick(ActionEvent event) {
        // Membuat objek FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File");

        // Mendapatkan window utama (Stage) dari TextField atau komponen lain
        Stage stage = (Stage) txtpropic.getScene().getWindow();
        // Menampilkan dialog open file
        File selectedFile = fileChooser.showOpenDialog(stage);
        // Jika pengguna memilih file
        if (selectedFile != null) {
            // Menampilkan NAMA FILE saja ke dalam TextField
            txtpropic.setText("src\\img\\" + selectedFile.getName());
            showgambar();
        }
    }

    @FXML
    private void submitClick(ActionEvent event) {
        if (txtNo.getLength() < 2 || txtNo.getLength() > 2) {
            Alert a = new Alert(Alert.AlertType.WARNING, "No maksimal dan minimal 2 angka\n ex: 02", ButtonType.OK);
            a.showAndWait();
        }
        if (txtThn.getLength() < 4 || txtThn.getLength() > 4) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Masukkan Tahun yang benar\n ex: 1290", ButtonType.OK);
            a.showAndWait();
        }

        String selectedClass = classcombo.getValue();
        String classCode = "";
        switch (selectedClass) {
            case "Duelist":
                classCode = "DL";
                ;
                break;
            case "Magi":
                classCode = "MG";
                ;
                break;
            case "Ranged":
                classCode = "RG";
                ;
                break;
            case "Support":
                classCode = "SP";
                ;
                break;
            case "Healer":
                classCode = "HL";
                ;
                break;
            case "Defender":
                classCode = "DF";
                ;
                break;
            case "Swiftstrike":
                classCode = "SS";
                ;
                break;
            default:
                classCode = "DL";
                ;
                break;
        }
        HeroModel n = new HeroModel();
        if (classcombo.getValue() == null || ordercombo.getValue() == null || racecombo.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Lengkapi Class, Order, dan Race", ButtonType.OK);
            a.showAndWait();
            return;
        }

        String finalrace;
        if ("Other".equals(racecombo.getValue())) {
            finalrace = txtrc.getText().trim();
            if (finalrace.isEmpty()) {
                Alert a = new Alert(Alert.AlertType.WARNING,
                        "Ketik nama ras lain terlebih dahulu", ButtonType.OK);
                a.showAndWait();
                txtrc.requestFocus();
                return;
            }

        } else {
            finalrace = racecombo.getValue();
        }

        n.setId_guild(classCode + txtThn.getText() + (ordercombo.getValue()).substring(0, 2) + txtNo.getText());
        n.setNm(txtName.getText());
        n.setRace(finalrace);
        n.setSubclass(txtSub.getText());
        n.setProfile_pic(txtpropic.getText());
        FXMLDocumentController.dtdbhero.setHeroModel(n);
        if (editdata) {
            //biar tahu apakah berhasil atau tidak?
            if (FXMLDocumentController.dtdbhero.update()) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah", ButtonType.OK);
                a.showAndWait();
                txtNo.setEditable(true);
                txtThn.setEditable(true);
                classcombo.setEditable(true);
                clearclick(event);
                back.getScene().getWindow().hide();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah", ButtonType.OK);
                a.showAndWait();
            }
        } else if (FXMLDocumentController.dtdbhero.validasi(n.getId_guild()) <= 0) {
            if (FXMLDocumentController.dtdbhero.insert()) {
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
            txtThn.requestFocus();
        }
    }

    @FXML
    private void clearclick(ActionEvent event) {
        txtThn.setText("");
        txtSub.setText("");
        txtName.setText("");
        txtNo.setText("");
        txtpropic.setText("");
        racecombo.setPromptText("Races");
        classcombo.setPromptText("Class");
        ordercombo.setPromptText("Order");
        pro_pic.setImage(null);
        txtThn.setDisable(false);
        txtNo.setDisable(false);
        classcombo.setDisable(false);
        ordercombo.setDisable(false);

        txtrc.clear();
        txtrc.setVisible(false);
        txtrc.setManaged(false);

        editdata = false;
        txtThn.requestFocus();
    }

    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }

    public void showgambar() {
        String path = txtpropic.getText();
        if (path == null || path.isEmpty()) {
            pro_pic.setImage(null);
            return;
        }
        try {
            Image gambar = new Image(new FileInputStream(path));
            pro_pic.setImage(gambar);
        } catch (FileNotFoundException ex) {

            pro_pic.setImage(null);

        }
    }

    @FXML
    private void inputpropic(ActionEvent event) {

    }

    @FXML
    private void racechange(ActionEvent event) {
        boolean isOther = "Other".equals(racecombo.getValue());
        txtrc.setVisible(isOther);
        txtrc.setManaged(isOther);
        if (isOther) {
            txtrc.requestFocus();
        } else {
            txtrc.clear();
        }
    }

    @FXML
    private void inputrc(ActionEvent event) {
        
    }
}
