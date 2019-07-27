package com.Models;

public class StudenOfSchedule {
    private String IdStudent;
    private String IdSchedule;
    private String name;
    private String sex;
    private String cmnd;
    private float diemGk;
    private float diemCk;
    private float diemKhac;
    private float diemTong;

    public StudenOfSchedule(String idStudent, String idSchedule, String name, String sex, String cmnd, float diemGk, float diemCk, float diemKhac, float diemTong) {
        IdStudent = idStudent;
        IdSchedule = idSchedule;
        this.name = name;
        this.sex = sex;
        this.cmnd = cmnd;
        this.diemGk = diemGk;
        this.diemCk = diemCk;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
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

    @Override
    public String toString() {
        return IdStudent + "," + IdSchedule + "," + name + "," +
                sex + "," + cmnd + "," + diemGk + "," +
                diemCk + "," + "," +
                diemKhac + "," +
                diemTong;
    }
}
