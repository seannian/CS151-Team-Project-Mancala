package mancala;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MancalaTester {
	static int count = 0;
	static int stoneNumber;
	
	private static JFrame frame;
	
	public static void main(String[] args) {
    	Draw draw = new Draw(stoneNumber);    	
        frame = new JFrame("Mancala Game");
        frame.setBounds(50,50,2000,2000);
        draw.setBounds(100, 100, 1000, 1000);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        panel.setLayout(new GridLayout(0, 1));

        JLabel undoLabel = new JLabel("You Have Undo " + count + " Times");
        JButton undoButton = new JButton("Undo");
        //undoButton.addActionListener(this);
        panel.add(undoLabel);
        panel.add(undoButton);
        
        JLabel inputLabel = new JLabel("Enter the number of stones——3 or 4 (and return): ");
        inputLabel.setBounds(100, 100, 100, 50);
        panel.add(inputLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(300, 100, 165, 50);
        
        JButton clickToRun = new JButton("Add Stones");
        
        
        clickToRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = userText.getText();
                if (number.equals("3"))
                {
                	draw.setStones(3);
                	draw.repaint();
                }
                else if (number.equals("4")){
                	draw.setStones(4);
                    draw.repaint();
                }
            }
        });
        JPanel stoneControler = new JPanel();
        stoneControler.setLayout(new FlowLayout());
        stoneControler.add(userText);
        stoneControler.add(clickToRun);
        panel.add(stoneControler);
        
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(draw);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
