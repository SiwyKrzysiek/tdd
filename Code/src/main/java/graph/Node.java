package graph;

import java.util.List;
import java.util.Objects;

public class Node<T> {
	private int id;
	private T value;
	private List<Node> neighbours;
	private int weightStorage;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node<?> node = (Node<?>) o;
		return id == node.id &&
				weightStorage == node.weightStorage &&
				value.equals(node.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, value, weightStorage);
	}

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
