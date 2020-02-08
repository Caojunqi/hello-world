package chessai.model;

/**
 * 棋子走法
 */
public class ChessMove {
    /**
     * 本次移动的起点X坐标
     */
    private int startX;
    /**
     * 本次移动的起点Y坐标
     */
    private int startY;
    /**
     * 本次移动的终点X坐标
     */
    private int targetX;
    /**
     * 本次移动的终点Y坐标
     */
    private int targetY;

    private ChessMove() {
        // 私有化构造器
    }

    public static ChessMove valueOf(int startX, int startY, int targetX, int targetY) {
        ChessMove chessMove = new ChessMove();
        chessMove.startX = startX;
        chessMove.startY = startY;
        chessMove.targetX = targetX;
        chessMove.targetY = targetY;
        return chessMove;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }
}
