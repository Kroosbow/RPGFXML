/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

package maintenance;

import maintenance.bestiaries.RaceModel;
import maintenance.bestiaries.FXMLRaceController;
import maintenance.hero_stats.DBHero_stats;
import maintenance.hero_stats.DBHero_info;
import maintenance.bestiaries.DBRace;
import maintenance.bestiaries.DBCls;
import maintenance.bestiaries.DBWld;
import java.io.IOException;
import maintenance.adventurer.DBHero;
import maintenance.item.DBItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    public static DBHero dtdbhero = new DBHero();
    public static DBItem dtdbitem = new DBItem();
    public static DBRace dtdbrace = new DBRace();
    public static DBCls dtdbcls = new DBCls();
    public static DBWld dtdbwld = new DBWld();
    public static DBHero_stats dtdbhrs = new DBHero_stats();
    public static DBHero_info dtdbhrinf = new DBHero_info();
    
    private Label label;
    @FXML
    private ImageView box;
    @FXML
    private Button statbtn;
    @FXML
    private Button dtbtn;
    @FXML
    private Button addbtn;
    @FXML
    private ImageView june;
    @FXML
    private Button talk;
    @FXML
    private Button viewHero;
    @FXML
    private Button ViewItem;
    @FXML
    private Button addHero;
    @FXML
    private Button addItem;
    @FXML
    private Button herostat;
    @FXML
    private Button heroinv;
    @FXML
    private Button addrc;
    @FXML
    private Button addcls;
    @FXML
    private Button Viewrc;
    @FXML
    private Button Viewcls;
    @FXML
    private Button Viewreg;
    @FXML
    private Button addreg;
    @FXML
    private Label txt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }
    @FXML
    private void dtbtnview(ActionEvent event) {
        boolean isView = viewHero.isVisible() && ViewItem.isVisible();
        boolean notView = !isView;
        
        
        viewHero.setVisible(notView); viewHero.setManaged(notView);
        ViewItem.setVisible(notView); ViewItem.setManaged(notView);
        Viewrc.setVisible(notView); Viewrc.setManaged(notView);
        Viewcls.setVisible(notView); Viewcls.setManaged(notView);
        Viewreg.setVisible(notView); Viewreg.setManaged(notView);
        
        herostat.setVisible(false); herostat.setManaged(false);
        heroinv.setVisible(false); heroinv.setManaged(false);
        addHero.setVisible(false); addHero.setManaged(false);
        addItem.setVisible(false); addItem.setManaged(false);
        addrc.setVisible(false); addrc.setManaged(false);
        addcls.setVisible(false); addcls.setManaged(false);
        addreg.setVisible(false); addreg.setManaged(false);
        txt.setText("Want to view something \n|| Ingin melihat data?");
    }

    @FXML
    private void statbtnview(ActionEvent event) {
        boolean isStat = herostat.isVisible() && heroinv.isVisible();
        boolean notStat = !isStat;
        
        herostat.setVisible(notStat); herostat.setManaged(notStat);
        heroinv.setVisible(notStat); heroinv.setManaged(notStat);
        
        viewHero.setVisible(false); viewHero.setManaged(false);
        ViewItem.setVisible(false); ViewItem.setManaged(false);
        Viewrc.setVisible(false); Viewrc.setManaged(false);
        Viewcls.setVisible(false); Viewcls.setManaged(false);
        Viewreg.setVisible(false); Viewreg.setManaged(false);
        
        addHero.setVisible(false); addHero.setManaged(false);
        addItem.setVisible(false); addItem.setManaged(false);
        addrc.setVisible(false); addrc.setManaged(false);
        addcls.setVisible(false); addcls.setManaged(false);
        addreg.setVisible(false); addreg.setManaged(false);
        txt.setText("Want to view something \n|| Ingin melihat data?");
    }

    @FXML
    private void addbtnview(ActionEvent event) {
        boolean isAdd = addHero.isVisible() && addItem.isVisible();
        boolean notAdd = !isAdd;
        
        addHero.setVisible(notAdd); addHero.setManaged(notAdd);
        addItem.setVisible(notAdd); addItem.setManaged(notAdd);
        addcls.setVisible(notAdd); addcls.setManaged(notAdd);
        addrc.setVisible(notAdd); addrc.setManaged(notAdd);
        addreg.setVisible(notAdd); addreg.setManaged(notAdd);
        
        herostat.setVisible(false); herostat.setManaged(false);
        heroinv.setVisible(false); heroinv.setManaged(false);
        viewHero.setVisible(false); viewHero.setManaged(false);
        ViewItem.setVisible(false); ViewItem.setManaged(false);
        Viewrc.setVisible(false); Viewrc.setManaged(false);
        Viewcls.setVisible(false); Viewcls.setManaged(false);
        Viewreg.setVisible(false); Viewreg.setManaged(false);
        txt.setText("Want to add something, Boss? \n|| Ingin ada yang ditambahkan?");
    }

    @FXML
    private void opnviewhero(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("adventurer/FXMLHero.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnviewitem(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("item/FXMLItem.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
        
    }

    @FXML
    private void opnaddhero(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("adventurer/FXMLInput.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnadditem(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("item/FXMLInputitem.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnherostat(ActionEvent event) {
          try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("hero_stats/FXMLherostats.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnheroinv(ActionEvent event) {
        txt.setText("Inventory still has no function, Sorry \n|| Inventory masih belum ada fungsinya, Maaf");
    }

    @FXML
    private void opnviewrc(ActionEvent event) {
          try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("bestiaries/FXMLRace.fxml"));    
            Parent root = (Parent)loader.load();
            
            FXMLRaceController controller = loader.getController();
            controller.selectTab(0);
            
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnaddrc(ActionEvent event) {
          try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("bestiaries/FXMLInputrace.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnaddclass(ActionEvent event) {
          try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("bestiaries/FXMLInputcls.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnviewcls(ActionEvent event) {
            try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("bestiaries/FXMLRace.fxml"));    
            Parent root = (Parent)loader.load();
            
            FXMLRaceController controller = loader.getController();
            controller.selectTab(1);
            
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
            
    }

    @FXML
    private void opnviewreg(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("bestiaries/FXMLRace.fxml"));    
            Parent root = (Parent)loader.load();
            
            FXMLRaceController controller = loader.getController();
            controller.selectTab(2);
            
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void opnaddreg(ActionEvent event) {
        try{  
            FXMLLoader loader=new FXMLLoader(getClass().getResource("bestiaries/FXMLinputwld.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void notuse(MouseEvent event) {
        txt.setText("Inventory still has no function, Sorry \n|| Inventory masih belum ada fungsinya, Maaf");
    }

    @FXML
    private void btntclick(ActionEvent event) {
        txt.setText("It's still have no function, Sorry");
    }
    
}
