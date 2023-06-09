package Control;

import Model.Revenue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Revenue_Controlling implements Serializable {

    public static Scanner sc = new Scanner(System.in);
    public static int i;
    public static List<Revenue> a = new ArrayList<>();
    public static final String FILENAME = "Revenue.txt";

    public static void DocTapTin() throws FileNotFoundException, IOException, ClassNotFoundException {
        File fl = new File(FILENAME);
        if (fl.exists() == true) {
            FileInputStream fIS = new FileInputStream(fl);
            ObjectInputStream oIS = new ObjectInputStream(fIS);
            List<Revenue> data = (List<Revenue>) oIS.readObject();
            a = new ArrayList<Revenue>(data.size());
            a.addAll(data);
            oIS.close();
            fIS.close();
        }
    }

    public static void GhiTapTin() throws FileNotFoundException, IOException {
        FileOutputStream fOS = new FileOutputStream(FILENAME);
        ObjectOutputStream oOS = new ObjectOutputStream(fOS);
        oOS.writeObject(a);
        oOS.close();
        fOS.close();
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Menu();
    }

    public static void Menu() throws IOException, FileNotFoundException, ClassNotFoundException {
        DocTapTin();
        int chon;
        do {
            System.out.println("                    |---------------Quản lý doanh thu--------------|");
            System.out.println("                    |-------------------Lựa chọn-------------------|");
            System.out.println("                    |1.Nhập thêm thông tin                         |");
            System.out.println("                    |2.Cập nhật thông tin                          |");
            System.out.println("                    |3.Xoá thông tin                               |");
            System.out.println("                    |4.In doanh thu các tháng                      |");
            System.out.println("                    |5.Sắp xếp doanh thu các tháng                 |");
            System.out.println("                    |6.Chênh lệch doanh thu hàng tháng             |");
            System.out.println("                    |0.Thoát                                       |");
            System.out.println("                    |----------------------------------------------|");
            System.out.print("Mời bạn đưa ra lựa chọn: ");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    NhapDT();
                    break;
                case 2:
                    CapNhat();
                    break;
                case 3:
                    Xoa();
                    break;
                case 4:
                    InDT();
                    break;
                case 5:
                    SapXep();
                    break;
                case 6:
                    ChenhLech();
                    break;
            }
        } while (chon != 0);
    }

    public static void InTieuDe() {
        System.out.printf("|%10s|%15s|%10s|%15s|\n", "Tháng", "Số tiền", "Chiết khấu", "Thành tiền");
    }

    public static void NhapDT() throws IOException, FileNotFoundException, ClassNotFoundException {
        int t;
        double st, ck;
        System.out.print("Tháng: ");
        t = sc.nextInt();
        System.out.print("Số tiền: ");
        st = sc.nextDouble();
        System.out.print("Chiết Khấu: ");
        ck = sc.nextDouble();
        Revenue x = new Revenue(t, st, ck);
        a.add(x);
        GhiTapTin();
        DocTapTin();
    }

    public static void CapNhat() throws IOException, FileNotFoundException, ClassNotFoundException {
        int t;
        System.out.print("Nhap tháng cần cập nhật: ");
        t = sc.nextInt();
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getThang() == t) {
                System.out.println("Tháng cần cập nhật là: ");
                InTieuDe();
                a.get(i).INDS();
                System.out.println("Cập nhật lại doanh thu của tháng");
                int th;
                double st, ck;
                System.out.print("Tháng: ");
                th = sc.nextInt();
                System.out.print("Số tiền: ");
                st = sc.nextDouble();
                System.out.print("Chiết Khấu: ");
                ck = sc.nextDouble();
                a.get(i).setThang(th);
                a.get(i).setSotien(st);
                a.get(i).setChietkhau(ck);
                GhiTapTin();
                DocTapTin();
                return;
            }
        }
    }

    public static void Xoa() throws IOException, FileNotFoundException, ClassNotFoundException {
        int t;
        System.out.print("Nhập tháng cần xoá là :");
        t = sc.nextInt();
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getThang() == t) {
                System.out.print("Tháng cần xoá là: ");
                InTieuDe();
                a.get(i).INDS();
                String c;
                sc.nextLine();
                System.out.print("Bạn có muốn xoá hay không(Y/N): ");
                c = sc.nextLine();
                if (c.equalsIgnoreCase("y")) {
                    a.remove(i);
                    System.out.println("Bạn đã xoá thành công");
                    GhiTapTin();
                    DocTapTin();
                }
                return;
            }
        }
    }

    public static void InDT() {
        InTieuDe();
        for (i = 0; i < a.size(); i++) {
            a.get(i).INDS();
        }
    }

    public static void SapXep() {
        Revenue[] b = new Revenue[a.size()];
        for (i = 0; i < a.size(); i++) {
            b[i] = a.get(i);
        }
        for (i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (b[i].TinhTien() < b[j].TinhTien()) {
                    Revenue tg = b[i];
                    b[i] = b[j];
                    b[j] = tg;
                }
            }
        }
        System.out.println("Doanh thu các tháng được sắp xếp giảm dần: ");
        InTieuDe();
        for (i = 0; i < a.size(); i++) {
            b[i].INDS();
        }
    }

    public static void ChenhLech() {
        System.out.printf("|%10s|%15s|%10s|%15s|\n", "Tháng", "Thành tiền", "Chênh lệch","Mô tả");
        System.out.printf("|%10d|%15.2f|%10s|%15s|\n", a.get(0).getThang(), a.get(0).TinhTien(),"----------","----------");
        for (i = 1; i < a.size(); i++) {
            System.out.printf("|%10d|%15.2f|", a.get(i).getThang(), a.get(i).TinhTien());
            if (a.get(i).TinhTien() > a.get(i - 1).TinhTien()) {
                System.out.printf("%8.2f%2s|%14s%d|\n", 100-(a.get(i - 1).TinhTien() / a.get(i).TinhTien() * 100), "%↑","So với T:",a.get(i-1).getThang());
            } else {
                System.out.printf("%8.2f%2s|%14s%d|\n", 100-(a.get(i).TinhTien() / a.get(i - 1).TinhTien() * 100), "%↓","So với T:",a.get(i-1).getThang());
            }
        }
    }
}
