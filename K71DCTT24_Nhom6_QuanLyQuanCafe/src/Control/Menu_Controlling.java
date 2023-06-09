package Control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Menu_Controlling implements Serializable {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Menu();
    }

    public static void Menu() throws IOException, FileNotFoundException, ClassNotFoundException {
        int chon;
        do {
            System.out.println("                    |---------------Quản lý thực đơn---------------|");
            System.out.println("                    |-------------------Lựa chọn-------------------|");
            System.out.println("                    |1.Đồ uống                                     |");
            System.out.println("                    |2.Đồ ăn nhanh                                 |");
            System.out.println("                    |0.Thoát                                       |");
            System.out.println("                    |----------------------------------------------|");
            System.out.print("Mời bạn đưa ra lựa chọn:");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    D_Menu dm = new D_Menu();
                    dm.Menu();
                    break;
                case 2:
                    F_Menu fm = new F_Menu();
                    fm.Menu();
                    break;
            }
        } while (chon != 0);
    }
}
