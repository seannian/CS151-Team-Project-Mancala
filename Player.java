/**
 * Fall 2022 CS151 Team Project
 * Simple Mancala Board
 * Instructor: Dr. Suneuy Kim
 * 
 * @author Sean Nian, Abdugafur Dalerzoda, Xianqiao Zhang, Aarushi  Gautam
 * @version 1.0 12/1/2022
 */

import java.util.*;

/**
 * Creates a Player that tracks how many undos they have left and if they have their turn
 */
public class Player 
{
	private boolean turn;
	private int undosLeft;
	
	/**
	 * Creates a player with a turn and 3 undos left
	 * @param turn
	 */
	public Player(boolean turn)
	{
		this.turn = turn;
		undosLeft = 3;
	}
	
	/**
	 * Returns true or false depending on if the player has a turn
	 * @return - a boolean true or false if the player has a turn
	 */
	public boolean hasTurn()
	{
		return turn;
	}
	
	/**
	 * Sets if the player has a turn
	 * @param turn - a boolean true or false
	 */
	public void setTurn(boolean turn)
	{
		this.turn = turn;
	}
	
	/**
	 * Returns the amount of undos left the player has
	 * @return - the amount of undosLeft
	 */
	public int getUndosLeft()
	{
		return undosLeft;
	}
	
	/**
	 * Removes an undo if the player has used an undo
	 */
	public void removeUndo()
	{
		undosLeft--;
	}
}
