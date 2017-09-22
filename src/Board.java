import java.util.*;

public class Board{
    private int size;
    //private final int[][] tiles;
    private int Hn;  //displace tiles #
    private int Fn;  //===> (Gn + Hn)
    private int Gn=0;  //cost so far
    private static final int SPACE =0;

    public LinkedList<Object> close = new LinkedList<>();
    public TreeMap<Integer,int[][]> open = new TreeMap<>();
    PriorityQueue pq = new PriorityQueue();


    int [][] goal = new int[][]{{1,2,3},
                                {8,0,4},
                                {7,6,5}};

    int [][] Start = new int[][]{{1,3,4},{8,6,2},{7,0,5}};


    public Board(){
        //Default constructor

    }

//    public Board(int [][] Node){
//        //Default
//        size = Node.length;
//       Node = deepCopy(goal);
//
//    }
    public int hamming(int[][]x, int[][]y){
        int displaced=0;

        for(int i=0;i<x.length;i++)
            for(int j=0;j<x.length;j++){
                if(x[i][j]!=y[i][j])
                    displaced++;
            }
        return displaced;
    }
    private int[][]deepCopy(int[][] from, int[][] here){
        //int[][] copy = new int[][size];
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                here[i][j] =from[i][j];
            }
        }
        return here;
    }
    public static boolean compare (int[][] x, int[][] y){
        for(int i=0;i<x.length;i++)
            for(int j=0;j<x.length;j++)
                if(x[i][j] !=y[i][j]){
                    return false;
                }
        return true;
    }
    public int getFn(int[][] node){

        int Fn = (Gn+(hamming(goal,node)));

        return Fn;
    }

    private int block(int[][]node,int row, int col) {
        return node[row][col];
    }

    public int dimension(int[][] node) {
        return node.length;
    }

    private int[][] swap(int[][] expend, int row1, int col1, int row2, int col2) {
        int[][] copy = new int[expend.length][expend.length];
        deepCopy(expend,copy);
        int tmp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = tmp;

        return copy;
    }

    private boolean isSpace(int block) {
        return block == SPACE;
    }

    public void checkArray(int[][] check){  //give an 2dArray to find its childs
        int length = dimension(check);
        int[][] y = new int[length][length];

        deepCopy(neighbors(check).getFirst(),y);
        int x = y.length;

        boolean com = compare(goal,y);

        while(!close.isEmpty()){
            for(int i=0;i<close.size();i++) {
                if (compare(y, close.get(i)&&));
            }
        }

        if(com ==true)
             System.out.println("First Expend node is same with goal");
        else
            System.out.println("First Expend node IS-NOT same with goal");

        int misplace = hamming(goal,y);
        System.out.println("Misplace between Goal: "+misplace);

        System.out.println("First Neighbore Gn: "+Gn);
    }

    public void run(){
        int fn = getFn(Start);
        open.put(fn,Start);

        while(!isGoal(open.firstEntry().getValue())){
            if(isGoal(open.firstEntry().getValue()))
                System.out.println("We are done!");
            else{
                close.add(open.firstEntry().getValue());

                checkArray(open.firstEntry().getValue());
                open.remove(fn);
            }


        }


    }

    public boolean isGoal(int[][] check){
        if(compare(check,goal))
            return true;
        else
            return false;
    }

    public void arrayCopy(int[] from, int[] here){
        for(int i=0;i<from.length;i++)
            here[i] = from[i];
    }

    public LinkedList<int[][]> neighbors(int[][] expend) {
        LinkedList<int[][]> neighbors = new LinkedList<>();

        int[] location = new int[2];

        arrayCopy(spaceLocation(expend),location);
//        location[0]=spaceLocation(expend)[0];

        int spaceRow = location[0];
        int spaceCol = location[1];

        if (spaceRow > 0) {              neighbors.add((swap(expend,spaceRow, spaceCol, spaceRow - 1, spaceCol)));}
        if (spaceRow < dimension(expend) - 1){  neighbors.add((swap(expend,spaceRow, spaceCol, spaceRow + 1, spaceCol)));}
        if (spaceCol > 0)       {         neighbors.add((swap(expend,spaceRow, spaceCol, spaceRow, spaceCol - 1)));}
        if (spaceCol < dimension(expend) - 1){   neighbors.add((swap(expend,spaceRow, spaceCol, spaceRow, spaceCol + 1)));}
        Gn++;

        return neighbors;
    }

    private int[] spaceLocation(int[][] node) {
        for (int row = 0; row < node.length; row++)
            for (int col = 0; col < node.length; col++)
                if (isSpace(block(node,row, col))) {
                    int[] location = new int[2];
                    location[0] = row;
                    location[1] = col;

                    return location;
                }
        throw new RuntimeException();
    }

//    public String toString(int[][] x) {
//        StringBuilder str = new StringBuilder();
//        str.append(dimension() + "\n");
//        for (int row = 0; row < x.length; row++) {
//            for (int col = 0; col < x.length; col++)
//                str.append(String.format("%2d ", block(x,row, col)));
//            str.append("\n");
//        }
//
//        return str.toString();
//    }



}
