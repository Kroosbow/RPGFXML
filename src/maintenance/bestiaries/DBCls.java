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
public class DBCls {
    private ClsModel dt = new ClsModel();

    public ClsModel getClsModel() {
        return (dt);
    }

    public void setClsModel(ClsModel s) {
        dt = s;
    }

    public ObservableList<ClsModel> Load() {
        try {
            ObservableList<ClsModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select id_cs, cls_nm, img_cls, description from cls");
            
            while (rs.next()) {
                ClsModel d = new ClsModel();
                d.setId_cls(rs.getString("id_cs"));
                d.setCls_nm(rs.getString("cls_nm"));
                d.setImg_cls(rs.getString("img_cls"));
                d.setDescription(rs.getString("description"));
                
                String ef = rs.getString("cls_nm");
                String bonus = "Unregistered";
                
                
                    if (ef.equals("Duelist")) bonus = "+5 STR, +5 DEX, + 5 CON";
                    else if (ef.equals("Magi")) bonus = "+ 20 INT, - 5 DEX";
                    else if (ef.equals("Healer")) bonus = "+10 INT, + 2 CON, +3 LUK";
                    else if (ef.equals("Support")) bonus = "+6 INT, + 5 LUK, + 4 CON";
                    else if (ef.equals("Swiftstrike")) bonus = "+15 STR, +10 AGI, -5 DEX, -5 CON";
                    else if (ef.equals("Ranged")) bonus = "+5 AGI, +5 LUK, +5 DEX";
                    else if (ef.equals("Defender")) bonus = "+10 STR, +10 CON, -5 AGI";
                    
                d.setStat_bon(bonus);
                
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from cls where id_cs = ? ");
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from cls where id_cs = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into cls (id_cs, cls_nm, img_cls, description) values (?,?,?,?)");
            con.preparedStatement.setString(1, getClsModel().getId_cls());
            con.preparedStatement.setString(2, getClsModel().getCls_nm());
            con.preparedStatement.setString(3, getClsModel().getImg_cls());
            con.preparedStatement.setString(4, getClsModel().getDescription());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update cls set cls_nm = ?, img_cls = ?, description = ? where id_cs = ? ");
            con.preparedStatement.setString(1, getClsModel().getCls_nm());
            con.preparedStatement.setString(2, getClsModel().getImg_cls());
            con.preparedStatement.setString(3, getClsModel().getDescription());
            con.preparedStatement.setString(4, getClsModel().getId_cls());
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

    public ObservableList<ClsModel> CariCls(String id, String namaClass) {
        try {
            ObservableList<ClsModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            // Memperbaiki tabel dari 'barang' menjadi 'cls' dan kolom pencarian yang sesuai
            ResultSet rs = con.statement.executeQuery("select * from cls WHERE id_cs LIKE '" + id + "%' OR cls_nm LIKE '" + namaClass + "%'");
            
            while (rs.next()) {
                ClsModel d = new ClsModel();
                d.setId_cls(rs.getString("id_cs"));
                d.setCls_nm(rs.getString("cls_nm"));
                d.setImg_cls(rs.getString("img_cls"));
                d.setDescription(rs.getString("description"));
                String ef = rs.getString("cls_nm");
                String bonus = "Unregistered";
                
                
                    if (ef.equals("Duelist")) bonus = "+5 ATK, +5 DEF, + 5 CON";
                    else if (ef.equals("Magi")) bonus = "+ 20 INT, - 5 DEF";
                    else if (ef.equals("Healer")) bonus = "+10 INT, + 2 CON, +3 LUK";
                    else if (ef.equals("Support")) bonus = "+6 INT, + 5 LUK, + 4 CON";
                    else if (ef.equals("Swiftstrike")) bonus = "+15 ATK, +10 AGI, -5 DEF, -5 CON";
                    else if (ef.equals("Ranged")) bonus = "+5 AGI, +5 LUK, +5 DEX";
                    else if (ef.equals("Defender")) bonus = "+10 DEF, +10 CON, -5 AGI";
                    
                d.setStat_bon(bonus);

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