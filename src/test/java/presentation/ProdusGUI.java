package presentation;

import java.awt.*;

import java.util.List;

import javax.swing.*;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.Produs;
import businessLogic.validators.ProdusBL;

public class ProdusGUI {
    private JFrame frameProdus;
    private JTable table;
    //JScrollPane sp = new JScrollPane(table);
    private Magazin mag;
    private ProdusBL prod;


    public ProdusGUI(Magazin x) {
        mag = x;
        prod = new ProdusBL();

        frameProdus = new JFrame();
        frameProdus.setVisible(true);
        frameProdus.getContentPane().setBackground(new Color(8, 217, 176));
        frameProdus.setBounds(120, 70, 1200, 650);
        frameProdus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JPanel panel4 = new JPanel();
        final JPanel panel5 = new JPanel();
        final JPanel panel6 = new JPanel();
        //panel.setBackground(new Color(102, 255, 178));
        frameProdus.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JTextPane titlu = new JTextPane();
        titlu.setBackground(new Color(8, 217, 176));
        titlu.setForeground(new Color(0, 0, 0));
        titlu.setFont(new Font("Comic Sanas MS", Font.BOLD, 45));
        titlu.setText("Produs");
        panel.add(titlu);
        titlu.setEditable(false);

        JButton btnInap = new JButton("Inapoi");
        btnInap.addActionListener(e -> {
            mag.getmagaFrame().setVisible(true);
            frameProdus.setVisible(false);
        });

        JButton selectAll = new JButton("SELECT *");
        //selectAll.setBounds(100, 100, 100, 30);
        JButton insert = new JButton("INSERT");
        JButton delete = new JButton("DELETE");
        JButton update = new JButton("UPDATE");
        final JButton ok = new JButton("OK");
        ok.setBackground(new Color(192, 192, 192));

        JLabel label1 = new JLabel("ID: ");
        JLabel label2 = new JLabel("PRET: ");
        JLabel label3 = new JLabel("CANTITATE: ");
        JLabel label4 = new JLabel("NUME PRODUS: ");
        //JLabel label5 = new JLabel("PRET: ");

        final JTextField tf1 = new JTextField();
        tf1.setColumns(10);
        tf1.setEditable(true);
        final JTextField tf2 = new JTextField();
        tf2.setColumns(10);
        tf2.setEditable(true);
        final JTextField tf3 = new JTextField();
        tf3.setColumns(10);
        tf3.setEditable(true);
        final JTextField tf4 = new JTextField();
        tf4.setColumns(10);
        tf4.setEditable(true);

        selectAll.addActionListener(e -> {
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
                                                "ID", "NUME", "PRET", "GRAMAJ", "STOC"
                                        }));
                table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
                table.setBounds(60, 400, 650, 100);
                //JScrollPane sp = new JScrollPane(table);
                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                System.out.println("ss");
                List<Produs> pd = prod.findAll();

                for (Produs prod : pd) {
                    int id = prod.getId();
                    String nume = prod.getNume_produs();
                    float pret = prod.getPret();
                    int gr = prod.getCantitate();
                    int stoc = prod.getCantitate();

                    tableModel.addRow(new Object[]{id, nume, pret, gr, stoc});

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
            prod.delete(Integer.parseInt(tf1.getText()), "ID");
        });
        update.addActionListener(e -> {
            Produs clntt = new Produs(Integer.parseInt(tf1.getText()),Float.parseFloat(tf2.getText()),Integer.parseInt(tf3.getText()), tf4.getText());
            prod.update(clntt, Integer.parseInt(tf1.getText()), "ID");
        });
        insert.addActionListener(e -> {
            Produs clntt = new Produs(Integer.parseInt(tf1.getText()),Float.parseFloat(tf2.getText()),Integer.parseInt(tf3.getText()), tf4.getText());
            prod.insert(clntt);
        });


        panel2.setLayout(null);
        panel2.add(titlu);
        titlu.setBounds(60, 35, 250, 250);
        panel2.add(btnInap);
        panel2.setBackground(new Color(8, 217, 176));
        panel.add(panel2);

        panel1.setVisible(true);
        panel1.add(selectAll);
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

        panel4.add(btnInap);
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

        frameProdus.setContentPane(panel);
    }
}
