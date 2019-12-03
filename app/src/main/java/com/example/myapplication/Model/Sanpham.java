package com.example.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sanpham  implements Serializable {

@SerializedName("ID")
@Expose
private Integer iD;
@SerializedName("TenSP")
@Expose
private String tenSP;
@SerializedName("GiaSP")
@Expose
private Integer giaSP;
@SerializedName("NgayGiamGia")
@Expose
private String ngayGiamGia;
@SerializedName("GiamGia")
@Expose
private Integer giamGia;
@SerializedName("HinhAnhSP")
@Expose
private String hinhAnhSP;
@SerializedName("MoTaSP")
@Expose
private String moTaSP;
@SerializedName("IDSP")
@Expose
private Integer iDSP;

public Integer getID() {
return iD;
}

public void setID(Integer iD) {
this.iD = iD;
}

public String getTenSP() {
return tenSP;
}

public void setTenSP(String tenSP) {
this.tenSP = tenSP;
}

public Integer getGiaSP() {
return giaSP;
}

public void setGiaSP(Integer giaSP) {
this.giaSP = giaSP;
}

public String getNgayGiamGia() {
return ngayGiamGia;
}

public void setNgayGiamGia(String ngayGiamGia) {
this.ngayGiamGia = ngayGiamGia;
}

public Integer getGiamGia() {
return giamGia;
}

public void setGiamGia(Integer giamGia) {
this.giamGia = giamGia;
}

public String getHinhAnhSP() {
return hinhAnhSP;
}

public void setHinhAnhSP(String hinhAnhSP) {
this.hinhAnhSP = hinhAnhSP;
}

public String getMoTaSP() {
return moTaSP;
}

public void setMoTaSP(String moTaSP) {
this.moTaSP = moTaSP;
}

public Integer getIDSP() {
return iDSP;
}

public void setIDSP(Integer iDSP) {
this.iDSP = iDSP;
}

}