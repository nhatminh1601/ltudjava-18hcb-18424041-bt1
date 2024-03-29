package com.Models;

public class Student {
    private String Mssv;
    private String Name;
    private String IdClass;
    private String Cmnd;
    private String Sex;
    private String Password;

    public Student(String mssv, String name, String idClass, String cmnd, String sex, String password) {
        Mssv = mssv;
        Name = name;
        IdClass = idClass;
        Cmnd = cmnd;
        Sex = sex;
        Password = password;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        Mssv = mssv;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdClass() {
        return IdClass;
    }

    public void setIdClass(String idClass) {
        IdClass = idClass;
    }

    public String getCmnd() {
        return Cmnd;
    }

    public void setCmnd(String cmnd) {
        Cmnd = cmnd;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return Mssv + "," + Name + "," + IdClass + "," + Cmnd + "," + Sex + "," + Password;
    }
}
