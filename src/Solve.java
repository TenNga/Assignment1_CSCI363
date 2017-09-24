
import java.util.*;
import java.util.PriorityQueue;
import  java.util.Iterator;

public class Solve {
//    int [][] goal = new int[][]{{1,2,3},
//                                {8,0,4},
//                                {7,6,5}};
//
    static int [][] Start = new int[][]{{1,3,4},
                                        {8,6,2},
                                        {7,0,5}};

    static public LinkedList<Object> close = new LinkedList<>();
    static public TreeMap<Integer,Node> open = new TreeMap<>();

    //public  Board board = new Board(Start);

    private Node goalNode;

    public class Node implements Comparable<Node>{
        public Board board;
        public Node previous;
        public int moves;

        public int compareTo(Node that){
            //StdOut.println("i:" + this.priority() + " j:" + that.priority() + " "+ ((this.priority() > that.priority()) ? 1 :  -1));
            if(this.priority() == that.priority()) return 0;
            return (this.priority() > that.priority()) ? 1 :  -1;
        }

        public Node(Board b, Node prev, int m){
            board = b;
            previous = prev;
            moves = m;
        }

        public int priority(){
            return board.hamming() + moves;
        }
    }

    public Solve(Board initial) {
        Board initialBoard;
        PriorityQueue<Board> neighbors = new PriorityQueue<>();
        initialBoard = initial;

        Node currentNode = new Node(initial, null, 0);
        open.put(currentNode.board.getFn(), currentNode);

        while (!currentNode.board.isGoal() && !currentNode.board.isGoal()) {
            currentNode = open.pollFirstEntry().getValue();

            for (Board b : currentNode.board.neighbors()) {
                if (!b.equals(currentNode.board))
                    open.put(currentNode.board.getFn(), new Node(b, currentNode, currentNode.moves + 1));
            }
        }

        if (currentNode.board.isGoal())
            goalNode = currentNode;
    }
    public boolean isSolvable(){
        return goalNode != null;
    }
    public int move(){
        return goalNode.moves;
    }

    public static void main(String[] args){

//        System.out.println("Starting the test");
//        if(compare(goal,Start)==true)
//            System.out.println("both goal and start are same.");
//        else
//            System.out.println("Not same");
//      int displace = board.hamming(goal,Start);
//      System.out.println("Displace tiles: "+displace);
//      if(compare(goal,Start)==true)
//              System.out.println("Goal is equal to Start!");
//      else
//          System.out.println("Goal is not-equal to Start");

     //   open.put(board.getFn(),Start);


//        while(!board.isGoal()){
//            if(board.isGoal()){
//                System.out.println("We Are DONE!");
//            }else{
//                board.close.add(board.open.remove(1));
//                for(int i=0;i<board.neighbors().size();i++){
//                    if(board.neighbors().get(i).isGoal())
//                        System.out.println("Found the Goal.");
//                }
//            }
//        }
//            System.out.println("Displace tiles: "+board.hamming());
//            System.out.println("Bn for start: "+ board.getFn());
//            System.out.print(board.neighbors().getFirst().getBn());
        Board initial = new Board(Start);
        Solve solver = new Solve(initial);

        if (!solver.isSolvable())
            System.out.println("No solution possible");
        else {
            System.out.println("Minimum number of moves = " + solver.move());
//            for (Board board : solver.solution())
//                System.out.println(board);
        }


    }



}
