package algorythm;

import org.junit.jupiter.api.Test;

import java.util.List;


class DijkstraTest
{
    @Test
    public void dijkstraTest() {



//pisząc testy wzorowałem się na zadaniu ze strony: https://zasoby1.open.agh.edu.pl/dydaktyka/matematyka/c_badania_operacyjne/testy/test2.html

        Dijkstra graph = new Dijkstra();
        Dijkstra graph2 = new Dijkstra();
        Dijkstra graph3 = new Dijkstra();
        Dijkstra graph4 = new Dijkstra();

        // add a bunch of edges
        graph.add("A", "B", 1);
        graph.add("A", "E", 1);
        graph.add("B", "C", 2);
        graph.add("C", "H", 3);
        graph.add("C", "D", 1);
        graph.add("A", "D", 2);
        graph.add("D", "E", 2);
        graph.add("E", "F", 2);
        graph.add("E", "G", 3);
        graph.add("G", "H", 2);
        graph.add("F","H", 1);
        graph.add("D", "H", 4);

        List<String> path = graph.getPath("A", "H");

        List<String> path2 = graph.getPath("A", "C");

        List<String> path3 = graph.getPath("G", "A");

        List<String> path4 = graph.getPath("D", "F");

        assert(path.toString().contains("[A : cost : 0, E : cost : 1, F : cost : 3, H : cost : 4]"));
        assert(path2.toString().contains("[A : cost : 0, B : cost : 1, C : cost : 3]"));
        assert(path3.toString().contains("[No path found]"));
        assert(path4.toString().contains("[D : cost : 0, E : cost : 2, F : cost : 4]"));
    }



}
