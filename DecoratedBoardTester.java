

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DecoratedBoardTester 
{
    private static JFrame frame;

    public static void main(String[] args) {
    	//final BoardDesigner draw;
    	
    	frame = new JFrame("Mancala Game");
		frame.setSize(2000,2000);
		frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        panel.setLayout(new GridLayout(0, 1));

        JButton circle = new JButton("Circle Style");
        circle.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		BoardDesigner draw = new DecoratedBoard();
        		BoardFrame rectangle = new BoardFrame(draw);
        	}
        });
        panel.add(circle);
        
        JButton rectangle = new JButton("Rectangle Style");
        rectangle.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		BoardDesigner draw = new RectangleBoard();
        		BoardFrame circle = new BoardFrame(draw);
        	}
        });
        panel.add(rectangle);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
