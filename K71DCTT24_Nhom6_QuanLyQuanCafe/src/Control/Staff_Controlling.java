package Control;

import Model.Staff;
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

public class Staff_Controlling implements Serializable {

    public static Scanner sc = new Scanner(System.in);
    public static int i;
    public static List<Staff> a = new ArrayList<>();
    public static final String FILENAME = "Staff.txt";   //Khai báo đường dẫn của tập tin(final:thể hiện sự vô sinh không cho phép thay đổi dữ liệu)

    public static void DocTapTin() throws FileNotFoundException, IOException, ClassNotFoundException {
        File fl = new File(FILENAME); // Dùng để kiểm tra nếu tập tin tồn tại thì mới đọc dữ liệu
        if (fl.exists() == true) {
            FileInputStream fIS = new FileInputStream(fl);//fileinputstream giống như luồng dữ liệu vào với nguồn từ file. nó tương đương scanner(system.in) là luồng vào từ hệ thống 
            ObjectInputStream oIS = new ObjectInputStream(fIS);//objectinputstream là dữ liệu nhập từ luồng file là kiểu object //giống như số nguyên là sc.nextint, chuỗi là sc.nextline                                                         
            List<Staff> data = (List<Staff>) oIS.readObject(); //Đọc toàn bộ dữ liệu ở tập tin
            a = new ArrayList<Staff>(data.size());
            a.addAll(data);
            oIS.close();
            fIS.close(); //Đóng luồng
        }
    }

    public static void GhiTapTin() throws FileNotFoundException, IOException {
        FileOutputStream fOS = new FileOutputStream(FILENAME);
        ObjectOutputStream oOS = new ObjectOutputStream(fOS);
        oOS.writeObject(a);    //cái cần ghi chính là mảng a;
        oOS.close();
        fOS.close();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Menu();
    }

