package loner;
import io.jbotsim.core.Topology;
import io.jbotsim.ui.JViewer;

public class Main {

	public static void main(String[] args) {
		Topology tp = new Topology();
		//tp.setDefaultNodeModel(LonerGraphBased.class);
		tp.setDefaultNodeModel(LonerMessageBased.class);
		new JViewer(tp);
		tp.start();

	}

}
