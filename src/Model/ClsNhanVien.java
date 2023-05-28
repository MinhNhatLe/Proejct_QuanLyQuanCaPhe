/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ClsNhanVien {
    private String nvMa, nvHoTen, nvQue, nvGioiTinh, nvSDT, nvCMND, nvTenDangNhap, nvMatKhau;

   
    private Date nvNgaySinh;
      
    public ClsNhanVien()    {
        nvMa = new String("NAM00");
        nvHoTen = new String("ADMIN");
        nvQue = new String("Cần Thơ");
        nvGioiTinh = new String("Nam");
        nvSDT = new String("1234567890");
        nvNgaySinh = new Date(2000-01-01);
       
    }

    public ClsNhanVien(String nvMa, String nvHoTen, String nvQue, String nvGioiTinh, String nvSDT, Date nvNgaySinh) {
        this.nvMa = nvMa;
        this.nvHoTen = nvHoTen;
        this.nvQue = nvQue;
        this.nvGioiTinh = nvGioiTinh;
        this.nvSDT = nvSDT;
        this.nvNgaySinh = nvNgaySinh;
        this.nvMatKhau = nvMatKhau;
        this.nvNgaySinh = nvNgaySinh;
    }

    public String getNvMa() {
        return nvMa;
    }

    public String getNvHoTen() {
        return nvHoTen;
    }

    public String getNvQue() {
        return nvQue;
    }

    public String getNvGioiTinh() {
        return nvGioiTinh;
    }

    public String getNvSDT() {
        return nvSDT;
    }

    public Date getNvNgaySinh() {
        return nvNgaySinh;
    }   

    public void setNvMa(String nvMa) {
        this.nvMa = nvMa;
    }

    public void setNvHoTen(String nvHoTen) {
        this.nvHoTen = nvHoTen;
    }

    public void setNvQue(String nvQue) {
        this.nvQue = nvQue;
    }

    public void setNvGioiTinh(String nvGioiTinh) {
        this.nvGioiTinh = nvGioiTinh;
    }

    public void setNvSDT(String nvSDT) {
        this.nvSDT = nvSDT;
    }

    public void setNvNgaySinh(Date nvNgaySinh) {
        this.nvNgaySinh = nvNgaySinh;
    }
    
     public String getNvCMND() {
        return nvCMND;
    }

    public void setNvCMND(String nvCMND) {
        this.nvCMND = nvCMND;
    }
    
    public String getNvTenDangNhap() {
        return nvTenDangNhap;
    }

    public void setNvTenDangNhap(String nvTenDangNhap) {
        this.nvTenDangNhap = nvTenDangNhap;
    }

    public String getNvMatKhau() {
        return nvMatKhau;
    }

    public void setNvMatKhau(String nvMatKhau) {
        this.nvMatKhau = nvMatKhau;
    }
   
}
