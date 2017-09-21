import java.util.Stack;

public class Board{
    private int size;
    //private final int[][] tiles;
    private int zeroRow;
    private int zeroCol;
    private int hamming;
    private int manhattan;

    public Board(){
        //Default constructor
    }

    public Board(int [][] Node){
        //Default
        size = Node.length;
        //tiles = deepCopy(Node);

    }
    public int hamming(int[][]x, int[][]y){
        int displaced=0;

        for(int i=0;i<x.length;i++)
            for(int j=0;j<x.length;j++){
                if(x[i][j]!=y[i][j])
                    displaced++;
            }
        return displaced;
    }
    private int[][]deepCopy(int[][] array){
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copy[i][j] =array[i][j];
            }
        }
        return copy;
    }
//    public Iterable<Board> neighbors(){
//        Stack<Board> boards = new Stack<Board>();
//
//        if(zeroRow > 0){
//            Board boardUP = new Board(swap(tiles,-1,0));
//            boards.push(boardUP);
//        }
//
//        if(zeroRow < size-1){
//            Board boardDown = new Board(swap(tiles,1,0));
//            boards.push(boardDown);
//        }
//
//        if(zeroCol > 0){
//            Board boardLeft = new Board(swap(tiles,0,-1));
//            boards.push(boardLeft);
//        }
//
//        if(zeroCol <size-1){
//            Board boardRight = new Board(swap(tiles,0,1));
//            boards.push(boardRight);
//        }
//
//        return boards;
//
//    }
//    public int[][] swap(int[][] board, int rowOffset, int colOffset){
//        int[][] tempBoard =  deepCopy(board);
//        tempBoard[zeroRow][zeroCol]= tiles[zeroRow+rowOffset][zeroCol+colOffset];
//        tempBoard[zeroRow+rowOffset][zeroCol+colOffset]=0;
//
//        return tempBoard;
//    }
}
