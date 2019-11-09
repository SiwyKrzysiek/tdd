import java.util.List;

public class Node<T> {
	private int id;
	private T value;
	private List<Node> neighbours;
	private int weightStorage;

	public Node(T value) {
		this.value = value;
	}

	public Node(int id, T value) {
		this.id = id;
		this.value = value;
		this.weightStorage = 0;
	}

	public Node(int id, T value, int weightStorage) {
		this.id = id;
		this.value = value;
		this.weightStorage = weightStorage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public int getWeightStorage() {
		return weightStorage;
	}

	public void setWeightStorage(int weightStorage) {
		this.weightStorage = weightStorage;
	}

	public void addNeighbour(Node node) {
		this.neighbours.add(node);
	}

	public List<Node> getNeighbours() {
		return this.neighbours;
	}

}
