package com.Guis;

import com.Models.Schedule;
import com.Models.StudenOfSchedule;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.Guis.MainFrame.*;

public class ManagerScores extends JPanel {
    private JLabel mssv, name, monhoc, diemGk, diemCk, diemKhac, diemTong, trangthai, ThongBao;
    private JTextField txtMasv, txtName, txtDiemGk, txtDiemCk, txtDiemKhac, txtDiemTong, txtStatus;
    private JButton them, xoa, sua, reset;
    private JLabel IdMonHoc;
    private JComboBox txtMonHoc;
    private JComboBox txtMonHoc1;
    private JTable tableScores;
    private DefaultTableModel model;
    private JScrollPane sc;
    private int CountSumSV = 0;
    private int CountPass = 0;
    private int CountFall = 0;
    private int CountUnknown = 0;

    public ManagerScores() {
        listScores = handleFile.ReadFileDiem();
        listSchedule = handleFile.ReadFileSchedule();
        IdMonHoc = new JLabel("Danh Sách Điểm Môn Học");
        txtMonHoc = new JComboBox();
        txtMonHoc1 = new JComboBox();
        initVar();
        Table();
        setMaMonhoc();
        float hocsinhdau= Math.round((float)(CountPass)/(float) (CountSumSV)*(float)(100));
        float hocsinhrot= Math.round((float)(CountFall)/(float) (CountSumSV)*(float)(100));
        ThongBao.setText("Tổng Sinh Viên: "+CountSumSV+" -- "+"Tổng Học Sinh Đậu: "+CountPass+
                " -- "+"Tổng Học Sinh Rớt: "+CountFall+" -- "+"Tổng Học Sinh Chưa có điểm: "+CountUnknown+
                " -- "+ "Tỷ Lệ Học Sinh Đậu: "+hocsinhdau+"% -- "+"Tỷ Lệ Học Sinh Rớt: "+hocsinhrot+"%");
        SetLayout();
        resetForm();
        processEventScores();
        resetForm();
        evenClickTable();
    }

