package com.Models;

public class StudenOfSchedule {
    private String IdStudent;
    private String IdSchedule;
    private String subject;
    private String name;
    private float diemGk;
    private float diemCk;
    private float diemKhac;
    private float diemTong;

    public StudenOfSchedule(String idStudent, String idSchedule, String subject, String name, float diemGk, float diemCk, float diemKhac, float diemTong) {
        IdStudent = idStudent;
        IdSchedule = idSchedule;
        this.subject = subject;
        this.name = name;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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


    @Override
    public String toString() {
        return IdStudent + "," + IdSchedule + "," + name + ","
                + diemGk + "," +
                diemCk + "," + "," +
                diemKhac + "," +
                diemTong;
    }
}
