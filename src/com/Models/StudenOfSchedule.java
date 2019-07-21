package com.Models;

public class StudenOfSchedule {
    private int IdStudent;
    private int IdSchedule;

    public StudenOfSchedule(int idStudent, int idSchedule) {
        IdStudent = idStudent;
        IdSchedule = idSchedule;
    }

    public int getIdStudent() {
        return IdStudent;
    }

    public void setIdStudent(int idStudent) {
        IdStudent = idStudent;
    }

    public int getIdSchedule() {
        return IdSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        IdSchedule = idSchedule;
    }
}
