package empty;
import io.jbotsim.core.Node;
import io.jbotsim.core.Message;
public class EmptyNode extends Node{
public void onStart() {
// JBotSim executes this method on each node upon initialization
}
public void onSelection() {
// JBotSim executes this method on a selected node
}
public void onClock() {
// JBotSim executes this method on each node in each round
}
public void onMessage(Message message) {
// JBotSim executes this method on a node every time it receives
// a message
}
}