package com.gallifreyo.firedoom;

import javax.swing.*;

public class Window extends JFrame {

    public Window(){
        setTitle("fireDoom");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Panel(1000,400));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
