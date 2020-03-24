package chessai.model.historyheuristic;

import chessai.model.ChessMove;

/**
 * 棋子走法，带历史得分
 */
public class HistoryScoreChessMove extends ChessMove implements Comparable<HistoryScoreChessMove> {
    /**
     * 本次移动的历史得分
     */
    private int score;

    private HistoryScoreChessMove() {
        // 私有化构造器
    }

    public static HistoryScoreChessMove valueOf(int startX, int startY, int targetX, int targetY, int score) {
        HistoryScoreChessMove chessMove = new HistoryScoreChessMove();
        chessMove.startX = startX;
        chessMove.startY = startY;
        chessMove.targetX = targetX;
        chessMove.targetY = targetY;
        chessMove.score = score;
        return chessMove;
    }

    @Override
    public int compareTo(HistoryScoreChessMove o) {
        return o.score - this.score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
