package aggregation;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.ui.icons.Icons;
public class SensorNodeBool extends TreeNode 
{

	Boolean value = false;
	Integer result ;
	int numChildren;

	@Override
	public void onSelection() 
	{
		super.onSelection();
		setIcon(Icons.STATION);
		setColor(DEFAULT_COLOR);
	}

	protected void sense()
	{ 
		int booleen = (int)(Math.random() * 2);
		if(booleen == 0)
		{
			value = false;
		}else
		{
			value = true;
		}
		setColor(new Color((int)(Math.random() * 256 ), 0, 0));
		System.out.println("Node" + this.getID() + " has value: " + value);
	}

	protected void aggregate()
	{
		if(value)
		{
			result = 1;
		}else
		{
			result = 0;
		}
		
		numChildren = 0;

		if(children.size() == 0)
		{
			send(parent, new Message(result, "DA"));
		}
	}

	@Override
	public void onMessage(Message message) {

		super.onMessage(message);

		if(message.getFlag().equals("DA"))
		{
			numChildren++;

			result = (Integer )message.getContent() + result;


			if(numChildren == children.size()) 
			{
				if(parent != this)
				{
					send(parent, new Message(result, "DA"));
				}else
				{
					System.out.println("Nombre de true " + result);

				}

			}
		}
	}




}
