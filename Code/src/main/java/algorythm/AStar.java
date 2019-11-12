package algorythm;

import java.util.*;

public class AStar {
    public static final int DIAGONAL_COST = 3;
    public static final int SIDE_COST = 2;

    static class Field {
        int baseCost = 0;
        int finalCost = 0;
        int i, j;
        Field parent;
        boolean passable;

        Field(int i, int j) {
            this.i = i;
            this.j = j;
            this.passable = true;
        }

        @Override
        public String toString() {
            return "[" + this.i + ", " + this.j + "]";
        }
    }

    static Field[][] plot = new Field[5][5];

    static PriorityQueue<Field> open;

    static boolean closed[][];
    static int startPointI;
    static int startPointJ;
    static int endPointI;
    static int endPointJ;

    public static void setBlocked(int i, int j)
    {
        plot[i][j].passable = false;
    }

    public static void setStartPoint(int i, int j)
    {
        startPointI = i;
        startPointJ = j;
    }

    public static void setEndPoint(int i, int j)
    {
        endPointI = i;
        endPointJ = j;
    }

    static void checkAndUpdateCost(Field current, Field temp, int movementCost)
    {
        if (!temp.passable || closed[temp.i][temp.j])
            return;

        int tempFinalCost = temp.baseCost + movementCost;

        if (!open.contains(temp) || tempFinalCost < temp.finalCost)
        {
            temp.finalCost = tempFinalCost;
            temp.parent = current;
            if (!open.contains(temp))
                open.add(temp);
        }
    }

    public static boolean findPath() {

        //add the start location to open list.
        open.add(plot[startPointI][startPointJ]);

        Field curr;

        //Searching for path and checking if it's exist
        while (true) {
            curr = open.poll();
            if(curr == null)
            {
                //there is no path
                return false;
            }
            if (!curr.passable)
                break;
            closed[curr.i][curr.j] = true;

            if (curr.equals(plot[endPointI][endPointJ]))
            {
                //found
                return true;
            }

            Field temp;
            if (curr.i - 1 >= 0)
            {
                temp = plot[curr.i - 1][curr.j];
                checkAndUpdateCost(curr, temp, curr.finalCost + SIDE_COST);

                if (curr.j - 1 >= 0)
                {
                    temp = plot[curr.i - 1][curr.j - 1];
                    checkAndUpdateCost(curr, temp, curr.finalCost + DIAGONAL_COST);
                }

                if (curr.j + 1 < plot[0].length)
                {
                    temp = plot[curr.i - 1][curr.j + 1];
                    checkAndUpdateCost(curr, temp, curr.finalCost + DIAGONAL_COST);
                }
            }

            if (curr.j - 1 >= 0)
            {
                temp = plot[curr.i][curr.j - 1];
                checkAndUpdateCost(curr, temp, curr.finalCost + SIDE_COST);
            }

            if (curr.j + 1 < plot[0].length)
            {
                temp = plot[curr.i][curr.j + 1];
                checkAndUpdateCost(curr, temp, curr.finalCost + SIDE_COST);
            }

            if (curr.i + 1 < plot.length)
            {
                temp = plot[curr.i + 1][curr.j];
                checkAndUpdateCost(curr, temp, curr.finalCost + SIDE_COST);

                if (curr.j - 1 >= 0)
                {
                    temp = plot[curr.i + 1][curr.j - 1];
                    checkAndUpdateCost(curr, temp, curr.finalCost + DIAGONAL_COST);
                }

                if (curr.j + 1 < plot[0].length)
                {
                    temp = plot[curr.i + 1][curr.j + 1];
                    checkAndUpdateCost(curr, temp, curr.finalCost + DIAGONAL_COST);
                }
            }
        }
        return false;
    }

    public static LinkedList<Field> listPath()
    {
        LinkedList<Field> path = new LinkedList<>();
        Field current = plot[endPointI][endPointJ];
        path.add(0, current);
        while (current.parent != null) {
            path.add(0, current.parent);
            current = current.parent;
        }
        return path;
    }
}