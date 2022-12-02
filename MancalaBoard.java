/**
 * Fall 2022 CS151 Team Project
 * Simple Mancala Board
 * Instructor: Dr. Suneuy Kim
 * 
 * @author Sean Nian, Abdugafur Dalerzoda, Xianqiao Zhang, Aarushi  Gautam
 * @version 1.0 12/1/2022
 */

import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The model of the board which contains the data of the MancalaBoard.
 * Stores data in an Arraylist and has two players which views detecting if the data has changed
 */
public class MancalaBoard 
{
	private ArrayList<Pit> board;
	private ArrayList<Pit> previousBoard;
	private Player player1;
	private Player player2;
	private Player playerWithLastTurn;
	private ArrayList<ChangeListener> listeners;
	private boolean finished;
	
	/**
	 * Creates a MancalaBoard using ArrayLists of pits to store data
	 * @param numStones - number of stones in each pit
	 */
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
	
	/**
	 * Attaches a ChangeListener to the model to see if the data has changed
	 * @param listener - the ChangeListener being attached
	 * postcondition: a change listener will be added to the board
	 */
	public void attach(ChangeListener listener)
	{
		listeners.add(listener);
	}
	
	/**
	 * Helper method that initializes the ArrayLists of the board.
	 */
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
	
	/**
	 * Helper method that sets the number of stones in each pit
	 * @param numStones - number of stones in each pit
	 */
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
	
	/**
	 * Gives each pit a correlation to see which pit correlates to which in the steal function.
	 * @param tempBoard - the ArrayList that needs correlations to be added
	 */
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
	
	/**
	 * Checks if a player has stolen the other player's stones
	 * @param player - the player that is attempting to steal
	 * @param lastPitNumber - the last pit a stone has entered
	 * postcondition: If successful, stones will be moved into the player who has attempted to steal's mancala pit
	 */
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
	
	/**
	 * Moves the stones in a selected pit
	 * @param pit - the pit that was selected to have stones moved from
	 * postcondition: depending on where the last stone lands, the many variables, such as player turns, finished, and the board itself will change
	 */
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
	
	/**
	 * Checks to see if the Mancala game is done
	 * @return finished - if the Mancala game is over
	 */
	public boolean isFinished()
	{
		return finished;
	}
	
	/**
	 * Sets the previous board to the current one
	 * postcondition: the previous board is set to the current board
	 */
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
	
	/**
	 * Sets the current board to the previous, will happen if undo is called
	 * precondition: previous board must exist
	 * postcondition: the board is set to the previous one and the turn is given to the previous player, and previous board is cleared
	 */
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
	
	/**
	 * Undos if the player has enough undos left and a previous board exists
	 * postcondition: the board will be set to the previous board and the previous player will get their turn back
	 */
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
	
	/**
	 * Checks if the pits are empty
	 * @return - a boolean true or false if the pits are empty (not including the mancala pits)
	 */
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
	
	/**
	 * Checks if player 1's side is empty
	 * @return a boolean true or false if player 1's side is empty
	 */
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
	
	/**
	 * Checks if player 2's side is empty
	 * @return a boolean true or false if player 2's side is empty
	 */
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
	
	/**
	 * Empties player 1's side if player 2 does not have any more stones in his pits 
	 */
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
	
	/**
	 * Empties player 2's side if player 1 does not have any more stones in his pits 
	 */
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
	
	/**
	 * Checks for the winner at the end of the game
	 * @return a string that announces the winner
	 */
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
	
	/**
	 * Returns the state of the current board 
	 * @return the current board
	 */
	public ArrayList<Pit> getBoard()
	{
		return board;
	}
	
	/**
	 * Returns the player with the turn
	 * @return - the player with the turn
	 */
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
	
	/**
	 * Returns the player who had the last turn
	 * @return - player who had the last turn
	 */
	public Player getPlayerWithLastTurn()
	{
		return playerWithLastTurn;
	}
	
	/**
	 * Returns player1
	 * @return - player1
	 */
	public Player getPlayer1()
	{
		return player1;
	}
	
	/**
	 * Returns player2
	 * @return - player2
	 */
	public Player getPlayer2()
	{
		return player2;
	}
}
