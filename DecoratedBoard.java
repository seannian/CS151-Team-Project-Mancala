
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DecoratedBoard extends JPanel {
    private int x = 450;
    private int y = 215;
    private int width = 80;
    private int[] arr = new int[12];
    private Point mousePoint;
    private ArrayList<PitShape> pitShapes;


    public DecoratedBoard(int stones) {
        //stoneNumber = stones;
        //setAllPitStones(stoneNumber);
        pitShapes = new ArrayList<>();
        addMouseListener(new MousePressedListener());

    }

    private class MousePressedListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event)
        {
             mousePoint = event.getPoint();
            for (PitShape s : pitShapes)
            {
                if (s.contains(mousePoint)){
                    setPitStone(0,0);
                    setPitStone(1,5);
                    setPitStone(2,5);
                    setPitStone(3,5);
                    setPitStone(4,5);
                    setPitStone(5,4);
                    setPitStone(6,4);
                    setPitStone(7,4);
                    setPitStone(8,4);
                    setPitStone(8,4);
                    setPitStone(10,4);
                    setPitStone(11,4);
                }
            }

            repaint();
        }
    }

    public void paintComponent(Graphics g) {



        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Double(250, 110, 975, 730);
        g2.setPaint(new Color(255,255,204));

        g2.fill(rectangle);
        g2.setPaint(Color.BLACK);
        g2.setPaint(Color.RED);

//
//
//        PitShape pitShape = new PitShape(0,0,100);
//        pitShape.draw(g2,0,0,1,200,Color.RED);

        /** Pits */

        for (int i = 0; i < 12; i++) {
            PitShape pitShape;
            g2.setPaint(Color.BLACK);
            g2.setStroke(new BasicStroke(5));
            if (i < 6){
                pitShape = new PitShape(i,x,y,width,i);
                pitShape.draw(g2,x,y,i,width,new Color(204,204,204));
                pitShapes.add(pitShape);
            }
            else{
                pitShape = new PitShape(i,x,y+200,width,i-6);
                pitShape.draw(g2,x,y+200,i-6,width,new Color(102,102,102));
                pitShapes.add(pitShape);
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

        g2.setColor(new Color(255,102,102));
        /** Draw 3 stones */

        for(int i=0;i<12;i++){
            if (i < 6) {
                drawStonesOfPit(g2, arr[i], 450 + i * 100, 240);
            }else {
                drawStonesOfPit(g2, arr[i], 450 + (i-6) * 100, 430);
            }
        }


        g2.setPaint(Color.BLACK);
        g2.setBackground(new Color(102,102,102));
        /** Mancala */
        g2.drawRect(320, 200, 80, 350);

        g2.drawRect(1070, 200, 80, 350);
        g2.setPaint(new Color(204,204,204));
        g2.fillRect(320, 200, 80, 350);
        g2.setPaint(new Color(102,102,102));

        g2.fillRect(1070, 200, 80, 350);


        g2.setColor(Color.BLACK);

        AffineTransform at = new AffineTransform();
        at.setToRotation(-Math.toRadians(90), 80, 100);
        g2.setTransform(at);
        g2.drawString("MANCALA B", -645, 605);

        AffineTransform at2 = new AffineTransform();
        at2.setToRotation(Math.toRadians(90), 80, 100);
        g2.setTransform(at2);
        g2.drawString("MANCALA A", 650, -2180);
    }
    public void drawStonesOfPit(Graphics2D g2, int numberOfStones,int xPit,int yPit){
        int summer=0;
        int stoneCounter = 0;
        int y = yPit;
        for(int i=0;i<numberOfStones;i++){
            if(stoneCounter == 6){
                y = y+14;
                summer = 0;
                stoneCounter = 0;
            }
            summer=summer+12;
            g2.fillOval(xPit + summer, y, 10, 10);
            stoneCounter++;
        }
    }

    public void setPitStone(int pitNumber,int numberOfStones){
        arr[pitNumber] = numberOfStones;
    }

    public void setAllPitStones(int stoneNumber){
        for(int i=0;i< arr.length;i++){
            arr[i]=stoneNumber;
        }
    }




}
