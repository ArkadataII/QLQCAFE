package Model;

import java.io.Serializable;

public class Drinks implements Serializable {

    public String douong;
    private double giaD;

    public Drinks() {

    }

    public Drinks(String douong, double giaD) {
        this.douong = douong;
        this.giaD = giaD;
    }

    public String getDouong() {
        return douong;
    }

    public void setDouong(String douong) {
        this.douong = douong;
    }

    public double getGiaD() {
        return giaD;
    }

    public void setGiaD(double giaD) {
        this.giaD = giaD;
    }

    public void InDSD() {
        System.out.printf("|%15s|%10.1f%s|\n", douong, giaD,"$");
    }
}
