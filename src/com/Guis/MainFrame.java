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
    public static StudentLayout studentLayout;
    public static ResetPass resetPass;

    public static int keyType=0; // keyType =0 là giáo vụ, keyType =1 là sinh viên
    public static int flag = 0;
    public static String classNameS="";

    public MainFrame() {
        processVar();
        setTitle("Quản Lý Sinh Viên");
        setMinimumSize(new Dimension(700, 400));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        if (flag == 0) {
            loginDialog = new LoginDialog(this);
        }
        AddConponent();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (keyType == 0) {
            flag = 1;
            setJMenuBar(createMenuBar());
        }
        if (keyType == 1) {
            flag = 1;
            setJMenuBar(menusv());
        }

    }

    private void AddConponent() {
        if (keyType == 0) {
            add(managerStudent, BorderLayout.CENTER);
            add(toolbar, BorderLayout.NORTH);
            toolbar = new Toolbar(this);
        }
        if (keyType == 1) {
            add(studentLayout, BorderLayout.CENTER);
        }

    }

    private void processVar() {
        try {
            handleFile = new HandleFile();
            listUser = handleFile.ReadFileUser();
            listClass = handleFile.ReadFileClass();
            listScores = handleFile.ReadFileDiem();
            listStudent = handleFile.ReadFileStudent();
            listSchedule = handleFile.ReadFileSchedule();
            if (flag == 0) {
                listLogin = handleFile.ReadFileLogin();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        fileChooser = new JFileChooser();
        toolbar = new Toolbar(this);
        managerStudent = new ManagerStudent();
        managerSchedule = new ManagerSchedule();
        managerScores = new ManagerScores();
        resetPass = new ResetPass();


    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem windowMenu = new JMenuItem("Change Password");

        JMenuItem exStudenItem = new JMenuItem("Import Student");
        JMenuItem exScoresItem = new JMenuItem("Import Scores");
        JMenuItem exScheduleItem = new JMenuItem("Import Schedule");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exStudenItem);
        fileMenu.add(exScoresItem);
        fileMenu.add(exScheduleItem);
        fileMenu.addSeparator();
        fileMenu.add(windowMenu);
        fileMenu.add(exitItem);


        menuBar.add(fileMenu);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_E);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit the application?",
                        "Comfirm Exit", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }

            }
        });
        exStudenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getPath();
                    if (handleFile.ReadWriteFileImStudent(filePath)) {
                        managerStudent = new ManagerStudent();
                        managerSchedule = new ManagerSchedule();
                        managerScores = new ManagerScores();
                        BorderLayout LayoutMater= (BorderLayout) getContentPane().getLayout();
                        remove(LayoutMater.getLayoutComponent(BorderLayout.CENTER));
                        if(keyType==0 || keyType ==1){
                            add(managerStudent,BorderLayout.CENTER);
                        }
                        if(keyType==2){
                            add(managerSchedule,BorderLayout.CENTER);
                        }
                        if(keyType==3){
                            add(managerScores,BorderLayout.CENTER);
                        }

                        invalidate();
                        validate();
                        repaint();
                        JOptionPane.showMessageDialog(null, "Import Thành công !");

                    } else {
                        JOptionPane.showMessageDialog(null, "Import Thất bại công !");
                    }
                    repaint();
                }
            }
        });
        windowMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorderLayout LayoutMater = (BorderLayout) getContentPane().getLayout();
                remove(LayoutMater.getLayoutComponent(BorderLayout.CENTER));
                add(resetPass, BorderLayout.CENTER);
                invalidate();
                validate();
                repaint();
            }
        });
        return menuBar;
    }

    private JMenuBar menusv() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem windowMenu = new JMenuItem("Change Password");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(windowMenu);
        fileMenu.add(exitItem);


        menuBar.add(fileMenu);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_E);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit the application?",
                        "Comfirm Exit", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }

            }
        });
        windowMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorderLayout LayoutMater = (BorderLayout) getContentPane().getLayout();
                remove(LayoutMater.getLayoutComponent(BorderLayout.CENTER));
                add(resetPass, BorderLayout.CENTER);
                invalidate();
                validate();
                repaint();
            }
        });
        return menuBar;
    }

}
