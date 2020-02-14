package chessai.controller.evaluation;

import chessai.model.CampType;
import chessai.model.PointState;

/**
 * 棋局评估器接口
 */
public interface IBoardEvaluator {
    /**
     * 评估指定棋局
     *
     * @param boardPosition 待评估的棋局局势
     * @param campType      接下来要走棋的阵营
     * @return 棋局的估值
     */
    int evaluate(PointState[][] boardPosition, CampType campType);
}
