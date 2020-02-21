package aggregation;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.ui.icons.Icons;

public class SensorNode extends TreeNode 
{
	Integer[] value = new Integer[2];
	Integer[] result = new Integer[2];
	int numChildren;

	@Override
	public void onSelection() 
	{
		super.onSelection();
		setIcon(Icons.STATION);
		setColor(DEFAULT_COLOR);
	}
	
	protected void senseOri()
	{
		value[0] = this.getID(); 
		value[1] = (int)(Math.random() * 256 );
		setColor(new Color(value[1], 0, 0));
		System.out.println("Node" + this.getID() + " has value: " + value[1]);
	}
	
	protected void aggregate()
	{
		result = value;
		numChildren = 0;
		
		if(children.size() == 0)
		{
			send(parent, new Message(result, "DA"));
		}
	}
//Sense modifié pour la moyenne
	protected void sense()
	{
			value[0] = 1; 
			value[1] = (int)(Math.random() * 256 );
			setColor(new Color(value[1], 0, 0));
			System.out.println("Node" + this.getID() + " has value: " + value[1]);
		
	}
	
	@Override
	public void onMessage(Message message) {

		super.onMessage(message);
		
		if(message.getFlag().equals("DA"))
		{
			numChildren++;
			
			Integer[] max = (Integer[])message.getContent();
			//result = result[1] < max[1] ? max : result;
			result[0] = result[0] + max[0];
			result[1] = result[1] + max[1];
			
			if(numChildren == children.size()) 
			{
				if(parent != this)
				{
					send(parent, new Message(result, "DA"));
				}else
				{
					System.out.println("Maximum: " + result[1] + " was sensed by "+ result[0]);
					System.out.println("The average is " + (double) result[1] / result[0]);
					
				}
				
			}
		}
	}
	
	
	
}
