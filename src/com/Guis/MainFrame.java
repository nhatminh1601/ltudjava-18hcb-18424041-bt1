package com.Guis;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private LoginDialog loginDialog;
    public MainFrame(){
        setVisible(true);
        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        loginDialog = new LoginDialog(this);
        loginDialog.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
