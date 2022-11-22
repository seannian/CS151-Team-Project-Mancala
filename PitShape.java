import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class PitShape {
    private int x;
    private int y;
    private int width;
    private int pitNum;
    public PitShape(int pitNum,int x, int y, int width,int i)
    {
        this.x = x+i*100;
        this.y = y;
        this.width = width;
        this.pitNum = pitNum;
    }

    public void draw(Graphics2D g2,int x,int y,int i, int width, Color color)
    {

        g2.drawOval(x+i*100, y, width, width);
        g2.setPaint(color);
        g2.fillOval(x+i*100, y, width, width);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean contains(Point2D p)
    {
        return getX() <= p.getX() && p.getX() <= getX() + getWidth()
                && getY() <= p.getY() && p.getY() <= getY() + getWidth() / 2;
    }

    public int getPitNum(){
        return pitNum;
    };
}
