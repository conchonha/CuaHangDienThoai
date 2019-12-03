package com.example.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonDatHang {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("tentaikhoan")
@Expose
private String tentaikhoan;
@SerializedName("email")
@Expose
private String email;
@SerializedName("diachi")
@Expose
private String diachi;
@SerializedName("sodienthoai")
@Expose
private String sodienthoai;
@SerializedName("ngaydat")
@Expose
private String ngaydat;
@SerializedName("trinhtrang")
@Expose
private String trinhtrang;
@SerializedName("idtaikhoan")
@Expose
private Integer idtaikhoan;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getTentaikhoan() {
return tentaikhoan;
}

public void setTentaikhoan(String tentaikhoan) {
this.tentaikhoan = tentaikhoan;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getDiachi() {
return diachi;
}

public void setDiachi(String diachi) {
this.diachi = diachi;
}

public String getSodienthoai() {
return sodienthoai;
}

public void setSodienthoai(String sodienthoai) {
this.sodienthoai = sodienthoai;
}

public String getNgaydat() {
return ngaydat;
}

public void setNgaydat(String ngaydat) {
this.ngaydat = ngaydat;
}

public String getTrinhtrang() {
return trinhtrang;
}

public void setTrinhtrang(String trinhtrang) {
this.trinhtrang = trinhtrang;
}

public Integer getIdtaikhoan() {
return idtaikhoan;
}

public void setIdtaikhoan(Integer idtaikhoan) {
this.idtaikhoan = idtaikhoan;
}

}