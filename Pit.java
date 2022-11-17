import java.util.*;

public class Pit 
{
	private ArrayList<Stone> stones;
	private int pitNumber;
	
	public Pit(int numberOfStones, int pitNumber)
	{
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
}
