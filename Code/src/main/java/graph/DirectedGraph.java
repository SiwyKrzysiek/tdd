package graph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DirectedGraph<T> {
	private final HashSet<Node<T>> nodes;
	private final Set<DirectedEdge<T>> edges;

	public DirectedGraph() {
		nodes = new HashSet<>();
		edges = new HashSet<>();
	}

	public void addVertex(Node<T> vertex) {
		if (nodes.stream().map(e -> e.getId()).collect(Collectors.toSet()).contains(vertex.getId())) {
			throw new IllegalArgumentException("Vertex with provided id is already in set");
		}

		nodes.add(vertex);
	}

	public void createEdge(Node<T> a, Node<T> b) {
		if (!nodes.contains(a) || !nodes.contains(b)) {
			throw new IllegalArgumentException("Graph doesn't contain one of the nodes");
		}

		edges.add(new DirectedEdge<T>(a, b));
		a.addNeighbour(b);
	}

	public void createEdge(Node<T> a, Node<T> b, int weight) {
		if (!nodes.contains(a) || !nodes.contains(b)) {
			throw new IllegalArgumentException("Graph doesn't contain one of the nodes");
		}

		edges.add(new DirectedEdge<T>(a, b, weight));
		a.addNeighbour(b);
	}

	public HashSet<Node<T>> getNodes() {
		return nodes;
	}

	public Set<DirectedEdge<T>> getEdges() {
		return edges;
	}
}
