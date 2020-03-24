package chessai.controller.searchengine;

import chessai.controller.ChessManager;
import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.*;
import chessai.util.ChessBoardUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索引擎抽象类
 */
public abstract class AbstractSearchEngine {
    /**
     * 当前局面走法集合 <搜索层数, 每层的合法走法>
     */
    private Map<Integer, List<ChessMove>> moves = new HashMap<>();
    /**
     * 当前局面的最优走法
     */
    protected ChessMove bestMove;
    /**
     * 棋局评估器
     */
    protected IBoardEvaluator boardEvaluator;

    /**
     * 针对当前棋局局势搜索一个最佳棋子移动方案
     *
     * @return 最佳棋子移动方案
     */
    public abstract ChessMove searchBestMove();

    /**
     * 用以产生当前局面中所有可能的走法
     *
     * @param boardPosition 当前局势
     * @param curDepth      当前搜索的层数，同一棋子在每层的走法存在不同的位置
     * @param maxDepth      本次搜索的最大搜索层数
     */
    protected void createPossibleMoves(PointState[][] boardPosition, int curDepth, int maxDepth) {
        // 在每次生成指定层的所有可能走法时需要先清除掉当前层之前的走法
        clearPossibleMoves(curDepth);
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState pointState = boardPosition[i][j];
                if (pointState == PointState.NO_CHESS) {
                    continue;
                }
                CampType nextCamp = (maxDepth + curDepth) % 2 == 0 ? ChessBoardUtils.AI_CAMP : ChessBoardUtils.AI_CAMP.getEnemyCamp();
                if (!pointState.isSameCamp(nextCamp)) {
                    continue;
                }

                AbstractChessMoveGenerator moveGenerator = ChessManager.getInstance().getChessMoveGenerator(pointState);
                List<Position> positions = moveGenerator.generateValidMove(boardPosition, i, j);
                for (Position position : positions) {
                    addMove(i, j, position.getX(), position.getY(), curDepth);
                }
            }
        }
    }

    /**
     * 在走法集合中添加走法
     *
     * @param startX  移动起始点X坐标
     * @param startY  移动起始点Y坐标
     * @param targetX 移动目标点X坐标
     * @param targetY 移动目标点Y坐标
     * @param nPly    搜索层数
     */
    private void addMove(int startX, int startY, int targetX, int targetY, int nPly) {
        this.moves.computeIfAbsent(nPly, n -> new ArrayList<>()).add(ChessMove.valueOf(startX, startY, targetX, targetY));
    }

    /**
     * 获取指定搜索层数的所有合理走法
     *
     * @param nPly 搜索层数
     * @return 该指定搜索层数的所有合理走法
     */
    protected List<ChessMove> getPossibleMoves(int nPly) {
        List<ChessMove> possibleMoves = moves.get(nPly);
        if (possibleMoves == null) {
            possibleMoves = new ArrayList<>();
        }
        return possibleMoves;
    }

    /**
     * 清除指定层数的所有合理走法
     *
     * @param nPly 搜索层数
     */
    protected void clearPossibleMoves(int nPly) {
        this.moves.remove(nPly);
    }

    /**
     * 清空所有合理走法
     */
    protected void clearPossibleMoves() {
        this.moves.clear();
    }

    /**
     * 在指定棋局中移动一步棋子
     *
     * @param boardPosition 所面对的棋局局势
     * @param move          棋子移动
     * @return 棋子移动前，目标位置的棋点状态
     */
    public PointState makeMove(PointState[][] boardPosition, ChessMove move) {
        // 获取目标位置棋点状态
        PointState oldTargetState = boardPosition[move.getTargetX()][move.getTargetY()];
        // 把棋子移动到目标位置
        boardPosition[move.getTargetX()][move.getTargetY()] = boardPosition[move.getStartX()][move.getStartY()];
        // 将原位置清空
        boardPosition[move.getStartX()][move.getStartY()] = PointState.NO_CHESS;
        // 返回被吃掉的棋子
        return oldTargetState;
    }

    /**
     * 在指定棋局中撤销一步移动
     *
     * @param boardPosition  所面对的棋局局势
     * @param move           所撤销的棋子移动
     * @param oldTargetState 棋子移动前目标位置的棋点状态
     */
    public void unMakeMove(PointState[][] boardPosition, ChessMove move, PointState oldTargetState) {
        // 将目标位置的棋子拷回原位
        boardPosition[move.getStartX()][move.getStartY()] = boardPosition[move.getTargetX()][move.getTargetY()];
        // 恢复目标位置的棋子
        boardPosition[move.getTargetX()][move.getTargetY()] = oldTargetState;
    }

    /**
     * 判断当前棋局是否已分出胜负
     *
     * @param boardPosition 所面对的棋局局势
     * @return true-棋局胜负已分；false-棋局胜负未分。
     */
    public boolean isGameOver(PointState[][] boardPosition) {
        if (!ChessBoardUtils.isBlackLive(boardPosition)) {
            // 黑将已死
            return true;
        }
        if (!ChessBoardUtils.isRedLive(boardPosition)) {
            // 红帅已死
            return true;
        }
        return false;
    }

    /**
     * 估值函数
     *
     * @param boardPosition 待估值棋面
     * @param curDepth      当前剩余搜索层数
     * @param maxDepth      本次搜索计划的最大搜索层数
     * @return 估值结果
     */
    protected int evaluate(PointState[][] boardPosition, int curDepth, int maxDepth) {
        // (maxDepth+curDepth)的值如果是奇数，表明当前局面接下来由敌人走棋，我们需要从当前层的所有局面中挑选最大值
        // 如果是偶数，表明当前局面接下来由AI走棋，我们需要从当前层的所有局面中挑选最小值。
        // 但是无论是采用负极大值搜索还是Alpha-Beta剪枝，最后一层的估值结果都会被先取一个负号，然后再进行大小比较，因此应该再加上一个负号来进行抵消。
        int ratio = (maxDepth + curDepth) % 2 == 0 ? 1 : -1;
        CampType nextCamp = (maxDepth + curDepth) % 2 == 0 ? ChessBoardUtils.AI_CAMP : ChessBoardUtils.AI_CAMP.getEnemyCamp();
        if (ChessBoardUtils.AI_CAMP.isWin(boardPosition)) {
            return ratio * ChessBoardUtils.MAX_EVALUATE_VALUE;
        }

        if (ChessBoardUtils.AI_CAMP.isLose(boardPosition)) {
            return -ratio * ChessBoardUtils.MAX_EVALUATE_VALUE;
        }

        return ratio * boardEvaluator.evaluate(boardPosition, nextCamp);
    }

}
