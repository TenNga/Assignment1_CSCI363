import java.util.*;

public class Board{
    private int size;
    //private final int[][] tiles;
    private int Hn;  //displace tiles #
    private int Fn;  //===> (Gn + Hn)
    private int Gn=0;  //cost so far
  //  private String moveBlank;
    private static final int SPACE =0;

    public LinkedList<Object> close = new LinkedList<>();
    public TreeMap<Integer,Object> open = new TreeMap<>();
   // public LinkedList<String> moveSequence = new LinkedList<>();
//    PriorityQueue pq = new PriorityQueue();

    private int[][] xNode;

    //private  int Bn;
   // public Board parent;

    int [][] goal = new int[][]{{1,2,3},
                                {8,0,4},
                                {7,6,5}};

    //int [][] Start = new int[][]{{1,3,4},{8,6,2},{7,0,5}};


    public Board(){
        //Default constructor

    }


    public Board(int [][] Node){
        //Default
        xNode = new int[Node.length][Node.length];
        deepCopy(Node,xNode);
        open.put(getFn(),xNode);


    }
    public int hamming(){ // returns displacement of tiles
        int displaced=0;

        for(int i=0;i<xNode.length;i++)
            for(int j=0;j<xNode.length;j++){
                if(goal[i][j] ==0)
                    continue;
                else if(goal[i][j]!=xNode[i][j])
                    displaced++;
            }
        return displaced;
    }
    public int manhattan() {
        int sum = 0;
        for (int row = 0; row < xNode.length; row++)
            for (int col = 0; col < xNode.length; col++)
                sum += calculateDistances(row, col);

        return sum;
    }
    private int calculateDistances(int row, int col) {
        int block = block(row, col);

        return (isSpace(block)) ? 0 : Math.abs(row - row(block)) + Math.abs(col - col(block));
    }
    private int block(int row, int col) {
        return xNode[row][col];
    }
    private int row (int block) {
        return (block - 1) / dimension(xNode);
    }

    private int col (int block) {
        return (block - 1) % dimension(xNode);
    }
    private void deepCopy(int[][] from, int[][] here){   // Copy FromArray to HereArray
        //int[][] copy = new int[][size];
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                here[i][j] =from[i][j];
            }
        }
    }
    public boolean isGoal(){   // Checkes current node is goals
        for(int i=0;i<xNode.length;i++)
            for(int j=0;j<xNode.length;j++)
                if (goal[i][j] != xNode[i][j])
                        return false;
        return true;
    }
    public boolean compare (int[][] x){   // compare two array
        for(int i=0;i<x.length;i++)
            for(int j=0;j<x.length;j++)
                if(x[i][j] !=this.xNode[i][j])
                    return false;

        return true;
    }
    public int getFn(){   //returns F(n)= B(n)+H(n)

        int Fn = (getGn()+hamming());

        return Fn;
    }

    public int getGn(){  // return number of expension
        return Gn;
    }

    public  void setGn(int x){
        Gn = x;
    }
    public int[][] getElement(){
        return xNode;
    }

//    public String getMoveBlank(){
//        return moveBlank;
//    }

    private int block(int[][]node,int row, int col) {  //
        return node[row][col];
    }

    public int dimension(int[][] node) {  //size of array
        return node.length;
    }



    private int[][] swap(int[][] expend, int row1, int col1, int row2, int col2) {  // Swaps two tiles and return now array
        int[][] copy = new int[expend.length][expend.length];
        deepCopy(expend,copy);
        int tmp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = tmp;

        return copy;
    }

    private boolean isSpace(int block) {  //return space which is Zero
        return block == SPACE;
    }

//    public void checkArray(int[][] check){  //give an 2dArray to find its childs
//        int length = dimension(check);
//        int[][] y = new int[length][length];
//
//        deepCopy(neighbors(check).getFirst(),y);
//        int x = y.length;
//
//        boolean com = compare(goal,y);
//
//        while(!close.isEmpty()){
//            for(int i=0;i<close.size();i++) {
//                if (compare(y, close.get(i)))
//                    System.out.println("We are done!");
//                else{
//
//                }
//            }
//        }
//
//        if(com ==true)
//             System.out.println("First Expend node is same with goal");
//        else
//            System.out.println("First Expend node IS-NOT same with goal");
//
//        int misplace = hamming(goal,y);
//        System.out.println("Misplace between Goal: "+misplace);
//
//        System.out.println("First Neighbore Gn: "+Gn);
//    }
//
//    public void run(){
//        int fn = getFn(Start);
//        open.put(fn,Start);
//
//        while(!isGoal(open.firstEntry().getValue())){
//            if(isGoal(open.firstEntry().getValue()))
//                System.out.println("We are done!");
//            else{
//                close.add(open.firstEntry().getValue());
//
//                checkArray(open.firstEntry().getValue());
//                open.remove(fn);
//            }
//
//
//        }
//
//
//    }

    public void arrayCopy(int[] from, int[] here){  // No-return void copy array
        for(int i=0;i<from.length;i++)
            here[i] = from[i];
    }

    public LinkedList<Board> neighbors() {  // Expension of Childs of current board
        LinkedList<Board> neighbors = new LinkedList<>();

        int[] location = new int[2];

        arrayCopy(spaceLocation(xNode),location);
//        location[0]=spaceLocation(expend)[0];
        //Gn++;
        int spaceRow = location[0];
        int spaceCol = location[1];

        if (spaceRow > 0) {
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow - 1, spaceCol)));

        }
        if (spaceRow < dimension(xNode) - 1) {
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow + 1, spaceCol)));

        }
        if (spaceCol > 0)       {
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow, spaceCol - 1)));

        }
        if (spaceCol < dimension(xNode) - 1){ //RIGHT
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow, spaceCol + 1)));

        }

//    while(!neighbors.isEmpty()){
//        neighbors.getFirst();
//    }
        return neighbors;
    }


    private int[] spaceLocation(int[][] node) {  // returns the location of empty tiles
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

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append( "\n");
        for (int row = 0; row < xNode.length; row++) {
            for (int col = 0; col < xNode.length; col++)
                str.append(String.format("%2d ", block(xNode,row, col)));
            str.append("\n");
        }

        return str.toString();
    }



}
