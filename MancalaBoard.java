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
	
	public MancalaBoard(int numStones)
	{
		initializeBoard(numStones);
		player1 = new Player(true);
		player2 = new Player(false);
	}
	
	public void initializeBoard(int numStones)
	{
		board = new ArrayList<Pit>();
		previousBoard = new ArrayList<Pit>();
		
		for(int i = 0; i < 14; i++)
		{
			if(i != 6 || i != 13)
			{
				board.add(new Pit(numStones, i));
			}
			else
			{
				board.add(new Pit(0, i));
			}
		}
		
		addCorrelation(board);
	}
	
	public void addCorrelation(ArrayList<Pit> tempBoard)
	{
		int j = 12;
		for(int i = 0; i < 6; i++)
		{
			tempBoard.get(i).setCorrelation(tempBoard.get(j));
			j--;
		}
		for(int i = 0; i < 6; i++)
		{
			tempBoard.get(i).setPlayer(player1);
		}
		for(int i = 7; i < 13; i++)
		{
			tempBoard.get(i).setPlayer(player2);
		}
	}
	
	public void checkSteal(Player player, int lastPitNumber)
	{
		Pit pit = board.get(lastPitNumber);
		if(player == player1)
		{
			if(pit.getPlayer() == player1 && pit.getSize() == 1)
			{
				if(pit.getCorrelation().getSize() != 0)
				{
					int numStones = pit.getCorrelation().getSize() + 1;
					pit.emptyPit();
					pit.getCorrelation().emptyPit();
					board.get(6).addStones(numStones);
				}
			}
		}
		else if(player == player2)
		{
			if(pit.getPlayer() == player2 && pit.getSize() == 1)
			{
				if(pit.getCorrelation().getSize() != 0)
				{
					int numStones = pit.getCorrelation().getSize() + 1;
					pit.emptyPit();
					pit.getCorrelation().emptyPit();
					board.get(13).addStones(numStones);
				}
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
				pitNumber++;
				if(pitNumber == 13)
				{
					pitNumber = 0;
				}
				lastPitNumber = pitNumber;
				board.get(pitNumber).addStones(1);
				numberOfStones--;
			}
			if(lastPitNumber != 6)
			{
				player1.setTurn(false);
				player2.setTurn(true);
			}
		}
		
		else if(player2.hasTurn())
		{
			while(numberOfStones != 0)
			{
				pitNumber++;
				if(pitNumber == 14)
				{
					pitNumber = 0;
				}
				if(pitNumber == 6)
				{
					pitNumber++;
				}
				lastPitNumber = pitNumber;
				board.get(pitNumber).addStones(1);
				numberOfStones--;
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
		addCorrelation(previousBoard);
	}
	
	public void setCurrentBoardToPreviousBoard()
	{
		ArrayList<Pit> tempBoard = new ArrayList<Pit>();
		for(int i = 0; i < 14; i++)
		{
			tempBoard.add(new Pit(previousBoard.get(i).getSize(), i));
		}
		board = tempBoard;
		addCorrelation(board);
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
