package Control;

import Model.Drinks;
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

public class D_Menu implements Serializable {

    public static Scanner sc = new Scanner(System.in);
    public static int i;
    public static List<Drinks> a = new ArrayList<>();
    public static final String FILENAME = "Drinks.txt";

    public static void DocTapTin() throws FileNotFoundException, IOException, ClassNotFoundException {
        File fl = new File(FILENAME);
        if (fl.exists() == true) {
            FileInputStream fIS = new FileInputStream(fl);
            ObjectInputStream oIS = new ObjectInputStream(fIS);
            List<Drinks> data = (List<Drinks>) oIS.readObject();
            a = new ArrayList<Drinks>(data.size());
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
            System.out.println("                    |--------------------Đồ Uống-------------------|");
            System.out.println("                    |-------------------Lựa chọn-------------------|");
            System.out.println("                    |1.Nhập thêm đồ uống                           |");
            System.out.println("                    |2.Cập nhật thông tin đồ uống                  |");
            System.out.println("                    |3.Xoá đồ uống                                 |");
            System.out.println("                    |4.In danh sách đồ uống                        |");
            System.out.println("                    |5.Sắp xếp đồ uống theo giá tiền               |");
            System.out.println("                    |0.Thoát                                       |");
            System.out.println("                    |----------------------------------------------|");
            System.out.print("Mời bạn đưa ra lựa chọn: ");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    Nhap();
                    break;
                case 2:
                    CapNhat();
                    break;
                case 3:
                    Xoa();
                    break;
                case 4:
                    InDS();
                    break;
                case 5:
                    SapXep();
                    break;
            }
        } while (chon != 0);
    }

    public static void InTieuDe() {
        System.out.printf("|%15s|%11s|\n", "Đồ uống", "Giá tiền");
    }

    public static void Nhap() throws IOException, FileNotFoundException, ClassNotFoundException {
        String du;
        double gt;
        sc.nextLine();
        System.out.print("Nhập tên đồ uống: ");
        du = sc.nextLine();
        System.out.print("Giá tiền: ");
        gt = sc.nextDouble();
        Drinks x = new Drinks(du, gt);
        a.add(x);
        GhiTapTin();
        DocTapTin();
    }

    public static void InDS() {
        InTieuDe();
        for (i = 0; i < a.size(); i++) {
            a.get(i).InDSD();
        }
    }

    public static void SapXep() {
        Drinks[] b = new Drinks[a.size()];
        for (i = 0; i < a.size(); i++) {
            b[i] = a.get(i);
        }
        for (i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (b[i].getGiaD() > b[j].getGiaD()) {
                    Drinks tg = b[i];
                    b[i] = b[j];
                    b[j] = tg;
                }
            }
        }
        System.out.println("Danh sách đồ uống theo giá tăng dần :");
        InTieuDe();
        for (int i = 0; i < a.size(); i++) {
            b[i].InDSD();
        }
    }

    public static void CapNhat() throws IOException, FileNotFoundException, ClassNotFoundException {
        String c;
        sc.nextLine();
        System.out.print("Nhap ten do uong can cap nhat: ");
        c = sc.nextLine();
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getDouong().equalsIgnoreCase(c)) {
                System.out.println("Đồ uống cần cập nhật là:");
                a.get(i).InDSD();
                String du;
                double gt;
                sc.nextLine();
                System.out.println("Cập nhật lại đồ uống: ");
                System.out.print("Nhập tên đồ uống: ");
                du = sc.nextLine();
                System.out.print("Giá tiền: ");
                gt = sc.nextDouble();
                a.get(i).douong = du;
                a.get(i).setGiaD(gt);
                GhiTapTin();
                DocTapTin();
                return;
            }
        }
        System.out.println("Không tìm thấy đồ uống trên");
    }

    public static void Xoa() throws IOException, FileNotFoundException, ClassNotFoundException {
        String c;
        sc.nextLine();
        System.out.print("Nhập tên đồ uống cần xoá: ");
        c = sc.nextLine();
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getDouong().equalsIgnoreCase(c)) {
                System.out.println("Đồ uống cần xoá là: ");
                a.get(i).InDSD();
                String h;
                System.out.print("Bạn có muốn xoá hay không(Y/N): ");
                h = sc.nextLine();
                if (h.equalsIgnoreCase("Y")) {
                    a.remove(i);
                    System.out.println("Bạn đã xoá thành công");
                    GhiTapTin();
                    DocTapTin();
                }
                return;
            }
        }
        System.out.println("Không tìm thấy đồ uống trên");
    }
}
