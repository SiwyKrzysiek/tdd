package graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mathematical graph representation having set of Vertices and Edges
 *
 * @param <T> Node value type
 */
public class Graph<T> {
	private final HashSet<Node<T>> nodes;
	private final Set<UndirectedEdge<T>> edges;

    public Graph() {
		nodes = new HashSet<>();
		edges = new HashSet<>();
	}

	public void addVertex(Node<T> vertex) {
	    if (nodes.stream().map(e -> e.getId()).collect(Collectors.toSet()).contains(vertex.getId())) {
	        throw new IllegalArgumentException("Vertex with provided id is already in set");
        }

		nodes.add(vertex);
	}

	public void removeVertex(Node<T> vertex) {
		nodes.remove(vertex);
	}

    public void createEdge(Node<T> a, Node<T> b) {
        if (!nodes.contains(a) || !nodes.contains(b)) {
            throw new IllegalArgumentException("Graph doesn't contain one of the nodes");
        }

        edges.add(new UndirectedEdge<T>(a, b));
        a.addNeighbour(b);
        b.addNeighbour(a);
    }

    public void createEdge(Node<T> a, Node<T> b, int weigh) {
	    if (!nodes.contains(a) || !nodes.contains(b)) {
	        throw new IllegalArgumentException("Graph doesn't contain one of the nodes");
        }
	    
	    edges.add(new UndirectedEdge<T>(a, b, weigh));
        a.addNeighbour(b);
        b.addNeighbour(a);
    }

    public Set<Node<T>> getNodes() {
        return nodes;
    }

    public Set<UndirectedEdge<T>> getEdges() {
        return edges;
    }
}
