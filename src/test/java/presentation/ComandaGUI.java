package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.ComandaBL;

import businessLogic.CreatFis;
import dao.ComandaDAO;
import model.Client;
import model.Comanda;
import model.Produs;

public class ComandaGUI {
    private JFrame frameComanda;
    private JTable table;
    //JScrollPane sp = new JScrollPane(table);
    private Magazin wp;
    private ComandaBL com;
    private ComandaDAO comndDAO;


    public ComandaGUI(Magazin x) {
        wp = x;
        com = new ComandaBL();
        comndDAO = new ComandaDAO();

        frameComanda = new JFrame();
        frameComanda.setVisible(true);
        frameComanda.getContentPane().setBackground(new Color(8, 217, 176));
        frameComanda.setBounds(120, 70, 1200, 650);
        frameComanda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JPanel panel4 = new JPanel();
        final JPanel panel5 = new JPanel();
        final JPanel panel6 = new JPanel();
        //panel.setBackground(new Color(102, 255, 178));
        frameComanda.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JTextPane titlu = new JTextPane();
        titlu.setBackground(new Color(8, 217, 176));
        titlu.setForeground(new Color(0, 0, 0));
        titlu.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
        titlu.setText("Comanda");
        titlu.setBounds(60, 35, 250, 250);
        //panel.add(titlu);
        titlu.setEditable(false);

        JButton btnBack = new JButton("Inapoi");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wp.getmagaFrame().setVisible(true);
                frameComanda.setVisible(false);
            }
        });
        btnBack.setBounds(800, 50, 100, 30);
        //panel.add(btnBack);

        JButton findAll = new JButton("SELECT *");
        findAll.setBackground(new Color(192, 192, 192));
        //findAll.setBounds(100, 100, 100, 30);
        JButton insert = new JButton("INSERT");
        insert.setBackground(new Color(192, 192, 192));
        JButton delete = new JButton("DELETE");
        delete.setBackground(new Color(192, 192, 192));
        JButton update = new JButton("UPDATE");
        update.setBackground(new Color(192, 192, 192));
        final JButton ok = new JButton("OK");
        ok.setBackground(new Color(192, 192, 192));

        JLabel label1 = new JLabel("ID: ");
        JLabel label2 = new JLabel("ID PRODUS: ");
        JLabel label3 = new JLabel("ID CLIENT: ");
        JLabel label4 = new JLabel("CANTITATE: ");
        JLabel label5 = new JLabel("PRET: ");

        final JTextField tf1 = new JTextField();
        tf1.setColumns(8);
        final JTextField tf2 = new JTextField();
        tf2.setColumns(8);
        final JTextField tf3 = new JTextField();
        tf3.setColumns(8);
        final JTextField tf4 = new JTextField();
        tf4.setColumns(8);
        final JTextField tf5 = new JTextField();
        tf5.setColumns(8);

        findAll.addActionListener(e -> {
            //if(ok.getModel().isPressed())
            {
                table = new JTable();
                table.setModel(new DefaultTableModel
                        (
                                new Object[][]
                                        {

                                        },
                                new String[]
                                        {
                                                "ID", "ID_PRODUS", "ID_CLIENT", "CANTITATE", "PRET"
                                        }));
                table.setBounds(60, 400, 650, 100);
                //JScrollPane sp = new JScrollPane(table);
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                List<Comanda> comnd = com.findAll();
                System.out.println("dSasda");

                for (Comanda Comanda : comnd) {

                    int id = Comanda.getId();
                    int id_produs = Comanda.getId_produs();
                    int id_client = Comanda.getId_client();
                    int cantitate = Comanda.getCantitate();
                    float pret = Comanda.getPret();
                    tableModel.addRow(new Object[]{id,id_client, id_produs,cantitate,pret});

                }
                JScrollPane sp = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                panel6.removeAll();
                panel6.add(sp);
                table.repaint();
                sp.repaint();
                panel6.repaint();
                panel6.revalidate();
            }
        });
        delete.addActionListener(e -> {
            comndDAO.delete(Integer.parseInt(tf1.getText()), "ID");
        });
        update.addActionListener(e -> {
            Comanda clntt = new Comanda(Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()), Integer.parseInt(tf3.getText()), Integer.parseInt(tf4.getText()), Float.parseFloat(tf5.getText()));
           // comndDAO.insert(clntt, Integer.parseInt(tf1.getText()), "ID");
        });
        insert.addActionListener(e -> {
            Comanda clntt = new Comanda(Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()), Integer.parseInt(tf3.getText()), Integer.parseInt(tf4.getText()), Float.parseFloat(tf5.getText()));
            comndDAO.insert(clntt);
            String textFisier=("Clientul "+tf2.getText()+" "+
                    " a comandat produsul "+ tf3.getText() +" in cantitatea "+tf4.getText());
            CreatFis fisiernouw=new CreatFis();
            fisiernouw.scrie("Coamnda",textFisier);
        });

        panel2.setLayout(null);
        panel2.add(titlu);
        titlu.setBounds(60, 35, 250, 250);
        panel2.add(btnBack);
        btnBack.setBounds(800, 50, 100, 30);
        panel2.setBackground(new Color(8, 217, 176));
        panel.add(panel2);

        panel1.setVisible(true);
        panel1.add(findAll);
        panel1.add(insert);
        panel1.add(delete);
        panel1.add(update);
        panel1.setLayout(new FlowLayout());
        panel1.setBackground(new Color(8, 217, 176));
        panel.add(panel1);

        panel3.add(label1);
        panel3.add(tf1);
        panel3.add(label2);
        panel3.add(tf2);
        panel3.add(label3);
        panel3.add(tf3);
        panel3.add(label4);
        panel3.add(tf4);
        panel3.add(label5);
        panel3.add(tf5);
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(new Color(8, 217, 176));
        panel.add(panel3);

        panel4.add(btnBack);
        panel4.setLayout(new FlowLayout());
        panel4.setBackground(new Color(8, 217, 176));
        panel.add(panel4);

        //panel5.add(table);
        //panel5.setLayout(new FlowLayout());
        //panel.add(panel5);

        //panel.add(sp);
        panel.add(panel6);
        panel6.setBackground(new Color(8, 217, 176));
        panel.setBackground(new Color(8, 217, 176));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frameComanda.setContentPane(panel);
    }}