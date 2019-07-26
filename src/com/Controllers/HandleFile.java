package com.Controllers;

import java.io.File;
import java.io.IOException;

public class HandleFile {
    private File fileStudent, fileSchedule, fileClass, fileUser, fileDiem;
    public  HandleFile() throws IOException {
        File dir = new File("File");
        if(!dir.exists()){
            dir.mkdir();
        }
        fileStudent = new File("File/Student.csv");
        if(!fileStudent.exists()){
            fileStudent.createNewFile();
        }

        fileSchedule = new File("File/Schedule.csv");
        if(!fileSchedule.exists()){
            fileSchedule.createNewFile();
        }
        fileClass = new File("File/Class.csv");
        if(!fileClass.exists()){
            fileClass.createNewFile();
        }
        fileUser = new File("File/User.csv");
        if(!fileUser.exists()){
            fileUser.createNewFile();
        }
        fileDiem = new File("File/Diem.csv");
        if(!fileDiem.exists()){
            fileDiem.createNewFile();
        }
    }

}
