
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.Shape;

public class DecoratedBoard extends JPanel {
    private int x = 450;
    private int y = 215;
    private int width = 80;
    private MancalaBoard board = new MancalaBoard(0);
    private Point mousePoint;
    private ArrayList<PitShape> pitShapes;

    public DecoratedBoard(int stones) {
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
                if (s.contains(mousePoint) && board.getBoard().get(s.getPitNum()).getSize() != 0 && s.getPlayer() == board.getPlayerWithTurn()){
                	setPitStone(s.getPitNum());
                	break;
                }
            }

            repaint();
        }
    }
    
    public void undo()
    {
    	board.undo();
    }
    
    public String getTurn()
    {
    	if(board.getPlayerWithTurn() == board.getPlayer1())
    	{
    		return "Player A";
    	}
    	else
    	{
    		return "Player B";
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
        int j = 12;
        for (int i = 0; i < 13; i++) {
            PitShape pitShape;
            g2.setPaint(Color.BLACK);
            g2.setStroke(new BasicStroke(5));
            if (i < 6)
            {
            	pitShape = new PitShape(i,x,y+200,width,i, board.getPlayer1());
                pitShape.draw(g2,x,y+200,i,width,new Color(102,102,102));
                pitShapes.add(pitShape);
            }
            else
            {
            	if(i == 6)
            	{
            		i++;
            	}
            	pitShape = new PitShape(j,x,y,width,i-7,board.getPlayer2());
                pitShape.draw(g2,x,y,i-7,width,new Color(204,204,204));
                pitShapes.add(pitShape);
                j--;
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
        
        g2.drawString("It is " + getTurn() + "'s turn", 635, 875);
        if(board.isFinished())
        {
        	g2.drawString(board.checkWinner(), 635, 925);
        }

        g2.setColor(new Color(255,102,102));
        /** Draw 3 stones */

        j = 7;
        for(int i=0;i<6;i++){
            {
            	drawStonesOfPit(g2, board.getBoard().get(i).getSize(), 450 + (i) * 100, 430);
            }
        }
        for(int i = 12; i > 6; i--)
        {
        	drawStonesOfPit(g2, board.getBoard().get(j).getSize(), 450 + (i-7) * 100, 240);
        	j++;
        }
        
        //test();

        g2.setPaint(Color.BLACK);
        g2.setBackground(new Color(102,102,102));
        /** Mancala */
        g2.drawRect(320, 200, 80, 350);

        g2.drawRect(1070, 200, 80, 350);
        g2.setPaint(new Color(204,204,204));
        g2.fillRect(320, 200, 80, 350);
        g2.setPaint(new Color(102,102,102));

        g2.fillRect(1070, 200, 80, 350);

        g2.setColor(new Color(255,102,102));
        drawStonesOfMancala(g2, board.getBoard().get(13).getSize(),320,300);
        drawStonesOfMancala(g2, board.getBoard().get(6).getSize(),1070,300);
        
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
    
    public void drawStonesOfMancala(Graphics2D g2, int numberOfStones,int xPit,int yPit){
        int x = 0;
        int y = 0;
        int counter = 0;

        for(int i=0;i<numberOfStones;i++){
            g2.fillOval(xPit+x, yPit+y, 10, 10);
            x+=10;
            counter++;
            if(counter == 8){
                y+=10;
                counter = 0;
                x=0;
            }
        }
    }

    public void setPitStone(int pitNumber){
        board.moveStones(board.getBoard().get(pitNumber));
    }

    public void setAllPitStones(int stoneNumber){
        //for(int i=0;i< arr.length;i++){
        //    arr[i]=stoneNumber;
        //}
        
        board.setBoard(stoneNumber);
    }
    
    public void test()
    {
    	for(int i = 0; i < 14; i++)
    	{
    		System.out.print(board.getBoard().get(i).getSize() + " ");
    	}
    	System.out.println();
    }


}
