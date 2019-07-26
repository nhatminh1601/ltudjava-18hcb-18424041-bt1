package com.Guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog  extends JDialog {
    private JLabel nameLabel, passLabel, optionLabel;
    private JTextField nameField;
    private JPasswordField passField;
    private JComboBox optionComboBox;
    Button login, exit;
    MainFrame mainFrame;


    public LoginDialog(Frame parent) {
        super(parent, "Log In", true);
        layoutComponents();

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }

    private void layoutComponents() {

        nameLabel =new JLabel("Username: ");
        passLabel = new JLabel("Password: ");
        optionLabel = new JLabel("Option: ");

        nameField = new JTextField(20);
        passField = new JPasswordField(20);

        optionComboBox= new JComboBox();
        DefaultComboBoxModel type = new DefaultComboBoxModel();
        type.addElement("Giáo Vụ");
        type.addElement("Học Sinh");

        optionComboBox.setModel(type);

        login = new Button("LOGIN");
        exit = new Button("EXIT");

        setLayout(new GridBagLayout());
        GridBagConstraints gc= new GridBagConstraints();
        gc.insets= new Insets(10,0,0,5);
        gc.fill =GridBagConstraints.NONE;
        gc.weightx=2;
        gc.weighty=0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor=GridBagConstraints.LINE_END;
        add(nameLabel,gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor=GridBagConstraints.LINE_START;
        add(nameField,gc);

        gc.weightx =1;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor=GridBagConstraints.LINE_END;
        add(passLabel,gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor=GridBagConstraints.LINE_START;
        add(passField,gc);

        gc.weightx =1;
        gc.weighty = 0;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor=GridBagConstraints.LINE_END;
        add(optionLabel,gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor=GridBagConstraints.LINE_START;
        add(optionComboBox,gc);

        gc.weighty=0.5;
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor=GridBagConstraints.LINE_END;
        add(login,gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor=GridBagConstraints.LINE_START;
        add(exit,gc);
        setSize(400,300);
        setBackground(Color.white);
        setFont(new Font("Helvetica",Font.PLAIN, 18));

    }
}
