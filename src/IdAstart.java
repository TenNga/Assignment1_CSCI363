/*
NAME: TENZIN NGAWANG
PROJECT NAME: 8 PUZZLE
ALGORITHM USED: A*, A* MANHATTAN, IDA*, DFBnB
DESCRIPTION: THIS PROJECT IS FOR AI CLASS CSCI363 WITH PROFESSOR CHANGHE YUAN, WHICH WILL SOLVE 8 PUZZLE PROBLEM
    WITH DIFFERENT STATES.
CONTACT: TENNGAWANG6080@GMAIL.COM
-------------------------COPYWRITE @ TENZIN NGAWANG
 */
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Arrays;

public class IdAstart {
    static public HashMap<String,Node> close = new HashMap<>();
    static public PriorityQueue<Node> open = new PriorityQueue<>();
    static public PriorityQueue<Node> openQ = new PriorityQueue<>();


    private Node goalNode2;
    static public int NodeExp =0;
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
    public IdAstart(Board initial) {
        Node currentNode = new Node(initial, null, 0);

        openQ.add( currentNode);  //Insert in open list
        int limit =0;
        while (!openQ.isEmpty()) {  //while not reached GOAL
            int Qsize = openQ.size();
            for(int i=0;i<Qsize;i++) {
                if(openQ.peek().board.getGn()==limit) {
                    currentNode = openQ.remove();  //remove minimum F(n) from OpenList Return
                    close.put(Arrays.deepToString(currentNode.board.getElement()), currentNode);
                    if (currentNode.board.isGoal()) {
                        break;
                    } else {
                      //  System.out.println("Manhattan: " + currentNode.board.getFn());
                        NodeExp++; //increment node expend variable
                        for (Board b : currentNode.board.neighbors()) {  //for every childs insert in OpenList
                            if (close.containsKey(Arrays.deepToString(b.getElement()))) {
                                //  System.out.println("SameBoard Don't put in OpenList");
                                continue; // Skip this iteration
                            } else {
                                b.setGn(currentNode.board.getGn() + 1);          //increase Level
                                openQ.add(new Node(b, currentNode, currentNode.moves + 1)); //create new node and add to Openlist
                                // System.out.println(b.getGn()+", ");
                            }
                        }//for loop
                    }
                } //end if limit
                if (currentNode.board.isGoal()) {

                    break;
                }

            } limit++;
            if (currentNode.board.isGoal()) {
                break;
            }
        } //While Loop

        if (currentNode.board.isGoal())
            goalNode2 = currentNode;
        print();
    } //end Solve

    public boolean isSolvable(){
        return goalNode2 != null;
    }

    public int move2(){
        return goalNode2.moves;
    }
    public void print() {

        long startTime = System.nanoTime();
        if (!isSolvable())
            System.out.println("No solution possible");
        else {
            System.out.println("//////////////////---[ IDA* Algorithm ]---/////////////////\n");
            System.out.println("Minimum number of moves = " + move2());
            System.out.println("NODE EXPENDED: " + NodeExp);

        }
        long endTime = System.nanoTime();
        long duration=(endTime-startTime);
        System.out.println("Execution time: "+duration);
        open.clear();
        close.clear();
    }

}
