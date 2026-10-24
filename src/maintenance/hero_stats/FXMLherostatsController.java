/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.hero_stats;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import maintenance.FXMLDocumentController;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import maintenance.adventurer.FXMLHeroController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLherostatsController implements Initializable {

    @FXML
    private TableView<hero_statsModel> tbstats;
    @FXML
    private ImageView imgprof;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtnm;
    @FXML
    private TextField txtrc;
    @FXML
    private TextField txtcs;
    @FXML
    private TextField txtef;
    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private Button first;
    @FXML
    private Button last;
    @FXML
    private TableView<hero_statsModel> tbinfo;
    @FXML
    private TextField txtfr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showinfo();
        
    tbinfo.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                TabletoTxt();
                showstats();
            }
        });

    tbinfo.getSelectionModel().selectFirst();
    showgambar();
    }    
    public void showinfo() {
        ObservableList<hero_statsModel> data = FXMLDocumentController.dtdbhrinf.Load();
        if (data != null) {
            tbinfo.getColumns().clear();
            tbinfo.getItems().clear();
            TableColumn col = new TableColumn("hr_nm");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("hr_nm"));
            tbinfo.getColumns().addAll(col);
            col = new TableColumn("cls");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("hr_cls"));
            tbinfo.getColumns().addAll(col);
            col = new TableColumn("hr_effect");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("hr_effect"));
            tbinfo.getColumns().addAll(col);
            col = new TableColumn("hr_rc");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("hr_rc"));
            tbinfo.getColumns().addAll(col);
            col = new TableColumn("hr_id");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("hr_id"));
            tbinfo.getColumns().addAll(col);
            col = new TableColumn("hr_from");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("hr_from"));
            tbinfo.getColumns().addAll(col);
            
            
            tbinfo.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbinfo.getScene().getWindow().hide();
        }
    }
    
    public void showstats() {
        ObservableList<hero_statsModel> data = FXMLDocumentController.dtdbhrs.Load(txtid.getText());
        if (data != null) {
            tbstats.getColumns().clear();
            tbstats.getItems().clear();
            TableColumn col = new TableColumn("STR");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("STR"));
            tbstats.getColumns().addAll(col);
            col = new TableColumn("AGI");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("AGI"));
            tbstats.getColumns().addAll(col);
            col = new TableColumn("DEX");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("DEX"));
            tbstats.getColumns().addAll(col);
            col = new TableColumn("INT");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("INT"));
            tbstats.getColumns().addAll(col);
            col = new TableColumn("CON");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("CON"));
            tbstats.getColumns().addAll(col);
            col = new TableColumn("LUK");
            col.setCellValueFactory(new PropertyValueFactory<hero_statsModel, String>("LUK"));
            tbstats.getColumns().addAll(col);
            
            
            tbstats.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbstats.getScene().getWindow().hide();
        }
    }
    private void TabletoTxt(){
        if (tbinfo.getSelectionModel().getSelectedItem() != null) {
            hero_statsModel r = tbinfo.getSelectionModel().getSelectedItem();
            txtid.setText(r.getHr_id());
            txtnm.setText(r.getHr_nm());
            txtrc.setText(r.getHr_rc());
            txtcs.setText(r.getHr_cls());
            txtef.setText(r.getHr_effect());
            txtfr.setText(r.getHr_from());
        }
    }
    public void showgambar(){
        Image gambar = null;
        try {
            gambar = new Image(new FileInputStream(tbinfo.getSelectionModel().getSelectedItem().getHr_pic()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLHeroController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        imgprof.setImage(gambar);
    }
    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }
    
    @FXML
    private void nextclick(ActionEvent event) {
        tbinfo.getSelectionModel().selectNext();
        tbinfo.requestFocus();
        showgambar();
        
    }

    @FXML
    private void prevclick(ActionEvent event) {
        tbinfo.getSelectionModel().selectPrevious();
        tbinfo.requestFocus();
        showgambar();
    }

    @FXML
    private void firstclick(ActionEvent event) {
        tbinfo.getSelectionModel().selectFirst();
        tbinfo.requestFocus();
        showgambar();
    }

    @FXML
    private void lastclick(ActionEvent event) {
        tbinfo.getSelectionModel().selectLast();
        tbinfo.requestFocus();
        showgambar();
        
    }
    
}
