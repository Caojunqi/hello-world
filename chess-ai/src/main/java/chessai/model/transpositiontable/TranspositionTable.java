package chessai.model.transpositiontable;

import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.RandomUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 置换表  采用Zobrist哈希技术
 */
public class TranspositionTable {

    /**
     * 置换表所属棋局
     */
    private ChessBoard chessBoard;
    /**
     * 存放32位随机数
     */
    private int[][][] randomKey32;
    /**
     * 存放64位随机数
     */
    private long[][][] randomKey64;
    /**
     * 存放最大值节点的数据
     */
    private Map<Integer, HashItem> maxNodeHashMap = new HashMap<>();
    /**
     * 存放最小值节点的数据
     */
    private Map<Integer, HashItem> minNodeHashMap = new HashMap<>();
    /**
     * 当前棋局的32位HASH值
     */
    private int chessBoardHashKey32;
    /**
     * 当前棋局的64位HASH值
     */
    private long chessBoardHashKey64;

    private TranspositionTable() {
        // 私有化构造器
    }

    public static TranspositionTable valueOf(ChessBoard chessBoard) {
        TranspositionTable transpositionTable = new TranspositionTable();
        transpositionTable.chessBoard = chessBoard;
        transpositionTable.initRandomKey();
        transpositionTable.calcChessBoardInitHashKey();


        return transpositionTable;
    }

