
package Controller;


import Model.ClsLoaiSanPham;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


    

public class DAO {
    
    public static Connection conn;
    String url = "jdbc:sqlserver://LAPTOP-AP54ORLO\\SQLEXPRESS:1433;databasename=QuanLiQuanCaPhe;" 
            + "username=sa;password=sa;encrypt=true;trustServerCertificate=true";
    
    
    
    public DAO(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);    
            System.out.println("ket noi thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
//    public ArrayList<ClsMenu> getListMenu(){
//        ArrayList<ClsMenu> list = new ArrayList<>();
//        String sql = "SELECT * FROM MENU";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            System.out.println("Ket noi thanh cong !");
//            while(rs.next()){
//                ClsMenu s = new ClsMenu();
//                s.setSpMa(rs.getString("SPMa"));
//                s.setSpLoai(rs.getString("SPLoai"));
//                s.setSpTen(rs.getString("SPTen"));
//                s.setSpDonVi(rs.getString("SPDonVi"));
//                s.setGia(rs.getLong("SPGia"));
//                list.add(s);
//                
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//    
//    public ArrayList<ClsNhanVien>getListNhanVien(){
//        ArrayList<ClsNhanVien> list = new ArrayList<>();
//        String sql = "Select* from NhanVien";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            System.out.println("Ket noi thanh cong !");
//            while(rs.next()){
//                ClsNhanVien s = new ClsNhanVien();
//                s.setNvMa(rs.getString("NVMa"));
//                s.setNvHoTen(rs.getString("NVHoTen"));
//                s.setNvGioiTinh(rs.getString("NVGioiTinh"));
//                s.setNvNgaySinh(rs.getDate("NVNgaySinh"));
//                s.setNvQue(rs.getString("NVQue"));
//                s.setNvSDT(rs.getString("NVSDT"));
//                s.setNvCMND(rs.getString("NVCMND"));
//                s.setNvTenDangNhap(rs.getString("NVTenDangNhap"));
//                s.setNvMatKhau(rs.getString("NVMatKhau"));
//                list.add(s);
//               
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    
//    public ArrayList<ClsThongKe> getListThongKe(){
//        ArrayList<ClsThongKe> list = new ArrayList<>();
//        String sql = "select* from ThongKe";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            System.out.println("Ket noi thanh cong !");
//            while(rs.next()){
//                ClsThongKe s = new ClsThongKe();
//                s.setTkBan(rs.getInt("TKBan"));
//                s.setTkNgay(rs.getString("TKNgay"));
//                s.setTkTenNV(rs.getString("NVHoTen"));
//                s.setTkThoiGian(rs.getString("TKThoiGian"));
//                s.setTkTongTien(rs.getInt("TKTongTien"));
//                s.setTkTienKhach(rs.getInt("TKTienKhach"));
//                s.setTkTienThua(rs.getInt("TKTienThua"));
//                s.setTkTongHD(rs.getInt("TKTongHD"));
//                s.setTkTongDoanhThu(rs.getInt("TKTongDoanhThu"));
//                list.add(s);
//               
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    
   
            
        

    
    
            
//    public boolean suaMenu(String maSP){
//            String sqlsua = "SELECT * FROM MENU";
//            try {
//                PreparedStatement ps = conn.prepareStatement(sqlsua);
//                ps.executeUpdate();
//                conn.close();
//                return true;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        return  false;
//    }
//    
//    
//
   
    public static void main(String[] args)  {
        new DAO(); 
    }
} 
 


