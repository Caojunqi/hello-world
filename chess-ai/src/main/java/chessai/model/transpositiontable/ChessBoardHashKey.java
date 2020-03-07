package chessai.model.transpositiontable;

/**
 * 棋盘HASH值计算结果
 */
public class ChessBoardHashKey {
    /**
     * 32位HASH值
     */
    private int hashKey32;
    /**
     * 64位HASH值
     */
    private long hashKey64;

    private ChessBoardHashKey() {
        // 私有化构造器
    }

    public static ChessBoardHashKey valueOf(int hashKey32, long hashKey64) {
        ChessBoardHashKey hashKey = new ChessBoardHashKey();
        hashKey.hashKey32 = hashKey32;
        hashKey.hashKey64 = hashKey64;
        return hashKey;
    }

    public int getHashKey32() {
        return hashKey32;
    }

    public long getHashKey64() {
        return hashKey64;
    }
}
