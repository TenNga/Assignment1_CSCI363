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
public class Solve {
    static int[][] Easy = new int[][]{{1, 3, 4}, {8, 6, 2}, {7, 0, 5}};
    static int[][] Medium = new int[][]{{2, 8, 1}, {0, 4, 3}, {7, 6, 5}};
    static int[][] Hard = new int[][]{{2, 8, 1}, {4, 6, 3}, {0, 7, 5}};
    static int[][] Worst = new int[][]{{5, 6, 7}, {4, 0, 8}, {3, 2, 1}};
    static LinkedList<int[][]> list = new LinkedList<>();
    static LinkedList<Board> board = new LinkedList<>();
    public static void main(String[] args) {
            list.add(Easy); //------- 0
            list.add(Medium);//------- 1
            list.add(Hard);//------- 2
            list.add(Worst);//------- 3
        /*
        CHOOSE STATE
        (****************************************************************);
        (Easy -- 0 || Medium -- 1 || Hard -- 2 || Worst -- 3 || EXIT -- 4);
        (****************************************************************);
     */
        board.add(new Board(list.get(0)));
        board.add(new Board(list.get(1)));
        board.add(new Board(list.get(2)));
        board.add(new Board(list.get(3)));

            for(int i=0;i<board.size();i++) {
                if(i==0){
                    System.out.println("################-- EASY --###################");
                }else if(i==1){
                    System.out.println("################-- MEDIUM --###################");
                }else if(i==2){
                    System.out.println("################-- HARD --###################");
                }else if(i==3){
                    System.out.println("################-- WORST --###################");
                }
                System.out.println("");
                aStar solver = new aStar(board.get(i));
                IdAstart solver2 = new IdAstart(board.get(i));
                AstarManh solver3 = new AstarManh(board.get(i));
                DFBB solver4 = new DFBB(board.get(i));

            }
    }//end main
}//end class
