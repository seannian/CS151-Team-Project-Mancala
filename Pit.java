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
 * The Pit class contains an ArrayList of stones, the player who owns the pit, the number of the pit, and which correlation the pit has to another pit.
 */
public class Pit 
{
	private ArrayList<Stone> stones;
	private Player player;
	private int pitNumber;
	private Pit correlation;
	
	/**
	 * Creates a pit with a number of stones and the pit number
	 * @param numberOfStones - the number of stones in the pit
	 * @param pitNumber - the number of the pit
	 */
	public Pit(int numberOfStones, int pitNumber)
	{
		stones = new ArrayList<Stone>();
		this.pitNumber = pitNumber;
		for(int i = 0; i < numberOfStones; i++)
		{
			stones.add(new Stone());
		}
	}
	
	/**
	 * Returns how many stones are in the pit
	 * @return - the number of stones in the pit
	 */
	public int getSize()
	{
		return stones.size();
	}
	
	/**
	 * Returns the pit number
	 * @return - pit number
	 */
	public int getPitNumber()
	{
		return pitNumber;
	}
	
	/**
	 * Empties the pit
	 */
	public void emptyPit()
	{
		stones.clear();
	}
	
	/**
	 * Adds a certain amount of stones to the pit
	 * @param number - number of stones to add
	 */
	public void addStones(int number)
	{
		for(int i = 0; i < number; i++) 
		{
			stones.add(new Stone());
		}
	}
	
	/**
	 * Sets the correlation of the pits
	 * @param pit - the pit that needs correlation to be added
	 */
	public void setCorrelation(Pit pit)
	{
		this.correlation = pit;
		pit.correlation = this;
	}
	
	/**
	 * Returns the correlation of the pit
	 * @return - the correlation of the pit
	 */
	public Pit getCorrelation()
	{
		return correlation;
	}
	
	/**
	 * Set's the player who owns the pit
	 * @param player - the player who owns the pit
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	/**
	 * Returns the player who owns the pit
	 * @return - player who owns the pit
	 */
	public Player getPlayer()
	{
		return player;
	}
}
