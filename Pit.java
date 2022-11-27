import java.util.*;

public class Pit 
{
	private ArrayList<Stone> stones;
	private Player player;
	private int pitNumber;
	private Pit correlation;
	
	public Pit(int numberOfStones, int pitNumber)
	{
		stones = new ArrayList<Stone>();
		this.pitNumber = pitNumber;
		for(int i = 0; i < numberOfStones; i++)
		{
			stones.add(new Stone());
		}
	}
	
	public int getSize()
	{
		return stones.size();
	}
	
	public int getPitNumber()
	{
		return pitNumber;
	}
	
	public void emptyPit()
	{
		stones.clear();
	}
	
	public void addStones(int number)
	{
		for(int i = 0; i < number; i++) 
		{
			stones.add(new Stone());
		}
	}
	
	public void setCorrelation(Pit pit)
	{
		this.correlation = pit;
		pit.correlation = this;
	}
	
	public Pit getCorrelation()
	{
		return correlation;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}
