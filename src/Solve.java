
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

    static public LinkedList<Node> close = new LinkedList<>();
    static public TreeMap<Integer,Node> open = new TreeMap<>();

    //public  Board board = new Board(Start);

    private Node goalNode;
    private int Gn=0;
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

        open.put(currentNode.board.getFn(), currentNode);  //Insert in open list


        while (!currentNode.board.isGoal() && !currentNode.board.isGoal()) {  //while not reached GOAL

            currentNode = open.pollFirstEntry().getValue();  //This node to be expend

            outerloop:
//            open.remove(open.firstKey());
            //System.out.println("First key"+currentNode.board.open.firstKey());

            if (currentNode.board.isGoal()) {
                break;
            } else{
                    close.add(currentNode);

                    for (Board b : currentNode.board.neighbors()) {

                        if (!b.equals(currentNode.board)) {

                            System.out.println("F(n): "+b.getFn());
                            System.out.println("G(n): "+b.getGn());
                            System.out.println("H(n): "+b.hamming());
                            System.out.println("**************************");

                            open.put(b.getFn(), new Node(b, currentNode, currentNode.moves + 1));
                            System.out.println("Neibore: "+b.toString());

                            for(Node n: close){
                                if(n.equals(b)) {
                                    System.out.println("Found same Board");
                                    break outerloop;
                                }
                            }

                        }
                        else{
                            System.out.println("CurrentNode: "+b.toString());
                        }

                    }

                }currentNode.board.setGn(Gn++);


        }

        if (currentNode.board.isGoal())
            goalNode = currentNode;
    } //end Solve

    public Iterable<Board> solution(){
        PriorityQueue<Board> trace = new PriorityQueue<>();
        trace.add(goalNode.board);
        while (goalNode.previous != null){
            goalNode = goalNode.previous;
            trace.add(goalNode.board);
        }

        return trace;
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
            for (Board board : solver.solution())
                System.out.println(board.toString());
        }


    }



}
