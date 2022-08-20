package presentation;

import businessLogic.ClientBL;
import dao.ClientDAO;
import model.Client;
import model.Produs;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.Flow;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ClientGUI {
    private final JFrame clienFrame;
    private JFrame frameClient;
    private JTable table;
    //JScrollPane sp = new JScrollPane(table);
    private Magazin mag;
    private ClientBL c;
    private ClientDAO clntDAO;


    public ClientGUI(Magazin par) {

        mag = par;
        clntDAO = new ClientDAO();
        c = new ClientBL();
        clienFrame = new JFrame();
        clienFrame.setVisible(true);
        clienFrame.setBounds(120, 70, 1200, 650);
        clienFrame.getContentPane().setBackground(new Color(8, 217, 176));
        clienFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JPanel panel4 = new JPanel();
        final JPanel panel5 = new JPanel();
        final JPanel panel6 = new JPanel();
        //panel.setBackground(new Color(102, 255, 178));


        clienFrame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JTextPane titlu = new JTextPane();
        titlu.setBackground(new Color(8, 217, 176));
        titlu.setForeground(new Color(0, 0, 0));
        titlu.setFont(new Font("Comic Sans MS", Font.BOLD, 45));
        titlu.setText("Client");
        titlu.setBounds(60, 35, 250, 250);
        panel.add(titlu);
        titlu.setEditable(false);

        JButton btnBack = new JButton("Inapoi");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mag.getmagaFrame().setVisible(true);
                clienFrame.setVisible(false);
            }
        });

        JButton findAll = new JButton("SELECT *");
        //findAll.setBounds(100, 100, 100, 30);
        JButton insert = new JButton("INSERT");
        JButton delete = new JButton("DELETE");
        JButton update = new JButton("UPDATE");

        JLabel label1 = new JLabel("ID: ");
        JLabel label2 = new JLabel("NUME: ");
        JLabel label3 = new JLabel("PRENUME: ");
        JLabel label4 = new JLabel("EMAIL: ");
        //JLabel label5 = new JLabel("PRET: ");

        final JTextField tf1 = new JTextField();
        tf1.setColumns(8);
        final JTextField tf2 = new JTextField();
        tf2.setColumns(8);
        final JTextField tf3 = new JTextField();
        tf3.setColumns(8);
        final JTextField tf4 = new JTextField();
        tf4.setColumns(8);


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
                                                "ID", "NUME", "PRENUME", "EMAIL"
                                        }));
                table.setBounds(60, 400, 650, 100);
                //JScrollPane sp = new JScrollPane(table);
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                List<Client> clnt = c.findAll();

                for (Client Client : clnt) {
                    int id = Client.getId();
                    String nume = Client.getNume();
                    String prenume = Client.getPrenume();
                    String email = Client.getEmail();

                    tableModel.addRow(new Object[]{id,nume,prenume,email});

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
            clntDAO.delete(Integer.parseInt(tf1.getText()), "ID");
        });
        update.addActionListener(e -> {
            Client clntt = new Client(Integer.parseInt(tf1.getText()), tf2.getText(), tf3.getText(), tf4.getText());
            //clntDAO.update(clntt, Integer.parseInt(tf1.getText()), "ID");
        });
        insert.addActionListener(e -> {
            Client clntt = new Client(Integer.parseInt(tf1.getText()), tf2.getText(), tf3.getText(), tf4.getText());
            clntDAO.insert(clntt);
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
        //panel3.add(label5);
        //panel3.add(tf5);
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(new Color(8, 217, 176));
        panel.add(panel3);

        panel4.add(btnBack);
        panel4.setBackground(new Color(8, 217, 176));
        panel.add(panel4);

//        panel5.add(table);
//        panel5.setLayout(new FlowLayout());
//        panel.add(panel5);
//
//        panel.add(sp);
        panel.add(panel6);
        panel6.setBackground(new Color(8, 217, 176));

        panel.setBackground(new Color(8, 217, 176));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


    }


}