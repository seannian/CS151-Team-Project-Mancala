import java.awt.*;

public interface BoardDesigner {
    void paintComponent(Graphics g);
    void drawStonesOfPit(Graphics2D g2, int numberOfStones,int xPit,int yPit);
    void drawStonesOfMancala(Graphics2D g2, int numberOfStones,int xPit,int yPit);
    void setPitStone(int pitNumber);
   // void setMancalaStone(int mancalaNum, int numberOfStones);
   void setAllPitStones(int stoneNumber);
}
