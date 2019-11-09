import java.util.List;

public class Node {
	private int id;
	private List<Node> neighbours;
	private int weightStorage;

	public Node() {
	}

	public Node(int id) {
		this.id = id;
		this.weightStorage = 0;
	}

	public Node(int id, int weightStorage) {
		this.id = id;
		this.weightStorage = weightStorage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWeightStorage() {
		return weightStorage;
	}

	public void setWeightStorage(int weightStorage) {
		this.weightStorage = weightStorage;
	}

	public void appendNodeToNeighbours(Node node) {
		this.neighbours.add(node);
	}

	public Node getNodeFromNeighbours(int nr) {
		return this.neighbours.get(nr);
	}
}
