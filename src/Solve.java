import java.util.PriorityQueue;

public class Solve {
    PriorityQueue pq = new PriorityQueue();
   static int [][] goal = new int[][]{{1,2,3},{8,0,4},{7,6,5}};
   static int [][] Start = new int[][]{{1,3,4},{8,6,2},{7,0,5}};

    public static Board board = new Board(goal);


    public static void main(String[] args){

//        System.out.println("Starting the test");
//        if(compare(goal,Start)==true)
//            System.out.println("both goal and start are same.");
//        else
//            System.out.println("Not same");
      int displace = board.hamming(goal,Start);
      System.out.println("Displace tiles: "+displace);
      if(compare(goal,Start)==true)
              System.out.println("Goal is equal to Start!");
      else
          System.out.println("Goal is not-equal to Start");
    }

    public static boolean compare (int[][] x, int[][] y){
        for(int i=0;i<x.length;i++)
            for(int j=0;j<x.length;j++)
                if(x[i][j] !=y[i][j]){
                     return false;
                 }
        return true;
    }


}
