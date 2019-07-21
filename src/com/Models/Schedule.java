package com.Models;

public class Schedule {
    private int Id;
    private String IdSubject;
    private int IdClass;
    private String Subject;
    private String Classroom;

    public Schedule(int id, String idSubject, int idClass, String subject, String classroom) {
        Id = id;
        IdSubject = idSubject;
        IdClass = idClass;
        Subject = subject;
        Classroom = classroom;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getIdSubject() {
        return IdSubject;
    }

    public void setIdSubject(String idSubject) {
        IdSubject = idSubject;
    }

    public int getIdClass() {
        return IdClass;
    }

    public void setIdClass(int idClass) {
        IdClass = idClass;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String classroom) {
        Classroom = classroom;
    }
}
