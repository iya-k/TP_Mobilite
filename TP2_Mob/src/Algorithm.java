import java.util.ArrayList;
import java.util.List;

import io.jbotsim.core.Point;
public class Algorithm {
	
	protected List<Point> points;
	
	public void setPoints(List<Point> l) 
	{
		points = l;
		
	}
	
	public List<Point> getItineary(){
		
		return points;
	}

	protected List<Point> nearestNeighbors() {
		
		List<Point> itineary = new ArrayList<>();
			
			double d = 0;
			int size = points.size();
			Point first = points.get(0);
			itineary.add(first);
			points.remove(0);
			
			for(int i = 1; i < size ; i++) 
			{
				d = itineary.get(i - 1).distance(first);
				int proche_voisin = 0;
				for(int j = 1; j < points.size() ; j++) 
				{
					Point voisin = points.get(j);
					
					if(itineary.get(i - 1).distance(voisin) < d) 
					{
						d = itineary.get(i - 1).distance(voisin);
						
						proche_voisin = j;
					}
					itineary.add(points.get(proche_voisin));
					points.remove(proche_voisin);
					
				}
				
			}
			return itineary;
	
	}
	
protected List<Point> voyageurCommerce() {
		
		List<Point> itineary = new ArrayList<>();
			
			double d = 0;
			int size = points.size();
			Point first = points.get(0);
			itineary.add(first);
			points.remove(0);
			
			for(int i = 1; i < size ; i++) 
			{
				d = itineary.get(i - 1).distance(first);
				int proche_voisin = 0;
				for(int j = 1; j < points.size() ; j++) 
				{
					Point voisin = points.get(j);
					
					if(itineary.get(i - 1).distance(voisin) < d) 
					{
						d = itineary.get(i - 1).distance(voisin);
						
						proche_voisin = j;
					}
					itineary.add(points.get(proche_voisin));
					points.remove(proche_voisin);
					
				}
				
			}
			return itineary;
	
	}
	
}
