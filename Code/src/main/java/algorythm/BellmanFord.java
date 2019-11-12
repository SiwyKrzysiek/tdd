package algorythm;

import graph.DirectedEdge;
import graph.DirectedGraph;
import graph.Node;

import java.util.Iterator;
import java.util.Stack;

public class BellmanFord<T> implements Iterator<Node<T>> {
	private DirectedGraph<T> graph;
	private Node<T> startPoint;
	private int iteration;
	private int nodeId;
	private Stack<Node<T>> toVisit;

	public BellmanFord(DirectedGraph<T> graph, Node<T> start) {
		this.graph = graph;
		this.startPoint = start;
		this.iteration = 1;
		this.nodeId = 0;
		toVisit = new Stack<>();

		toVisit.push(start);
	}

	@Override
	public boolean hasNext() {
		return !toVisit.empty();
	}

	@Override
	public Node<T> next() {
		Node<T> currentNode = toVisit.pop();
		if (this.nodeId < graph.getNodes().size() - 1) {
			for (Node<T> obj : graph.getNodes()) {
				if (obj.getId() == this.nodeId + 1) {
					toVisit.push(obj);
					break;
				}
			}
		} else {
			if (this.iteration < graph.getNodes().size() - 1) {
				this.nodeId = -1;
				this.iteration++;
				toVisit.push(this.startPoint);
			}
		}
		if (currentNode.getWeightStorage() != Integer.MAX_VALUE) {
			for (Node<T> neighbour : currentNode.getNeighbours()) {
				int weight = 0;
				for (DirectedEdge edge : graph.getEdges()) {
					if (edge.getA().equals(currentNode) && edge.getB().equals(neighbour)) {
						weight = edge.getWeight().getAsInt();
						break;
					}
				}
				if (neighbour.getWeightStorage() == Integer.MAX_VALUE || currentNode.getWeightStorage() + weight < neighbour.getWeightStorage()) {
					neighbour.setWeightStorage(currentNode.getWeightStorage() + weight);
				}
			}
		}
		this.nodeId++;

		return currentNode;
	}
}
