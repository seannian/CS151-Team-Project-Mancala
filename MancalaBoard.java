import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaBoard 
{
	private ArrayList<Pit> board;
	private ArrayList<Pit> previousBoard;
	private Player player1;
	private Player player2;
	private Player playerWithLastTurn;
	private ArrayList<ChangeListener> listeners;
	private boolean finished;
	
	public MancalaBoard(int numStones)
	{
		finished = false;
		listeners = new ArrayList<ChangeListener>();
		player1 = new Player(true);
		playerWithLastTurn = player1;
		player2 = new Player(false);
		initializeBoard();
		System.out.println("Player A turn: " + player1.hasTurn());
		System.out.println("Player B turn: " + player2.hasTurn());
	}
	
	public void attach(ChangeListener listener)
	{
		listeners.add(listener);
	}
	
	public void initializeBoard()
	{
		board = new ArrayList<Pit>();
		previousBoard = new ArrayList<Pit>();
		
		for(int i = 0; i < 14; i++)
		{
			board.add(new Pit(0, i));
		}
		
		addCorrelation(board);
	}
	
	public void setBoard(int numStones)
	{
		for(int i = 0; i < 14; i++)
		{
			if(i != 6 && i != 13)
			{
				board.get(i).addStones(numStones);
			}
		}
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}
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
					System.out.println(pit.getPlayer() + " has stolen marbles!");
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
				checkSteal(player1, lastPitNumber);
				player1.setTurn(false);
				player2.setTurn(true);
			}
			playerWithLastTurn = player1;
		}
		else
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
				checkSteal(player2, lastPitNumber);
				player1.setTurn(true);
				player2.setTurn(false);
			}
			playerWithLastTurn = player2;
		}
		
		if(player1SideEmpty())
		{
			emptyPlayer2Side();
		}
		else if(player2SideEmpty())
		{
			emptyPlayer1Side();
		}
		
		if(!checkIfBoardIsEmpty())
		{
			System.out.println("Player A turn: " + player1.hasTurn());
			System.out.println("Player B turn: " + player2.hasTurn());
		}
		else
		{
			System.out.println(checkWinner());
			finished = true;
		}
		
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}
	}
	
	public boolean isFinished()
	{
		return finished;
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
		if(previousBoard.isEmpty())
		{
			System.out.println("Cannot undo as the previous board has already been undone!");
			return;
		}
		
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
			setCurrentBoardToPreviousBoard();
			if(playerWithLastTurn == player)
			{
				player.removeUndo();
				player.setTurn(true);
				otherPlayer.setTurn(false);
			}
			else
			{
				otherPlayer.removeUndo();
			}
			if(finished)
			{
				finished = false;
			}
		}
		
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}
	}
	
	public boolean checkIfBoardIsEmpty()
	{
		boolean check = true;
		for(int i = 0; i < 14; i++)
		{
			if(board.get(i).getSize() != 0 && i != 6 && i != 13)
			{
				check = false;
			}
		}
		return check;
	}
	
	public boolean player1SideEmpty()
	{
		boolean check = true;
		for(int i = 0; i < 6; i++)
		{
			if(board.get(i).getSize() != 0)
			{
				check = false;
			}
		}
		return check;
	}
	
	public boolean player2SideEmpty()
	{
		boolean check = true;
		for(int i = 7; i < 13; i++)
		{
			if(board.get(i).getSize() != 0)
			{
				check = false;
			}
		}
		return check;
	}
	
	public void emptyPlayer1Side()
	{
		int size = 0;
		for(int i = 0; i < 6; i++)
		{
			size += board.get(i).getSize();
			board.get(i).emptyPit();
		}
		board.get(6).addStones(size);
	}
	
	public void emptyPlayer2Side()
	{
		int size = 0;
		for(int i = 7; i < 13; i++)
		{
			size += board.get(i).getSize();
			board.get(i).emptyPit();
		}
		board.get(13).addStones(size);
	}
	
	public String checkWinner()
	{
		if(board.get(6).getSize() > board.get(13).getSize())
		{
			return "Player A has won!";
		}
		else if(board.get(6).getSize() < board.get(13).getSize())
		{
			return "Player B has won!";
		}
		else
		{
			return "It's a tie!";
		}
	}
	
	public ArrayList<Pit> getBoard()
	{
		return board;
	}
	
	public Player getPlayerWithTurn()
	{
		if(player1.hasTurn())
		{
			return player1;
		}
		else
		{
			return player2;
		}
	}
	
	public Player getPlayerWithLastTurn()
	{
		return playerWithLastTurn;
	}
	
	public Player getPlayer1()
	{
		return player1;
	}
	
	public Player getPlayer2()
	{
		return player2;
	}
}
