package chessai.model;

/**
 * 位置信息
 */
public class Position {
    /**
     * X坐标
     */
    private int x;
    /**
     * Y坐标
     */
    private int y;

    private Position() {
        // 私有化构造器
    }

    public static Position valueOf(int x, int y) {
        Position position = new Position();
        position.x = x;
        position.y = y;
        return position;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