    private void evenClickTable() {
        tableScores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount()==1){
                    JTable target = (JTable) e.getSource();
                    txtMasv.setText(tableScores.getValueAt(target.getSelectedRow(), 0).toString());
                    txtName.setText(tableScores.getValueAt(target.getSelectedRow(), 1).toString());
                    txtDiemCk.setText(tableScores.getValueAt(target.getSelectedRow(), 3).toString());
                    txtDiemGk.setText(tableScores.getValueAt(target.getSelectedRow(), 2).toString());
                    txtDiemKhac.setText(tableScores.getValueAt(target.getSelectedRow(), 4).toString());
                    txtDiemTong.setText(tableScores.getValueAt(target.getSelectedRow(), 5).toString());

                    txtMasv.setEnabled(false);
                    txtMonHoc1.setEnabled(false);
                    sua.setEnabled(true);
                    xoa.setEnabled(true);
                    them.setEnabled(false);
                }
            }
        });
    }

    private void resetForm() {
        txtMasv.setText("");
        txtName.setText("");
        txtDiemGk.setText("");
        txtDiemCk.setText("");
        txtDiemTong.setText("");
        txtDiemKhac.setText("");
        txtStatus.setText("0");
        txtMonHoc1.setEnabled(true);
        txtMasv.setEnabled(true);
        sua.setEnabled(false);
        them.setEnabled(true);
        xoa.setEnabled(false);
    }

    private void processEventScores() {
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
        them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudenOfSchedule data= new StudenOfSchedule(txtMasv.getText(),KeyMaMonHoc,txtMonHoc1.getSelectedItem().toString(),txtName.getText(),
                        Float.parseFloat(txtDiemGk.getText()),Float.parseFloat(txtDiemCk.getText()),Float.parseFloat(txtDiemKhac.getText())
                        ,Float.parseFloat(txtDiemTong.getText()),Integer.parseInt(txtStatus.getText()));
                listScores.add(data);
                handleFile.WriterFile(listScores,handleFile.getFileDiem());
                evenReloadTable();
                resetForm();
                JOptionPane.showMessageDialog(null, "Sửa sinh viên thành công!!!");


            }
        });
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (StudenOfSchedule item: listScores){
                    if(item.getIdSchedule().equals(KeyMaMonHoc) && item.getIdStudent().trim().equals(txtMasv.getText().trim()))
                    {
                        listScores.remove(item);
                        break;
                    }
                }
                handleFile.WriterFile(listScores,handleFile.getFileDiem());
                evenReloadTable();
                resetForm();
            }
        });
        sua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (StudenOfSchedule item: listScores){
                    if(item.getIdSchedule().equals(KeyMaMonHoc) && item.getIdStudent().trim().equals(txtMasv.getText().trim()))
                    {
                        item.setStatus(Integer.parseInt(txtStatus.getText()));
                        item.setDiemGk(Float.parseFloat(txtDiemGk.getText()));
                        item.setDiemCk(Float.parseFloat(txtDiemCk.getText()));
                        item.setDiemKhac(Float.parseFloat(txtDiemKhac.getText()));
                        item.setDiemTong(Float.parseFloat(txtDiemTong.getText()));
                        break;
                    }
                }
                handleFile.WriterFile(listScores,handleFile.getFileDiem());
                evenReloadTable();
                resetForm();
            }
        });
    }

    private void evenReloadTable() {
        System.out.println(KeyMaMonHoc);
        CountSumSV=0;
        CountFall=0;
        CountPass=0;
        CountUnknown=0;
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        for (StudenOfSchedule item : listScores) {
            if (item.getIdSchedule().trim().equals(listSchedule.get(txtMonHoc.getSelectedIndex()).getIdSubject().trim())) {
                CountSumSV++;
                KeyMaMonHoc= item.getIdSchedule();
                String kq = "";
                if (item.getStatus() == 1) {
                    if (item.getDiemTong() < 5) {
                        kq = "rớt";
                        CountFall++;
                    }
                    else {
                        kq = "đậu";
                        CountPass++;
                    }
                }
                else{
                    kq = "Chưa Có điểm";
                    CountUnknown++;
                }

                String[] row = {item.getIdStudent() + " ", item.getName() + " ", item.getDiemGk() + "",
                        item.getDiemCk() + " ", item.getDiemKhac() + " ", item.getDiemTong() + "", kq};
                model.addRow(row);
            }
        }
        float hocsinhdau= Math.round((float)(CountPass)/(float) (CountSumSV)*(float)(100));
        float hocsinhrot= Math.round((float)(CountFall)/(float) (CountSumSV)*(float)(100));
        ThongBao.setText("Tổng Sinh Viên: "+CountSumSV+" -- "+"Tổng Học Sinh Đậu: "+CountPass+
                " -- "+"Tổng Học Sinh Rớt: "+CountFall+" -- "+"Tổng Học Sinh Chưa có điểm: "+CountUnknown+
                " -- "+ "Tỷ Lệ Học Sinh Đậu: "+hocsinhdau+"% -- "+"Tỷ Lệ Học Sinh Rớt: "+hocsinhrot+"%");
        repaint();
    }

    private void initVar() {
        mssv = new JLabel("Mã Số Sinh Viên: ");
        name = new JLabel("Họ Và Tên: ");
        diemCk = new JLabel("Điểm Cuối Kỳ: ");
        diemGk = new JLabel("Điểm Giữa Kỳ: ");
        diemKhac = new JLabel("Điểm Khác: ");
        diemTong = new JLabel("Điểm Tổng: ");
        trangthai = new JLabel("Trạng Thái: ");
        monhoc = new JLabel("Tên Môn Học: ");

        ThongBao = new JLabel("Thống kê");

        txtMasv = new JTextField(20);
        txtDiemCk = new JTextField(20);
        txtDiemGk = new JTextField(20);
        txtDiemKhac = new JTextField(20);
        txtName = new JTextField(20);
        txtStatus = new JTextField(10);
        txtDiemTong = new JTextField(20);

        them = new JButton("Save");
        xoa = new JButton("Remove");
        reset = new JButton("Reset");
        sua = new JButton("Edit");
    }

    private void setMaMonhoc() {
        DefaultComboBoxModel Data = new DefaultComboBoxModel();
        for (Schedule item : listSchedule) {
            Data.addElement(item.getSubject());
        }
        txtMonHoc1.setModel(Data);
        txtMonHoc.setModel(Data);
        txtMonHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(KeyMaMonHoc);
                CountSumSV=0;
                CountFall=0;
                CountPass=0;
                CountUnknown=0;
                for (int i = model.getRowCount() - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                for (StudenOfSchedule item : listScores) {
                    if (item.getIdSchedule().trim().equals(listSchedule.get(txtMonHoc.getSelectedIndex()).getIdSubject().trim())) {
                        CountSumSV++;
                        KeyMaMonHoc= item.getIdSchedule();
                        String kq = "";
                        if (item.getStatus() == 1) {
                            if (item.getDiemTong() < 5) {
                                kq = "rớt";
                                CountFall++;
                            }
                            else {
                                kq = "đậu";
                                CountPass++;
                            }
                        }
                        else{
                            kq = "Chưa Có điểm";
                            CountUnknown++;
                        }

                        String[] row = {item.getIdStudent() + " ", item.getName() + " ", item.getDiemGk() + "",
                                item.getDiemCk() + " ", item.getDiemKhac() + " ", item.getDiemTong() + "", kq};
                        model.addRow(row);
                    }
                }
                float hocsinhdau= Math.round((float)(CountPass)/(float) (CountSumSV)*(float)(100));
                float hocsinhrot= Math.round((float)(CountFall)/(float) (CountSumSV)*(float)(100));
                ThongBao.setText("Tổng Sinh Viên: "+CountSumSV+" -- "+"Tổng Học Sinh Đậu: "+CountPass+
                        " -- "+"Tổng Học Sinh Rớt: "+CountFall+" -- "+"Tổng Học Sinh Chưa có điểm: "+CountUnknown+
                        " -- "+ "Tỷ Lệ Học Sinh Đậu: "+hocsinhdau+"% -- "+"Tỷ Lệ Học Sinh Rớt: "+hocsinhrot+"%");
                repaint();
            }
        });
    }

    private void Table() {
        model = new DefaultTableModel();
        tableScores = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tableScores.setModel(model);
        model.addColumn("MSSV");
        model.addColumn("Họ tên");
        model.addColumn("Điểm GK");
        model.addColumn("Điểm CK");
        model.addColumn("Điểm Khác");
        model.addColumn("Điểm Tổng");
        model.addColumn("Kết Quả");

        CountSumSV=0;
        CountFall=0;
        CountPass=0;
        for (StudenOfSchedule item : listScores) {
            if (listSchedule.size() > 0) {
                if (item.getIdSchedule().trim().equals(listSchedule.get(0).getIdSubject().trim())) {
                    KeyMaMonHoc= item.getIdSchedule();
                    CountSumSV++;
                    String kq = "Chưa Có điểm";
                    if (item.getStatus() == 1) {

                        if (item.getDiemTong() < 5) {
                            kq = "rớt";
                            CountFall++;
                        }
                        else{
                            CountPass++;
                            kq = "đậu";
                        }
                    }
                    String[] row = {item.getIdStudent() + " ", item.getName() + " ", item.getDiemGk() + "",
                            item.getDiemCk() + " ", item.getDiemKhac() + " ", item.getDiemTong() + "", kq};
                    model.addRow(row);
                }
            }

        }
        sc = new JScrollPane(tableScores, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void SetLayout() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        Box Main = Box.createVerticalBox();

        Box main2 = Box.createVerticalBox();
        main2.setBorder(BorderFactory.createTitledBorder("Thêm Sửa Điểm Sinh Viên"));
        Box subtext1 = Box.createHorizontalBox();
        subtext1.add(mssv);
        subtext1.add(txtMasv);
        txtMasv.setMaximumSize(txtMasv.getPreferredSize());

        Box subtext2 = Box.createHorizontalBox();
        subtext2.add(name);
        subtext2.add(Box.createRigidArea(new Dimension(35, 0)));
        subtext2.add(txtName);
        txtName.setMaximumSize(txtName.getPreferredSize());

        Box subtext3 = Box.createHorizontalBox();
        subtext3.add(monhoc);
        subtext3.add(txtMonHoc1);
        txtMonHoc1.setMaximumSize(txtMonHoc1.getPreferredSize());

        Box subtext4 = Box.createHorizontalBox();
        subtext4.add(diemGk);
        subtext4.add(Box.createRigidArea(new Dimension(10, 0)));
        subtext4.add(txtDiemGk);
        txtDiemGk.setMaximumSize(txtDiemGk.getPreferredSize());

        Box subtext5 = Box.createHorizontalBox();
        subtext5.add(diemCk);
        subtext5.add(Box.createRigidArea(new Dimension(10, 0)));
        subtext5.add(txtDiemCk);
        txtDiemCk.setMaximumSize(txtDiemCk.getPreferredSize());

        Box subtext6 = Box.createHorizontalBox();
        subtext6.add(diemKhac);
        subtext6.add(Box.createRigidArea(new Dimension(30, 0)));
        subtext6.add(txtDiemKhac);
        txtDiemKhac.setMaximumSize(txtDiemKhac.getPreferredSize());

        Box subtext7 = Box.createHorizontalBox();
        subtext7.add(diemTong);
        subtext7.add(Box.createRigidArea(new Dimension(30, 0)));
        subtext7.add(txtDiemTong);
        txtDiemTong.setMaximumSize(txtDiemTong.getPreferredSize());

        Box subtext8 = Box.createHorizontalBox();
        subtext8.add(trangthai);
        subtext8.add(Box.createRigidArea(new Dimension(30, 0)));
        subtext8.add(txtStatus);
        txtStatus.setMaximumSize(txtStatus.getPreferredSize());


        main2.add(subtext1);
        main2.add(Box.createVerticalStrut(5));
        main2.add(subtext2);
        main2.add(Box.createVerticalStrut(5));
        main2.add(subtext3);
        main2.add(Box.createVerticalStrut(5));
        main2.add(subtext4);
        main2.add(Box.createVerticalStrut(5));
        main2.add(subtext5);
        main2.add(Box.createVerticalStrut(5));
        main2.add(subtext6);
        main2.add(Box.createVerticalStrut(5));
        main2.add(subtext7);
        main2.add(Box.createVerticalStrut(5));
        main2.add(subtext8);

        //Button
        Box main3 = Box.createHorizontalBox();
        main3.add(them);
        main3.add(Box.createRigidArea(new Dimension(10, 0)));
        main3.add(reset);
        main3.add(Box.createRigidArea(new Dimension(10, 0)));
        main3.add(sua);
        main3.add(Box.createRigidArea(new Dimension(10, 0)));
        main3.add(xoa);

        // Thống kê
        Box main4 = Box.createHorizontalBox();
        main4.setBorder(BorderFactory.createTitledBorder("Thống kê"));
        main4.add(ThongBao);

        // danh sách sinh viên
        Box main1 = Box.createVerticalBox();
        main1.setBorder(BorderFactory.createTitledBorder("Danh Sách Điểm Sinh Viên"));
        Box subm1 = Box.createHorizontalBox();
        subm1.add(IdMonHoc);
        subm1.add(txtMonHoc);
        txtMonHoc.setMaximumSize(txtMonHoc.getPreferredSize());

        main1.add(subm1);
        main1.add(sc);

        Main.add(main2);
        Main.add(Box.createVerticalStrut(10));
        Main.add(main3);
        Main.add(main4);
        Main.add(main1);

        add(Main);

    }
}
