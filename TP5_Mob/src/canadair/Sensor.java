package canadair;

import io.jbotsim.core.Color;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.ui.icons.Icons;

import java.util.ArrayList;
import java.util.List;

public class Sensor extends Node 
{
  
	Node parent = null;
    List<Fire> sensedFires = new ArrayList<>();
    int lastReceivedTime;

    @Override
    public void onStart() 
    {
        setIcon(Icons.SENSOR);
        setIconSize(16);
        setCommunicationRange(120);
        setSensingRange(60);
        lastReceivedTime = 0;//pour mettre à jour les capteurs voisin au bout de 3 ms
    }  
    
    //permet de detecter un feu
    @Override
	public void onSensingIn(Node node) 
    {
    	//pour afficher le feu, cliquer tout simplement dans le cercle
		super.onSensingIn(node);
		
		if(node instanceof Fire)
		{
			this.setColor(Color.red);
			sensedFires.add((Fire) node);
		}
	}
    
    //pour effacer le feu de la liste s'il n'est plus détecter
	@Override
	public void onSensingOut(Node node) 
	{
		super.onSensingOut(node);
		//le casting n'est pas obligatoire pour remove
		sensedFires.remove(node);
		if(sensedFires.isEmpty())
		{
			this.setColor(null);
		}
	}

	//construction de l'arbre couvrant avec l'algorithme de vague
	@Override
	public void onMessage(Message message) {
		
		super.onMessage(message);
		
		if(parent == null)
		{
			parent = message.getSender();
			getCommonLinkWith(parent).setWidth(3);
			getCommonLinkWith(parent).setColor(Color.yellow);
			sendAll(message);
		}
		if(message.getContent() instanceof Point && message.getSender() instanceof Sensor) 
		{
			if(sensedFires.isEmpty())
			{
				this.setColor(Color.orange);
			}
			send(parent, message);
			lastReceivedTime = getTime();
		}
	}
    
	@Override
    public void onClock() {
    	super.onClock();
    	
    	if(!sensedFires.isEmpty())
		{
    		send(parent, new Message(this.getLocation()));
		}
    	
    	if(getTime() - lastReceivedTime >= 10 && this.getColor() == Color.orange)
    		//si la difference est > 10 et que je suis en mode alerte
    	{
    		this.setColor(null);
    	}
    	
    }

}