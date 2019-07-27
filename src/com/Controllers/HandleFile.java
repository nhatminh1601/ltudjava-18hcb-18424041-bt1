package com.Controllers;

import java.io.*;
import java.util.ArrayList;

public class HandleFile {
    private File fileStudent, fileSchedule, fileClass, fileUser, fileDiem;
    private ArrayList<Object> Result;

    public HandleFile() throws IOException {
        File dir = new File("File");
        if (!dir.exists()) {
            dir.mkdir();
        }
        fileStudent = new File("File/Student.csv");
        if (!fileStudent.exists()) {
            fileStudent.createNewFile();
        }

        fileSchedule = new File("File/Schedule.csv");
        if (!fileSchedule.exists()) {
            fileSchedule.createNewFile();
        }
        fileClass = new File("File/Class.csv");
        if (!fileClass.exists()) {
            fileClass.createNewFile();
        }
        fileUser = new File("File/User.csv");
        if (!fileUser.exists()) {
            fileUser.createNewFile();
        }
        fileDiem = new File("File/Diem.csv");
        if (!fileDiem.exists()) {
            fileDiem.createNewFile();
        }
    }

    public ArrayList<?> ReadFile(File name) {
        Result = new ArrayList<>();
        Object Data;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(name));
            while ((Data = reader.readLine()) != null) {
                Result.add(Data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return Result;
    }
    public Boolean WriterFile(ArrayList<?> Data, File Writer){
        BufferedWriter writerFile= null;
        try {
            writerFile = new BufferedWriter(new FileWriter(Writer));
            int sz = Data.size();
            for(int i = 0; i < sz; i++){
                writerFile.write(Data.get(i).toString() + "\n");
            }
            writerFile.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public File getFileStudent() {
        return fileStudent;
    }

    public void setFileStudent(File fileStudent) {
        this.fileStudent = fileStudent;
    }

    public File getFileSchedule() {
        return fileSchedule;
    }

    public void setFileSchedule(File fileSchedule) {
        this.fileSchedule = fileSchedule;
    }

    public File getFileClass() {
        return fileClass;
    }

    public void setFileClass(File fileClass) {
        this.fileClass = fileClass;
    }

    public File getFileUser() {
        return fileUser;
    }

    public void setFileUser(File fileUser) {
        this.fileUser = fileUser;
    }

    public File getFileDiem() {
        return fileDiem;
    }

    public void setFileDiem(File fileDiem) {
        this.fileDiem = fileDiem;
    }

    public ArrayList<Object> getResult() {
        return Result;
    }

    public void setResult(ArrayList<Object> result) {
        Result = result;
    }
}
