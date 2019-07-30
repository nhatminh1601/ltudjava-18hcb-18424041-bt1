package com.Guis;

import com.Models.Schedule;
import com.Models.StudenOfSchedule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Guis.MainFrame.listSchedule;
import static com.Guis.MainFrame.listScores;

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
        setMaMonhoc();
        SetLayout();
    }

    private void setMaMonhoc() {
        DefaultComboBoxModel Data = new DefaultComboBoxModel();
        for (Schedule item : listSchedule) {
            Data.addElement(item.getSubject());
        }
        txtMonHoc.setModel(Data);
        txtMonHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println();
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                for (StudenOfSchedule item : listScores) {
                    if (item.getIdSchedule().trim().equals(listSchedule.get(txtMonHoc.getSelectedIndex()).getIdSubject().toString().trim())) {
                        String kq = "đậu";
                        if (item.getDiemTong() < 5) {
                            kq = "rớt";
                        }
                        String[] row = {item.getIdStudent() + " ", item.getName() + " ", item.getDiemGk() + "",
                                item.getDiemCk() + " ", item.getDiemKhac() + " ", item.getDiemTong() + "", kq};
                        model.addRow(row);
                    }
                }
                repaint();
                System.out.println(1);
            }
        });
    }

    private void Table() {
        model = new DefaultTableModel();
        tableScores = new JTable();
        tableScores.setModel(model);
        model.addColumn("MSSV");
        model.addColumn("Họ tên");
        model.addColumn("Điểm GK");
        model.addColumn("Điểm CK");
        model.addColumn("Điểm Khác");
        model.addColumn("Điểm Tổng");
        model.addColumn("Kết Quả");
        for (StudenOfSchedule item : listScores) {
            String kq = "đậu";
            if (item.getDiemTong() < 5) {
                kq = "rớt";
            }
            String[] row = {item.getIdStudent() + " ", item.getName() + " ", item.getDiemGk() + "",
                    item.getDiemCk() + " ", item.getDiemKhac() + " ", item.getDiemTong() + "", kq};
            model.addRow(row);
        }
        sc = new JScrollPane(tableScores, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void SetLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        Box Main = Box.createVerticalBox();
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
