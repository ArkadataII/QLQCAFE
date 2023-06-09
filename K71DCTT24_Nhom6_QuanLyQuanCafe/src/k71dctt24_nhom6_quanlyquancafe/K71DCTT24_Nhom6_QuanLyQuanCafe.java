
package k71dctt24_nhom6_quanlyquancafe;
                                                                                //Nhóm 6 - Lớp 71DCTT24
import Control.Menu_Controlling;                                                //Trần Duy Hải - 71DCTT22051
import Control.Revenue_Controlling;                                             //Hoàng Tiến Đạt - 71DCTT21096
import Control.Staff_Controlling;                                               //Bùi Anh Vũ - 71DCTT21088
import java.io.FileNotFoundException;                                           //Trần Trung Kiên - 71DCTT21005
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class K71DCTT24_Nhom6_QuanLyQuanCafe implements Serializable {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Scanner s= new Scanner(System.in);
        int c;
        do{
            System.out.println("                    |----------Phần mềm quản lý quán CaFe----------|");
            System.out.println("                    |-------------------Lựa chọn-------------------|");
            System.out.println("                    |1.Quản lý nhân viên                           |");
            System.out.println("                    |2.Quản lý thực đơn                            |");
            System.out.println("                    |3.Quản lý doanh thu                           |");
            System.out.println("                    |0.Thoát chương trình                          |");
            System.out.println("                    |----------------------------------------------|");
            System.out.print("Mời bạn đưa ra lựa chọn:");
            c=s.nextInt();
            switch(c){
                case 1:
                    Staff_Controlling sc= new Staff_Controlling();
                    sc.Menu();
                    break;
                case 2:
                    Menu_Controlling mc= new Menu_Controlling();
                    mc.Menu();
                    break;
                case 3:
                    Revenue_Controlling rc=new Revenue_Controlling();
                    rc.Menu();
                    break;
            }
        }while(c!=0);
    }
}













































//Nhóm 6 - Quản lý quán cafe
//Trần Duy Hải
//Bùi Anh Vũ
//Hoàng Tiến Đạt
//Trần Trung Kiên