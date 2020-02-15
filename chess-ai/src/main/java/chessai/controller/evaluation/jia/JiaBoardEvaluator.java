package chessai.controller.evaluation.jia;

import chessai.controller.ChessManager;
import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.CampType;
import chessai.model.ChessBoard;
import chessai.model.PointState;
import chessai.model.Position;
import chessai.util.ChessBoardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 棋局评估器-甲号
 */
@Component
public class JiaBoardEvaluator implements IBoardEvaluator {
    @Autowired
    private ChessManager chessManager;

    private static IBoardEvaluator instance;

    public static IBoardEvaluator getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    // 每种棋子的基本价值
    // 兵-100 士-250 象-250 车-500 马-350 炮-350 将-10000
    private int PAWN_BASE_VALUE = 100;
    private int GUARD_BASE_VALUE = 250;
    private int MINISTER_BASE_VALUE = 250;
    private int ROOK_BASE_VALUE = 500;
    private int KNIGHT_BASE_VALUE = 350;
    private int CANNON_BASE_VALUE = 350;
    private int KING_BASE_VALUE = 10000;
    private int NO_CHESS_BASE_VALUE = 0;
    private Map<PointState, Integer> chessBaseValues = new HashMap<>(PointState.values().length);

    {
        chessBaseValues.put(PointState.BLACK_PAWN, PAWN_BASE_VALUE);
        chessBaseValues.put(PointState.RED_PAWN, PAWN_BASE_VALUE);
        chessBaseValues.put(PointState.BLACK_GUARD, GUARD_BASE_VALUE);
        chessBaseValues.put(PointState.RED_GUARD, GUARD_BASE_VALUE);
        chessBaseValues.put(PointState.BLACK_MINISTER, MINISTER_BASE_VALUE);
        chessBaseValues.put(PointState.RED_MINISTER, MINISTER_BASE_VALUE);
        chessBaseValues.put(PointState.BLACK_ROOK, ROOK_BASE_VALUE);
        chessBaseValues.put(PointState.RED_ROOK, ROOK_BASE_VALUE);
        chessBaseValues.put(PointState.BLACK_KNIGHT, KNIGHT_BASE_VALUE);
        chessBaseValues.put(PointState.RED_KNIGHT, KNIGHT_BASE_VALUE);
        chessBaseValues.put(PointState.BLACK_CANNON, CANNON_BASE_VALUE);
        chessBaseValues.put(PointState.RED_CANNON, CANNON_BASE_VALUE);
        chessBaseValues.put(PointState.BLACK_KING, KING_BASE_VALUE);
        chessBaseValues.put(PointState.RED_KING, KING_BASE_VALUE);
        chessBaseValues.put(PointState.NO_CHESS, NO_CHESS_BASE_VALUE);
    }

    // 每种棋子的灵活性，也就是每多一个可走位置应加上的分值
    // 兵-15 士-1 象-1 车-6 马-12 炮-6 将-0
    private int PAWN_FLEXIBILITY = 15;
    private int GUARD_FLEXIBILITY = 1;
    private int MINISTER_FLEXIBILITY = 1;
    private int ROOK_FLEXIBILITY = 6;
    private int KNIGHT_FLEXIBILITY = 12;
    private int CANNON_FLEXIBILITY = 6;
    private int KING_FLEXIBILITY = 0;
    private int NO_CHESS_FLEXIBILITY = 0;
    private Map<PointState, Integer> chessFlexValues = new HashMap<>(PointState.values().length);

    {
        chessFlexValues.put(PointState.BLACK_PAWN, PAWN_FLEXIBILITY);
        chessFlexValues.put(PointState.RED_PAWN, PAWN_FLEXIBILITY);
        chessFlexValues.put(PointState.BLACK_GUARD, GUARD_FLEXIBILITY);
        chessFlexValues.put(PointState.RED_GUARD, GUARD_FLEXIBILITY);
        chessFlexValues.put(PointState.BLACK_MINISTER, MINISTER_FLEXIBILITY);
        chessFlexValues.put(PointState.RED_MINISTER, MINISTER_FLEXIBILITY);
        chessFlexValues.put(PointState.BLACK_ROOK, ROOK_FLEXIBILITY);
        chessFlexValues.put(PointState.RED_ROOK, ROOK_FLEXIBILITY);
        chessFlexValues.put(PointState.BLACK_KNIGHT, KNIGHT_FLEXIBILITY);
        chessFlexValues.put(PointState.RED_KNIGHT, KNIGHT_FLEXIBILITY);
        chessFlexValues.put(PointState.BLACK_CANNON, CANNON_FLEXIBILITY);
        chessFlexValues.put(PointState.RED_CANNON, CANNON_FLEXIBILITY);
        chessFlexValues.put(PointState.BLACK_KING, KING_FLEXIBILITY);
        chessFlexValues.put(PointState.RED_KING, KING_FLEXIBILITY);
        chessFlexValues.put(PointState.NO_CHESS, NO_CHESS_FLEXIBILITY);
    }

