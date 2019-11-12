package algorythm;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStarTest {

    /*
        Params :
        si, sj = start point coordinates
        ei, ej = end point coordinates
        int[][] blocked = array with blocked nodes
        */

    @Test
    public void AStarTest_FindPathExistNotBlockade()
    {
        LinkedList<AStar.Field> path = test(10, 10, 0, 0, 3, 3, new int[][]{{0,4},{2,4},{3,1},{5,4}});
        assert(path.get(0).i == 0);
        assert(path.get(0).j == 0);
        assert(path.get(1).i == 1);
        assert(path.get(1).j == 1);
        assert(path.get(2).i == 2);
        assert(path.get(2).j == 2);
        assert(path.get(3).i == 3);
        assert(path.get(3).j == 3);
    }

    @Test
    public void AStarTest_FindPathExistBlockaded()
    {
        LinkedList<AStar.Field> path = test(10, 10, 0, 0, 7, 7, new int[][]{{0,4},{2,2},{2,1},{3,3},{4,3},{5,3},{6,3},{6,5},{5,6},{5,1},{4,2},{8,3},{9,7}});
        assert(path.get(0).i == 0);
        assert(path.get(0).j == 0);
        assert(path.get(1).i == 0);
        assert(path.get(1).j == 1);
        assert(path.get(2).i == 1);
        assert(path.get(2).j == 2);
        assert(path.get(3).i == 2);
        assert(path.get(3).j == 3);
        assert(path.get(4).i == 3);
        assert(path.get(4).j == 4);
        assert(path.get(5).i == 4);
        assert(path.get(5).j == 4);
        assert(path.get(6).i == 5);
        assert(path.get(6).j == 5);
        assert(path.get(7).i == 6);
        assert(path.get(7).j == 6);
        assert(path.get(8).i == 7);
        assert(path.get(8).j == 7);
    }

    @Test
    public void AStarTest_FindPathNotExist()
    {
        LinkedList<AStar.Field> path = test(10, 10, 0, 0, 9, 4, new int[][]{{9,3},{9,5},{8,3},{8,5},{7,3},{7,5},{7,4},{5,4}});
        assert(path == null);
    }



    public static LinkedList<AStar.Field> test(int x, int y, int si, int sj, int ei, int ej, int[][] blocked) {
        //Reset8
        AStar.plot = new AStar.Field[x][y];
        AStar.closed = new boolean[x][y];
        AStar.open = new PriorityQueue<>((Object o1, Object o2) -> {
            AStar.Field c1 = (AStar.Field) o1;
            AStar.Field c2 = (AStar.Field) o2;

            return c1.finalCost < c2.finalCost ? -1 :
                    c1.finalCost > c2.finalCost ? 1 : 0;
        });
        //Set start point
        AStar.setStartPoint(si, sj);

        //Set end point
        AStar.setEndPoint(ei, ej);

        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                AStar.plot[i][j] = new AStar.Field(i, j);
            }
        }
        AStar.plot[si][sj].finalCost = 0;

        for (int i = 0; i < blocked.length; ++i) {
            AStar.setBlocked(blocked[i][0], blocked[i][1]);
        }

        //Display initial grid:
        System.out.println("Grid: ");
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                if (i == si && j == sj) System.out.print("S   "); //Start point
                else if (i == ei && j == ej) System.out.print("E   ");  //End point
                else if (AStar.plot[i][j].passable) System.out.printf("%-3d ", 0);
                else System.out.print("XX  ");
            }
            System.out.println();
        }
        System.out.println();

        boolean result = AStar.findPath();
        if(result)
            System.out.println("\nPath found. ");
        else
            System.out.println("\nPath not found. ");
        System.out.println("\nNodes costs: ");
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < x; ++j) {
                if (AStar.plot[i][j].passable) System.out.printf("%-3d ", AStar.plot[i][j].finalCost);
                else System.out.print("XX  ");
            }
            System.out.println();
        }
        System.out.println();



        if (result) {
            //Trace back the path
            System.out.println("Path: ");

            LinkedList<AStar.Field> path = AStar.listPath();

            for(AStar.Field node: path)
            {
                System.out.print(node.toString() + " -> ");
            }

            System.out.print("SUCCES");
            return path;
        }
        else
        {
            return null;
        }

    }
}