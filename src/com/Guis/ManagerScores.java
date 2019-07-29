package com.Guis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManagerScores extends JPanel {
    private JLabel IdMonHoc;
    private JComboBox txtMonHoc;
    private JTable tableScores;
    private DefaultTableModel model;
    private JScrollPane sc;

    public ManagerScores() {
        IdMonHoc = new JLabel("Danh Sách Điểm Môn Học");
        txtMonHoc = new JComboBox();
        Table();
        SetLayout();
    }

    private void Table() {
        model = new DefaultTableModel();
        tableScores= new JTable();
        tableScores.setModel(model);
        model.addColumn("MSSV");
        model.addColumn("Họ tên");
        model.addColumn("Điểm GK");
        model.addColumn("Điểm CK");
        model.addColumn("Điểm Khác");
        model.addColumn("Điểm Tổng");
        model.addColumn("Kết Quả");

        sc = new JScrollPane(tableScores,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    private void SetLayout() {
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        Box Main= Box.createVerticalBox();
        Main.setBorder(BorderFactory.createTitledBorder("Danh Sách Điểm Sinh Viên"));
        Box main1 = Box.createHorizontalBox();
        main1.add(IdMonHoc);
        main1.add(txtMonHoc);
        txtMonHoc.setMaximumSize(txtMonHoc.getPreferredSize());

        Main.add(main1);
        Main.add(sc);
        add(Main);

    }
}
