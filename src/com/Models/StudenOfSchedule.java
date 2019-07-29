package com.Models;

public class StudenOfSchedule {
    private String IdStudent;
    private String IdSchedule;
    private String name;
    private float diemGk;
    private float diemCk;
    private float diemKhac;
    private float diemTong;
    private String ketQua;

    public StudenOfSchedule(String idStudent, String idSchedule, String name, float diemGk, float diemCk, float diemKhac, float diemTong, String ketQua) {
        IdStudent = idStudent;
        IdSchedule = idSchedule;
        this.name = name;
        this.diemGk = diemGk;
        this.diemCk = diemCk;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
        this.ketQua = ketQua;
    }

    public String getIdStudent() {
        return IdStudent;
    }

    public void setIdStudent(String idStudent) {
        IdStudent = idStudent;
    }

    public String getIdSchedule() {
        return IdSchedule;
    }

    public void setIdSchedule(String idSchedule) {
        IdSchedule = idSchedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiemGk() {
        return diemGk;
    }

    public void setDiemGk(float diemGk) {
        this.diemGk = diemGk;
    }

    public float getDiemCk() {
        return diemCk;
    }

    public void setDiemCk(float diemCk) {
        this.diemCk = diemCk;
    }

    public float getDiemKhac() {
        return diemKhac;
    }

    public void setDiemKhac(float diemKhac) {
        this.diemKhac = diemKhac;
    }

    public float getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(float diemTong) {
        this.diemTong = diemTong;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    @Override
    public String toString() {
        return IdStudent + "," + IdSchedule + "," + name + ","
                + diemGk + "," +
                diemCk + "," + "," +
                diemKhac + "," +
                diemTong+","+ ketQua;
    }
}
