package com.Guis;

import com.Models.Class;
import com.Models.Schedule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Guis.MainFrame.*;

public class ManagerSchedule extends JPanel {

    private JLabel Lop;
    private JComboBox txtLop;
    private JTable tableSchedule;
    private DefaultTableModel model;
    private JScrollPane sc;

    public ManagerSchedule() {
        listSchedule= handleFile.ReadFileSchedule();
        listClass = handleFile.ReadFileClass();
        Lop = new JLabel("Lớp:");
        txtLop = new JComboBox();
        Table();
        SetLop();
        SetLayout();
    }

    private void SetLop() {
        DefaultComboBoxModel Data = new DefaultComboBoxModel();
        for (Class lop : listClass) {
            Data.addElement(lop.getName());
        }
        txtLop.setModel(Data);
        txtLop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                for (Schedule item : listSchedule) {
                    if (item.getIdClass().trim().equals(listClass.get(txtLop.getSelectedIndex()).getName().toString().trim())) {
                        String[] row = {item.getIdSubject() + " ", item.getSubject() + " ", item.getClassroom()};
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
        tableSchedule = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tableSchedule.setModel(model);
        model.addColumn("Mã Môn");
        model.addColumn("Tên Môn");
        model.addColumn("Phòng Học");

        for (Schedule item : listSchedule) {
            if(listClass.size()>0){
                if (item.getIdClass().trim().equals(listClass.get(0).getName().toString().trim())) {
                    String[] row = {item.getIdSubject() + " ", item.getSubject() + " ", item.getClassroom()};
                    model.addRow(row);
                }
            }
        }

        sc = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void SetLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        Box Main = Box.createVerticalBox();
        Main.setBorder(BorderFactory.createTitledBorder("Thời Khóa Biểu"));
        Box main1 = Box.createHorizontalBox();
        main1.add(Lop);
        main1.add(txtLop);
        txtLop.setMaximumSize(txtLop.getPreferredSize());

        Main.add(main1);
        Main.add(sc);
        add(Main);

    }
}
