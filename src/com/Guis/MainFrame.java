package com.Guis;
import com.Controllers.HandleFile;
import com.Models.*;
import com.Models.Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private LoginDialog loginDialog;
    private JFileChooser fileChooser;
    public static HandleFile handleFile;

    public static ArrayList<Class> listClass;
    public static ArrayList<Student> listStudent;
    public static ArrayList<Schedule> listSchedule;
    public static ArrayList<StudenOfSchedule> listScores;
    public static ArrayList<Login> listLogin;
    public static ArrayList<User> listUser;

    private Toolbar toolbar;
    public static ManagerStudent managerStudent;
    public static ManagerSchedule managerSchedule;
    public static ManagerScores managerScores;
    public  MainFrame() {
        processVar();
        setTitle("Quản Lý Sinh Viên");
        setMinimumSize(new Dimension(700, 400));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        AddConponent();
        loginDialog = new LoginDialog(this);
        //loginDialog.setVisible(true);
        // add component


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
    }

    private void AddConponent() {
        add(managerStudent,BorderLayout.CENTER);
       // add(managerSchedule,BorderLayout.CENTER);
        add(toolbar,BorderLayout.NORTH);
        toolbar = new Toolbar(this);


       // managerStudent.setVisible(true);
        //add(managerSchedule,BorderLayout.CENTER);



    }

    private void processVar() {
        try {
            handleFile = new HandleFile();
            listUser = handleFile.ReadFileUser();
            listClass = handleFile.ReadFileClass();
            listScores = handleFile.ReadFileDiem();
            listStudent= handleFile.ReadFileStudent();
            listSchedule = handleFile.ReadFileSchedule();
            listLogin = handleFile.ReadFileLogin();

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileChooser = new JFileChooser();
        toolbar = new Toolbar(this);
        managerStudent = new ManagerStudent();
        managerSchedule = new ManagerSchedule();
        managerScores = new ManagerScores();


    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu windowMenu = new JMenu("Window");

        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu showmenu= new JMenu("show");
        JMenuItem showFormItem= new JMenuItem("Person Form");
        showmenu.add(showFormItem);
        windowMenu.add(showmenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_E);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action=  JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit the application?",
                        "Comfirm Exit",JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                    System.exit(0);
                }

            }
        });
        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    String filePath = fileChooser.getSelectedFile().getPath();
                    File file = new File(filePath);
                  ArrayList<?> data= handleFile.ReadFile(file);
                  handleFile.WriterFile(data,handleFile.getFileClass());
                }
            }
        });
        return menuBar; 
    }
    public void SwichPanels(){
        //LayoutMater= (BorderLayout) this.getContentPane().getLayout();
       // this.remove(LayoutMater.getLayoutComponent(BorderLayout.CENTER));
    }

}
