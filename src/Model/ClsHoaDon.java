/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Administrator
 */
public  class ClsHoaDon {
    String ban, hoTenNV, ngay, thoigian, tenSP[];
    int tongTien[], tienKhach, tienThua, soLuong[];
    int tienTongCong;

    public int getTienTongCong() {
        return tienTongCong;
    }

    public void setTienTongCong(int tienTongCong) {
        this.tienTongCong = tienTongCong;
    }
 
    public ClsHoaDon() {
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String[] getTenSP() {
        return tenSP;
    }

    public void setTenSP(String[] tenSP) {
        this.tenSP = tenSP;
    }

    public int[] getTongTien() {
        return tongTien;
    }

    public void setTongTien(int[] tongTien) {
        this.tongTien = tongTien;
    }

    public int getTienKhach() {
        return tienKhach;
    }

    public void setTienKhach(int tienKhach) {
        this.tienKhach = tienKhach;
    }

    public int getTienThua() {
        return tienThua;
    }

    public void setTienThua(int tienThua) {
        this.tienThua = tienThua;
    }

    public int[] getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int[] soLuong) {
        this.soLuong = soLuong;
    }

    
    
}
