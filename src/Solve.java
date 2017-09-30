
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Queue;

public class Solve {

    static int [][] Easy = new int[][]{{1,3,4}, {8,6,2}, {7,0,5}};
    static int[][] Medium = new int[][]{{2,8,1},{0,4,3},{7,6,5}};
    static int [][] Hard = new int[][]{{2,8,1}, {4,6,3}, {0,7,5}};
    static int [][] Worst = new int[][]{{5,6,7}, {4,0,8}, {3,2,1}};

    static public HashMap<String,Node> close = new HashMap<>();
    static public PriorityQueue<Node> open = new PriorityQueue<>();
    static public PriorityQueue<Node> openQ = new PriorityQueue<>();


    private Node goalNode;
    static private int NodeExp =0;
    public class Node implements Comparable<Node>{
        public Board board;
        public Node previous;
        public int moves;

        @Override
        public int compareTo(Node that){
            if(this.priority() == (that.priority()))
                return 0;
            else if(this.priority()< that.priority())
                return -1;
            else
                return 1;
        }

        public Node(Board b, Node prev, int m){
            board = b;
            previous = prev;
            moves = m;
        }

        public int priority(){
            return board.getGn();
        }
    }

//-----------------------///////////   A* algorithm  \\\\\\\\\\\-----------------------------------------------------
//    public class Node implements Comparable<Node>{
//        public Board board;
//        public Node previous;
//        public int moves;
//
//        @Override
//        public int compareTo(Node that){
//                if(this.priority() == (that.priority()))
//                return 0;
//            else if(this.priority()< that.priority())
//                return -1;
//            else
//                return 1;
//        }
//
//        public Node(Board b, Node prev, int m){
//            board = b;
//            previous = prev;
//            moves = m;
//        }
//
//        public int priority(){
//            return board.getFn();
//        }
//    }

//    public Solve(Board initial) {
//        Node currentNode = new Node(initial, null, 0);
//
//        open.add( currentNode);  //Insert in open list
//
//        while (!currentNode.board.isGoal()) {  //while not reached GOAL
//
//            currentNode = open.remove();  //remove minimum F(n) from OpenList Return
//
//            close.put(Arrays.deepToString(currentNode.board.getElement()), currentNode);
//
//                System.out.println("F(n): " + currentNode.board.getFn());
//                System.out.println("G(n): " + currentNode.board.getGn());
//                System.out.println("H(n): " + currentNode.board.hamming());
//
//                System.out.println("Expend Node: " + currentNode.board.toString());
////                printOpen();
////                printClose();
//
//                NodeExp++; //increment node expend variable
//                for (Board b : currentNode.board.neighbors()) {  //for every childs insert in OpenList
//                    if(close.containsKey(Arrays.deepToString(b.getElement()))){
//                        System.out.println("SameBoard Don't put in OpenList");
//                        continue; // Skip this iteration
//                    }
//                    else {
//                        b.setGn(currentNode.board.getGn() + 1);          //increase Level
//                        open.add(new Node(b, currentNode, currentNode.moves + 1)); //create new node and add to Openlist
//                    }
//                }//for loop
//
//                 System.out.println("+++++++++++++++++++++++++++++++++++");
//
//        } //While Loop
//
//        if (currentNode.board.isGoal())
//            goalNode = currentNode;
//    } //end Solve
//-----------------------///////////   A* algorithm  \\\\\\\\\\\-----------------------------------------------------


//++++++++++++++++++++++=====||  Iterate Deeping With Manhattan ||======+++++++++++++++++++++++++++++++++++++++++++++++
    public Solve(Board initial) {
        Node currentNode = new Node(initial, null, 0);

        openQ.add( currentNode);  //Insert in open list
        int limit =0;
        while (!openQ.isEmpty()) {  //while not reached GOAL
            for(int i=0;i<openQ.size();i++) {

                while (openQ.peek().board.getGn()==limit){
                    currentNode = openQ.remove();  //remove minimum F(n) from OpenList Return
                    close.put(Arrays.deepToString(currentNode.board.getElement()), currentNode);
                    if (currentNode.board.isGoal()) {

                        break;
                    } else  {
                        NodeExp++; //increment node expend variable
                        for (Board b : currentNode.board.neighbors()) {  //for every childs insert in OpenList
                            if(close.containsKey(Arrays.deepToString(b.getElement()))){
                              //  System.out.println("SameBoard Don't put in OpenList");
                                continue; // Skip this iteration
                            }
                            else {
                                b.setGn(currentNode.board.getGn() + 1);          //increase Level
                                openQ.add(new Node(b, currentNode, currentNode.moves + 1)); //create new node and add to Openlist
                               // System.out.println(b.getGn()+", ");
                            }

                        }//for loop

                    }
                }
                if (currentNode.board.isGoal()) {

                    break;
                }
                limit++;
            }
            if (currentNode.board.isGoal()) {
                System.out.println("GOAL FOUND! : "+currentNode.board.toString());
                break;
            }

//++++++++++++++++++++++=====||  Iterate Deeping With Manhattan ||======+++++++++++++++++++++++++++++++++++++++++++++++
//
//            close.put(Arrays.deepToString(currentNode.board.getElement()), currentNode);
//
//            System.out.println("F(n): " + currentNode.board.getFn());
//            System.out.println("G(n): " + currentNode.board.getGn());
//            System.out.println("H(n): " + currentNode.board.hamming());
//
//            System.out.println("Expend Node: " + currentNode.board.toString());
////                printOpen();
////                printClose();
//
//
//
//            System.out.println("+++++++++++++++++++++++++++++++++++");

        } //While Loop

        if (currentNode.board.isGoal())
            goalNode = currentNode;
    } //end Solve

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
        Board initial = new Board(Hard);
        Solve solver = new Solve(initial);

        if (!solver.isSolvable())
            System.out.println("No solution possible");
        else {
            System.out.println("(+|+|+|+|+|+|+|+|+|+|+|+|+|+|+|+|+|+|+|+|+)"+"\n");
            System.out.println("Minimum number of moves = " + solver.move());
            System.out.println("NODE EXPENDED: "+NodeExp);

            long EndTime = System.nanoTime();
            long duration = (EndTime - StartTime);
            System.out.println("Execution Time: "+(duration/1000000000));
        }


    }



}
