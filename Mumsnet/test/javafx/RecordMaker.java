/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author Ed
 */
public class RecordMaker {

    private JFrame frame;
    private JPanel panel;
    private JTextField nameInput;
    private JTextField rentInput;
    private JTextArea textArea;

    public RecordMaker() {
        init();

    }

    public void show() {
        frame.setVisible(true);

    }

    public void init() {
        frame = new JFrame();
        //set nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        frame = new JFrame();
        panel = new JPanel();
        JButton button1 = new JButton("addRecord");

        nameInput = new JTextField("RecordName", 15);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);

        panel.add(nameInput);

        panel.add(button1);

        panel.add(textArea);
        panel.setPreferredSize(new Dimension(400, 400));
        frame.add(panel);
        frame.setContentPane(panel);
        frame.setTitle("Record Maker");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();

    }

}
