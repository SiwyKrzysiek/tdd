package algorythm;

import graph.Node;

import java.util.*;

public class BFS<T> implements Iterator<Node<T>> {
    private Queue<Node> queue = new ArrayDeque<>();
    private List<Node> visited = new ArrayList<>();
    public BFS(Node<T> startingNode) {
        queue.add(startingNode);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Node<T> next() {
        Node<T> currentNode = queue.remove();
        for(Node<T> nodeToAdd: currentNode.getNeighbours()){
            if(!visited.contains(nodeToAdd))
                queue.add(nodeToAdd);
        }
        visited.add(currentNode);
        return currentNode;
    }
}
