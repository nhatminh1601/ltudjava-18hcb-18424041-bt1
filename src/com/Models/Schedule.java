package com.Models;

public class Schedule {
    private String IdSubject;
    private String IdClass;
    private String Subject;
    private String Classroom;

    public Schedule(String idSubject, String idClass, String subject, String classroom) {
        IdSubject = idSubject;
        IdClass = idClass;
        Subject = subject;
        Classroom = classroom;
    }

    public String getIdSubject() {
        return IdSubject;
    }

    public void setIdSubject(String idSubject) {
        IdSubject = idSubject;
    }

    public String getIdClass() {
        return IdClass;
    }

    public void setIdClass(String idClass) {
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

    @Override
    public String toString() {
        return IdSubject + "," + IdClass + "," + Subject + "," + Classroom;
    }
}
