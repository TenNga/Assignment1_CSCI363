
import java.util.*;

public class Solve {

    static int[][] Easy = new int[][]{{1, 3, 4}, {8, 6, 2}, {7, 0, 5}};
    static int[][] Medium = new int[][]{{2, 8, 1}, {0, 4, 3}, {7, 6, 5}};
    static int[][] Hard = new int[][]{{2, 8, 1}, {4, 6, 3}, {0, 7, 5}};
    static int[][] Worst = new int[][]{{5, 6, 7}, {4, 0, 8}, {3, 2, 1}};
    static LinkedList<int[][]> list = new LinkedList<>();
    public static void main(String[] args) {
            list.add(Easy);
            list.add(Medium);
            list.add(Hard);
            list.add(Worst);

            Board initial = new Board(list.get(0));
            aStar solver = new aStar(initial);
            IdAstart solver2 = new IdAstart(initial);
            AstarManh solver3 = new AstarManh(initial);
            DFBB solver4 = new DFBB(initial);
    }


}
