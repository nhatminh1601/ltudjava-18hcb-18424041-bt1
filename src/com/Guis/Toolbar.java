package com.Guis;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private JButton qlsv;
    private JButton dmmh;
    private JButton dsl;
    private JButton logOut;

    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());
        qlsv=new JButton("Quản Lý Sinh Viên");
        dmmh= new JButton("Quản Lý Thời Khóa Biểu");
        dsl = new JButton("Danh Sách Lớp");

        SetLayout();
    }

    private void SetLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(qlsv);
        add(dmmh);
        add(dsl);
    }
}
