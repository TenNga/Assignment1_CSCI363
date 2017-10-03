/*
NAME: TENZIN NGAWANG
PROJECT NAME: 8 PUZZLE
ALGORITHM USED: A*, A* MANHATTAN, IDA*, DFBnB
DESCRIPTION: THIS PROJECT IS FOR AI CLASS CSCI363 WITH PROFESSOR CHANGHE YUAN, WHICH WILL SOLVE 8 PUZZLE PROBLEM
    WITH DIFFERENT STATES.
CONTACT: TENNGAWANG6080@GMAIL.COM
-------------------------COPYWRITE @ TENZIN NGAWANG
 */
import java.util.*;

public class Board{
    private int size;
    private int Hn;  //displace tiles #
    private int Fn;  //===> (Gn + Hn)
    private int Gn=0;  //cost so far
    private static final int SPACE =0;

    public LinkedList<Object> close = new LinkedList<>();
    public TreeMap<Integer,Object> open = new TreeMap<>();

    private int[][] xNode;

    int [][] goal = new int[][]{{1,2,3},
                                {8,0,4},
                                {7,6,5}};

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
        int raw;
        int col;
        int sum=0;
        for(int i=0;i<xNode.length;i++)
            for(int j=0;j<xNode.length;j++){
            int location = xNode[i][j];
            if(location==0) {
                continue;
            }else{
                raw = calculateDistance(location)[0];
                col = calculateDistance(location)[1];
                sum += Math.abs((raw - i) + (col - j));
            }

            }
            return sum;
    }
    int[] calculateDistance(int lookFor){  //
        int[] location = new int[2];
        for(int i=0;i<goal.length;i++)
            for(int j=0;j<goal.length;j++){
                if(goal[i][j]==lookFor) {
                    location[0]=i;
                    location[1]=j;
                    return location;
                }
            }

        return location;
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

        int Fn = (Gn+hamming());

        return Fn;
    }
    public int getFnManh(){  // heuristic with manhattan
        int Fn = (Gn+manhattan());
        return Fn;
    }

    public int getGn(){  // return number of expension
        return Gn;
    }  // level of board

    public  void setGn(int x){
        Gn = x;
    }  // change level of board
    public int[][] getElement(){
        return xNode;
    }  // acces to the board from node



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
        ///////MOVE :  Up -- DOWN  --  LEFT  --  RIGHT
        if (spaceRow > 0) {  // move up
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow - 1, spaceCol)));

        }
        if (spaceRow < dimension(xNode) - 1) { // movie Down
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow + 1, spaceCol)));

        }
        if (spaceCol > 0)       { //move Left
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow, spaceCol - 1)));

        }
        if (spaceCol < dimension(xNode) - 1){ //move RIGHT
            neighbors.add(new Board(swap(xNode,spaceRow, spaceCol, spaceRow, spaceCol + 1)));

        }

        return neighbors; //returns array of childs
    }


    private int[] spaceLocation(int[][] node) {  // returns the location of empty tiles
        for (int row = 0; row < node.length; row++)
            for (int col = 0; col < node.length; col++)
                if (isSpace(block(node,row, col))) {
                    int[] location = new int[2];
                    location[0] = row;
                    location[1] = col;

                    return location; //location in array
                }
        throw new RuntimeException();
    }

    public String toString() {  // to display board
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
