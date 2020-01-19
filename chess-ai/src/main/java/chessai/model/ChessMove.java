package chessai.model;

/**
 * 棋子走法
 */
public class ChessMove {
    /**
     * 棋子
     */
    private Chess chess;
    /**
     * 本次移动的起点X坐标
     */
    private int fromX;
    /**
     * 本次移动的起点Y坐标
     */
    private int fromY;
    /**
     * 本次移动的终点X坐标
     */
    private int toX;
    /**
     * 本次移动的终点Y坐标
     */
    private int toY;
    /**
     * 本次移动产生的价值
     */
    private int score;

    public Chess getChess() {
        return chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