    // 兵在不同位置的附加值，基本上是过河之后越靠近敌方老将越高
    // 红卒的附加值矩阵
    private int[][] RED_PAWN_ADDITION = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {90, 90, 110, 120, 120, 120, 110, 90, 90},
            {90, 90, 110, 120, 120, 120, 110, 90, 90},
            {70, 90, 110, 110, 110, 110, 110, 90, 70},
            {70, 70, 70, 70, 70, 70, 70, 70, 70},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    // 黑兵的附加值矩阵
    private int[][] BLACK_PAWN_ADDITION = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {70, 70, 70, 70, 70, 70, 70, 70, 70},
            {70, 90, 110, 110, 110, 110, 110, 90, 70},
            {90, 90, 110, 120, 120, 120, 110, 90, 90},
            {90, 90, 110, 120, 120, 120, 110, 90, 90},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    /**
     * 获取指定位置的兵的附加值
     *
     * @param boardPosition 当前所面对的棋局局势
     * @param x             棋盘上的X坐标
     * @param y             棋盘上的Y坐标
     * @return 兵的附加值；如果指定位置上不是兵，返回0；
     */
    private int getPawnAddition(PointState[][] boardPosition, int x, int y) {
        PointState state = boardPosition[x][y];
        if (state == PointState.RED_PAWN) {
            return RED_PAWN_ADDITION[x][y];
        } else if (state == PointState.BLACK_PAWN) {
            return BLACK_PAWN_ADDITION[x][y];
        } else {
            return 0;
        }
    }

    @Override
    public int evaluate(PointState[][] boardPosition, CampType campType) {
        int[][] chessValues = new int[ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH];
        // 棋盘上各个点的受威胁度
        int[][] posThreat = new int[ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH];
        // 棋盘上各个点的受保护度
        int[][] posGuard = new int[ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH];
        // 棋盘上各个点的灵活性
        int[][] posFlexibility = new int[ChessBoard.CHESS_BOARD_HEIGHT][ChessBoard.CHESS_BOARD_LENGTH];
        int endGameScore = walkChessBoard(boardPosition, campType, posThreat, posGuard, posFlexibility);
        if (endGameScore > 0) {
            // 如果终局了，直接返回终局分数
            return endGameScore;
        }

        walkChessValue(boardPosition, chessValues, posFlexibility);

        endGameScore = walkChessData(boardPosition, campType, chessValues, posThreat, posGuard);
        if (endGameScore > 0) {
            // 如果终局了，直接返回终局分数
            return endGameScore;
        }
        return calBoardScore(boardPosition, campType, chessValues);
    }

