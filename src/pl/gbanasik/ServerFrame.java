package pl.gbanasik;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class ServerFrame extends JFrame {
    private int port;
    ServerFrame() {

        ImageIcon imageIcon = new ImageIcon("serverIcon.jpg");
        this.setBackground(new Color(96, 151, 240));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 600);
        this.setVisible(true);

        JTextField portTxt = new JTextField();
        portTxt.setEditable(true);



    }
}
