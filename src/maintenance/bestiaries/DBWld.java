/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenance.bestiaries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import maintenance.Connection;

/**
 *
 * @author User
 */
public class DBWld {
    
    private WldModel dt = new WldModel();

    public WldModel getWldModel() {
        return (dt);
    }

    public void setWldModel(WldModel s) {
        dt = s;
    }

    public ObservableList<WldModel> Load() {
        try {
            ObservableList<WldModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select id_chkpoint, chk_name, distance, map_img from map_reg");
            
            while (rs.next()) {
                WldModel d = new WldModel();
                
                d.setId_chkpoint(rs.getString("id_chkpoint"));
                d.setChk_name(rs.getString("chk_name"));
                d.setDistance(rs.getDouble("distance"));
                d.setMap_img(rs.getString("map_img"));
                
                tableData.add(d);
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
            con.openConnect();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from map_reg where id_chkpoint = ? ");
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from map_reg where id_chkpoint = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into map_reg (id_chkpoint, chk_name, distance, map_img) values (?,?,?,?)");
            con.preparedStatement.setString(1, getWldModel().getId_chkpoint());
            con.preparedStatement.setString(2, getWldModel().getChk_name());
            con.preparedStatement.setDouble(3, getWldModel().getDistance());
            con.preparedStatement.setString(4, getWldModel().getMap_img());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update map_reg set chk_name = ?, distance = ?, map_img = ? where id_chkpoint = ? ");
            con.preparedStatement.setString(1, getWldModel().getChk_name());
            con.preparedStatement.setDouble(2, getWldModel().getDistance());
            con.preparedStatement.setString(3, getWldModel().getMap_img());
            con.preparedStatement.setString(4, getWldModel().getId_chkpoint());
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

    public ObservableList<WldModel> CariWld(String id, String name) {
        try {
            ObservableList<WldModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select * from map_reg WHERE id_chkpoint LIKE '" + id + "%' OR chk_name LIKE '" + name + "%'");
            
            while (rs.next()) {
                WldModel d = new WldModel();
                d.setId_chkpoint(rs.getString("id_chkpoint"));
                d.setChk_name(rs.getString("chk_name"));
                d.setDistance(rs.getDouble("distance"));
                d.setMap_img(rs.getString("map_img"));

                tableData.add(d);
            }
            con.closeConnect();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}