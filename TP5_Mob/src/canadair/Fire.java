package canadair;

import java.util.ArrayList;
import java.util.Random;

import io.jbotsim.core.Node;
import io.jbotsim.ui.icons.Icons;

/* Gardez cette classe telle quelle */
public class Fire extends Node {
    static ArrayList<Fire> allFires = new ArrayList<Fire>();
    Random r=new Random();

    @Override
    public void onStart(){
        allFires.add(this);
        disableWireless();
        setIcon(Icons.FIRE);
    }

    @Override
    public void onClock(){
        if (Math.random() < 0.01 && allFires.size() < 100)
            propagate();
    }

    public void propagate(){
        double x = getX() + r.nextDouble() * 20 - 10;
        double y = getY() + r.nextDouble() * 20 - 10;
        for (Fire fire : allFires)
            if (fire.distance(x, y) < 10)
                return;
        getTopology().addNode(x, y, new Fire());
    }

    @Override
    public void die(){
        super.die();
        allFires.remove(this);
    }
}