package aggregation;

import java.util.ArrayList;
import java.util.List;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;

public class TreeNode extends Node 
{
	Node parent = null;
	List<Node> children = new ArrayList<>();
	
	
	@Override
	public void onSelection() 
	{
		parent = this;
		sendAll(new Message(this, "ST"));
		setColor(Color.RED);
	}
	
	@Override
	public void onMessage(Message message) 
	{
		if(message.getFlag().equals("ST"))
		{
			if(parent == null) {
				parent = message.getSender();
				getCommonLinkWith(parent).setWidth(4);
				sendAll(new Message(parent, "ST"));
			}else
			{
				if(message.getContent() == this)
				{
					children.add(message.getSender());
				}
				
			}
		}
		
		
	}
	
	

}
