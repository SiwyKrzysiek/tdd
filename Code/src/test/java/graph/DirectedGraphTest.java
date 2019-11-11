package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DirectedGraphTest {
	@Test
	void addVertexWithDifferentID() {
		// Given
		DirectedGraph<Integer> graph = new DirectedGraph<>();

		// When
		graph.addVertex(new Node<Integer>(0, 5));
		graph.addVertex(new Node<Integer>(1, 7));

		// Then no exception
	}

	@Test
	void addVertexWithSameID_ThrowsException() {
		// Given
		DirectedGraph<Integer> graph = new DirectedGraph<>();

		// When
		graph.addVertex(new Node<Integer>(0, 5));

		//Then
		assertThrows(IllegalArgumentException.class, () -> graph.addVertex(new Node<Integer>(0, 7)));
	}

	@Test
	void addEdge() {
		//Given
		DirectedGraph<Integer> graph = new DirectedGraph<>();
		Node<Integer> a = new Node<Integer>(0, 5);
		Node<Integer> b = new Node<Integer>(1, 7);
		graph.addVertex(a);
		graph.addVertex(b);

		//When
		graph.createEdge(a, b);

		//Then no exception
	}
}
