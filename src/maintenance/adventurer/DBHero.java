/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenance.adventurer;

import maintenance.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class DBHero {
    
    private HeroModel dt = new HeroModel();

    public HeroModel getHeroModel() {
        return (dt);
    }

    public void setHeroModel(HeroModel s) {
        dt = s;
    }

    public ObservableList<HeroModel> Load() {
        try {
            ObservableList<HeroModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select * from guild");
            int i = 1;
            while (rs.next()) {
                HeroModel d = new HeroModel();
                d.setId_guild(rs.getString("id_guild"));
                d.setNm(rs.getString("nm"));
                d.setRace(rs.getString("race"));
                
                //set class utama
                String subc = rs.getString("id_guild");
                String advcls = null;
                
                if (subc != null && subc.startsWith("SP")){
                    advcls = "Support";
                }else if (subc != null && subc.startsWith("DL")){
                    advcls = "Duelist";
                }else if (subc != null && subc.startsWith("MG")){
                    advcls = "Magi";
                }else if (subc != null && subc.startsWith("HL")){
                    advcls = "Healer";
                }else if (subc != null && subc.startsWith("SS")){
                    advcls = "Swiftstrike";
                }else if (subc != null && subc.startsWith("DF")){
                    advcls = "Defender";
                }else if (subc != null && subc.startsWith("RG")){
                    advcls = "Ranged";
                }
                
                d.setCls(advcls);
                d.setSubclass(rs.getString("subclass"));
                d.setProfile_pic(rs.getString("profile_pic"));
                
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from guild where id_guild  = ? ");
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from guild where id_guild = '" + nomor + "'");
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
            "insert into guild (id_guild, nm, race, subclass, profile_pic) values (?,?,?,?,?)"); 
        con.preparedStatement.setString(1, getHeroModel().getId_guild());
        con.preparedStatement.setString(2, getHeroModel().getNm());
        con.preparedStatement.setString(3, getHeroModel().getRace());
        con.preparedStatement.setString(4, getHeroModel().getSubclass());
        con.preparedStatement.setString(5, getHeroModel().getProfile_pic());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update guild set nm = ?, race = ?, subclass = ?, profile_pic =?  where  id_guild = ? ");
            con.preparedStatement.setString(1, getHeroModel().getNm());
            con.preparedStatement.setString(2, getHeroModel().getRace());
            con.preparedStatement.setString(3, getHeroModel().getSubclass());
            con.preparedStatement.setString(4, getHeroModel().getProfile_pic());
            con.preparedStatement.setString(5, getHeroModel().getId_guild());
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
        public ObservableList<HeroModel> CariHr(String idguild, String nma, String rc) {
        try {
            ObservableList<HeroModel> tableData;
            tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from guild WHERE id_guild LIKE '" + idguild + "%' OR nm LIKE '" + nma + "%' OR race LIKE '" + rc + "%'");
            int i = 1;
            while (rs.next()) {
                HeroModel d = new HeroModel();
                d.setId_guild(rs.getString("id_guild"));
                d.setNm(rs.getString("nm"));
                d.setRace(rs.getString("race"));
                
                //set class utama
                String subc = rs.getString("id_guild");
                String advcls = null;
                
                if (subc != null && subc.startsWith("SP")){
                    advcls = "Support";
                }else if (subc != null && subc.startsWith("DL")){
                    advcls = "Duelist";
                }else if (subc != null && subc.startsWith("MG")){
                    advcls = "Magi";
                }else if (subc != null && subc.startsWith("HL")){
                    advcls = "Healer";
                }else if (subc != null && subc.startsWith("SS")){
                    advcls = "Swiftstrike";
                }else if (subc != null && subc.startsWith("DF")){
                    advcls = "Defender";
                }else if (subc != null && subc.startsWith("RG")){
                    advcls = "Ranged";
                }
                
                d.setCls(advcls);
                d.setSubclass(rs.getString("subclass"));
                d.setProfile_pic(rs.getString("profile_pic"));

                

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
