/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenance.hero_stats;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import maintenance.Connection;

/**
 *
 * @author User
 */
public class DBHero_info {

    private hero_statsModel dt = new hero_statsModel();

    public hero_statsModel gethero_statsModel() {
        return (dt);
    }

    public void sethero_statsModel(hero_statsModel s) {
        dt = s;
    }

    //Ambil data dari database
    public ObservableList<hero_statsModel> Load() {
        try {
            ObservableList<hero_statsModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();

            // PERBAIKAN: Hapus potongan "WHERE id_guild LIKE" yang menggantung
            ResultSet rs = con.statement.executeQuery(
                    "SELECT s.STR, s.AGI, s.DEX, s.INT, s.CON, s.LUK, s.id_guild, "
                    + "g.nm, g.profile_pic, "
                    + "m.chk_name, r.race_nm, c.cls_nm "
                    + "FROM stats s "
                    + "JOIN guild g ON s.id_guild = g.id_guild "
                    + "JOIN map_reg m ON s.region = m.id_chkpoint "
                    + "JOIN race r ON s.race = r.id_rc "
                    + "JOIN cls c ON s.classes = c.id_cs"
            );

            while (rs.next()) {
                hero_statsModel d = new hero_statsModel();
                d.setHr_id(rs.getString("id_guild"));
                d.setSTR(rs.getInt("STR"));
                d.setAGI(rs.getInt("AGI"));
                d.setDEX(rs.getInt("DEX"));
                d.setINT(rs.getInt("INT"));
                d.setCON(rs.getInt("CON"));
                d.setLUK(rs.getInt("LUK"));
                d.setHr_nm(rs.getString("nm"));
                d.setHr_pic(rs.getString("profile_pic"));
                d.setHr_from(rs.getString("chk_name"));
                d.setHr_rc(rs.getString("race_nm"));
                d.setHr_cls(rs.getString("cls_nm"));

                String ef = rs.getString("race_nm");
                String effectName = "Unregistered";
                if (ef != null) {
                    if (ef.equals("Dragon")) {
                        effectName = "Elemental Scale";
                    } else if (ef.equals("Elf")) {
                        effectName = "Deadeye";
                    } else if (ef.equals("Tssiar")) {
                        effectName = "Natural Power";
                    } else if (ef.equals("Human")) {
                        effectName = "Fast adapt";
                    } else if (ef.equals("Homiconnee")) {
                        effectName = "Quick feet";
                    } else if (ef.equals("Canid")) {
                        effectName = "Huge Power";
                    } else if (ef.equals("Mimic")) {
                        effectName = "Sneaky";
                    } else if (ef.equals("Angel")) {
                        effectName = "Untouchable";
                    } else if (ef.equals("World Dragon")) {
                        effectName = "Earth's Protector";
                    }
                }


                
                d.setHr_effect(effectName);

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
