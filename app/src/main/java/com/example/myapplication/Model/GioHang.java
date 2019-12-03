package com.example.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GioHang {

@SerializedName("idgiohang")
@Expose
private Integer idgiohang;
@SerializedName("idsanpham")
@Expose
private Integer idsanpham;
@SerializedName("idtaikhoan")
@Expose
private String idtaikhoan;
@SerializedName("tensp")
@Expose
private String tensp;
@SerializedName("hinhsp")
@Expose
private String hinhsp;
@SerializedName("soluong")
@Expose
private Integer soluong;
@SerializedName("gia")
@Expose
private Integer gia;

public Integer getIdgiohang() {
return idgiohang;
}

public void setIdgiohang(Integer idgiohang) {
this.idgiohang = idgiohang;
}

public Integer getIdsanpham() {
return idsanpham;
}

public void setIdsanpham(Integer idsanpham) {
this.idsanpham = idsanpham;
}

public String getIdtaikhoan() {
return idtaikhoan;
}

public void setIdtaikhoan(String idtaikhoan) {
this.idtaikhoan = idtaikhoan;
}

public String getTensp() {
return tensp;
}

public void setTensp(String tensp) {
this.tensp = tensp;
}

public String getHinhsp() {
return hinhsp;
}

public void setHinhsp(String hinhsp) {
this.hinhsp = hinhsp;
}

public Integer getSoluong() {
return soluong;
}

public void setSoluong(Integer soluong) {
this.soluong = soluong;
}

public Integer getGia() {
return gia;
}

public void setGia(Integer gia) {
this.gia = gia;
}

}