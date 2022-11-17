import java.util.*;

public class Player 
{
	private boolean turn;
	private int undosLeft;
	
	public Player(boolean turn)
	{
		this.turn = turn;
		undosLeft = 3;
	}
	
	public boolean hasTurn()
	{
		return turn;
	}
	
	public void setTurn(boolean turn)
	{
		this.turn = turn;
	}
	
	public int getUndosLeft()
	{
		return undosLeft;
	}
	
	public void removeUndo()
	{
		undosLeft--;
	}
}
