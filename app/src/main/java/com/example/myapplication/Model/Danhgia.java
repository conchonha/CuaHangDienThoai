package com.example.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Danhgia {

    @SerializedName("Id_DanGgia")
    @Expose
    private Integer idDanGgia;
    @SerializedName("namsao")
    @Expose
    private Integer _5sao;
    @SerializedName("bonsao")
    @Expose
    private Integer _4sao;
    @SerializedName("basao")
    @Expose
    private Integer _3sao;
    @SerializedName("haisao")
    @Expose
    private Integer _2sao;
    @SerializedName("motsao")
    @Expose
    private Integer _1sao;
    @SerializedName("ComMent")
    @Expose
    private String comMent;
    @SerializedName("Id_SanPham")
    @Expose
    private Integer idSanPham;
    @SerializedName("username")
    @Expose
    private String idUser;
    @SerializedName("NgayDanhGia")
    @Expose
    private String ngayDanhGia;

    public Integer getIdDanGgia() {
        return idDanGgia;
    }

    public void setIdDanGgia(Integer idDanGgia) {
        this.idDanGgia = idDanGgia;
    }

    public Integer get5sao() {
        return _5sao;
    }

    public void set5sao(Integer _5sao) {
        this._5sao = _5sao;
    }

    public Integer get4sao() {
        return _4sao;
    }

    public void set4sao(Integer _4sao) {
        this._4sao = _4sao;
    }

    public Integer get3sao() {
        return _3sao;
    }

    public void set3sao(Integer _3sao) {
        this._3sao = _3sao;
    }

    public Integer get2sao() {
        return _2sao;
    }

    public void set2sao(Integer _2sao) {
        this._2sao = _2sao;
    }

    public Integer get1sao() {
        return _1sao;
    }

    public void set1sao(Integer _1sao) {
        this._1sao = _1sao;
    }

    public String getComMent() {
        return comMent;
    }

    public void setComMent(String comMent) {
        this.comMent = comMent;
    }

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(String ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }
}