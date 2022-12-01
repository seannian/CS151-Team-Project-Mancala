

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DecoratedBoardTester {
    static int stoneNumber;

    private static JFrame frame;

    public static void main(String[] args) {
        DecoratedBoard draw = new DecoratedBoard(stoneNumber);
        frame = new JFrame("Mancala Game");
        frame.setSize(2000,2000);

       draw.setBounds(100, 100, 1000, 1000);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        panel.setLayout(new GridLayout(0, 1));

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		draw.undo();
        	}
        });
        panel.add(undoButton);
        
        ChangeListener repaint = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				draw.repaint();
			}
		};
		draw.attach(repaint);

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
                    draw.setAllPitStones(3);
                    userText.setVisible(false);
                    clickToRun.setVisible(false);
                    inputLabel.setVisible(false);
                }
                else if (number.equals("4")){
                    draw.setAllPitStones(4);
                    userText.setVisible(false);
                    clickToRun.setVisible(false);
                    inputLabel.setVisible(false);
                }
            }
        });
        JPanel stoneControler = new JPanel();
        stoneControler.setLayout(new FlowLayout());
        stoneControler.add(userText);
        stoneControler.add(clickToRun);
        panel.add(stoneControler);
        //panel.add(moveStones);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(draw);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
