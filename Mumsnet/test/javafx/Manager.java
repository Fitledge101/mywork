/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager.*;
import mumsnet.Car;
import mumsnet.House;

/**
 *
 * @author Ed
 */
public class Manager {

    private ArrayList<House> houses;
    private JFrame frame;
    private JPanel panel;
    private JTextField nameInput;
    private JTextField rentInput;
    private JTextArea textArea;
    private final RecordMaker maker;

    public Manager() {
        maker = new RecordMaker();
        init();
        frame.setVisible(true);

    }

    public void init() {
        //set nimbus look and feel
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        houses = new ArrayList<>();
        frame = new JFrame();
        panel = new JPanel();
        JButton button1 = new JButton("addHouse");
        JButton button2 = new JButton("print");
        JButton button3 = new JButton("deleteall");
        JButton button4 = new JButton("addRecord");
        nameInput = new JTextField("House Name", 15);
        rentInput = new JTextField("Rent Amount", 15);
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);

        panel.add(nameInput);
        panel.add(rentInput);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(textArea);
        button1.addActionListener(new Action());
        button2.addActionListener(new Print());
        button3.addActionListener(new Delete());
        button4.addActionListener(new AddRecord());
        panel.setPreferredSize(new Dimension(400, 400));
        frame.add(panel);
        frame.setContentPane(panel);
        frame.setTitle("Mumsnet");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

    }

    public void addHouse(String name, String address, String owners, int remainingMortgage, String manager, int currentRent) {
        House newHouse = new House(name, address, owners, remainingMortgage, manager, currentRent);
        houses.add(newHouse);

    }

    public void addCar(String name, String registration) {
        Car newCar = new Car(name, registration);

    }

    public void removeItem(int index) {

        houses.remove(index);

    }

    public void clearList() {
        houses.removeAll(houses);
    }

    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {

                        String housename = nameInput.getText();
                        String rentString = rentInput.getText();
                        //need to catch incorrect format here
                        int rent = Integer.parseInt(rentString);

                        addHouse(housename, "14 Springate", "Tanners", 100000, "Cath", rent);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    class Print implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {

                        String word = "";
                        for (House house : houses) {
                            //System.out.println("House name is: " + house.getName());
                            //System.out.println("Rent is: " + Integer.toString(house.getCurrentRent()));
                            word += house.getName();
                            word += Integer.toString(house.getCurrentRent());

                        }

                        textArea.setText(word);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }

    class Delete implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {

                        clearList();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }

    class AddRecord implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {

                        maker.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
