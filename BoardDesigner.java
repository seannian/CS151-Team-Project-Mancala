import java.awt.*;

import javax.swing.event.ChangeListener;

public interface BoardDesigner 
{
    void paintComponent(Graphics g);
    void drawStonesOfPit(Graphics2D g2, int numberOfStones,int xPit,int yPit);
    void drawStonesOfMancala(Graphics2D g2, int numberOfStones,int xPit,int yPit);
    void setPitStone(int pitNumber);
    void setAllPitStones(int stoneNumber);
    void undo();
    void repaint();
    void setBounds(int x, int y, int i, int j);
    void attach(ChangeListener listener);
}
