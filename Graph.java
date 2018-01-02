import java.util.ArrayList;

public class Graph {
	private ArrayList<Node> nodes;

	public Graph() {
		this.nodes = new ArrayList<Node>();
	}

	public ArrayList<Node> getNodes() {
		return this.nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);

		return;
	}

	public Node addNode(String name) {
		Node node = null;

		// If such a node not already present, add it
		if (true) {
			node = new Node(name);
			this.nodes.add(node);
		}

		return node;
	}

	private Node getNodeByName(String name) {
		Node foundNode = null;

		for (Node node : this.nodes) {
			if (node.getName().equals(name)) {
				foundNode = node;
				break;
			}
		}

		return foundNode;
	}

	public String getPath(String fromNodeName, String toNodeName,
			boolean useDFS) throws Exception {
		Node node = null;
		String path = null;
		StringBuffer strBuffer = null;

		/* Mark all nodes as not yet visited */
		this.resetAllVisited();

		node = getNodeByName(fromNodeName);
		if (node == null) {
			throw new Exception("Node '" + fromNodeName + "' does not exist");
		}
		else {
			if (useDFS) {
				strBuffer = node.getPathToUseDFS(toNodeName);
			}
			else {
				strBuffer = node.getPathTo(toNodeName);
			}

			if (strBuffer != null) {
				path = strBuffer.toString();
			}
		}

		return path;
	}

	public void resetAllVisited() {
		for (Node node : nodes) {
			node.resetVisited();
		}
	}
}
