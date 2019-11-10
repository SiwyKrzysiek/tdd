package graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UndirectedEdgeTest {

    @Test
    void edgesWithSwitchedNodesEqual() {
        //Given
        Node<Integer> node1 = new Node<>(5);
        Node<Integer> node2 = new Node<>(7);

        //When
        UndirectedEdge<Integer> a = new UndirectedEdge<>(node1, node2);
        UndirectedEdge<Integer> b = new UndirectedEdge<>(node2, node1);

        //Then
        assertTrue(a.equals(b));
    }
}