package com.Guis;

import com.Controllers.HandleFile;

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
    private HandleFile handleFile;

    private Toolbar toolbar;
    private ManagerStudent managerStudent;
    public MainFrame() {
        processVar();
        try {
            handleFile = new HandleFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("Quản Lý Sinh Viên");
        setMinimumSize(new Dimension(700, 400));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginDialog = new LoginDialog(this);
        loginDialog.setVisible(true);
        // add component
        AddConponent();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
    }

    private void AddConponent() {
        add(toolbar,BorderLayout.NORTH);
        add(managerStudent.getMain(),BorderLayout.CENTER);
    }

    private void processVar() {
        fileChooser = new JFileChooser();
        toolbar = new Toolbar();
        managerStudent = new ManagerStudent();
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

}
