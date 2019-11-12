package algorythm;

import graph.Graph;
import graph.Node;
import org.junit.jupiter.api.Test;
import util.InfiniteCounter;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BFSTest {
    @Test
    public void bfsShouldGiveCorrectNodeOrder() {
        //given
        /*
            a
           / \
          b   c
         / |  | \
        d  e  f  g
           |
           h
         */
        Iterator<Integer> idGenerator = new InfiniteCounter();

        Node<String> a = new Node<String>(idGenerator.next(),"a");
        Node<String> b = new Node<String>(idGenerator.next(),"b");
        Node<String> c = new Node<String>(idGenerator.next(),"c");
        Node<String> d = new Node<String>(idGenerator.next(),"d");
        Node<String> e = new Node<String>(idGenerator.next(),"e");
        Node<String> f = new Node<String>(idGenerator.next(),"f");
        Node<String> g = new Node<String>(idGenerator.next(),"g");
        Node<String> h = new Node<String>(idGenerator.next(),"h");

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
        graph.createEdge(a, c);
        graph.createEdge(b, d);
        graph.createEdge(b, e);
        graph.createEdge(c, f);
        graph.createEdge(c, g);
        graph.createEdge(e, h);

        //when
        BFS bfs = new BFS<String>(a);
        StringBuilder result = new StringBuilder();

        while(bfs.hasNext()){
            Node<String> node= bfs.next();
            result.append(node.getValue());
        }
        //then
        String correctRoute = "abcdefgh";
        assertEquals(correctRoute, result.toString());
    }
}