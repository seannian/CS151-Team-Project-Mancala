/**
 * Fall 2022 CS151 Team Project
 * Simple Mancala Board
 * Instructor: Dr. Suneuy Kim
 * 
 * @author Sean Nian, Abdugafur Dalerzoda, Xianqiao Zhang, Aarushi  Gautam
 * @version 1.0 12/1/2022
 */

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * PitShape contains what the pits look like and draws out each pit given x and y coordinates
 */
public class PitShape {
    private int x;
    private int y;
    private int width;
    private int pitNum;
    private Player player;
    
    /**
     * Creates a PitShape
     * @param pitNum - the pit number the pit is in
     * @param x - xAxis
     * @param y - yAxis
     * @param width - width of the pit
     * @param i - variable to add to each axis to relocate where to draw the Pit
     * @param player - player who owns the pit
     */
    public PitShape(int pitNum,int x, int y, int width,int i, Player player)
    {
        this.x = x+i*100;
        this.y = y;
        this.width = width;
        this.pitNum = pitNum;
        this.player = player;
    }

    /**
     * Will draw the PitShape
     * @param g2 - the graphics that is being used to draw the pit
     * @param x - xAxis
     * @param y - yAxis
     * @param i - variable to add to each axis to relocate where to draw the Pit
     * @param width - width of the pit
     * @param color - color of the pit
     */
    public void draw(Graphics2D g2,int x,int y,int i, int width, Color color)
    {

        if(width == 80) {
            g2.drawOval(x + i * 100, y, width, width);
            g2.setPaint(color);
            g2.fillOval(x + i * 100, y, width, width);
        }else if(width == 84){
            g2.drawRect(x + i * 100, y, width, width);
            g2.setPaint(color);
            g2.fillRect(x + i * 100, y, width, width);
        }
        else{
            g2.setBackground(color);
            g2.drawRect(x, y, width, width+260);
            g2.fillRect(x, y, width, width+260);
        }
    }

    /**
     * Returns the xAxis
     * @return - xAxis
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the xAxis
     * @param x - xAxis
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the yAxis
     * @return - yAxis
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the yAxis
     * @param y - yAxis
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the pit
     * @return - width of the pit
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the pit
     * @param width - width of the pit
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Contains method which checks to see if point P is in the PitShape
     * @param p - point P being checked
     * @return - true or false if point P is in the pit
     */
    public boolean contains(Point2D p)
    {
        return getX() <= p.getX() && p.getX() <= getX() + getWidth()
                && getY() <= p.getY() && p.getY() <= getY() + getWidth();
    }

    /**
     * Returns the pit number
     * @return - the pit number
     */
    public int getPitNum(){
        return pitNum;
    };
    
    /**
     * Returns the player who owns the pit
     * @return - the player who owns the pit
     */
    public Player getPlayer()
    {
    	return player;
    }
}
