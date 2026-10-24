/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package maintenance.item;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import maintenance.FXMLDocumentController;
import maintenance.Maintenance;
import maintenance.Connection;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLItemController implements Initializable {

    @FXML
    private TableView<ItemModel> tbitem;
    @FXML
    private ImageView itemimg;
    @FXML
    private Button prev;
    @FXML
    private Button next;
    @FXML
    private Button back;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button remove;
    @FXML
    private TextField find;
    @FXML
    private TextArea txdesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        showdt();
        tbitem.getSelectionModel().selectFirst();
        showgambar();
        TabletoTxt();
    }    
    public void showdt() {
        ObservableList<ItemModel> data = FXMLDocumentController.dtdbitem.Load();
        if (data != null) {
            tbitem.getColumns().clear();
            tbitem.getItems().clear();
            TableColumn col = new TableColumn("Kode Barang");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("item_id"));
            tbitem.getColumns().addAll(col);
            col = new TableColumn("Nama Barang");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("nm_brg"));
            tbitem.getColumns().addAll(col);
            col = new TableColumn("Harga");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("tarif"));
            tbitem.getColumns().addAll(col);
            col = new TableColumn("Stok");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("stok"));

            tbitem.getColumns().addAll(col);
            tbitem.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
            a.showAndWait();
            tbitem.getScene().getWindow().hide();
        }
        
    }
    public void showgambar(){
        Image gambar = null;
        try {
            gambar = new Image(new FileInputStream(tbitem.getSelectionModel().getSelectedItem().getImg_brg()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        itemimg.setImage(gambar);
    }

    @FXML
    private void prevclick(ActionEvent event) {
        tbitem.getSelectionModel().selectAboveCell();
        showgambar();
        tbitem.requestFocus();
        TabletoTxt();
    }

    @FXML
    private void nextclick(ActionEvent event) {
        tbitem.getSelectionModel().selectBelowCell();
        showgambar();
        tbitem.requestFocus();
        TabletoTxt();
    }

    @FXML
    private void backclick(ActionEvent event) {
        back.getScene().getWindow().hide();
    }

    @FXML
    private void addclick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputitem.fxml"));
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
        tbitem.getSelectionModel().selectFirst();
        showgambar();
    }

    @FXML
    private void editclick(ActionEvent event) {
        ItemModel s = new ItemModel();
        s = tbitem.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInputitem.fxml"));
            Parent root = (Parent) loader.load();
            FXMLInputitemController isidtbrg = (FXMLInputitemController) loader.getController();
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
        tbitem.getSelectionModel().selectFirst();
        showgambar();
    }

    @FXML
    private void removeclick(ActionEvent event) {
        ItemModel s= new ItemModel();       
        s=tbitem.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Delete this item?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtdbitem.delete(s.getItem_id())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Item deleted succesfully", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Delete Failed", ButtonType.OK);
               b.showAndWait();               
           }    
           showdt();           
           tbitem.getSelectionModel().selectFirst();
            showgambar();       
        }
    }
    
    
    private void TabletoTxt() {
        txdesc.setText(tbitem.getSelectionModel().getSelectedItem().getDesc());
    }
    
    
    public void search(){
                ItemModel s = new ItemModel();
        String key = find.getText();
        if (key != "") {
            ObservableList<ItemModel> data = FXMLDocumentController.dtdbitem.CariIt(key, key);
            if (data != null) {
                tbitem.getColumns().clear();
                tbitem.getItems().clear();
                TableColumn col = new TableColumn("Kode Barang");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("item_id"));
            tbitem.getColumns().addAll(col);
            col = new TableColumn("Nama Barang");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("nm_brg"));
            tbitem.getColumns().addAll(col);
            col = new TableColumn("Harga");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("tarif"));
            tbitem.getColumns().addAll(col);
            col = new TableColumn("Stok");
            col.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("stok"));
            tbitem.getColumns().addAll(col);
            
                tbitem.setItems(data);
                
            tbitem.getSelectionModel().selectFirst();
            showgambar();
            TabletoTxt();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data kosong", ButtonType.OK);
                a.showAndWait();
                tbitem.getScene().getWindow().hide();
            }
        } else {
            showdt();
        }
        
    }

    @FXML
    private void gambar_desc(MouseEvent event) {
        showgambar();
        TabletoTxt();
    }

    @FXML
    private void searchitm(KeyEvent event) {
        search();
    }

    @FXML
    private void searchclick(ActionEvent event) {
        search();
        showgambar();
        TabletoTxt();
        tbitem.getSelectionModel().selectFirst();
    }
    
}
