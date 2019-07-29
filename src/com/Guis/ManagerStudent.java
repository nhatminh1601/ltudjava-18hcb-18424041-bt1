package com.Guis;

import com.Controllers.HandleFile;
import com.Models.Class;
import com.Models.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static com.Guis.MainFrame.listClass;
import static com.Guis.MainFrame.listStudent;

public class ManagerStudent extends JPanel {
    private JButton save;
    private JButton reset;
    private JButton exit;
    private JLabel mssv, name, sex, cmnd, lop , Title;
    private JTextField txtMssv, txtName, txtCmnd;
    private JRadioButton Male;
    private JRadioButton Female;
    private ButtonGroup group;
    private JComboBox txtLop,txtLopt;
    private JTable tableStudent;

    private JScrollPane sc;

    private DefaultTableModel model;


    private Box main;
    public  ManagerStudent(){


        save = new JButton("Save");
        reset = new JButton("Reset");
        exit = new JButton("Exit");
        mssv = new JLabel("MSSV:");
        name = new JLabel("Họ Và Tên:");
        sex = new JLabel("Giới Tính:");
        cmnd = new JLabel("CMND:");
        lop = new JLabel("Lớp:");
        Title = new JLabel("Danh Sách Sinh Viên");
        Title.setFont(new Font("Courier New", Font.BOLD, 28));
        Title.setForeground(Color.blue);
        txtMssv = new JTextField(40);
        txtName = new JTextField(40);
        txtCmnd = new JTextField(40);
        txtLop = new JComboBox();
        txtLopt = new JComboBox();

        Male= new JRadioButton("Nam");
        Female= new JRadioButton("Nữ");

        group= new ButtonGroup();
        group.add(Male);
        group.add(Female);


        tableStudent =new JTable();
        CreateTable();
        SetDataCombobox();
        SetLayout();

    }

    private void SetDataCombobox() {
        DefaultComboBoxModel Data= new DefaultComboBoxModel();
        for(Class lop: listClass){
            Data.addElement(lop.getName());
        }
        DefaultComboBoxModel Data1= new DefaultComboBoxModel();
        for(Class lop: listClass){
            Data1.addElement(lop.getName());
        }
        txtLop.setModel(Data);
        txtLopt.setModel(Data1);
        txtLopt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i= model.getRowCount()-1;i>=0;i--){
                    model.removeRow(i);
                }
                for(Student sv: listStudent){
                    if(sv.getIdClass().toString().trim().equals(listClass.get(txtLopt.getSelectedIndex()).getName().toString().trim())){
                        String [] row= {sv.getMssv()+" ",sv.getName() +" ",sv.getSex()+ " ",sv.getCmnd()};
                        model.addRow(row);
                    }
                }
                repaint();
            }
        });
    }

    private void CreateTable() {
        model = new DefaultTableModel();
        tableStudent= new JTable(model);
        model.addColumn("Mã Số Sinh Viên");
        model.addColumn("Họ Tên");
        model.addColumn("Giới Tính");
        model.addColumn("CMND");
        for(Student sv: listStudent){
            if(sv.getIdClass().toString().trim().equals(listClass.get(0).getName().toString().trim())){
                String [] row= {sv.getMssv()+" ",sv.getName() +" ",sv.getSex()+ " ",sv.getCmnd()};
                model.addRow(row);
            }
        }
         sc = new JScrollPane(tableStudent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableStudent.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                txtMssv.setText(tableStudent.getValueAt(tableStudent.getSelectedRow(),0).toString());
//                txtName.setText(tableStudent.getValueAt(tableStudent.getSelectedRow(),1).toString());
//                txtCmnd.setText(tableStudent.getValueAt(tableStudent.getSelectedRow(),3).toString());

            }
        });
    }

    private void SetLayout() {
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));

        main = Box.createVerticalBox();
        Box subMain = Box.createVerticalBox();
        subMain.setBorder(BorderFactory.createTitledBorder("Thêm Sửa Sinh Viên"));
        Box BorderMassv= Box.createHorizontalBox();
        Box BorderName= Box.createHorizontalBox();
        Box BorderSex= Box.createHorizontalBox();
        Box BorderCmnd= Box.createHorizontalBox();
        Box BorderLop= Box.createHorizontalBox();
        Box BorderTitle= Box.createHorizontalBox();
        Box BorderClass= Box.createHorizontalBox();
        Box BorderButton= Box.createHorizontalBox();
        Box BorderTable= Box.createHorizontalBox();

        add(main);

        main.add(subMain);
// mã số sinh viên
        subMain.add(BorderMassv);
        subMain.add(Box.createVerticalStrut(5));
        BorderMassv.add(mssv);
        BorderMassv.add(Box.createRigidArea(new Dimension(30,0)));
        BorderMassv.add(txtMssv);
        txtMssv.setMaximumSize(txtName.getPreferredSize());

// họ và tên
        subMain.add(BorderName);
        subMain.add(Box.createVerticalStrut(5));
        BorderName.add(name);
        BorderName.add(Box.createRigidArea(new Dimension(5,0)));
        BorderName.add(txtName);
        txtName.setMaximumSize(txtName.getPreferredSize());

        subMain.add(BorderSex);
        subMain.add(Box.createVerticalStrut(5));

// chứng minh nhân dân
        BorderCmnd.add(cmnd);
        BorderCmnd.add(Box.createRigidArea(new Dimension(28,0)));
        BorderCmnd.add(txtCmnd);
        txtCmnd.setMaximumSize(txtName.getPreferredSize());
        subMain.add(BorderCmnd);
        subMain.add(Box.createVerticalStrut(5));
// giới tình
        BorderSex.add(sex);
        BorderSex.add(Male);
        BorderSex.add(Female);
// lớp
        BorderLop.add(lop);
        BorderLop.add(Box.createRigidArea(new Dimension(28,0)));
        BorderLop.add(txtLop);
        subMain.add(BorderLop);
        subMain.add(Box.createVerticalStrut(15));
        txtLop.setMaximumSize(txtLop.getPreferredSize());
// button
        subMain.add(BorderButton);
        BorderButton.add(save);
        BorderButton.add(Box.createRigidArea(new Dimension(10,0)));
        BorderButton.add(reset);
        BorderButton.add(Box.createRigidArea(new Dimension(10,0)));
        BorderButton.add(exit);

        main.add(BorderTitle);
        BorderTitle.add(Title);

        main.add(BorderClass);
        BorderClass.add(lop);
        BorderClass.add(Box.createRigidArea(new Dimension(28,0)));
        BorderClass.add(txtLopt);
        txtLopt.setMaximumSize(txtLopt.getPreferredSize());

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
