package com.jzhung.demo.tcpip;

import javax.swing.*;

/**
 * Created by Jzhung on 2017/1/11.
 */
public class HttpWatcher {

    private JPanel mainPanel;
    private JButton startBtn;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("HttpWatcher");
        frame.setContentPane(new HttpWatcher().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
