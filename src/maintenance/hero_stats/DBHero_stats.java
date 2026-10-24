/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenance.hero_stats;

import maintenance.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class DBHero_stats {

    private hero_statsModel dt = new hero_statsModel();

    public hero_statsModel gethero_statsModel() {
        return (dt);
    }

    public void sethero_statsModel(hero_statsModel s) {
        dt = s;
    }

    //Ambil data dari database
    public ObservableList<hero_statsModel> Load(String kode) {
        try {
            ObservableList<hero_statsModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("SELECT s.STR, s.AGI, s.DEX, s.INT, s.CON, s.LUK, s.id_guild,"
                    + "g.nm, g.profile_pic, "
                    + "m.chk_name, r.race_nm, c.cls_nm "
                    + "FROM stats s "
                    + "JOIN guild g ON s.id_guild = g.id_guild "
                    + "JOIN map_reg m ON s.region = m.id_chkpoint "
                    + "JOIN race r ON s.race = r.id_rc "
                    + "JOIN cls c ON s.classes = c.id_cs WHERE s.id_guild LIKE '" + kode + "'");

            int i = 1;
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

                String sts = rs.getString("cls_nm");
                int str = rs.getInt("STR");
                int agi = rs.getInt("AGI");
                int dex = rs.getInt("DEX");
                int inte = rs.getInt("INT");
                int cons = rs.getInt("CON");
                int luk = rs.getInt("LUK");

                switch (sts) {
                    case "Duelist":
                        str += 5;
                        dex += 5;
                        cons += 5;
                        break;

                    case "Magi":
                        inte += 20;
                        dex -= 5;
                        break;

                    case "Healer":
                        inte += 10;
                        cons += 2;
                        luk += 3;
                        break;

                    case "Support":
                        inte += 6;
                        luk += 5;
                        cons += 4;
                        break;

                    case "Swiftstrike":
                        str += 15;
                        agi += 10;
                        dex -= 5;
                        cons -= 5;
                        break;

                    case "Ranged":
                        agi += 5;
                        luk += 5;
                        dex += 5;
                        break;

                    case "Defender":
                        str += 10;
                        cons += 10;
                        agi -= 5;
                        break;
                }

                d.setSTR(str);
                d.setAGI(agi);
                d.setDEX(dex);
                d.setINT(inte);
                d.setCON(cons);
                d.setLUK(luk);

                d.setHr_effect(effectName);

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