    /**
     * 遍历棋盘，完善每个棋点的数据
     *
     * @param boardPosition  当前的棋局局势
     * @param campType       接下来要走的阵营
     * @param posThreat      每个棋点的受威胁度
     * @param posGuard       每个棋点的受保护度
     * @param posFlexibility 每个棋点的灵活度
     * @return 如果碰到终局的情况，就直接返回一个终局评分
     */
    private int walkChessBoard(PointState[][] boardPosition, CampType campType, int[][] posThreat, int[][] posGuard, int[][] posFlexibility) {
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState pointState = boardPosition[i][j];
                if (pointState == PointState.NO_CHESS) {
                    continue;
                }
                AbstractChessMoveGenerator chessMoveGenerator = chessManager.getChessMoveGenerator(pointState);
                for (Position position : chessMoveGenerator.generateMove(boardPosition, i, j)) {
                    PointState targetState = boardPosition[position.getX()][position.getY()];
                    if (targetState == PointState.NO_CHESS) {
                        // 如果目标点事空白的，灵活性增加
                        posFlexibility[i][j]++;
                        continue;
                    }

                    if (pointState.isSameCamp(targetState)) {
                        // 如果目标点是己方棋子，目标受保护程度增加
                        posGuard[position.getX()][position.getY()]++;
                    } else {
                        // 如果目标点是敌方棋子，目标受威胁程度增加，己方棋子灵活性增加
                        posThreat[position.getX()][position.getY()]++;
                        posFlexibility[i][j]++;
                    }

                    if (campType.getEnemyKing() == targetState) {
                        // 如果能走到敌方老将的位置上，获胜
                        return ChessBoardUtils.MAX_EVALUATE_VALUE;
                    } else {
                        // 如果目标点不是老将，根据威胁的棋子加上威胁分值
                        posThreat[position.getX()][position.getY()] += (30 + (chessBaseValues.get(targetState) - chessBaseValues.get(pointState)) / 10) / 10;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 遍历完善所有棋子价值
     *
     * @param boardPosition  所面对的棋局局势
     * @param chessValues    每个棋点的棋子价值
     * @param posFlexibility 每个棋点的棋子灵活度
     */
    private void walkChessValue(PointState[][] boardPosition, int[][] chessValues, int[][] posFlexibility) {
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState chessState = boardPosition[i][j];
                if (chessState == PointState.NO_CHESS) {
                    continue;
                }
                // 如果棋子存在，则其价值不为0
                chessValues[i][j]++;
                chessValues[i][j] += chessFlexValues.get(chessState) * posFlexibility[i][j];
                // 加上兵的位置附加值
                chessValues[i][j] += getPawnAddition(boardPosition, i, j);
            }
        }
    }

    /**
     * 继续统计扫描到的数据
     *
     * @param boardPosition 当前的棋局局势
     * @param campType      接下来要走的阵营
     * @param chessValues   每个棋点的棋子价值
     * @param posThreat     每个棋点的受威胁度
     * @param posGuard      每个棋点的受保护度
     * @return 如果碰到终局的情况，就直接返回一个终局评分
     */
    private int walkChessData(PointState[][] boardPosition, CampType campType, int[][] chessValues, int[][] posThreat, int[][] posGuard) {
        int nHalfValue;
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState chessState = boardPosition[i][j];
                if (chessState == PointState.NO_CHESS) {
                    continue;
                }
                // 棋子基本价值的1/16作为威胁/保护增量
                nHalfValue = chessBaseValues.get(chessState) / 16;
                // 把每个棋子的基本价值计入其总价值
                chessValues[i][j] += chessBaseValues.get(chessState);
                if (posThreat[i][j] <= 0) {
                    // 当前棋点没有受到威胁
                    if (posGuard[i][j] > 0) {
                        // 当前棋点受到保护，加一点分
                        chessValues[i][j] += 5;
                    }
                } else {
                    // 当前棋点受到威胁
                    if (chessState.getCampType() == campType) {
                        // 接下来是自己走棋
                        if (chessState == campType.getSelfKing()) {
                            // 如果是己方老将，老将价值减低20
                            chessValues[i][j] -= 20;
                        } else {
                            // 除老将之外的其余子，价值减去2倍的nHalfValue
                            chessValues[i][j] -= nHalfValue * 2;
                            if (posGuard[i][j] > 0) {
                                // 被己方棋子保护，再加上nHalfValue
                                chessValues[i][j] += nHalfValue;
                            }
                        }
                    } else {
                        // 接下来轮到敌方走棋
                        if (chessState == campType.getSelfKing()) {
                            // 敌方可以直接杀掉自己的老将
                            return -ChessBoardUtils.MAX_EVALUATE_VALUE;
                        } else {
                            // 除老将之外的棋子，减去10倍的nHalfValue，表示威胁程度高
                            chessValues[i][j] -= nHalfValue * 10;
                            if (posGuard[i][j] > 0) {
                                // 如果被保护，再加上9倍的nHalfValue
                                chessValues[i][j] += nHalfValue * 9;
                            }
                        }
                    }
                    // 被威胁的棋子加上威胁差，防止一个兵威胁一个被保护的车，而估值函数没有反映之类的问题
                    chessValues[i][j] -= posThreat[i][j];
                }
            }
        }
        return 0;
    }

    /**
     * 统计棋局的总分数
     *
     * @param boardPosition 当前的棋局局势
     * @param campType      接下来要走的阵营
     * @param chessValues   每个棋点的棋子价值
     * @return 棋局的最终估值分数
     */
    private int calBoardScore(PointState[][] boardPosition, CampType campType, int[][] chessValues) {
        int redScore = 0;
        int blackScore = 0;
        for (int i = 0; i < ChessBoard.CHESS_BOARD_HEIGHT; i++) {
            for (int j = 0; j < ChessBoard.CHESS_BOARD_LENGTH; j++) {
                PointState chessState = boardPosition[i][j];
                if (chessState == PointState.NO_CHESS) {
                    continue;
                }
                if (chessState.isRed()) {
                    redScore += chessValues[i][j];
                } else {
                    blackScore += chessValues[i][j];
                }
            }
        }
        if (campType == CampType.RED) {
            return redScore - blackScore;
        } else {
            return blackScore - redScore;
        }
    }
}
