package chessai.controller;

import chessai.controller.movechecker.AbstractChessMoveChecker;
import chessai.controller.movegenerator.AbstractChessMoveGenerator;
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

    /**
     * 棋子走法生成器集合
     */
    private Map<PointState, AbstractChessMoveGenerator> chessMoveGenerators = new HashMap<>(PointState.values().length);

    public static ChessManager getInstance() {
        return self;
    }

    @PostConstruct
    public void init() {
        self = this;
    }

    /**
     * 注册棋子移动检测器
     *
     * @param moveChecker 棋子移动检测器
     */
    public void registerChessMoveChecker(AbstractChessMoveChecker moveChecker) {
        if (chessMoveCheckers.containsKey(moveChecker.getPointState())) {
            throw new RuntimeException("ChessMoveChecker状态重复！！重复状态：" + moveChecker.getPointState());
        }
        chessMoveCheckers.put(moveChecker.getPointState(), moveChecker);
    }

    /**
     * 注册棋子走法生成器
     *
     * @param moveGenerator 棋子走法生成器
     */
    public void registerChessMoveGenerator(AbstractChessMoveGenerator moveGenerator) {
        if (chessMoveGenerators.containsKey(moveGenerator.getPointState())) {
            throw new RuntimeException("ChessMoveGenerator状态重复！！重复状态：" + moveGenerator.getPointState());
        }
        chessMoveGenerators.put(moveGenerator.getPointState(), moveGenerator);
    }

    /**
     * 获取指定棋点状态对应的棋子移动检测器
     *
     * @param pointState 棋点状态
     * @return 棋子移动检测器
     */
    public AbstractChessMoveChecker getChessMoveChecker(PointState pointState) {
        AbstractChessMoveChecker moveChecker = chessMoveCheckers.get(pointState);
        if (moveChecker == null) {
            throw new IllegalStateException("不存在指定棋点状态对应的棋子移动检测器！！棋点状态：" + pointState);
        }
        return moveChecker;
    }

    /**
     * 获取指定棋点状态对应的棋子走法生成器
     *
     * @param pointState 棋点状态
     * @return 棋子走法生成器
     */
    public AbstractChessMoveGenerator getChessMoveGenerator(PointState pointState) {
        AbstractChessMoveGenerator moveGenerator = chessMoveGenerators.get(pointState);
        if (moveGenerator == null) {
            throw new IllegalStateException("不存在指定棋点状态对应的棋子走法生成器！！棋点状态：" + pointState);
        }
        return moveGenerator;
    }
}
