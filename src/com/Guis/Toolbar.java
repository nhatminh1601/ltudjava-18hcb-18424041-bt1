package com.Guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Guis.MainFrame.*;

public class Toolbar extends JPanel {
    private JButton qlsv;
    private JButton dmmh;
    private JButton dsl;
    private JButton logOut;
    static int KeyAction = 1;

    private JFrame frame;

    public Toolbar(JFrame frame) {
        setBorder(BorderFactory.createEtchedBorder());
        qlsv = new JButton("Quản Lý Sinh Viên");
        dmmh = new JButton("Quản Lý Thời Khóa Biểu");
        dsl = new JButton("Danh Sách Lớp");
        this.frame= frame;

        SetLayout();
        SetAction();
    }

    private void SetAction() {
        qlsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyAction = 1;
                BorderLayout LayoutMater= (BorderLayout) frame.getContentPane().getLayout();
                frame.remove(LayoutMater.getLayoutComponent(BorderLayout.CENTER));
                frame.add(managerStudent,BorderLayout.CENTER);
                frame.invalidate();
                frame.validate();
                frame.repaint();
                System.out.println(KeyAction);
            }
        });
        dmmh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorderLayout LayoutMater= (BorderLayout) frame.getContentPane().getLayout();
                frame.remove(LayoutMater.getLayoutComponent(BorderLayout.CENTER));
                frame.add(managerSchedule,BorderLayout.CENTER);
                frame.invalidate();
                frame.validate();
                frame.repaint();
                KeyAction = 2;
                System.out.println(KeyAction);
            }
        });
        dsl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorderLayout LayoutMater= (BorderLayout) frame.getContentPane().getLayout();
                frame.remove(LayoutMater.getLayoutComponent(BorderLayout.CENTER));
                frame.add(managerScores,BorderLayout.CENTER);
                frame.invalidate();
                frame.validate();
                frame.repaint();
                KeyAction = 3;
                System.out.println(KeyAction);
            }
        });
    }

    private void SetLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(qlsv);
        add(dmmh);
        add(dsl);
    }
}
