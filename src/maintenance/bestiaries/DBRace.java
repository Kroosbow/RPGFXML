package maintenance.bestiaries;
import maintenance.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBRace {
    private RaceModel dt = new RaceModel();

    public RaceModel getRaceModel() {
        return (dt);
    }

    public void setRaceModel(RaceModel s) {
        dt = s;
    }

    public ObservableList<RaceModel> Load() {
        try {
            ObservableList<RaceModel> tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select id_rc, race_nm, effect_desc, description, img_rc from race");
            
            while (rs.next()) {
                RaceModel d = new RaceModel();
                d.setId_rc(rs.getString("id_rc"));
                d.setRace_nm(rs.getString("race_nm"));
                
                String ef = rs.getString("race_nm");
                String effectName = "Unregistered";
                
                    if (ef.equals("Dragon")) effectName = "Elemental Scale";
                    else if (ef.equals("Elf")) effectName = "Deadeye";
                    else if (ef.equals("Tssiar")) effectName = "Natural Power";
                    else if (ef.equals("Human")) effectName = "Fast adapt";
                    else if (ef.equals("Homiconnee")) effectName = "Quick feet";
                    else if (ef.equals("Homicanid")) effectName = "Huge Power";
                    else if (ef.equals("Mimic")) effectName = "Sneaky";
                    else if (ef.equals("Angel")) effectName = "Untouchable";
                    else if (ef.equals("World Dragon")) effectName = "Earth's Protector";
                
                
                d.setEffect(effectName);
                d.setEffect_desc(rs.getString("effect_desc"));
                d.setDescription(rs.getString("description"));
                d.setImg_rc(rs.getString("img_rc"));
                
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from race where id_rc = ? ");
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
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from race where id_rc = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into race (id_rc, race_nm, effect_desc, description, img_rc) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getRaceModel().getId_rc());
            con.preparedStatement.setString(2, getRaceModel().getRace_nm());
            con.preparedStatement.setString(3, getRaceModel().getEffect_desc());
            con.preparedStatement.setString(4, getRaceModel().getDescription());
            con.preparedStatement.setString(5, getRaceModel().getImg_rc());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update race set race_nm = ?, effect_desc = ?, description = ?, img_rc = ? where id_rc = ? ");
            con.preparedStatement.setString(1, getRaceModel().getRace_nm());
            con.preparedStatement.setString(2, getRaceModel().getEffect_desc());
            con.preparedStatement.setString(3, getRaceModel().getDescription());
            con.preparedStatement.setString(4, getRaceModel().getImg_rc());
            con.preparedStatement.setString(5, getRaceModel().getId_rc());
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

        public ObservableList<RaceModel> CariRace(String id, String race) {
        try {
            ObservableList<RaceModel> tableData;
            tableData = FXCollections.observableArrayList();
            Connection con = new Connection();
            con.openConnect();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from race WHERE id_rc LIKE '" + id + "%' OR race_nm LIKE '" + race + "%'");
            int i = 1;
            while (rs.next()) {
                RaceModel d = new RaceModel();
                d.setId_rc(rs.getString("id_rc"));
                d.setRace_nm(rs.getString("race_nm"));
                
                String ef = rs.getString("race_nm");
                String effectName = "Unregistered";
                
                
                    if (ef.equals("Dragon")) effectName = "Elemental Scale";
                    else if (ef.equals("Elf")) effectName = "Deadeye";
                    else if (ef.equals("Tssiar")) effectName = "Natural Power";
                    else if (ef.equals("Human")) effectName = "Fast adapt";
                    else if (ef.equals("Homiconnee")) effectName = "Quick feet";
                    else if (ef.equals("Canid")) effectName = "Huge Power";
                    else if (ef.equals("Mimic")) effectName = "Sneaky";
                    else if (ef.equals("Angel")) effectName = "Untouchable";
                    else if (ef.equals("World Dragon")) effectName = "Earth's Protector";
                
                
                d.setEffect(rs.getString(effectName));
                d.setEffect_desc(rs.getString("effect_desc"));
                d.setDescription(rs.getString("description"));
                d.setImg_rc(rs.getString("img_rc"));

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