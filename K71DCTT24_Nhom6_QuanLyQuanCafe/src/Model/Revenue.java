package Model;

import java.io.Serializable;

public class Revenue implements Serializable {

    private int thang;
    protected double sotien;
    private double chietkhau;

    public Revenue() {

    }

    public Revenue(int thang, double sotien, double chietkhau) {
        this.thang = thang;
        this.sotien = sotien;
        this.chietkhau = chietkhau;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        this.sotien = sotien;
    }

    public double getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(double chietkhau) {
        this.chietkhau = chietkhau;
    }

    public double TinhTien() {
        return sotien - sotien * chietkhau;
    }

    public void INDS() {
        System.out.printf("|%10d|%14.2f%s|%10.2f|%14.2f%s|\n", thang, sotien, "$", chietkhau, TinhTien(), "$");
    }
}
