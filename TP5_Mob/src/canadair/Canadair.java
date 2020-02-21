package canadair;
//faire en sorte que un seul canadair puise aller et non les deux à la fois
/*
 * 
 * L = P1, P2, P3, P4
 * si x -> S et X € L ne fait rien
 *           sinon ajoute X à L
 *           
 * si le feux est éteint, efface X de L
 * 
 */

import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.core.Point;
import io.jbotsim.ui.icons.Icons;

public class Canadair extends WaypointNode {
    Point parking;
    Point lake = new Point(50,50);

    public Canadair(){
        setIcon(Icons.CANADAIR);
        setIconSize(16);
        setCommunicationRange(120);
        setSensingRange(30);
    }
    
    @Override
    public void onStart() {
        parking = getLocation();
        super.setSpeed(10);
    }

	@Override
	public void onSensingIn(Node node) 
	{
		super.onSensingIn(node);
		
		if(node instanceof Fire)
		{
			((Fire)node).die();
		}
	}
    
    @Override
	public void onMessage(Message message) {
		super.onMessage(message);
		
		if(message.getContent() instanceof Point)
		{
			Point targetSensor = (Point) message.getContent();
			double x = targetSensor.getX();
			double y = targetSensor.getY();
			double r = getSensingRange();//rayon de detection du canadaire
			
			Point t1 = new Point(x-r, y+r);//aller en haut
			Point t2 = new Point(x+r, y+r);//en bas
			Point t3 = new Point(x+r, y-r);//bas droite
			Point t4 = new Point(x-r, y-r);//bas gauche
			
			addDestination(lake);
			addDestination(t1);
			addDestination(t2);
			addDestination(t3);
			addDestination(t4);
			//on peut faire tout ceci dans la classe Sensor dans onSensing
			//ne marche pas car le canadair fait des aller-retour sur le même point même si le feu est éteint 
			//et n'arrive pas à éteindre les nouveaux feux
			//
			addDestination((Point) message.getContent());
			addDestination(parking);
			
		}
    }
    
}