    /**
     * 初始化Zobrist所需的随机数组
     */
    private void initRandomKey() {
        int[][][] randomKey32 = new int[PointState.values().length][ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH];
        long[][][] randomKey64 = new long[PointState.values().length][ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH];
        for (int i = 0; i < PointState.values().length; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_HEIGHT; j++) {
                for (int k = 0; k < ChessBoard.CHESS_BOARD_LENGTH; k++) {
                    randomKey32[i][j][k] = RandomUtils.nextInt();
                    randomKey64[i][j][k] = RandomUtils.nextLong();
                }
            }
        }
        this.randomKey32 = randomKey32;
        this.randomKey64 = randomKey64;
    }

    /**
     * 计算棋局的初始HASH值
     */
    private void calcChessBoardInitHashKey() {
        PointState[][] boardPosition = chessBoard.getCurChessBoard();
        int hashKey32 = 0;
        long hashKey64 = 0L;
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState pointState = boardPosition[i][j];
                hashKey32 = hashKey32 ^ getRandomKey32(pointState, i, j);
                hashKey64 = hashKey64 ^ getRandomKey64(pointState, i, j);
            }
        }
        this.chessBoardHashKey32 = hashKey32;
        this.chessBoardHashKey64 = hashKey64;
    }

    /**
     * 计算执行走法后的棋局HASH值
     *
     * @param move 所要执行的走法
     */
    public void calcMakeMoveHashKey(ChessMove move) {
        PointState[][] boardPosition = chessBoard.getCurChessBoard();
        PointState startState = boardPosition[move.getStartX()][move.getStartY()];
        PointState targetState = boardPosition[move.getTargetX()][move.getTargetY()];
        // 移除掉起点的随机数
        chessBoardHashKey32 = chessBoardHashKey32 ^ getRandomKey32(startState, move.getStartX(), move.getStartY());
        chessBoardHashKey64 = chessBoardHashKey64 ^ getRandomKey64(startState, move.getStartX(), move.getStartY());
        // 移除掉终点的随机数
        chessBoardHashKey32 = chessBoardHashKey32 ^ getRandomKey32(targetState, move.getTargetX(), move.getTargetY());
        chessBoardHashKey64 = chessBoardHashKey64 ^ getRandomKey64(targetState, move.getTargetX(), move.getTargetY());
        // 棋子移动后，添加上目标点的随机数
        chessBoardHashKey32 = chessBoardHashKey32 ^ getRandomKey32(startState, move.getTargetX(), move.getTargetY());
        chessBoardHashKey64 = chessBoardHashKey64 ^ getRandomKey64(startState, move.getTargetX(), move.getTargetY());
    }

    /**
     * 计算撤销走法后的棋局HASH值
     *
     * @param move           要撤销的走法
     * @param oldTargetState 棋子移动前目标位置的棋点状态
     */
    public void calcUnMakeMoveHashKey(ChessMove move, PointState oldTargetState) {
        PointState[][] boardPosition = chessBoard.getCurChessBoard();
        PointState targetState = boardPosition[move.getTargetX()][move.getTargetY()];
        // 将移动棋子在移动前位置上的随机数添入
        chessBoardHashKey32 = chessBoardHashKey32 ^ getRandomKey32(targetState, move.getStartX(), move.getStartY());
        chessBoardHashKey64 = chessBoardHashKey64 ^ getRandomKey64(targetState, move.getStartX(), move.getStartY());
        // 将移动棋子在现位置上的随机数删掉
        chessBoardHashKey32 = chessBoardHashKey32 ^ getRandomKey32(targetState, move.getTargetX(), move.getTargetY());
        chessBoardHashKey64 = chessBoardHashKey64 ^ getRandomKey64(targetState, move.getTargetX(), move.getTargetY());
        // 将被吃掉的棋子所对应的随机数添入
        chessBoardHashKey32 = chessBoardHashKey32 ^ getRandomKey32(oldTargetState, move.getTargetX(), move.getTargetY());
        chessBoardHashKey64 = chessBoardHashKey64 ^ getRandomKey64(oldTargetState, move.getTargetX(), move.getTargetY());
    }

    /**
     * 往置换表中插入数据
     *
     * @param itemType  数据类型
     * @param depth     取得数据值的搜索层次
     * @param value     棋局数据值
     * @param isMaxNode 当前局面是否是最大值节点，true-是最大值节点；false-是最小值节点
     */
    public void enterHashTable(HashItemType itemType, int depth, int value, boolean isMaxNode) {
        HashItem item = HashItem.valueOf(itemType, chessBoardHashKey64, depth, value);
        Map<Integer, HashItem> itemMap = isMaxNode ? maxNodeHashMap : minNodeHashMap;
        itemMap.put(chessBoardHashKey32, item);
    }

    /**
     * 查找哈希表
     *
     * @param alpha     alpha-beta搜索的上边界
     * @param beta      alpha-beta搜索的下边界
     * @param depth     当前搜索的层次
     * @param isMaxNode 当前局面是否是最大值节点，true-是最大值节点；false-是最小值节点
     * @return 棋局的估值，返回null表示置换表中没有命中该棋局
     */
    public Integer lookUpHashTable(int alpha, int beta, int depth, boolean isMaxNode) {
        Map<Integer, HashItem> itemMap = isMaxNode ? maxNodeHashMap : minNodeHashMap;
        HashItem item = itemMap.get(chessBoardHashKey32);
        if (item == null) {
            return null;
        }
        if (item.getDepth() < depth) {
            // 存储的棋局深度太浅
            return null;
        }
        if (item.getCheckSum() != chessBoardHashKey64) {
            // 不是同一棋局
            return null;
        }
        return item.getItemType().checkValue(alpha, beta, item.getValue());
    }


    /**
     * 获取指定棋点对应的32位随机数
     *
     * @param pointState 棋点类型
     * @param x          棋点X坐标
     * @param y          棋点Y左边
     * @return 32位随机数
     */
    private int getRandomKey32(PointState pointState, int x, int y) {
        return this.randomKey32[pointState.getCode()][x][y];
    }

    /**
     * 获取指定棋点对应的64位随机数
     *
     * @param pointState 棋点类型
     * @param x          棋点X坐标
     * @param y          棋点Y坐标
     * @return 64位随机数
     */
    private long getRandomKey64(PointState pointState, int x, int y) {
        return this.randomKey64[pointState.getCode()][x][y];
    }
}
