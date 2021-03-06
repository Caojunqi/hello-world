package chessai.model;

import chessai.model.transpositiontable.TranspositionTable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘
 */
@Component
public class ChessBoard {

    private static ChessBoard self;

    /**
     * 棋盘长
     */
    public static final int CHESS_BOARD_LENGTH = 9;
    /**
     * 棋盘高
     */
    public static final int CHESS_BOARD_HEIGHT = 10;
    /**
     * 初始棋盘
     */
    private final int[][] INIT_CHESS_BOARD = {
            {2, 3, 6, 5, 1, 5, 6, 3, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 0, 0, 0, 0, 4, 0},
            {7, 0, 7, 0, 7, 0, 7, 0, 7},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {14, 0, 14, 0, 14, 0, 14, 0, 14},
            {0, 11, 0, 0, 0, 0, 0, 11, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {9, 10, 13, 12, 8, 12, 13, 10, 9}
    };

    /**
     * 当前的棋盘状态
     */
    private PointState[][] curChessBoard;
    /**
     * 当前棋盘的置换表
     */
    private TranspositionTable transpositionTable;

    /**
     * 初始化棋盘
     */
    @PostConstruct
    public void init() {
        self = this;
    }

    /**
     * 初始化棋盘
     */
    public void initChessBoard() {
        PointState[][] curChessBoard = new PointState[CHESS_BOARD_HEIGHT][CHESS_BOARD_LENGTH];
        for (int i = 0; i < CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < CHESS_BOARD_LENGTH; j++) {
                curChessBoard[i][j] = PointState.getPointState(INIT_CHESS_BOARD[i][j]);
            }
        }
        this.curChessBoard = curChessBoard;
        this.transpositionTable = TranspositionTable.valueOf(this);
    }

    public static ChessBoard getInstance() {
        return self;
    }

    /**
     * 获取指定坐标点的状态
     *
     * @param x X坐标
     * @param y Y坐标
     * @return 棋盘点状态
     */
    public PointState getPointState(int x, int y) {
        return curChessBoard[x][y];
    }

    /**
     * 获取棋盘上所有指定状态的位置集合
     *
     * @param pointState 所要找的棋点状态
     * @return 棋点位置集合
     */
    public List<Position> getPositions(PointState pointState) {
        List<Position> result = new ArrayList<>();
        for (int i = 0; i < CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < CHESS_BOARD_LENGTH; j++) {
                if (curChessBoard[i][j] == pointState) {
                    result.add(Position.valueOf(i, j));
                    if (result.size() >= pointState.getMaxNum()) {
                        // 已经找到所有满足条件的棋点位置了，不用继续遍历下去了
                        return result;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 在棋盘上走一步棋
     *
     * @param chessMove 棋子走法
     * @return 被吃掉的棋子
     */
    public PointState makeMove(ChessMove chessMove) {
        // 先计算一下置换表的HASH值
        transpositionTable.calcMakeMoveHashKey(chessMove);
        // 保留原位置棋子
        PointState oldTargetState = curChessBoard[chessMove.getTargetX()][chessMove.getTargetY()];
        // 把棋子移动到目标位置
        curChessBoard[chessMove.getTargetX()][chessMove.getTargetY()] = curChessBoard[chessMove.getStartX()][chessMove.getStartY()];
        // 将原位置清空
        curChessBoard[chessMove.getStartX()][chessMove.getStartY()] = PointState.NO_CHESS;
        return oldTargetState;
    }

    /**
     * 获取当前的棋盘状态
     *
     * @return 当前的棋盘状态
     */
    public PointState[][] getCurChessBoard() {
        return curChessBoard;
    }

    /**
     * 获取当前棋盘的置换表
     *
     * @return 置换表
     */
    public TranspositionTable getTranspositionTable() {
        return transpositionTable;
    }
}