    public static void Menu() throws FileNotFoundException, IOException, ClassNotFoundException {
        DocTapTin();
        int chon;
        do {
            System.out.println("                    |---------------Quản lý nhân viên--------------|");
            System.out.println("                    |-------------------Lựa chọn-------------------|");
            System.out.println("                    |1.Nhập thêm nhân viên                         |");
            System.out.println("                    |2.Cập nhật thông tin                          |");
            System.out.println("                    |3.Xoá nhân viên                               |");
            System.out.println("                    |4.In danh sách nhân viên                      |");
            System.out.println("                    |5.Tìm kiếm nhân viên                          |");
            System.out.println("                    |6.Tính lương cho NV                           |");
            System.out.println("                    |7.Sắp xếp hồ sơ                               |");
            System.out.println("                    |0.Thoát                                       |");
            System.out.println("                    |----------------------------------------------|");
            System.out.print("Mời bạn đưa ra lựa chọn: ");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    NhapNV();
                    break;
                case 2:
                    CapNhat();
                    break;
                case 3:
                    XoaNV();
                    break;
                case 4:
                    InNV();
                    break;
                case 5:
                    TimKiem();
                    break;
                case 6:
                    IHS();
                    break;
                case 7:
                    SapXep();
                    break;
            }
        } while (chon != 0);
    }

    public static int KiemTra(String mnv) {
        int kt = 1;
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).manv.equalsIgnoreCase(mnv)) {
                kt = 0;
                break;
            }
        }
        if (kt == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void NhapNV() throws IOException, FileNotFoundException, ClassNotFoundException {
        String mnv, ht, cv, dc, sdt;
        int ns, nc;
        sc.nextLine();
        do {
            System.out.print("Mã nhân viên: ");
            mnv = sc.nextLine();
            if (KiemTra(mnv) == 0) {
                System.out.println("Mã nhân viên đã tồn tại");
            }
        } while (KiemTra(mnv) == 0);
        System.out.print("Họ và tên: ");
        ht = sc.nextLine();
        System.out.print("Chức vụ(Leader/Bartender/Staff/Guard) : ");
        cv = sc.nextLine();
        System.out.print("Ngày công: ");
        nc = sc.nextInt();
        sc.nextLine();
        System.out.print("Địa chỉ: ");
        dc = sc.nextLine();
        System.out.print("Số điện thoại: ");
        sdt = sc.nextLine();
        System.out.print("Năm sinh: ");
        ns = sc.nextInt();
        Staff x = new Staff(mnv, ht, cv, nc, ns, dc, sdt);
        a.add(x);
        GhiTapTin(); //Ghi lại mảng mới sau khi đã thêm
        DocTapTin(); //Gán lại mảng để lấy lại dữ liệu đang làm việc
    }

    public static void InTieuDe() {
        System.out.printf("|%20s|%20s|%10s|%10s|%10s|%15s|%15s|", "Mã nhân viên", "Họ tên", "Chức vụ", "Ngày công", "Năm sinh", "Địa chỉ", "Số điện thoại");
    }

    public static void InNV() {
        System.out.println("Danh sách nhân viên là: ");
        InTieuDe();
        System.out.println();
        for (i = 0; i < a.size(); i++) {
            a.get(i).IHS();
            System.out.println();
        }
    }

    public static void TimKiem() {
        System.out.println("                    |-------------------Tìm Kiếm-------------------|");
        System.out.println("                    |1.Tìm kiếm theo tên(mã) nhân viên             |");
        System.out.println("                    |2.Tìm kiếm theo dịa chỉ                       |");
        System.out.println("                    |3.Tìm kiếm theo chức vụ                       |");
        System.out.println("                    |0.Thoát                                       |");
        System.out.println("                    |----------------------------------------------|");
        int c;
        System.out.print("Mời bạn chọn: ");
        c = sc.nextInt();
        switch (c) {
            case 1:
                TimKiemTen();
                break;
            case 2:
                TimKiemDC();
                break;
            case 3:
                TimKiemCV();
                break;
        }
    }

    public static void TimKiemTen() {
        String c;
        int kt = 0;
        sc.nextLine();
        System.out.print("Nhập tên(mã) nhân viên cần tìm: ");
        c = sc.nextLine();
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getHoten().equalsIgnoreCase(c) || a.get(i).getManv().equalsIgnoreCase(c)) {
                InTieuDe();
                System.out.println();
                kt = 1;
                break;
            }
        }
        if (kt == 0) {
            System.out.println("Không có nhân viên trên");
        } else {
            for (i = 0; i < a.size(); i++) {
                if (a.get(i).getHoten().equalsIgnoreCase(c) || a.get(i).getManv().equalsIgnoreCase(c)) {
                    a.get(i).IHS(); //equalsIgnorecase:so sánh 2 chuỗi không phân biệt hoa thường
                    System.out.println();
                }
            }
        }
    }

    public static void TimKiemDC() {
        String c;
        int kt = 0;
        sc.nextLine();
        System.out.print("Nhập địa chỉ cần tìm: ");
        c = sc.nextLine();
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getDiachi().equalsIgnoreCase(c)) {
                InTieuDe();
                System.out.println();
                kt = 1;
                break;
            }
        }
        if (kt == 0) {
            System.out.println("Không nhân viên có địa chỉ trên");
        } else {
            for (i = 0; i < a.size(); i++) {
                if (a.get(i).getDiachi().equalsIgnoreCase(c)) {
                    a.get(i).IHS(); //equalsIgnorecase:so sánh 2 chuỗi không phân biệt hoa thường
                    System.out.println();
                }
            }
        }
    }

    public static void TimKiemCV() {
        String c;
        int kt = 0;
        sc.nextLine();
        System.out.print("Nhập chức vụ cần tìm(Leader/Bartender/Staff/Guard): ");
        c = sc.nextLine();
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getChucvu().equalsIgnoreCase(c)) {
                InTieuDe();
                System.out.println();
                kt = 1;
                break;
            }
        }
        if (kt == 0) {
            System.out.println("Không nhân viên nào có chức vụ trên");
        } else {
            for (i = 0; i < a.size(); i++) {
                if (a.get(i).getChucvu().equalsIgnoreCase(c)) {
                    a.get(i).IHS();
                    System.out.println();
                }
            }
        }
    }

    public static void SapXep() {
        System.out.println("                    |-------------------Sắp Xếp--------------------|");
        System.out.println("                    |1.Săp xếp nhân viên theo năm sinh             |");
        System.out.println("                    |2.Sắp xếp nhân viên theo chức vụ              |");
        System.out.println("                    |3.Săp xếp nhân viên theo địa chỉ              |");
        System.out.println("                    |4.Sắp xếp nhân viên theo ngày công            |");
        System.out.println("                    |0.Thoát                                       |");
        System.out.println("                    |----------------------------------------------|");
        int c;
        System.out.print("Mời bạn chọn: ");
        c = sc.nextInt();
        switch (c) {
            case 1:
                SapXepNS();
                break;
            case 2:
                SapXepCV();
                break;
            case 3:
                SapXepDC();
                break;
            case 4:
                SapXepNC();
                break;
        }
    }

    public static void SapXepNS() {
        Staff[] b = new Staff[a.size()];
        for (i = 0; i < a.size(); i++) {
            b[i] = a.get(i);
        }
        for (i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (b[i].getNamsinh() < b[j].getNamsinh()) {
                    Staff tg = b[i];
                    b[i] = b[j];
                    b[j] = tg;
                }
            }
        }
        System.out.println("Sắp xếp theo năm sinh: ");
        InTieuDe();
        System.out.println();
        for (i = 0; i < a.size(); i++) {
            b[i].IHS();
            System.out.println();
        }
    }

    public static void SapXepCV() {
        System.out.println("Sắp xếp theo chức vụ: ");
        InTieuDe();
        System.out.println();
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getChucvu().equalsIgnoreCase("Leader")) {
                a.get(i).IHS();
                System.out.println();
            }
        }
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getChucvu().equalsIgnoreCase("Bartender")) {
                a.get(i).IHS();
                System.out.println();
            }
        }
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getChucvu().equalsIgnoreCase("Staff")) {
                a.get(i).IHS();
                System.out.println();
            }
        }
        for (i = 0; i < a.size(); i++) {
            if (a.get(i).getChucvu().equalsIgnoreCase("Guard")) {
                a.get(i).IHS();
                System.out.println();
            }
        }

    }

    public static void SapXepDC() {
        Staff[] b = new Staff[a.size()];
        for (i = 0; i < a.size(); i++) {
            b[i] = a.get(i);
        }
        for (i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (b[i].getDiachi().compareTo(b[j].getDiachi()) > 0) {
                    Staff tg = b[i];
                    b[i] = b[j];
                    b[j] = tg;
                }
            }
        }
        System.out.println("Săp xếp theo địa chỉ: ");
        InTieuDe();
        System.out.println();
        for (i = 0; i < a.size(); i++) {
            b[i].IHS();
            System.out.println();
        }
    }

    public static void SapXepNC() {
        Staff[] b = new Staff[a.size()];
        for (i = 0; i < a.size(); i++) {
            b[i] = a.get(i);
        }
        for (i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (b[i].getNgaycong() < b[j].getNgaycong()) {
                    Staff tg = b[i];
                    b[i] = b[j];
                    b[j] = tg;
                }
            }
        }
        System.out.println("Sắp xêp theo ngày công: ");
        InTieuDe();
        System.out.println();
        for (i = 0; i < a.size(); i++) {
            b[i].IHS();
            System.out.println();
        }
        System.out.println("Nhân viên ưu tú của tháng: ");
        for (i = 0; i < a.size(); i++) {
            if (b[i].getNgaycong() == b[0].getNgaycong()) {
                System.out.println(b[i].getHoten());
            }
        }
    }

    public static void CapNhat() throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("                    |-------------------Cập Nhật-------------------|");
        System.out.println("                    |1.Cập nhật thông tin nhân viên                |");
        System.out.println("                    |2.Cập nhật ngày công cho nhân viên            |");
        System.out.println("                    |0.Thoát                                       |");
        System.out.println("                    |----------------------------------------------|");
        int c;
        System.out.print("Mời bạn chọn: ");
        c = sc.nextInt();
        switch (c) {
            case 1:
                CapNhatNhanVien();
                break;
            case 2:
                CapNhatNgayCong();
                break;
        }
    }

    public static void CapNhatNhanVien() throws IOException, FileNotFoundException, ClassNotFoundException {
        String cn;
        sc.nextLine();
        System.out.print("Nhập mã(tên) nhân viên cần cập nhật: ");
        cn = sc.nextLine();
        for (i = 0; i < a.size(); i++) {
            if ((a.get(i).getManv().equalsIgnoreCase(cn)) || a.get(i).getHoten().equalsIgnoreCase(cn)) {
                System.out.println("Nhân viên cần cập nhật là: ");
                InTieuDe();
                System.out.println();
                a.get(i).IHS();
                System.out.println();
                System.out.println("Cập nhật lại nhân viên: ");
                String mnv, ht, cv, dc, sdt;
                int ns, nc;
                sc.nextLine();
                System.out.print("Mã nhân viên: ");
                mnv = sc.nextLine();
                System.out.print("Họ và tên: ");
                ht = sc.nextLine();
                System.out.print("Chức vụ(Leader/Bartender/Staff/Guard) : ");
                cv = sc.nextLine();
                System.out.print("Ngày công: ");
                nc = sc.nextInt();
                sc.nextLine();
                System.out.print("Địa chỉ: ");
                dc = sc.nextLine();
                System.out.print("Số điện thoại: ");
                sdt = sc.nextLine();
                System.out.print("Năm sinh: ");
                ns = sc.nextInt();
                a.get(i).manv = mnv;
                a.get(i).hoten = ht;
                a.get(i).chucvu = cv;
                a.get(i).setNgaycong(nc);
                a.get(i).setDiachi(dc);
                a.get(i).setSodienthoai(sdt);
                GhiTapTin();
                DocTapTin();
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên trên");
    }

    public static void CapNhatNgayCong() throws IOException, FileNotFoundException, ClassNotFoundException {
        int c;
        System.out.println("Cập nhật ngày công: ");
        for (i = 0; i < a.size(); i++) {
            System.out.print(a.get(i).getHoten() + ": ");
            c = sc.nextInt();
            a.get(i).setNgaycong(c);
        }
        GhiTapTin();
        DocTapTin();
    }

    public static void IHS() {
        System.out.println("Lương nhân viên :");
        System.out.printf("|%20s|%10s|%11s|\n", "Họ tên", "Ngày công", "Lương tháng");
        for (i = 0; i < a.size(); i++) {
            System.out.printf("|%20s|%10d|", a.get(i).getHoten(), a.get(i).getNgaycong());
            System.out.printf("%10d%s|\n", a.get(i).TinhLuong(), "$");
        }
    }

    public static void XoaNV() throws IOException, FileNotFoundException, ClassNotFoundException {
        String cn;
        sc.nextLine();
        System.out.print("Nhập mã(tên) nhân viên cần xoá: ");
        cn = sc.nextLine();
        for (i = 0; i < a.size(); i++) {
            if ((a.get(i).getManv().equalsIgnoreCase(cn)) || a.get(i).getHoten().equalsIgnoreCase(cn)) {
                System.out.println("Nhân viên cần xoá là: ");
                InTieuDe();
                System.out.println();
                a.get(i).IHS();
                System.out.println();
                String c;
                System.out.print("Bạn có muốn xoá hay không(Y/N): ");
                c = sc.nextLine();
                if (c.equalsIgnoreCase("Y")) {
                    a.remove(i);
                    System.out.println("Bạn đã xoá nhân viên thành công");
                    GhiTapTin();
                    DocTapTin();
                }
                return;
            }
        }
        System.out.println("Không tìm thấy nhân viên trên");
    }
}
