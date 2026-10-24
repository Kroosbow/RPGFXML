/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.adventurer;
import maintenance.Connection;
import maintenance.Maintenance;
import maintenance.FXMLDocumentController;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLHeroController implements Initializable {

    @FXML
    private TableView<HeroModel> tbhero;
    @FXML
    private TextField searchbar;
    @FXML
    private ImageView propic;
    @FXML
    private Button searchbtn;
    @FXML
    private Button prev;
    @FXML
    private Button next;
    @FXML
    private Button first;
    @FXML
    private Button last;
    @FXML
    private Button edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdt();
        tbhero.getSelectionModel().selectFirst();
        showgambar();
    }
    
    public void showdt() {
        ObservableList<HeroModel> data = FXMLDocumentController.dtdbhero.Load();
        if (data != null) {
            tbhero.getColumns().clear();
            tbhero.getItems().clear();
            TableColumn col = new TableColumn("Id");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id_guild"));
            tbhero.getColumns().addAll(col);
            col = new TableColumn("Name");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("nm"));
            tbhero.getColumns().addAll(col);
            col = new TableColumn("Race");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("race"));
            tbhero.getColumns().addAll(col);
            col = new TableColumn("Class");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("cls"));
            tbhero.getColumns().addAll(col);
            col = new TableColumn("Subclass");
            col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("subclass"));

            tbhero.getColumns().addAll(col);
            tbhero.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbhero.getScene().getWindow().hide();
        }
    }
    
    @FXML
    public void showgambar(){
        Image gambar = null;
        try {
            gambar = new Image(new FileInputStream(tbhero.getSelectionModel().getSelectedItem().getProfile_pic()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLHeroController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        propic.setImage(gambar);
    }
    
    public void searchBrg(){
                HeroModel s = new HeroModel();
        String key = searchbar.getText();
        if (key != "") {
            ObservableList<HeroModel> data = FXMLDocumentController.dtdbhero.CariHr(key, key, key);
            if (data != null) {
                tbhero.getColumns().clear();
                tbhero.getItems().clear();
                TableColumn col = new TableColumn("Id");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("id_guild"));
                tbhero.getColumns().addAll(col);
                col = new TableColumn("Name");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("nm"));
                tbhero.getColumns().addAll(col);
                col = new TableColumn("Race");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("race"));
                tbhero.getColumns().addAll(col);
                col = new TableColumn("Class");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("cls"));
                tbhero.getColumns().addAll(col);
                col = new TableColumn("Subclass");
                col.setCellValueFactory(new PropertyValueFactory<HeroModel, String>("subclass"));
                tbhero.getColumns().addAll(col);
                tbhero.setItems(data);
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data kosong", ButtonType.OK);
                a.showAndWait();
                tbhero.getScene().getWindow().hide();
            }
        } else {
            showdt();
        }
        
    }

    @FXML
    private void searchbartype(KeyEvent event) {
        searchBrg();
        tbhero.getSelectionModel().selectFirst();
        showgambar();
    }

    @FXML
    private void searchclick(ActionEvent event) {
        searchBrg();
        tbhero.getSelectionModel().selectFirst();
        showgambar();
        tbhero.requestFocus();
    }

    @FXML
    private void prevclick(ActionEvent event) {
        tbhero.getSelectionModel().selectPrevious();
        showgambar();
        tbhero.requestFocus();
    }

    @FXML
    private void nextclick(ActionEvent event) {
        tbhero.getSelectionModel().selectNext();
        showgambar();
        tbhero.requestFocus();
    }

    @FXML
    private void cirstclick(ActionEvent event) {
        tbhero.getSelectionModel().selectFirst();
        showgambar();
        tbhero.requestFocus();
    }

    @FXML
    private void lastclick(ActionEvent event) {
        tbhero.getSelectionModel().selectLast();
        showgambar();
        tbhero.requestFocus();
    }

    @FXML
    private void editclick(ActionEvent event) {
        HeroModel s = new HeroModel();
        s = tbhero.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInput.fxml"));
            Parent root = (Parent) loader.load();
            FXMLInputController isidtbrg = (FXMLInputController) loader.getController();
            isidtbrg.execute(s);
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
        cirstclick(event);
    }

    @FXML
    private void delclick(ActionEvent event) {
        HeroModel s= new HeroModel();       
        s=tbhero.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Delete this item?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtdbitem.delete(s.getId_guild())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Item deleted succesfully", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Delete Failed", ButtonType.OK);
               b.showAndWait();               
           }    
           showdt();           
           tbhero.getSelectionModel().selectFirst();
            showgambar();       
        }
    }

    @FXML
    private void addclick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInput.fxml"));
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
        tbhero.getSelectionModel().selectFirst();
        showgambar();
    }
    
}
