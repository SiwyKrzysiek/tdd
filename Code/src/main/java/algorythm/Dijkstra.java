package algorythm;/* Generic Directed Weighted Graph with Dijkstra's Shortest Path Algorithm
 * by /u/Philboyd_Studge
 * for /r/javaexamples
 */

import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;


 class Dijkstra<T extends Comparable<T>>
{
    public enum State {nieOdwiedzony, odwiedzone, skonczone};

    private ArrayList<Vertex> wierzcholki;
    private ArrayList<Edge> sciezki;


    public Dijkstra()
    {
        wierzcholki = new ArrayList<>();
        sciezki = new ArrayList<>();
    }


    public void add(T skad, T dokad, int cost)
    {
        Edge temp = findEdge(skad, dokad);
        if (temp != null)
        {

            System.out.println("Edge " + skad + "," + dokad + " already exists. Changing cost.");
            temp.cost = cost;
        }
        else
        {

            Edge e = new Edge(skad, dokad, cost);
            sciezki.add(e);
        }
    }


    private Vertex findVertex(T v)
    {
        for (Vertex each : wierzcholki)
        {
            if (each.value.compareTo(v)==0)
                return each;
        }
        return null;
    }


    private Edge findEdge(Vertex v1, Vertex v2)
    {
        for (Edge each : sciezki)
        {
            if (each.from.equals(v1) && each.to.equals(v2))
            {
                return each;
            }
        }
        return null;
    }


    private Edge findEdge(T from, T to)
    {
        for (Edge each : sciezki)
        {
            if (each.from.value.equals(from) && each.to.value.equals(to))
            {
                return each;
            }
        }
        return null;
    }


    private void clearStates()
    {
        for (Vertex each : wierzcholki)
        {
            each.state = State.nieOdwiedzony;
        }
    }


    public boolean isConnected()
    {
        for (Vertex each : wierzcholki)
        {
            if (each.state != State.skonczone)
                return false;
        }
        return true;
    }

    public boolean DepthFirstSearch()
    {
        if (wierzcholki.isEmpty()) return false;

        clearStates();

        Vertex root = wierzcholki.get(0);
        if (root==null) return false;


        DepthFirstSearch(root);
        return isConnected();
    }


    private void DepthFirstSearch(Vertex v)
    {
        v.state = State.odwiedzone;

        // loop through neighbors
        for (Vertex each : v.outgoing)
        {
            if (each.state ==State.nieOdwiedzony)
            {
                DepthFirstSearch(each);
            }
        }
        v.state = State.skonczone;
    }


    public boolean BreadthFirstSearch()
    {
        if (wierzcholki.isEmpty()) return false;
        clearStates();

        Vertex root = wierzcholki.get(0);
        if (root==null) return false;

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(root);
        root.state = State.skonczone;

        while (!queue.isEmpty())
        {
            root = queue.peek();
            for (Vertex each : root.outgoing)
            {

                if (each.state==State.nieOdwiedzony)
                {
                    each.state = State.skonczone;
                    queue.add(each);
                }
            }
            queue.remove();
        }
        return isConnected();
    }


    public boolean BreadthFirstSearch(T v1)
    {
        if (wierzcholki.isEmpty()) return false;
        clearStates();

        Vertex root = findVertex(v1);
        if (root==null) return false;

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(root);
        root.state = State.skonczone;

        while (!queue.isEmpty())
        {
            root = queue.peek();
            for (Vertex each : root.outgoing)
            {

                if (each.state==State.nieOdwiedzony)
                {
                    each.state = State.skonczone;
                    queue.add(each);
                }
            }
            queue.remove();
        }
        return isConnected();
    }


    private boolean Dijkstra(T v1)
    {
        if (wierzcholki.isEmpty()) return false;


        zresetujDystans();


        Vertex source = findVertex(v1);
        if (source==null) return false;


        source.minDistance = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(source);

        while (!pq.isEmpty())
        {

            Vertex u = pq.poll();


            for (Vertex v : u.outgoing)
            {

                Edge e = findEdge(u, v);
                if (e==null) return false;

                int totalDistance = u.minDistance + e.cost;
                if (totalDistance < v.minDistance)
                {
                    // new cost is smaller, set it and add to queue
                    pq.remove(v);
                    v.minDistance = totalDistance;
                    // link vertex
                    v.previous = u;
                    pq.add(v);
                }
            }
        }
        return true;
    }


    private List<String> getShortestPath(Vertex target)
    {
        List<String> path = new ArrayList<String>();

        // check for no path found
        if (target.minDistance==Integer.MAX_VALUE)
        {
            path.add("No path found");
            return path;
        }

        // loop through the vertices from end target
        for (Vertex v = target; v !=null; v = v.previous)
        {
            path.add(v.value + " : cost : " + v.minDistance);
        }

        // flip the list
        Collections.reverse(path);
        return path;
    }


    private void zresetujDystans()
    {
        for (Vertex each : wierzcholki)
        {
            each.minDistance = Integer.MAX_VALUE;
            each.previous = null;
        }
    }


    public List<String> getPath(T from, T to)
    {
        boolean test = Dijkstra(from);
        if (test==false) return null;
        List<String> path = getShortestPath(findVertex(to));
        return path;
    }


    @Override
    public String toString()
    {
        String retval = "";
        for (Vertex each : wierzcholki)
        {
            retval += each.toString() + "\n";
        }
        return retval;
    }


    public String edgesToString()
    {
        String retval = "";
        for (Edge each : sciezki)
        {
            retval += each + "\n";
        }
        return retval;
    }


    class Vertex implements Comparable<Vertex>
    {
        T value;

        // variables for Dijkstra Tree
        Vertex previous = null;
        int minDistance = Integer.MAX_VALUE;

        List<Vertex> incoming;
        List<Vertex> outgoing;
        State state;


        public Vertex(T value)
        {
            this.value = value;
            incoming = new ArrayList<>();
            outgoing = new ArrayList<>();
            state = State.nieOdwiedzony;
        }


        @Override
        public int compareTo(Vertex other)
        {
            return Integer.compare(minDistance, other.minDistance);
        }


        public void addIncoming(Vertex vert)
        {
            incoming.add(vert);
        }
        public void addOutgoing(Vertex vert)
        {
            outgoing.add(vert);
        }


        @Override
        public String toString()
        {
            String retval = "";
            retval += "Vertex: " + value + " : ";
            retval += " In: ";
            for (Vertex each : incoming) retval+= each.value + " ";
            retval += "Out: ";
            for (Vertex each : outgoing) retval += each.value + " ";
            return retval;
        }
    }

    class Edge
    {
        Vertex from;
        Vertex to;
        int cost;


        public Edge(T v1, T v2, int cost)
        {
            from = findVertex(v1);
            if (from == null)
            {
                from = new Vertex(v1);
                wierzcholki.add(from);
            }
            to = findVertex(v2);
            if (to == null)
            {
                to = new Vertex(v2);
                wierzcholki.add(to);
            }
            this.cost = cost;

            from.addOutgoing(to);
            to.addIncoming(from);
        }


        @Override
        public String toString()
        {
            return "Edge From: " + from.value + " to: " + to.value + " cost: " + cost;
        }
    }
}