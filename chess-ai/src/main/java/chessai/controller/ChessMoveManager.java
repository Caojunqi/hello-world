package chessai.controller;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.model.Position;
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
     * @param nPly          当前搜索的层数，同一棋子在每层的走法存在不同的位置
     */
    public void createPossibleMoves(PointState[][] boardPosition, int nPly) {
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState pointState = boardPosition[i][j];
                if (pointState == PointState.NO_CHESS) {
                    continue;
                }
                if (!pointState.isSameCamp(ChessBoardUtils.AI_CAMP)) {
                    continue;
                }

                AbstractChessMoveGenerator moveGenerator = ChessManager.getInstance().getChessMoveGenerator(pointState);
                List<Position> positions = moveGenerator.generateMove(boardPosition, i, j);
                for (Position position : positions) {
                    addMove(i, j, position.getX(), position.getY(), nPly);
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
        PointState targetState = boardPosition[targetX][targetY];
        if (startState.isSameCamp(targetState)) {
            // 吃自己的棋，非法
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
