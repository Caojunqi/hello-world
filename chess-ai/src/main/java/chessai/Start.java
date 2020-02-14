package chessai;

import chessai.model.ChessBoard;

/**
 * 启动类
 */
public class Start {

    /**
     * 程序调用计数器
     */
    private static int COUNT = 0;
    /**
     * 最大值常量
     */
    private static int MAX_VALUE = 99999;

    public static void main(String[] args) {
        int[][] board = new int[ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH];
        System.out.println(board[2][2]);
        changeArr(board);
        System.out.println(board[2][2]);
    }

    private static void changeArr(int[][] arr) {
        arr[2][2] = 99;
    }
}
