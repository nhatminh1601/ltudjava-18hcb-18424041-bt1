package com.Models;

public class Student {
    private int Id;
    private String Name;
    private String Mssv;
    private int IdClass;
    private String Cmnd;

    public Student(int id, String name, String mssv, int idClass, String cmnd) {
        Id = id;
        Name = name;
        Mssv = mssv;
        IdClass = idClass;
        Cmnd = cmnd;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMssv() {
        return Mssv;
    }

    public void setMssv(String mssv) {
        Mssv = mssv;
    }

    public int getIdClass() {
        return IdClass;
    }

    public void setIdClass(int idClass) {
        IdClass = idClass;
    }

    public String getCmnd() {
        return Cmnd;
    }

    public void setCmnd(String cmnd) {
        Cmnd = cmnd;
    }
}
