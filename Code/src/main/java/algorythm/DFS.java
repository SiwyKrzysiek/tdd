package algorythm;

import graph.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * Depth Firs Search Iterator
 * @param <T> Node value type
 */
public class DFS<T> implements Iterator<Node<T>> {
    private final Set<Node> visitedNodes;
    private final Stack<Node<T>> toVisit;

    public DFS(Node<T> initialNode) {
        visitedNodes = new HashSet<>();
        toVisit = new Stack<>();

        toVisit.push(initialNode);
    }

    @Override
    public boolean hasNext() {
        return !toVisit.empty();
    }

    @Override
    public Node<T> next() {
        Node<T> currentNode = toVisit.pop();
        visitedNodes.add(currentNode);

        for(Node<T> neighbour : currentNode.getNeighbours()) {
            if (!visitedNodes.contains(neighbour)) {
                toVisit.push(neighbour);
            }
        }

        return currentNode;
    }
}
