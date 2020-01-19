package chessai.model;

/**
 * 棋子
 */
public class Chess {
    /**
     * 棋子的唯一ID
     */
    private int id;
    /**
     * 棋子X坐标
     */
    private int x;
    /**
     * 棋子Y坐标
     */
    private int y;
    /**
     * 棋子类型
     */
    private ChessType chessType;

    private Chess() {
        // 私有化构造器
    }

    /**
     * 构建棋子
     *
     * @param id        棋子唯一ID
     * @param x         棋子X坐标
     * @param y         棋子Y坐标
     * @param chessCode 棋子编号
     * @return 棋子
     */
    public static Chess valueOf(int id, int x, int y, int chessCode) {
        Chess chess = new Chess();
        chess.id = id;
        chess.x = x;
        chess.y = y;
        chess.chessType = ChessType.getChessType(chessCode);
        return chess;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ChessType getChessType() {
        return chessType;
    }

}
