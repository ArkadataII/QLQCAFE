
package Model;

import java.io.Serializable;

public class Fastfood extends Drinks implements Serializable {
    public String doan;
    private double giatien;
    public Fastfood(){
        
    }
    public Fastfood(String doan,double giatien){
        this.doan=doan;
        this.giatien=giatien;
    }

    public String getDoan() {
        return doan;
    }

    public void setDoan(String doan) {
        this.doan = doan;
    }

    public double getGiatien() {
        return giatien;
    }

    public void setGiatien(double giatien) {
        this.giatien = giatien;
    }
    @Override
    public void InDSD(){
        System.out.printf("|%15s|%10.1f%s|\n",doan,giatien,"$");
    }
}
