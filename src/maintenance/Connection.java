/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenance;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class Connection {

    public java.sql.Connection dbKoneksi;
    public Statement statement;
    public PreparedStatement preparedStatement;

    public Connection() {
        this.dbKoneksi = null;
    }

    public void openConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbKoneksi = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/explorers?user=root&password=");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnect() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbKoneksi != null) {
                dbKoneksi.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}