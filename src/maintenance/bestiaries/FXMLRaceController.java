/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.bestiaries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import maintenance.FXMLDocumentController;
import maintenance.adventurer.FXMLHeroController;
import maintenance.adventurer.FXMLInputController;
import maintenance.adventurer.HeroModel;
import maintenance.item.ItemModel;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLRaceController implements Initializable {

    @FXML
    private TableView<RaceModel> tbrc;
    @FXML
    private Button edit;
    @FXML
    private Button create;
    @FXML
    private Button del;
    @FXML
    private TextField txtId;
    @FXML
    private TextArea txtDesc;
    @FXML
    private Label txtRace;
    @FXML
    private Label txtEffect;
    @FXML
    private Button back;
    @FXML
    private ImageView img_rc;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Label txteffectdesc;
    @FXML
    private TabPane mainTabPane;
    @FXML
    private Button next1;
    @FXML
    private Button back1;
    @FXML
    private Button prev1;
    @FXML
    private Button edit1;
    @FXML
    private Button create1;
    @FXML
    private Button del1;
    @FXML
    private TableView<ClsModel> tbcls;
    @FXML
    private ImageView imgcls;
    @FXML
    private TextField txtidcls;
    @FXML
    private TextField txtcls;
    @FXML
    private TextArea txtclsdesc;
    @FXML
    private TextArea txtbonus;
    @FXML
    private Button delwld;
    @FXML
    private Button createwld;
    @FXML
    private Button editwld;
    @FXML
    private Button prev11;
    @FXML
    private Button back11;
    @FXML
    private Button next11;
    @FXML
    private ImageView imgwld;
    @FXML
    private TextField txtidReg;
    @FXML
    private TextField txtReg;
    @FXML
    private TextField txtDist;
    @FXML
    private TableView<WldModel> tbwld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showdt();
        showdt1();
        showdt2();

        tbrc.getSelectionModel().selectFirst();
        tbcls.getSelectionModel().selectFirst();
        tbwld.getSelectionModel().selectFirst();

        showgambar();
        TabletoTxt();
    }

    public void selectTab(int index) {
        if (mainTabPane != null) {
            mainTabPane.getSelectionModel().select(index);
        }
    }

    public void showdt() {
        ObservableList<RaceModel> data = FXMLDocumentController.dtdbrace.Load();
        if (data != null) {
            tbrc.getColumns().clear();
            tbrc.getItems().clear();
            TableColumn col = new TableColumn("id");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("id_rc"));
            tbrc.getColumns().addAll(col);
            col = new TableColumn("Nm");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("race_nm"));
            tbrc.getColumns().addAll(col);
            col = new TableColumn("effectd");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("effect_desc"));
            tbrc.getColumns().addAll(col);
            col = new TableColumn("efds");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("description"));
            tbrc.getColumns().addAll(col);
            col = new TableColumn("desc");
            col.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("effect"));
            tbrc.getColumns().addAll(col);
            tbrc.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbrc.getScene().getWindow().hide();
        }

    }

    public void showdt2() {
        ObservableList<WldModel> data = FXMLDocumentController.dtdbwld.Load();
        if (data != null) {
            tbwld.getColumns().clear();
            tbwld.getItems().clear();
            TableColumn col = new TableColumn("cs");
            col.setCellValueFactory(new PropertyValueFactory<ClsModel, String>("id_chkpoint"));
            tbwld.getColumns().addAll(col);
            col = new TableColumn("Nmcs");
            col.setCellValueFactory(new PropertyValueFactory<ClsModel, String>("chk_name"));
            tbwld.getColumns().addAll(col);
            col = new TableColumn("dcs");
            col.setCellValueFactory(new PropertyValueFactory<ClsModel, String>("distance"));
            tbwld.getColumns().addAll(col);

            tbwld.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbwld.getScene().getWindow().hide();
        }

    }

    public void showdt1() {
        ObservableList<ClsModel> data = FXMLDocumentController.dtdbcls.Load();
        if (data != null) {
            tbcls.getColumns().clear();
            tbcls.getItems().clear();
            TableColumn col = new TableColumn("cs");
            col.setCellValueFactory(new PropertyValueFactory<ClsModel, String>("id_cs"));
            tbcls.getColumns().addAll(col);
            col = new TableColumn("Nmcs");
            col.setCellValueFactory(new PropertyValueFactory<ClsModel, String>("cls_nm"));
            tbcls.getColumns().addAll(col);
            col = new TableColumn("dcs");
            col.setCellValueFactory(new PropertyValueFactory<ClsModel, String>("description"));
            tbcls.getColumns().addAll(col);
            col = new TableColumn("bonus");
            col.setCellValueFactory(new PropertyValueFactory<ClsModel, String>("stat_bon"));
            tbcls.getColumns().addAll(col);

            tbcls.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbcls.getScene().getWindow().hide();
        }

    }

    public void showgambar() {
        try {
            if (tbrc.getSelectionModel().getSelectedItem() != null && tbrc.getSelectionModel().getSelectedItem().getImg_rc() != null) {
                String pathRc = tbrc.getSelectionModel().getSelectedItem().getImg_rc();
                if (!pathRc.isEmpty()) {
                    img_rc.setImage(new Image(new FileInputStream(pathRc)));
                }
            }

            if (tbcls.getSelectionModel().getSelectedItem() != null && tbcls.getSelectionModel().getSelectedItem().getImg_cls() != null) {
                String pathCls = tbcls.getSelectionModel().getSelectedItem().getImg_cls();
                if (!pathCls.isEmpty()) {
                    imgcls.setImage(new Image(new FileInputStream(pathCls)));
                }
            }
            if (tbwld.getSelectionModel().getSelectedItem() != null && tbwld.getSelectionModel().getSelectedItem().getMap_img() != null) {
                String pathCls = tbwld.getSelectionModel().getSelectedItem().getMap_img();
                if (!pathCls.isEmpty()) {
                    imgwld.setImage(new Image(new FileInputStream(pathCls)));
                }
            }
        } catch (FileNotFoundException ex) {

            System.out.println("Logo/ Image not found " + ex.getMessage());
        }

    }

    private void TabletoTxt() {
        //Race Data
        if (tbrc.getSelectionModel().getSelectedItem() != null) {
            RaceModel r = tbrc.getSelectionModel().getSelectedItem();
            txtId.setText(r.getId_rc());
            txteffectdesc.setText(r.getEffect_desc());
            txtEffect.setText(r.getEffect());
            txtRace.setText(r.getRace_nm());
            txtDesc.setText(r.getDescription());
        }

        // Classes Data
        if (tbcls.getSelectionModel().getSelectedItem() != null) {
            ClsModel c = tbcls.getSelectionModel().getSelectedItem();
            txtidcls.setText(c.getId_cls());
            txtcls.setText(c.getCls_nm());
            txtclsdesc.setText(c.getDescription());
            txtbonus.setText(c.getStat_bon());
        }

        // World Data
        if (tbwld.getSelectionModel().getSelectedItem() != null) {
            WldModel w = tbwld.getSelectionModel().getSelectedItem();
            txtidReg.setText(w.getId_chkpoint());
            txtReg.setText(w.getChk_name());
            txtDist.setText((String.valueOf(w.getDistance())) + " Miles");
        }
    }

    @FXML
    private void editclick(ActionEvent event) {
        RaceModel s = new RaceModel();
        s = tbrc.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputrace.fxml"));
            Parent root = (Parent) loader.load();
            FXMLInputraceController isidtrc = (FXMLInputraceController) loader.getController();
            isidtrc.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdt();
        tbrc.getSelectionModel().selectFirst();
        showgambar();

    }

    @FXML
    private void createclick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputrace.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdt();
        tbrc.getSelectionModel().selectFirst();
        showgambar();

    }

    @FXML
    private void delclick(ActionEvent event) {
        RaceModel s = new RaceModel();
        s = tbrc.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete this race information?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtdbrace.delete(s.getId_rc())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Race data deleted succesfully", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Delete Failed", ButtonType.OK);
                b.showAndWait();
            }
            showdt();
            tbrc.getSelectionModel().selectFirst();
            showgambar();
            TabletoTxt();
        }
    }

    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }

    @FXML
    private void nextclick(ActionEvent event) {
        tbrc.getSelectionModel().selectNext();
        showgambar();
        tbrc.requestFocus();
        TabletoTxt();
    }

    @FXML
    private void prevclick(ActionEvent event) {
        tbrc.getSelectionModel().selectPrevious();
        showgambar();
        tbrc.requestFocus();
        TabletoTxt();
    }

    //Untuk CLASSES
    @FXML
    private void nextclickcls(ActionEvent event) {
        tbcls.getSelectionModel().selectNext();
        showgambar();
        tbcls.requestFocus();
        TabletoTxt();

    }

    @FXML
    private void prevclickcls(ActionEvent event) {
        tbcls.getSelectionModel().selectPrevious();
        showgambar();
        tbcls.requestFocus();
        TabletoTxt();
    }

    @FXML
    private void editclickcls(ActionEvent event) {
        ClsModel s = new ClsModel();
        s = tbcls.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputcls.fxml"));
            Parent root = (Parent) loader.load();
            FXMLInputclsController isidtcls = (FXMLInputclsController) loader.getController();
            isidtcls.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdt1();
        tbcls.getSelectionModel().selectFirst();
        showgambar();

    }

    @FXML
    private void createclickcls(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputcls.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdt1();
        tbcls.getSelectionModel().selectFirst();
        showgambar();
    }

    @FXML
    private void delclickcls(ActionEvent event) {
        ClsModel s = new ClsModel();
        s = tbcls.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete this class information?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtdbcls.delete(s.getId_cls())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Class data deleted succesfully", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Delete Failed", ButtonType.OK);
                b.showAndWait();
            }
            showdt1();
            tbcls.getSelectionModel().selectFirst();
            showgambar();
            TabletoTxt();

        }
    }

    //World 
    @FXML
    private void delclickwld(ActionEvent event) {
        WldModel s = new WldModel();
        s = tbwld.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Delete this region information?", ButtonType.YES, ButtonType.NO);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES) {
            if (FXMLDocumentController.dtdbwld.delete(s.getId_chkpoint())) {
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Region data deleted succesfully", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Delete Failed", ButtonType.OK);
                b.showAndWait();
            }
            showdt2();
            tbwld.getSelectionModel().selectFirst();
            TabletoTxt();
            showgambar();

        }
    }

    @FXML
    private void createclickwld(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputwld.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdt2();
        tbwld.getSelectionModel().selectFirst();
        showgambar();

    }

    @FXML
    private void editclickwld(ActionEvent event) {
        WldModel s = new WldModel();
        s = tbwld.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLinputwld.fxml"));
            Parent root = (Parent) loader.load();
            FXMLinputwldController isidtrc = (FXMLinputwldController) loader.getController();
            isidtrc.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showdt2();
        tbwld.getSelectionModel().selectFirst();
        showgambar();
    }

    @FXML
    private void prevclickwld(ActionEvent event) {
        tbwld.getSelectionModel().selectPrevious();
        showgambar();
        tbwld.requestFocus();
        TabletoTxt();
    }

    @FXML
    private void nextclickwld(ActionEvent event) {
        tbwld.getSelectionModel().selectNext();
        showgambar();
        tbwld.requestFocus();
        TabletoTxt();
    }

}
