package chessai.controller;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.*;
import chessai.util.ChessBoardUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 棋子走法管理类
 */
@Component
public class ChessMoveManager {

    private static ChessMoveManager self;

    /**
     * 当前局面走法集合 <搜索层数, 每层的合法走法>
     */
    private Map<Integer, List<ChessMove>> moves = new HashMap<>();

    public static ChessMoveManager getInstance() {
        return self;
    }

    @PostConstruct
    public void init() {
        self = this;
    }

    /**
     * 用以产生当前局面中所有可能的走法
     *
     * @param boardPosition 当前局势
     * @param curDepth      当前搜索的层数，同一棋子在每层的走法存在不同的位置
     * @param maxDepth      本次搜索的最大搜索层数
     */
    public void createPossibleMoves(PointState[][] boardPosition, int curDepth, int maxDepth) {
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
     * 判断指定棋子移动是否合法
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        要移动的起点X坐标
     * @param startY        要移动的起点Y坐标
     * @param targetX       要移动的目标点X坐标
     * @param targetY       要移动的目标点Y坐标
     * @return true-棋子移动合法；false-棋子移动不合法。
     */
    public boolean isValidMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        if (!ChessBoardUtils.inBoard(targetX, targetY)) {
            // 目标点超出棋盘范围
            return false;
        }

        if (startX == targetX && startY == targetY) {
            // 目标与源相同
            return false;
        }

        PointState startState = boardPosition[startX][startY];
        if (startState == PointState.NO_CHESS) {
            // 不能移动无子的棋点
            return false;
        }
        PointState targetState = boardPosition[targetX][targetY];
        if (startState.isSameCamp(targetState)) {
            // 吃自己的棋，非法
            return false;
        }

        AbstractChessMoveChecker moveChecker = ChessManager.getInstance().getChessMoveChecker(startState);
        return moveChecker.checkMove(boardPosition, startX, startY, targetX, targetY);
    }

    /**
     * 判断指定棋子是否能移动到相关联的位置，包括所有合理位置，以及自己能提供保护的位置
     * 注：此接口与{@link ChessMoveManager#isValidMove(PointState[][], int, int, int, int)}接口的区别只在于，本接口是可以把棋子走到己方棋子的位置上的，因为这样就能为己方棋子提供保护
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        要移动的起点X坐标
     * @param startY        要移动的起点Y坐标
     * @param targetX       要移动的目标点X坐标
     * @param targetY       要移动的目标点Y坐标
     * @return true-棋子可移动至相关联的位置；false-棋子不可移动至相关联的位置。
     */
    public boolean isRelateMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY) {
        if (!ChessBoardUtils.inBoard(targetX, targetY)) {
            // 目标点超出棋盘范围
            return false;
        }

        if (startX == targetX && startY == targetY) {
            // 目标与源相同
            return false;
        }

        PointState startState = boardPosition[startX][startY];
        if (startState == PointState.NO_CHESS) {
            // 不能移动无子的棋点
            return false;
        }

        AbstractChessMoveChecker moveChecker = ChessManager.getInstance().getChessMoveChecker(startState);
        return moveChecker.checkMove(boardPosition, startX, startY, targetX, targetY);
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
    public void addMove(int startX, int startY, int targetX, int targetY, int nPly) {
        this.moves.computeIfAbsent(nPly, n -> new ArrayList<>()).add(ChessMove.valueOf(startX, startY, targetX, targetY));
    }

    /**
     * 获取指定搜索层数的所有合理走法
     *
     * @param nPly 搜索层数
     * @return 该指定搜索层数的所有合理走法
     */
    public List<ChessMove> getPossibleMoves(int nPly) {
        List<ChessMove> possibleMoves = moves.get(nPly);
        if (possibleMoves == null) {
            possibleMoves = new ArrayList<>();
        }
        return possibleMoves;
    }

    /**
     * 清空所有合理走法
     */
    public void clearPossibleMoves() {
        this.moves.clear();
    }

}
