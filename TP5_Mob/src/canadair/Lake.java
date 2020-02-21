package canadair;

import io.jbotsim.core.Node;
import io.jbotsim.ui.icons.Icons;

public class Lake extends Node {

    public Lake(){
        setIcon(Icons.LAKE);
        setIconSize(40);
        disableWireless();
    }
}