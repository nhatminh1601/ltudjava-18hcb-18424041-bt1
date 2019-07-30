package com.Controllers;

import com.Models.*;
import com.Models.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HandleFile<T> {
    private File fileStudent, fileSchedule, fileClass, fileUser, fileDiem, fileLogin;
    private ArrayList<Object> Result;
    private ArrayList<User> listUser;
    private ArrayList<Class> listClass;
    private ArrayList<Schedule> listSchedule;
    private ArrayList<Student> listStudent;
    private ArrayList<Login> listLogin;
    private ArrayList<StudenOfSchedule> ListScores;


    public HandleFile() throws IOException {
        File dir = new File("File");
        if (!dir.exists()) {
            dir.mkdir();
        }
        fileStudent = new File("File/Student.csv");
        if (!fileStudent.exists()) {
            fileStudent.createNewFile();
        }
        fileLogin = new File("File/Login.csv");
        if (!fileLogin.exists()) {
            fileLogin.createNewFile();
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
        if(name == fileUser){

        }
        Object Data,temp;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(name));
            while ((temp=reader.readLine()) != null) {
                Data = ((String) temp).split(",");
                Result.add(Arrays.asList(Data));
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
            return Result;
        }

    }
    public ArrayList<Student> ReadFileStudent() {
        listStudent = new ArrayList<>();
        BufferedReader reader = null;
        Object temp;
        try {
            reader = new BufferedReader(new FileReader(fileStudent));
            while ((temp=reader.readLine()) != null) {
                String[] Data = ((String) temp).split(",");
                listStudent.add(new Student(Data[0],Data[1],Data[2],Data[3],Data[4],Data[5]));
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
            return listStudent;
        }

    }
    public ArrayList<com.Models.Class> ReadFileClass() {
        listClass = new ArrayList<com.Models.Class>();
        BufferedReader reader = null;
        Object temp;
        try {
            reader = new BufferedReader(new FileReader(fileClass));
            while ((temp=reader.readLine()) != null) {
                String[] Data = ((String) temp).split(",");
                listClass.add(new com.Models.Class(Data[0],Data[1]));
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
            return listClass;
        }

    }

    public ArrayList<Schedule> ReadFileSchedule() {
        listSchedule = new ArrayList<>();
        BufferedReader reader = null;
        Object temp;
        try {
            reader = new BufferedReader(new FileReader(fileSchedule));
            while ((temp=reader.readLine()) != null) {
                String[] Data = ((String) temp).split(",");
                listSchedule.add(new Schedule(Data[0],Data[1],Data[2],Data[3]));
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
            return listSchedule;
        }

    }
    public ArrayList<User> ReadFileUser() {
        listUser = new ArrayList<>();
        BufferedReader reader = null;
        Object temp;
        try {
            reader = new BufferedReader(new FileReader(fileUser));
            while ((temp=reader.readLine()) != null) {
                String[] Data = ((String) temp).split(",");
                listUser.add(new User(Integer.parseInt(Data[0]),Data[1],Data[2]));
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
            return listUser;
        }

    }
    public ArrayList<Login> ReadFileLogin() {
        listLogin = new ArrayList<>();
        BufferedReader reader = null;
        Object temp;
        try {
            reader = new BufferedReader(new FileReader(fileLogin));
            while ((temp=reader.readLine()) != null) {
                String[] Data = ((String) temp).split(",");
                listLogin.add(new Login(Data[0],Data[1],Data[2]));
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
            return listLogin;
        }

    }
    public ArrayList<StudenOfSchedule> ReadFileDiem() {
        ListScores = new ArrayList<>();
        BufferedReader reader = null;
        Object temp;
        try {
            reader = new BufferedReader(new FileReader(fileDiem));
            while ((temp=reader.readLine()) != null) {
                String[] Data = ((String) temp).split(",");
                ListScores.add(new StudenOfSchedule(Data[0],Data[1],Data[2],Data[3],
                        Float.parseFloat(Data[4]),Float.parseFloat(Data[5]),Float.parseFloat(Data[6]),
                        Float.parseFloat(Data[7])));
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
            return ListScores;
        }

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
    public Boolean WriterFileStudent(ArrayList<Student> Data){
        BufferedWriter writerFile= null;
        try {
            writerFile = new BufferedWriter(new FileWriter(fileStudent));
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
}
