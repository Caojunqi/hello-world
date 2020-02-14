package chessai.controller.evaluation.jia.chessevaluator;

import chessai.controller.evaluation.jia.JiaBoardEvaluator;
import chessai.model.PointState;
import chessai.model.Position;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 棋子评估器抽象类
 */
public abstract class AbstractChessEvaluator {

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

    /**
     * 搜索指定位置上棋子的所有相关位置，包括可走到的位置和可保护的位置
     *
     * @param boardPosition 当前棋局局势
     * @param x             棋子的X坐标
     * @param y             棋子的Y坐标
     * @return 当前棋盘上跟指定位置的棋子有关的所有位置点
     */
    public abstract List<Position> getRelatePieces(PointState[][] boardPosition, int x, int y);
}
