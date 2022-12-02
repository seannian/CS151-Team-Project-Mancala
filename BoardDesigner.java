/**
 * Fall 2022 CS151 Team Project
 * Simple Mancala Board
 * Instructor: Dr. Suneuy Kim
 * 
 * @author Sean Nian, Abdugafur Dalerzoda, Xianqiao Zhang, Aarushi  Gautam
 * @version 1.0 12/1/2022
 */

import java.awt.*;

import javax.swing.event.ChangeListener;

/**
 * The methods that each design of the board should contain
 */
public interface BoardDesigner 
{
	/**
	 * The repaint method will call paintComponent and paint out all the components of the design
	 * @param g - the graphics in which the design is being painted with
	 */
    void paintComponent(Graphics g);
    
    /**
     * Will draw the stones in each pit.
     * @param g2- the graphics in which the design is being painted with
     * @param numberOfStones - the number of stones that need to be drawn
     * @param xPit - the xAxis of the stones
     * @param yPit = the yAxis of the stones
     */
    void drawStonesOfPit(Graphics2D g2, int numberOfStones,int xPit,int yPit);
    
    /**
     * Will draw the stones in the biggest mancala pit
     * @param g2- the graphics in which the design is being painted with
     * @param numberOfStones - the number of stones that need to be drawn
     * @param xPit - the xAxis of the stones
     * @param yPit = the yAxis of the stones
     */
    void drawStonesOfMancala(Graphics2D g2, int numberOfStones,int xPit,int yPit);
    
    /**
     * Sets the amount of stones in the pit
     * @param pitNumber - the amount of stones in the pit
     */
    void setPitStone(int pitNumber);
    
    /**
     * Sets every pit to have these amount of stones
     * @param stoneNumber - the amount of stones in every pit
     */
    void setAllPitStones(int stoneNumber);
    
    /**
     * Undos the board to the previous board
     */
    void undo();
    
    /**
     * Repaints the board
     */
    void repaint();
    
    /**
     * Sets the bounds of the JPanel
     * @param x - xAxis
     * @param y - yAxis
     * @param w - width
     * @param h - height
     */
    void setBounds(int x, int y, int w, int h);
    
    /**
     * Attaches a ChangeListener (view) to the board
     * @param listener - the listener being attached
     */
    void attach(ChangeListener listener);
    
    
}
