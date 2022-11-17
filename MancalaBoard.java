import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaBoard 
{
	private ArrayList<Pit> board;
	private ArrayList<Pit> previousBoard;
	private Player player1;
	private Player player2;
	private ArrayList<ChangeListener> listeners;
	
	public MancalaBoard()
	{
		initializeBoard();
		player1 = new Player(true);
		player2 = new Player(false);
	}
	
	public void initializeBoard()
	{
		board = new ArrayList<Pit>();
		previousBoard = new ArrayList<Pit>();
		
		for(int i = 0; i < 14; i++)
		{
			if(i != 6 || i != 13)
			{
				board.add(new Pit(4, i));
			}
			else
			{
				board.add(new Pit(0, i));
			}
		}
	}
	
	//Make sure that they don't click on an empty pit, or the opponent's pit
	public void moveStones(Pit pit)
	{
		setPreviousBoard();
		
		int numberOfStones = pit.getSize();
		int pitNumber = pit.getPitNumber();
		int lastPitNumber = 0;
		pit.emptyPit();
		
		if(player1.hasTurn())
		{
			while(numberOfStones != 0)
			{
				if(pitNumber == 13)
				{
					pitNumber = -1;
				}
				lastPitNumber = pitNumber;
				pitNumber++;
				board.get(pitNumber).addStones(1);
				numberOfStones--;
			}
			if(lastPitNumber != 6)
			{
				player1.setTurn(false);
				player2.setTurn(true);
			}
		}
		
		if(player2.hasTurn())
		{
			while(numberOfStones != 0)
			{
				while(numberOfStones != 0 && pitNumber < 14)
				{
					if(pitNumber == 6)
					{
						pitNumber++;
					}
					lastPitNumber = pitNumber;
					pitNumber++;
					board.get(pitNumber).addStones(1);
					numberOfStones--;
				}
				pitNumber = 0;
			}
			if(lastPitNumber != 13)
			{
				player1.setTurn(true);
				player2.setTurn(false);
			}
		}
	}
	
	public void setPreviousBoard()
	{
		ArrayList<Pit> tempBoard = new ArrayList<Pit>();
		for(int i = 0; i < 14; i++)
		{
			tempBoard.add(new Pit(board.get(i).getSize(), i));
		}
		previousBoard = tempBoard;
	}
	
	public void setCurrentBoardToPreviousBoard()
	{
		ArrayList<Pit> tempBoard = new ArrayList<Pit>();
		for(int i = 0; i < 14; i++)
		{
			tempBoard.add(new Pit(previousBoard.get(i).getSize(), i));
		}
		board = tempBoard;
		previousBoard.clear();
	}
	
	//The player that JUST made a move can undo before the other player makes their move
	public void undo()
	{
		Player player;
		Player otherPlayer;
		if(!player1.hasTurn())
		{
			player = player1;
			otherPlayer = player2;
		}
		else
		{
			player = player2;
			otherPlayer = player1;
		}
		if(player.getUndosLeft() > 0)
		{
			player.removeUndo();
			setCurrentBoardToPreviousBoard();
			player.setTurn(true);
			otherPlayer.setTurn(false);
		}
	}
}
