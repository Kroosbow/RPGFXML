/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenance.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import maintenance.Connection;
import maintenance.Maintenance;
import maintenance.FXMLDocumentController;

/**
 *
 * @author User
 */
public class DBItem {
    private ItemModel dt = new ItemModel();

    public ItemModel getItemModel() {
        return (dt);
    }

    public void setItemModel(ItemModel s) {
        dt = s;
    }

    public ObservableList<ItemModel> Load() {
        try {
            ObservableList<ItemModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from shop");
            int i = 1;
            while (rs.next()) {
                ItemModel d = new ItemModel();
                d.setItem_id(rs.getString("item_id"));
                d.setNm_brg(rs.getString("nm_brg"));
                d.setTarif(rs.getDouble("tarif"));
                d.setStok(rs.getInt("stok"));
                d.setDesc(rs.getString("descrp"));
                d.setImg_brg(rs.getString("img_brg"));
                
                tableData.add(d);
                i++;
            }
            con.closeConnect();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean delete(String nomor) {
        boolean berhasil = false;
        Connection con = new Connection();
        try {
            con.openConnect();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from shop where item_id  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.closeConnect();
            return berhasil;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from shop where item_id = '" + nomor + "'");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
    boolean berhasil = false;
    Connection con = new Connection();
    try {
        con.openConnect();
        con.preparedStatement = con.dbKoneksi.prepareStatement(
            "insert into shop (item_id, nm_brg, tarif, stok, descrp, img_brg) values (?,?,?,?,?,?)"); 
        con.preparedStatement.setString(1, getItemModel().getItem_id());
        con.preparedStatement.setString(2, getItemModel().getNm_brg());
        con.preparedStatement.setDouble(3, getItemModel().getTarif());
        con.preparedStatement.setInt(4, getItemModel().getStok());
        con.preparedStatement.setString(5, getItemModel().getDesc());
        con.preparedStatement.setString(6, getItemModel().getImg_brg());
        con.preparedStatement.executeUpdate();
        berhasil = true;
    } catch (Exception e) {
        e.printStackTrace();
        berhasil = false;
    } finally {
        con.closeConnect();
        return berhasil;
    }
}

    
    public boolean update() {
        boolean berhasil = false;
        Connection con = new Connection();
        try {
            con.openConnect();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update shop set nm_brg = ?, tarif = ?, stok = ?, descrp =?, img_brg =?  where  item_id = ? ");
            con.preparedStatement.setString(1, getItemModel().getNm_brg());
            con.preparedStatement.setDouble(2, getItemModel().getTarif());
            con.preparedStatement.setInt(3, getItemModel().getStok());
            con.preparedStatement.setString(4, getItemModel().getDesc());
            con.preparedStatement.setString(5, getItemModel().getImg_brg());
            con.preparedStatement.setString(6, getItemModel().getItem_id());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.closeConnect();
            return berhasil;
        }
    }
        public ObservableList<ItemModel> CariIt(String idbrg, String brg) {
        try {
            ObservableList<ItemModel> tableData;
            tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from shop WHERE item_id LIKE '" + idbrg + "%' OR nm_brg LIKE '" + brg + "%'");
            int i = 1;
            while (rs.next()) {
                ItemModel d = new ItemModel();
                d.setItem_id(rs.getString("item_id"));
                d.setNm_brg(rs.getString("nm_brg"));
                d.setTarif(rs.getDouble("tarif"));
                d.setStok(rs.getInt("stok"));
                d.setDesc(rs.getString("descrp"));
                d.setImg_brg(rs.getString("img_brg"));

                tableData.add(d);
                i++;
            }
            con.closeConnect();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
