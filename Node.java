import java.util.ArrayList;

public class Node {
	private static final int QUEUE_SIZE = 30;
	private String name;
	private ArrayList<Node> adjacentNodes;
	private boolean isVisited;

	public Node(String name) {
		this.name = name;
		this.adjacentNodes = null;
		this.isVisited = false;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Node> getAdjacentNodes() {
		return adjacentNodes;
	}

	private Node getAdjacentByName(String name) {
		Node foundNode = null;

		if (adjacentNodes != null) {
			for (Node node : adjacentNodes) {
				if (node.getName().equals(name)) {
					foundNode = node;
					break;
				}
			}
		}

		return foundNode;
	}

	public void addAdjacentNode(Node node) {
		if (node == null) {
			System.out.println("addAdjacentNode: Error, null input node");
			return;
		}

		if (getAdjacentByName(node.getName()) == null) {
			if (adjacentNodes == null) {
				adjacentNodes = new ArrayList<Node>();
			}

			adjacentNodes.add(node);
		}
		else {
			System.out.println(node.getName() +
				" already exists as an adjacent node of " + this.getName());
		}
	}

	public void removeAdjacentNode(String name) {
		if (name == null) {
			System.out.println("removeAdjacentNode: Error, null node name");
			return;
		}
		else {
			for (int i = 0; i < adjacentNodes.size(); i++) {
				if (adjacentNodes.get(i).getName().equals(name)) {
					adjacentNodes.remove(i);
					break;
				}
			}
		}
	}

	public void removeAdjacentNode(Node node) {
		if (node == null) {
			System.out.println("removeAdjacentNode: Error, null input node");
			return;
		}
		else {
			removeAdjacentNode(node.getName());
		}
	}

	public void setVisited() {
		isVisited = true;
	}

	public void resetVisited() {
		isVisited = false;
	}

	public boolean isVisited() {
		return isVisited;
	}

	/* Uses Depth First Search instead of the default Breadth First Search */
	public StringBuffer getPathToUseDFS(String toNodeName) {
		StringBuffer resultStr = null;

		System.out.println("getPathTo called on " + this.getName());

		if (! this.isVisited() && adjacentNodes != null) {
			this.setVisited();

			for (Node node : adjacentNodes) {
				if (node.getName().equals(toNodeName)) {
					node.setVisited(); // For good measure
					resultStr = new StringBuffer(toNodeName);
					break;
				}
				else {
					resultStr = node.getPathTo(toNodeName);
					if (resultStr != null) {
						break;
					}
				}
			}
		}

		if (resultStr != null) {
			resultStr.insert(0, this.getName() + ".");
		}

		return resultStr;
	}

	public StringBuffer getPathTo(String toNodeName) {
		StringBuffer resultStr = null;

		Queue<Node> queue = new Queue<Node>(Node.QUEUE_SIZE);
		queue.add(this);
		Queue<String> queuePath = new Queue<String>(Node.QUEUE_SIZE);
		queuePath.add(this.getName());

		while (! queue.isEmpty()) {
			Node node = queue.poll();
			String path = queuePath.poll();

			String name = node.getName();
			System.out.println("getPathTo checking " + name + " with " + path);

			if (name.equals(toNodeName)) {
				resultStr = new StringBuffer(path);
				break;
			}
			else {
				if (node.adjacentNodes != null) {
					for (Node nextNode : node.adjacentNodes) {
						if (! nextNode.isVisited()) {
							queue.add(nextNode);
							queuePath.add(path + "." + nextNode.getName());
							nextNode.setVisited();
						}
					}
				}
			}
		}

		return resultStr;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer(this.name);
		boolean firstAdjacent = true;

		if (adjacentNodes != null) {
			buffer.append("(");
			for (Node node : adjacentNodes) {
				if (! firstAdjacent) {
					buffer.append(", ");
				}
				else {
					firstAdjacent = false;
				}

				buffer.append(node.getName());
			}
			buffer.append(")");
		}

		return buffer.toString();
	}
}
