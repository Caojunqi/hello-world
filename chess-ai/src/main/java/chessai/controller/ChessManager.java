package chessai.controller;

import chessai.model.PointState;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 中国象棋管理类
 */
@Component
public class ChessManager {

    private static ChessManager self;

    /**
     * 棋子移动检测器集合
     */
    private Map<PointState, AbstractChessMoveChecker> chessMoveCheckers = new HashMap<>(PointState.values().length);

    public static ChessManager getInstance() {
        return self;
    }

    @PostConstruct
    public void init() {
        self = this;
    }

    public void registerChessMoveChecker(AbstractChessMoveChecker moveChecker) {
        if (chessMoveCheckers.containsKey(moveChecker.getPointState())) {
            throw new RuntimeException("ChessMoveChecker状态重复！！重复状态：" + moveChecker.getPointState());
        }
        chessMoveCheckers.put(moveChecker.getPointState(), moveChecker);
    }

    /**
     * 获取指定棋点状态对应的棋子移动检测器
     *
     * @param pointState 棋点状态
     * @return 棋子移动检测器
     */
    public AbstractChessMoveChecker getChessMoveChecker(PointState pointState) {
        return chessMoveCheckers.get(pointState);
    }
}
