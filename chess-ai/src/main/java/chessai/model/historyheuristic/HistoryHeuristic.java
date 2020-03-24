package chessai.model.historyheuristic;

import chessai.model.ChessBoard;
import chessai.model.ChessMove;

/**
 * 历史启发核心类
 */
public class HistoryHeuristic {

    /**
     * 历史记录表
     * 需要能够记录所有走法，所以记录表的大小是“棋盘长*棋盘宽”
     */
    private int[][] historyTable = new int[ChessBoard.CHESS_BOARD_LENGTH * ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH * ChessBoard.CHESS_BOARD_HEIGHT];

    /**
     * 将历史记录表清空
     */
    public void resetHistoryTable() {
        this.historyTable = new int[ChessBoard.CHESS_BOARD_LENGTH * ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH * ChessBoard.CHESS_BOARD_HEIGHT];
    }

    /**
     * 获取指定走法的历史得分
     *
     * @param startX  本次移动的起点X坐标
     * @param startY  本次移动的起点Y坐标
     * @param targetX 本次移动的终点X坐标
     * @param targetY 本次移动的终点Y坐标
     * @return 历史得分
     */
    public int getHistoryScore(int startX, int startY, int targetX, int targetY) {
        // 原始位置
        int start = startX * ChessBoard.CHESS_BOARD_LENGTH + startY;
        // 目标位置
        int target = targetX * ChessBoard.CHESS_BOARD_LENGTH + targetY;
        // 返回历史得分
        return historyTable[start][target];
    }

    /**
     * 将某一最佳走法汇入历史记录表
     *
     * @param move  走法
     * @param depth 走法所在的搜索深度
     */
    public void enterHistoryScore(ChessMove move, int depth) {
        // 原始位置
        int start = move.getStartX() * ChessBoard.CHESS_BOARD_LENGTH + move.getStartY();
        // 目标位置
        int target = move.getTargetX() * ChessBoard.CHESS_BOARD_LENGTH + move.getTargetY();
        // 增量为2的depth次方
        historyTable[start][target] += 2 << depth;
    }
}
