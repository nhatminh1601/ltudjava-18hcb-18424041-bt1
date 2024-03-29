package com.Controllers;

import com.Models.*;
import com.Models.Class;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.Guis.MainFrame.classNameS;
import static com.Guis.MainFrame.listScores;


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
        if (name == fileUser) {

        }
        Object Data, temp;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(name));
            while ((temp = reader.readLine()) != null) {
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
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileStudent), "UTF8"));
            while ((temp = reader.readLine().replace("\uFEFF", "")) != null) {
                String[] Data = ((String) temp).split(",");
                listStudent.add(new Student(Data[0], Data[1], Data[2], Data[3], Data[4], Data[5]));
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

    public Boolean ReadWriteFileImStudent(String path) {
        File file = new File(path);
        listStudent = new ArrayList<>();
        listStudent = ReadFileStudent();
        listClass = new ArrayList<>();
        listClass = ReadFileClass();
        ArrayList<Student> listImStudent = new ArrayList<>();
        ArrayList<Class> listImClass = new ArrayList<>();
        BufferedReader reader = null;
        Object temp, lop;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String[] malop = ((String) reader.readLine()).replace("\uFEFF", "").split(",");
            classNameS = malop[0];
            listImClass.add(new Class(malop[0], malop[0]));
            reader.readLine();
            System.out.println("pass");
            while ((temp = reader.readLine()) != null) {
                System.out.println(temp);
                String[] Data = ((String) temp).split(",");
                listImStudent.add(new Student(Data[1], Data[2], malop[0], Data[4], Data[3], Data[1]));
            }

            for (Class item : listClass) {
                for (Class n : listImClass) {
                    if (n.getId().trim().equals(item.getName())) {
                        listImClass.remove(n);
                        break;
                    }
                }

            }

            listClass.addAll(listImClass);
            WriterFile(listClass, fileClass);

            for (Student item : listStudent) {
                for (Student n : listImStudent) {
                    if (n.getMssv().trim().equals(item.getMssv())) {
                        listImStudent.remove(n);
                        break;
                    }
                }
            }
            listStudent.addAll(listImStudent);
            WriterFile(listStudent, fileStudent);
            reader.close();
            return true;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Boolean ReadWriteFileImSchedule(String path) {
        File file = new File(path);
        listSchedule = new ArrayList<>();
        listSchedule = ReadFileSchedule();
        listScores = new ArrayList<>();
        listScores = ReadFileDiem();
        listStudent = ReadFileStudent();

        ArrayList<Schedule> listImSchedule = new ArrayList<>();
        ArrayList<StudenOfSchedule> listImScores = new ArrayList<>();
        ArrayList<Student> listStudentTemp = new ArrayList<>();
        ArrayList<Class> listImClass = new ArrayList<>();
        BufferedReader reader = null;
        Object temp;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String[] malop = ((String) reader.readLine()).replace("\uFEFF", "").split(",");
            classNameS = malop[0];
            listImClass.add(new Class(malop[0], malop[0]));
            reader.readLine();
            System.out.println("passlop");
            while ((temp = reader.readLine()) != null) {
                System.out.println(temp);
                String[] Data = ((String) temp).split(",");
                System.out.println(Data[0] + "-" + Data[1] + "-" + Data[2] + "-" + Data[3]);
                listImSchedule.add(new Schedule(Data[1], malop[0], Data[2], Data[3]));
            }

            // import lop
            for (Class item : listClass) {
                for (Class n : listImClass) {
                    if (n.getId().trim().equals(item.getName())) {
                        listImClass.remove(n);
                        break;
                    }
                }

            }

            listClass.addAll(listImClass);
            WriterFile(listClass, fileClass);
            // danh sách sinh viên
            for (Student item : listStudent) {
                if (item.getIdClass().equals(malop[0])) {
                    listStudentTemp.add(item);
                }
            }

            // import thời khóa biểu
            for (Schedule item : listSchedule) {
                for (Schedule n : listImSchedule) {
                    System.out.println(item.getIdSubject());
                    if (n.getIdSubject().equals(item.getIdSubject())) {
                        System.out.println(n.getIdSubject() + " --test remo list schedule");
                        listImSchedule.remove(n);
                        break;
                    }
                }
            }
            listSchedule.addAll(listImSchedule);
            WriterFile(listSchedule, fileSchedule);

            for (Schedule item : listImSchedule) {
                for (Student sv : listStudentTemp) {
                    System.out.println(" import điểm" + sv.getMssv());
                    listImScores.add(new StudenOfSchedule(sv.getMssv(),
                            item.getIdSubject(), item.getSubject(), sv.getName(), 0, 0, 0, 0,0));
                }
            }
            //status 0 chưa có điểm , 1 có điểm
            listScores.addAll(listImScores);
            WriterFile(listScores, fileDiem);

            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;

    }
    public Boolean ReadWriteFileImScores(String path) {
        File file = new File(path);
        listSchedule = new ArrayList<>();
        listSchedule = ReadFileSchedule();
        listScores = new ArrayList<>();
        listScores = ReadFileDiem();
        listStudent = ReadFileStudent();

        ArrayList<StudenOfSchedule> listImScores = new ArrayList<>();
        ArrayList<Class> listImClass = new ArrayList<>();
        BufferedReader reader = null;
        Object temp, temp2;
        String maMonHoc="";

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String[] malop = ((String) reader.readLine()).replace("\uFEFF", "").split(",");
            String[] list= malop[0].split("-");
            classNameS = list[0];
            maMonHoc= list[1];
            listImClass.add(new Class(list[0], list[0]));
            reader.readLine();
            System.out.println("passlop");
            while ((temp = reader.readLine()) != null) {
                System.out.println(temp);
                String[] Data = ((String) temp).split(",");
                System.out.println(Data[0] + "-" + Data[1] + "-" + Data[2] + "-" + Data[3]);
                listImScores.add(new StudenOfSchedule(Data[1],maMonHoc,"",Data[2]
                ,Float.parseFloat(Data[3]),Float.parseFloat(Data[4]),Float.parseFloat(Data[5]),
                        Float.parseFloat(Data[6]),1));
            }

            // import lop
            for (Class item : listClass) {
                for (Class n : listImClass) {
                    if (n.getId().trim().equals(item.getName())) {
                        listImClass.remove(n);
                        break;
                    }
                }

            }

            listClass.addAll(listImClass);
            WriterFile(listClass, fileClass);


            for (StudenOfSchedule d : listImScores) {
                int flag=0;
                for (StudenOfSchedule item : listScores) {
                    System.out.println(" import điểm" + d.getDiemTong());
                   if(item.getIdStudent().trim().equals(d.getIdStudent().trim()) && item.getIdSchedule().trim().equals(d.getIdSchedule().trim())){
                       item.setDiemGk(d.getDiemGk());
                       item.setDiemCk(d.getDiemCk());
                       item.setDiemKhac(d.getDiemKhac());
                       item.setDiemTong(d.getDiemTong());
                       item.setStatus(1);
                       flag=1;
                   }
                }
                if(flag==0){
                    listScores.add(d);
                }
            }
            //status 0 chưa có điểm , 1 có điểm
            //listScores.addAll(listImScores);
            WriterFile(listScores, fileDiem);

            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;

    }
    public ArrayList<com.Models.Class> ReadFileClass() {
        listClass = new ArrayList<com.Models.Class>();
        BufferedReader reader = null;
        Object temp;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileClass), "UTF8"));
            while ((temp = reader.readLine().replace("\uFEFF", "")) != null) {

                String[] Data = ((String) temp).split(",");
                System.out.println(Data[1]);
                listClass.add(new com.Models.Class(Data[0], Data[1]));
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
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileSchedule), "UTF8"));
            while ((temp = reader.readLine().replace("\uFEFF", "")) != null) {
                String[] Data = ((String) temp).split(",");
                listSchedule.add(new Schedule(Data[0], Data[1], Data[2], Data[3]));
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
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileUser), "UTF8"));
            while ((temp = reader.readLine().replace("\uFEFF", "")) != null) {
                String[] Data = ((String) temp).split(",");
                listUser.add(new User(Integer.parseInt(Data[0]), Data[1], Data[2]));
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
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileLogin), "UTF8"));
            while ((temp = reader.readLine()) != null) {
                String[] Data = ((String) temp).split(",");
                listLogin.add(new Login(Data[0], Data[1], Data[2]));
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
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileDiem), "UTF8"));
            while ((temp = reader.readLine().replace("\uFEFF", "")) != null) {
                String[] Data = ((String) temp).split(",");
                ListScores.add(new StudenOfSchedule(Data[0], Data[1], Data[2], Data[3],
                        Float.parseFloat(Data[4]), Float.parseFloat(Data[5]), Float.parseFloat(Data[6]),
                        Float.parseFloat(Data[7]),Integer.parseInt(Data[8])));
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

    public Boolean WriterFile(ArrayList<?> Data, File Writer) {
        BufferedWriter writerFile = null;
        try {
            writerFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Writer), StandardCharsets.UTF_8));
            int sz = Data.size();
            for (int i = 0; i < sz; i++) {
                writerFile.write(Data.get(i).toString() + "\n");
            }
            writerFile.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean WriterFileStudent(ArrayList<Student> Data) {
        BufferedWriter writerFile = null;
        try {
            writerFile = new BufferedWriter(new FileWriter(fileStudent));
            int sz = Data.size();
            for (int i = 0; i < sz; i++) {
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
