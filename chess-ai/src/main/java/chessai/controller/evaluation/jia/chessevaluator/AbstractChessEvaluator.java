package chessai.controller.evaluation.jia.chessevaluator;

import chessai.controller.ChessManager;
import chessai.controller.evaluation.jia.JiaBoardEvaluator;
import chessai.model.PointState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * 棋子评估器抽象类
 */
public abstract class AbstractChessEvaluator {

    @Autowired
    private ChessManager chessManager;
    @Autowired
    protected JiaBoardEvaluator jiaBoardEvaluator;

    @PostConstruct
    public void init() {
        jiaBoardEvaluator.registerChessEvaluator(this);
    }

    /**
     * 所要移动的棋点状态
     *
     * @return 棋点状态
     */
    public abstract PointState getPointState();

    /**
     * 获取棋子基本价值
     *
     * @return 棋子的基本价值
     */
    public abstract int getChessBaseValue();

    /**
     * 获取棋子的灵活性价值
     *
     * @return 棋子的灵活性价值
     */
    public abstract int getChessFlexValue();

}
