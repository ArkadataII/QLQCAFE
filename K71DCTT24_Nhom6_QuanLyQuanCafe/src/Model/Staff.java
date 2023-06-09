package Model;

import java.io.Serializable;

public class Staff extends Personal implements Serializable {

    public String manv, chucvu;
    private int ngaycong;
    protected String sodienthoai;

    public Staff() {
    }

    public Staff(String manv, String hoten, String chucvu, int ngaycong, int namsinh, String diachi, String sodienthoai) {
        this.manv = manv;// Từ khoá "this" dùng để tham chiếu tới biến instance(manv) của lớp hiện tại.
        this.hoten = hoten;
        this.chucvu = chucvu;
        this.ngaycong = ngaycong;
        this.namsinh = namsinh;
        this.diachi = diachi;
        this.sodienthoai = sodienthoai;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public int getNgaycong() {
        return ngaycong;
    }

    public void setNgaycong(int ngaycong) {
        this.ngaycong = ngaycong;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public void IHS() {
        System.out.printf("|%20s|%20s|%10s|%10d|%10d|%15s|%15s|", manv, getHoten(), chucvu, ngaycong, namsinh, diachi, sodienthoai);
    }

    public int TinhLuong() {
        int s = 0;
        if (chucvu.equalsIgnoreCase("Leader")) {
            s = ngaycong * 200;
        }
        if (chucvu.equalsIgnoreCase("Bartender")) {
            s = ngaycong * 150;
        }
        if (chucvu.equalsIgnoreCase("Staff") || chucvu.equalsIgnoreCase("Guard")) {
            s = ngaycong * 100;
        }
        return s;
    }
}
