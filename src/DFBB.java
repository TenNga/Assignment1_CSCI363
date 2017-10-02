
import java.util.*;

public class DFBB {
    static public HashMap<String, Node> close = new HashMap<>();
    static public Stack<Node> open = new Stack<>();

    private Node goalNode;
    static private int NodeExp = 0;

    public class Node {
        public Board board;
        public Node previous;
        public int moves;
//
//        @Override
//        public int compareTo(Node that) {
//            if (this.priority() == (that.priority()))
//                return 0;
//            else if (this.priority() < that.priority())
//                return -1;
//            else
//                return 1;
//        }
//
        public Node(Board b, Node prev, int m) {
            board = b;
            previous = prev;
            moves = m;
        }
//
//        public int priority() {
//            return board.getFnManh();
//        }
    }

    public DFBB(Board initial) {
        Node currentNode = new Node(initial, null, 0);

        //open.add(currentNode);  //Insert in open list
        open.push(currentNode);
        int cost=open.peek().board.manhattan();;
        System.out.println("//////////////////---[ Depth - First B & B Algorithm ]---/////////////////\n");
        while (!open.isEmpty()) {  //while not reached GOAL
            currentNode = open.pop();
            if(currentNode.board.isGoal()){
                System.out.println("Goal Founded: "+currentNode.board.toString());
                     cost=currentNode.board.manhattan();
                     goalNode=currentNode;
                     print();
            }
            close.put(Arrays.deepToString(currentNode.board.getElement()), currentNode);

            NodeExp++; //increment node expend variable
            for (Board b : currentNode.board.neighbors()) {  //for every childs insert in OpenList
                if (close.containsKey(Arrays.deepToString(b.getElement()))|| b.manhattan()>cost) {
                    continue; // Skip this iteration
                } else {
                    b.setGn(currentNode.board.getGn() + 1);          //increase Level
                    //open.add(); //create new node and add to Openlist
                    open.push(new Node(b, currentNode, currentNode.moves + 1));
                }
            }//for loop
        } //While Loop

        if (currentNode.board.isGoal())
            goalNode = currentNode;
       // print();
    } //end Solve


    public boolean isSolvable() {
        return goalNode != null;
    }

    public int move() {
        return goalNode.moves;
    }
    public void print() {

        long startTime = System.nanoTime();
        if (!isSolvable())
            System.out.println("No solution possible");
        else {
            System.out.println("Minimum number of moves = " + move());
            System.out.println("NODE EXPENDED: " + NodeExp);
        }
        long endTime = System.nanoTime();
        long duration=(endTime-startTime);
        System.out.println("Execution time: "+duration+"\n");
    }


}
