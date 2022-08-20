package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Flow;
import javax.swing.*;

public class Magazin {
    private JFrame magaFrame;

    public Magazin() {
        final Magazin mag = this;
        magaFrame = new JFrame();
        magaFrame.setVisible(true);
        magaFrame.setBounds(600, 300, 400, 200);
        magaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JPanel titlu1 = new JPanel();

        titlu1.setBackground(new Color(3, 213, 7));
        panel.setBackground(new Color(3, 213, 7));
        magaFrame.getContentPane().add(titlu1, BorderLayout.PAGE_START);
        magaFrame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton b1 = new JButton("Client");
        b1.setBackground(new Color(161, 148, 148));
        b1.addActionListener(e -> {
            magaFrame.setVisible(false);
            ClientGUI cl = new ClientGUI(mag);
        });
        panel.add(b1);

        JButton b2 = new JButton("Produs");
        b2.setBackground(new Color(161, 148, 148));
        b2.addActionListener(e -> {
            magaFrame.setVisible(false);
             ProdusGUI p = new ProdusGUI(mag);
        });
        panel.add(b2);

        JButton b3 = new JButton("Comanda");
        b3.setBackground(new Color(161, 148, 148));
        b3.addActionListener(e -> {
            magaFrame.setVisible(false);
            ComandaGUI cm = new ComandaGUI(mag);
        });
        panel.add(b3);

        JTextPane titlu = new JTextPane();
        titlu.setBackground(new Color(3, 213, 7));
        titlu.setForeground(new Color(0, 0, 0));
        titlu.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
        titlu.setText("Magazin");
        titlu.setBounds(60, 35, 250, 250);
        titlu1.add(titlu);
        titlu.setEditable(false);

        panel.setLayout(new FlowLayout());
    }

    public JFrame getmagaFrame() {
        return magaFrame;
    }

    public void setmagaFrame(JFrame magaFrame) {
        this.magaFrame = magaFrame;
    }
}