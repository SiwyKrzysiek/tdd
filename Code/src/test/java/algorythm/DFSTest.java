package algorythm;

import graph.Graph;
import graph.Node;
import org.junit.jupiter.api.Test;
import util.InfiniteCounter;

import java.util.Iterator;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class DFSTest {

    @Test
    public void DFSWalkSimpleGraph_CheckNodeOrder() {
        //Given
        Iterator<Integer> idGenerator = new InfiniteCounter();

        Node<String> a = new Node<>(idGenerator.next(), "a");
        Node<String> b = new Node<>(idGenerator.next(), "b");
        Node<String> c = new Node<>(idGenerator.next(), "c");
        Node<String> d = new Node<>(idGenerator.next(), "d");
        Node<String> e = new Node<>(idGenerator.next(), "e");
        Node<String> f = new Node<>(idGenerator.next(), "f");
        Node<String> g = new Node<>(idGenerator.next(), "g");
        Node<String> h = new Node<>(idGenerator.next(), "h");

        /* Graph structure
              a
             +  +
            b    e
           +      +
          c        f
         +        + +
        d        g   h
         */
        Graph<String> graph = new Graph<>();
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);

        graph.createEdge(a, b);
        graph.createEdge(b, c);
        graph.createEdge(c, d);
        graph.createEdge(a, e);
        graph.createEdge(e, f);
        graph.createEdge(f, g);
        graph.createEdge(f, h);

        // When
        DFS<String> dfs = new DFS(a);
        StringBuilder resultBuilder = new StringBuilder(8);

        while (dfs.hasNext()) {
            Node<String> node = dfs.next();
            resultBuilder.append(node.getValue());
        }

        // Then
        String result = resultBuilder.toString();
        String expectedResult = "aefhgbcd";

        assertEquals(expectedResult, result);
    }
}