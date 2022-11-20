package mancala;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Draw extends JPanel {
	int stoneNumber;

	public Draw(int stones) {
		stoneNumber = stones;
	}
	
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Double(250, 110, 975, 730);
        g2.setPaint(Color.WHITE);
        g2.fill(rectangle);
        g2.setPaint(Color.BLACK);

        /** Pits */
        for (int i = 0; i < 12; i++)
        {
            if (i < 6){
                g2.drawOval(450 + i*100, 215, 75, 150);
            }
            else{
                g2.drawOval(450 + (i-6)*100, 405, 75, 150);
            }
        }

        Font font1 = new Font("Comic Sans MS", Font.BOLD, 14);
        g2.setFont(font1);
        g2.setPaint(Color.BLACK);
        for (int i = 0; i < 12; i++)
        {
            if (i < 6){
                String s = Integer.toString(i+1);
                g2.drawString("B"+s, 980 - i*100, 205);
            }
            else{
                String s = Integer.toString(i-5);
                g2.drawString("A"+s, 480 + (i-6)*100, 575);
            }
        }

        Font font2 = new Font("Comic Sans MS", Font.BOLD, 20);
        g2.setFont(font2);
        g2.drawString("<——— PLAYER B", 655, 150);
        g2.drawString("———> PLAYER A", 655, 625);

        Font font3 = new Font("Comic Sans MS", Font.BOLD, 24);
        g2.setFont(font3);
        g2.drawString("MANCALA BOARD", 635, 85);

        /** Draw 3 stones */
        if (stoneNumber == 3) {
            for (int i = 0; i < 12; i++)
            {
                if (i < 6){
                    g2.fillOval(468 + i*100, 240, 10, 10);
                    g2.fillOval(482 + i*100, 240, 10, 10);
                    g2.fillOval(496 + i*100, 240, 10, 10);
                }
                else {
                    g2.fillOval(468 + (i-6)*100, 430, 10, 10);
                    g2.fillOval(482 + (i-6)*100, 430, 10, 10);
                    g2.fillOval(496 + (i-6)*100, 430, 10, 10);
                }
            }
        }
        else if (stoneNumber == 4) {
                    for (int i = 0; i < 12; i++)
                   {
                        if (i < 6){
                             g2.fillOval(468 + i*100, 240, 10, 10);
                             g2.fillOval(482 + i*100, 240, 10, 10);
                             g2.fillOval(496 + i*100, 240, 10, 10);
                             g2.fillOval(468 + i*100, 254, 10, 10);
                         }
                         else {
                             g2.fillOval(468 + (i-6)*100, 430, 10, 10);
                             g2.fillOval(482 + (i-6)*100, 430, 10, 10);
                             g2.fillOval(496 + (i-6)*100, 430, 10, 10);
                             g2.fillOval(468 + (i-6)*100, 444, 10, 10);
                       }
                     }
        }

        /** Mancala */
        g2.drawOval(320, 200, 80, 350);
        g2.drawOval(1070, 200, 80, 350);

        AffineTransform at = new AffineTransform();
        at.setToRotation(-Math.toRadians(90), 80, 100);
        g2.setTransform(at);
        g2.drawString("MANCALA B", -645, 605);

        AffineTransform at2 = new AffineTransform();
        at2.setToRotation(Math.toRadians(90), 80, 100);
        g2.setTransform(at2);
        g2.drawString("MANCALA A", 650, -2180);
    }
    
    public void setStones(int stones) {
    	this.stoneNumber = stones;
    }
}
