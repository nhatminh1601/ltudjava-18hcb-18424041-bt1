package com.Guis;

import com.Models.StudenOfSchedule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.Guis.MainFrame.listLogin;
import static com.Guis.MainFrame.listScores;

public class StudentLayout extends JPanel {

    private JLabel Title;
    private JTable tableStudent;

    private JScrollPane sc;

    private DefaultTableModel model;


    private Box main;

    public StudentLayout() {

        Title = new JLabel("Danh Sách Điểm");
        Title.setFont(new Font("Courier New", Font.BOLD, 28));
        Title.setForeground(Color.blue);


        tableStudent = new JTable();
        CreateTable();
        SetLayout();

    }


    private void CreateTable() {
        model = new DefaultTableModel();
        tableStudent = new JTable(model) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        model.addColumn("Mã Môn");
        model.addColumn("Tên Môn");
        model.addColumn("Điểm Gk");
        model.addColumn("Điểm Ck");
        model.addColumn("Điểm Khác");
        model.addColumn("Điểm Tổng");
        for (StudenOfSchedule item : listScores) {

            if (item.getIdStudent().toString().trim().equals(listLogin.get(0).getUserName().toString().trim())) {
                String[] row = {item.getIdSchedule() + " ", item.getSubject() + " ", item.getDiemGk() + ""
                        , item.getDiemCk() + " ", item.getDiemKhac() + " ", item.getDiemTong() + " "};
                model.addRow(row);
            }
        }
        sc = new JScrollPane(tableStudent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    }

    private void SetLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        main = Box.createVerticalBox();
        Box subMain = Box.createVerticalBox();
        subMain.setBorder(BorderFactory.createTitledBorder("Thêm Sửa Sinh Viên"));
        Box BorderTitle = Box.createHorizontalBox();
        Box BorderTable = Box.createHorizontalBox();

        add(main);
        main.add(BorderTitle);
        BorderTitle.add(Title);

        main.add(BorderTable);
        BorderTable.add(sc);

    }

    public Box getMain() {
        return main;
    }

    public void setMain(Box main) {
        this.main = main;
    }
}
