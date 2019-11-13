package algorythm;

import graph.DirectedGraph;
import graph.Node;

import java.util.*;


class Dijkstra<T extends Comparable<T>> {
    private ArrayList<Vertex> wierzcholki;
    private DirectedGraph<T> graph;
    private Node<T> startPoint;
    private int iteration;
    private int nodeId;
    private Stack<Node<T>> toVisit;
    private ArrayList<Edge> sciezki;

    public Dijkstra(DirectedGraph<T> graph, Node<T> start) {
        this.graph = graph;
        this.startPoint = start;
        this.iteration = 1;
        this.nodeId = 0;
        toVisit = new Stack<>();
        toVisit.push(start);
    }

    public Dijkstra() {
        wierzcholki = new ArrayList<>();
        sciezki = new ArrayList<>();
    }

    public void add(T skad, T dokad, int cost) {
        Edge temp = findEdge(skad, dokad);
        if (temp != null) temp.cost = cost;
        else {
            Edge e = new Edge(skad, dokad, cost);
            sciezki.add(e);
        }
    }

    private Vertex znajdzWierzcholek(T v) {
        for (Vertex each : wierzcholki) {
            if (each.value.compareTo(v) == 0)
                return each;
        }
        return null;
    }

    private Edge findEdge(Vertex v1, Vertex v2) {
        for (Edge each : sciezki) if (each.from.equals(v1) && each.to.equals(v2)) return each;

        return null;
    }

    private Edge findEdge(T from, T to) {
        for (Edge each : sciezki) {
            if (each.from.value.equals(from) && each.to.value.equals(to)) {
                return each;
            }
        }
        return null;
    }


    private boolean Dijkstra(T v1) {
        if (wierzcholki.isEmpty()) return false;


        zresetujDystans();


        Vertex source = znajdzWierzcholek(v1);
        if (source == null) return false;


        source.minDistance = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(source);

        while (!pq.isEmpty()) {

            Vertex u = pq.poll();


            for (Vertex v : u.outgoing) {

                Edge e = findEdge(u, v);
                if (e == null) return false;

                int totalDistance = u.minDistance + e.cost;
                if (totalDistance < v.minDistance) {

                    pq.remove(v);
                    v.minDistance = totalDistance;

                    v.previous = u;
                    pq.add(v);
                }
            }
        }
        return true;
    }

    private List<String> getShortestPath(Vertex target) {
        List<String> path = new ArrayList<String>();

        // check for no path found
        if (target.minDistance == Integer.MAX_VALUE) {
            path.add("No path found");
            return path;
        }


        for (Vertex v = target; v != null; v = v.previous) {
            path.add(v.value + " : cost : " + v.minDistance);
        }


        Collections.reverse(path);
        return path;
    }

    private void zresetujDystans() {
        for (Vertex each : wierzcholki) {
            each.minDistance = Integer.MAX_VALUE;
            each.previous = null;
        }
    }

    public List<String> getPath(T from, T to) {
        boolean test = Dijkstra(from);
        if (test == false) return null;
        List<String> path = getShortestPath(znajdzWierzcholek(to));
        return path;
    }

    @Override
    public String toString() {
        String retval = "";
        for (Vertex each : wierzcholki) {
            retval += each.toString() + "\n";
        }
        return retval;
    }


    public enum State {nieOdwiedzony}

    class Vertex implements Comparable<Vertex> {
        T value;


        Vertex previous = null;
        int minDistance = Integer.MAX_VALUE;

        List<Vertex> incoming;
        List<Vertex> outgoing;
        State state;


        public Vertex(T value) {
            this.value = value;
            incoming = new ArrayList<>();
            outgoing = new ArrayList<>();
            state = State.nieOdwiedzony;
        }


        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(minDistance, other.minDistance);
        }


        public void addIncoming(Vertex vert) {
            incoming.add(vert);
        }

        public void addOutgoing(Vertex vert) {
            outgoing.add(vert);
        }


        @Override
        public String toString() {
            String retval = "";
            retval += "Vertex: " + value + " : ";
            retval += " In: ";
            for (Vertex each : incoming) retval += each.value + " ";
            retval += "Out: ";
            for (Vertex each : outgoing) retval += each.value + " ";
            return retval;
        }
    }

    class Edge {
        Vertex from;
        Vertex to;
        int cost;


        public Edge(T v1, T v2, int cost) {
            from = znajdzWierzcholek(v1);
            if (from == null) {
                from = new Vertex(v1);
                wierzcholki.add(from);
            }
            to = znajdzWierzcholek(v2);
            if (to == null) {
                to = new Vertex(v2);
                wierzcholki.add(to);
            }
            this.cost = cost;

            from.addOutgoing(to);
            to.addIncoming(from);
        }


        @Override
        public String toString() {
            return "Edge From: " + from.value + " to: " + to.value + " cost: " + cost;
        }
    }
}