package algorythm;

import graph.DirectedGraph;
import graph.Node;
import org.junit.jupiter.api.Test;
import util.InfiniteCounter;

import java.util.Iterator;

class BellmanFordTest {
	@Test
	public void bellmanFordTest() {
		//Given
		Iterator<Integer> idGenerator = new InfiniteCounter();

		Node<String> s = new Node<>(idGenerator.next(), "s", 0);
		Node<String> a = new Node<>(idGenerator.next(), "a", Integer.MAX_VALUE);
		Node<String> b = new Node<>(idGenerator.next(), "b", Integer.MAX_VALUE);
		Node<String> c = new Node<>(idGenerator.next(), "c", Integer.MAX_VALUE);
		Node<String> d = new Node<>(idGenerator.next(), "d", Integer.MAX_VALUE);
		Node<String> e = new Node<>(idGenerator.next(), "e", Integer.MAX_VALUE);

		/* Graph structure

			   [s]
			  /  \
			(8)  (10)
			/      \
		   V        -----------\
		  [e]		           V
		   |         -------> [a]
		   |        /        /  ^--
		  (1)   (-4)        /      \
		   |  --/         (2)       |
		   V /           /         (1)
		  [d]           /           |
		    \          V            |
		     --(-1)->[c]----(-2)-->[b]

		   [X] -- node name
		   (y) -- edge weight
		   - | / \  -- edges
		   > < ^ V -- edge direction
		*/

		DirectedGraph<String> graph = new DirectedGraph<>();

		//When

		//Then
	}
}