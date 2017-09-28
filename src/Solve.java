
import java.util.*;
import java.util.PriorityQueue;
import  java.util.Iterator;
import java.util.Arrays;

public class Solve {
//    static int [][] Start = new int[][]{{1,2,3},
//            {0,8,4},
//            {7,6,5}};
//////
//   static int [][] Start = new int[][]{{1,3,4},
//                                        {8,6,2},
//                                        {7,0,5}};

//   static int[][] Start = new int[][]{{2,8,1},{0,4,3},{7,6,5}};
////
//        static int [][] Start = new int[][]{{2,8,1},
//                                            {4,6,3},
//                                            {0,7,5}};
//////
    static int [][] Start = new int[][]{{5,6,7},
                                        {4,0,8},
                                        {3,2,1}};



   // static public LinkedList<Node> close = new LinkedList<>();
    static public HashMap<String,Node> close = new HashMap<>();
    static public PriorityQueue<Node> open = new PriorityQueue<>();

    //public  Board board = new Board(Start);

    private Node goalNode;
   // private int Gn=0;
    static private int NodeExp =0;

    public class Node implements Comparable<Node>{
        public Board board;
        public Node previous;
        public int moves;
    @Override
        public int compareTo(Node that){
            //StdOut.println("i:" + this.priority() + " j:" + that.priority() + " "+ ((this.priority() > that.priority()) ? 1 :  -1));
            if(this.priority() == (that.priority()))
                return 0;
            else if(this.priority()< that.priority())
                return -1;
            else
                return 1;

//            if(this.board.equals(that))
//                return  1;
//            else
//                return 0;

        }

        public Node(Board b, Node prev, int m){
            board = b;
            previous = prev;
            moves = m;
        }

        public int priority(){
            return board.getFn();
        }
    }

    public Solve(Board initial) {
        //Board initialBoard;
     //   PriorityQueue<Board> neighbors = new PriorityQueue<>();
       // initialBoard = initial;

        Node currentNode = new Node(initial, null, 0);

        open.add( currentNode);  //Insert in open list
        //System.out.println("First Node: ");

        while (!currentNode.board.isGoal()) {  //while not reached GOAL

            currentNode = open.remove();  //remove minimum F(n) from OpenList Return
            String currentNodeKey = Arrays.deepToString(currentNode.board.getElement());

            if(close.containsKey(currentNodeKey)){
                System.out.println("Key : "+close.get(currentNodeKey).board.getGn()+"--"+currentNode.board.getGn());
                System.out.println("Same board found!!");
                System.out.println("**************************");
                // close.put(Arrays.toString(b.getElement()),currentNode);
            }
            else{
                close.put(Arrays.deepToString(currentNode.board.getElement()), currentNode);

                System.out.println("F(n): " + currentNode.board.getFn());
                System.out.println("G(n): " + currentNode.board.getGn());
                System.out.println("H(n): " + currentNode.board.hamming());

                System.out.println("Choosen Node: " + currentNode.board.toString());

                printOpen();
                printClose();

                NodeExp++;
                for (Board b : currentNode.board.neighbors()) {  //for every childs insert in OpenList
                    b.setGn(currentNode.board.getGn() + 1);          //increase Level
                    open.add(new Node(b, currentNode, currentNode.moves + 1));
                }//for loop

                 System.out.println("**************************");
            }//else if ends

        } //While Loop

        if (currentNode.board.isGoal())
            goalNode = currentNode;
    } //end Solve

//    public Iterable<Board> solution(){
//        PriorityQueue<Board> trace = new PriorityQueue<>();
//        trace.add(goalNode.board);
//        while (goalNode.previous != null){
//            goalNode = goalNode.previous;
//            trace.add(goalNode.board);
//        }

//        return trace;
//    }

    public boolean isSolvable(){
        return goalNode != null;
    }

    public int move(){
        return goalNode.moves;
    }

    public void printOpen(){
        System.out.print("Open List: ");
        for(Node n: open){
            System.out.print(n.board.getFn()+" ");
        }
        System.out.println("");
    }
    public void printClose(){
        System.out.print("Close List: ");
        for(Node c: close.values())
            System.out.print(c.board.getFn()+" ");
        System.out.println("");
    }

    public static void main(String[] args){
    long StartTime = System.currentTimeMillis();
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
//                System.out.println(board.toString());
        }

    long EndTime = System.nanoTime();
        long duration = (EndTime - StartTime);
        System.out.println("//================================//");
        System.out.println("NODE EXPENDED: "+NodeExp);
        System.out.println("Execution Time: "+(duration/1000000000));
    }



}
