package com.Guis;

import com.Models.Student;
import com.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Guis.MainFrame.*;

public class ResetPass extends JPanel {
    private JLabel passOld, passNew, pass;
    private JPasswordField txtpass, txtpassnew, txtpassC;
    private JButton Save, Reset;

    public ResetPass() {
        createVar();
        SetLayout();

    }

    private void SetLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Box main = Box.createVerticalBox();
        Box subMain = Box.createVerticalBox();
        subMain.setBorder(BorderFactory.createTitledBorder("Đổi Mật Khẩu"));
        Box BorderMassv = Box.createHorizontalBox();
        Box BorderName = Box.createHorizontalBox();
        Box BorderCmnd = Box.createHorizontalBox();
        Box BorderButton = Box.createHorizontalBox();


        add(main);
        main.add(subMain);

        subMain.add(BorderMassv);
        subMain.add(Box.createVerticalStrut(5));
        BorderMassv.add(passOld);
        BorderMassv.add(Box.createRigidArea(new Dimension(8, 0)));
        BorderMassv.add(txtpass);
        txtpass.setMaximumSize(txtpass.getPreferredSize());

        subMain.add(BorderName);
        subMain.add(Box.createVerticalStrut(5));
        BorderName.add(passNew);
        BorderName.add(Box.createRigidArea(new Dimension(30, 0)));
        BorderName.add(txtpassnew);
        txtpassnew.setMaximumSize(txtpassnew.getPreferredSize());

        BorderCmnd.add(pass);
        BorderCmnd.add(Box.createRigidArea(new Dimension(20, 0)));
        BorderCmnd.add(txtpassC);
        txtpassC.setMaximumSize(txtpassC.getPreferredSize());
        subMain.add(BorderCmnd);
        subMain.add(Box.createVerticalStrut(5));

// button
        subMain.add(BorderButton);
        BorderButton.add(Save);
        BorderButton.add(Box.createRigidArea(new Dimension(10, 0)));
        BorderButton.add(Reset);
        BorderButton.add(Box.createRigidArea(new Dimension(10, 0)));

    }

    private void createVar() {
        passOld = new JLabel("Password Old:");
        passNew = new JLabel("         New:");
        pass = new JLabel("      Retype:");

        txtpass = new JPasswordField(20);
        txtpassC = new JPasswordField(20);
        txtpassnew = new JPasswordField(20);

        Save = new JButton("Lưu");
        Reset = new JButton("Làm mới");

        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtpass.setText("");
                txtpassC.setText("");
                txtpassnew.setText("");
            }
        });
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passcu = txtpass.getText();
                String passmoi = txtpassnew.getText();
                String passR = txtpassC.getText();
                if (listLogin.get(0).getPassword().equals(passcu)) {
                    if (passmoi.equals(passR)) {
                        if (Integer.parseInt(listLogin.get(0).getType()) == 1) {
                            for (Student item : listStudent) {
                                if (item.getMssv().equals(listLogin.get(0).getUserName())) {
                                    item.setPassword(passmoi);
                                    JOptionPane.showMessageDialog(null, "Đổi pass Thành công");
                                    handleFile.WriterFile(listStudent, handleFile.getFileStudent());
                                    resetInput();
                                    return;
                                }
                            }
                        }
                        if (Integer.parseInt(listLogin.get(0).getType()) == 0) {
                            for (User item : listUser) {
                                if (item.getName().equals(listLogin.get(0).getUserName())) {
                                    item.setPassword(passmoi);
                                    JOptionPane.showMessageDialog(null, "Đổi pass Thành công");
                                    handleFile.WriterFile(listUser, handleFile.getFileUser());
                                    resetInput();
                                    return;
                                }
                            }
                        }
                    }
                }

            }
        });

    }

    private void resetInput() {
        txtpass.setText("");
        txtpassC.setText("");
        txtpassnew.setText("");
    }
}
