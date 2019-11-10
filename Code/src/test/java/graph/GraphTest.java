package graph;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
	@org.junit.jupiter.api.Test
	void main() {
		assertTrue(true);
	}

    @Test
    void addVertexWithDifferentID() {
		// Given
		Graph<Integer> graph = new Graph<>();

		// When
		graph.addVertex(new Node<Integer>(0, 5));
		graph.addVertex(new Node<Integer>(1, 7));

		// Then no exception
    }

	@Test
	void addVertexWithSameID_ThrowsException() {
		// Given
		Graph<Integer> graph = new Graph<>();

		// When
		graph.addVertex(new Node<Integer>(0, 5));

		//Then
		assertThrows(IllegalArgumentException.class , () -> graph.addVertex(new Node<Integer>(0, 7)));
	}
}