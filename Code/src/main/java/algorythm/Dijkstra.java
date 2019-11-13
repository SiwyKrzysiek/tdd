package algorythm;

import graph.Graph;
import graph.Node;
import graph.UndirectedEdge;

import java.util.*;

/**
 * Dijkstra's path finding algorithm
 * @param <T> Node value type
 */
public class Dijkstra<T> {
    private final Graph<T> graph;
    private final Node<T> start;
    private final Node<T> end;

    private final Map<Node<T>, NodeMetadata> nodesMetadata;
    private final PriorityQueue<Node<T>> toVisit;
    private final Set<Node<T>> visited;

    // Map used for finding edge in O(1) time
    private Map<UndirectedEdge<T>, UndirectedEdge<T>> edgeShortcut;


    public Dijkstra(Graph<T> graph, Node<T> start, Node<T> end) {
        this.graph = graph;
        this.start = start;
        this.end = end;

        visited = new HashSet<>();

        nodesMetadata = new HashMap<>();
        for (Node<T> node : graph.getNodes()) {
            nodesMetadata.put(node, new NodeMetadata());
        }
        nodesMetadata.get(start).travelCost = 0;

        toVisit = new PriorityQueue<>(
                (tNode, t1) -> nodesMetadata.get(tNode).travelCost - nodesMetadata.get(t1).travelCost
        );
        toVisit.addAll(graph.getNodes());

        for (UndirectedEdge<T> edge : graph.getEdges()) {
            edgeShortcut.put(edge, edge);
        }
    }

    /**
     * Calculates shortest path between points given in constructor.
     * Currently this can be done only <b>once</b>.
     *
     * @return Shortest path between points given in constructor
     */
    public List<Node<T>> calculateShortestPath() {
        while (!toVisit.isEmpty()) {
            Node<T> currentNode = toVisit.poll();
            final int currentNodeTravelCost = nodesMetadata.get(currentNode).travelCost;
            visited.add(currentNode);

            for(Node<T> neighbor : currentNode.getNeighbours()) {
                if (toVisit.contains(currentNode)) {
                    int potentialCost = currentNodeTravelCost + edgeWeight(currentNode, neighbor);
                    int neighborTravelCost = nodesMetadata.get(neighbor).travelCost;

                    if (potentialCost < neighborTravelCost) { // Path from currentNode is better
                        nodesMetadata.get(neighbor).travelCost = potentialCost;
                        nodesMetadata.get(neighbor).previousNode = currentNode;
                    }
                }
            }
        }

        return readShortestPath();
    }

    private List<Node<T>> readShortestPath() {
        List<Node<T>> path = new LinkedList<>();
        Node<T> currentNode = end;
        path.add(end);

        while (currentNode != start) {
            currentNode = nodesMetadata.get(currentNode).previousNode;
            path.add(currentNode);
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Support method to get weight of edge between two nodes
     * @return edge weight
     */
    private int edgeWeight(Node<T> a, Node<T> b) {
        UndirectedEdge<T> toFind = new UndirectedEdge<>(a, b);
        return edgeShortcut.get(toFind).getWeight().getAsInt();
    }


    /**
     * Additional data used by the algorithm
     */
    private class NodeMetadata {
        private int travelCost;
        private Node<T> previousNode;

        public NodeMetadata() {
            this(Integer.MAX_VALUE, null);
        }

        public NodeMetadata(int travelCost, Node<T> previousNode) {
            this.travelCost = travelCost;
            this.previousNode = previousNode;
        }
    }
}
