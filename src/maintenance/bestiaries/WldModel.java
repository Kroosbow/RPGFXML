/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maintenance.bestiaries;

/**
 *
 * @author User
 */
public class WldModel {
    private String id_chkpoint, chk_name, map_img;
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getId_chkpoint() {
        return id_chkpoint;
    }

    public void setId_chkpoint(String id_chkpoint) {
        this.id_chkpoint = id_chkpoint;
    }

    public String getChk_name() {
        return chk_name;
    }

    public void setChk_name(String chk_name) {
        this.chk_name = chk_name;
    }

    public String getMap_img() {
        return map_img;
    }

    public void setMap_img(String map_img) {
        this.map_img = map_img;
    }
    
}
