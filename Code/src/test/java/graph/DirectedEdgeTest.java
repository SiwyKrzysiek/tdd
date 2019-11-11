package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DirectedEdgeTest {
	@Test
	 void directedEdgesCreationTest(){
		//Given
		Node<Integer> node1 = new Node<>(5);
		Node<Integer> node2 = new Node<>(7);

		//When
		DirectedEdge<Integer> a = new DirectedEdge<>(node1, node2);

		//Then
		assertNotNull(a);
	}

	@Test
	void edgeFromA_ToB_isDifferent() {
		//Given
		Node<Integer> a = new Node<>(0, 5);
		Node<Integer> b = new Node<>(1, 7);

		//When
		DirectedEdge<Integer> oneWay = new DirectedEdge<>(a, b);
		DirectedEdge<Integer> otherWay = new DirectedEdge<>(b, a);

		//Then
		assertNotEquals(oneWay, otherWay);
	}
}
