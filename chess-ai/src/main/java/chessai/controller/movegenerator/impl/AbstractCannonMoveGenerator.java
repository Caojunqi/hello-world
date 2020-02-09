package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;

/**
 * 棋子走法生成器--双方炮移动
 */
public abstract class AbstractCannonMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public void generateMove(PointState[][] boardPosition, int startX, int startY, int nPly) {
        PointState startState = boardPosition[startX][startY];
        CheckData checkData = new CheckData();
        int targetX, targetY;
        // 插入向右方向的可走位置
        targetX = startX;
        targetY = startY + 1;
        while (targetY < 9) {
            checkCannonMove(boardPosition, startX, startY, targetX, targetY, nPly, checkData);
            if (checkData.isContinue) {
                targetY++;
            } else {
                break;
            }
        }

        // 插入向左方向的可走位置
        checkData = new CheckData();
        targetX = startX;
        targetY = startY - 1;
        while (targetY >= 0) {
            checkCannonMove(boardPosition, startX, startY, targetX, targetY, nPly, checkData);
            if (checkData.isContinue) {
                targetY--;
            } else {
                break;
            }
        }

        // 插入向上方向的可走位置
        checkData = new CheckData();
        targetY = startY;
        targetX = startX + 1;
        while (targetX < 10) {
            checkCannonMove(boardPosition, startX, startY, targetX, targetY, nPly, checkData);
            if (checkData.isContinue) {
                targetX++;
            } else {
                break;
            }
        }

        // 插入向下方向的可走位置
        checkData = new CheckData();
        targetY = startY;
        targetX = startX - 1;
        while (targetX >= 0) {
            checkCannonMove(boardPosition, startX, startY, targetX, targetY, nPly, checkData);
            if (checkData.isContinue) {
                targetX--;
            } else {
                break;
            }
        }
    }

    /**
     * 检测炮移动是否合法
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        移动起始点X坐标
     * @param startY        移动起始点Y坐标
     * @param targetX       移动目标点X坐标
     * @param targetY       移动目标点Y坐标
     * @param nPly          搜索层数
     * @param checkData     检测所需数据
     */
    private void checkCannonMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY, int nPly, CheckData checkData) {
        PointState startState = boardPosition[startX][startY];
        PointState targetState = boardPosition[targetX][targetY];
        if (targetState == PointState.NO_CHESS) {
            if (!checkData.hasChess) {
                // 目标点没有棋子，且起始点和目标点之间没有棋子隔着，为可走位置
                chessMoveManager.addMove(startX, startY, targetX, targetY, nPly);
            }
        } else {
            if (!checkData.hasChess) {
                // 目标点有棋子，且起始点和目标点之间没有棋子隔着，此棋子是第一个阻碍，设置标识
                checkData.hasChess = true;
            } else {
                if (!targetState.isSameCamp(startState)) {
                    // 目标点为敌方棋子，且起始点和目标点之间隔着一个棋子，为可走位置
                    chessMoveManager.addMove(startX, startY, targetX, targetY, nPly);
                    checkData.isContinue = false;
                }
            }
        }
    }

    /**
     * 执行检查所需读取和修改的数据
     */
    private static class CheckData {
        /**
         * 标识位，判断起始点和目标点之间是否有棋子隔着
         */
        private boolean hasChess;
        /**
         * 是否可以继续当前循环，true-继续循环；false-终止循环。
         */
        private boolean isContinue = true;

    }
}
