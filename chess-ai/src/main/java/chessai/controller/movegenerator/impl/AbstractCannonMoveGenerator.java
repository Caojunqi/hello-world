package chessai.controller.movegenerator.impl;

import chessai.controller.movegenerator.AbstractChessMoveGenerator;
import chessai.model.PointState;
import chessai.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋子走法生成器--双方炮移动
 */
public abstract class AbstractCannonMoveGenerator extends AbstractChessMoveGenerator {

    @Override
    public List<Position> generateValidMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        CheckData checkData = new CheckData();
        int targetX, targetY;
        // 插入向右方向的可走位置
        targetX = startX;
        targetY = startY + 1;
        while (targetY < 9) {
            checkCannonValidMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
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
            checkCannonValidMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
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
            checkCannonValidMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
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
            checkCannonValidMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
            if (checkData.isContinue) {
                targetX--;
            } else {
                break;
            }
        }

        return positions;
    }

    @Override
    public List<Position> generateRelateMove(PointState[][] boardPosition, int startX, int startY) {
        List<Position> positions = new ArrayList<>();
        CheckData checkData = new CheckData();
        int targetX, targetY;
        // 插入向右方向的可走位置
        targetX = startX;
        targetY = startY + 1;
        while (targetY < 9) {
            checkCannonRelateMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
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
            checkCannonRelateMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
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
            checkCannonRelateMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
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
            checkCannonRelateMove(boardPosition, startX, startY, targetX, targetY, checkData, positions);
            if (checkData.isContinue) {
                targetX--;
            } else {
                break;
            }
        }

        return positions;
    }

    /**
     * 检测炮移动是否合法
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        移动起始点X坐标
     * @param startY        移动起始点Y坐标
     * @param targetX       移动目标点X坐标
     * @param targetY       移动目标点Y坐标
     * @param checkData     检测所需数据
     * @param result        走法集合
     */
    private void checkCannonValidMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY, CheckData checkData, List<Position> result) {
        PointState startState = boardPosition[startX][startY];
        PointState targetState = boardPosition[targetX][targetY];
        if (targetState == PointState.NO_CHESS) {
            if (!checkData.hasChess) {
                // 目标点没有棋子，且起始点和目标点之间没有棋子隔着，为可走位置
                result.add(Position.valueOf(targetX, targetY));
            }
        } else {
            if (!checkData.hasChess) {
                // 目标点有棋子，且起始点和目标点之间没有棋子隔着，此棋子是第一个阻碍，设置标识
                checkData.hasChess = true;
            } else {
                if (!targetState.isSameCamp(startState)) {
                    // 目标点为敌方棋子，且起始点和目标点之间隔着一个棋子，为可走位置
                    result.add(Position.valueOf(targetX, targetY));
                    checkData.isContinue = false;
                } else {
                    // 目标点是同阵营的棋子，后续不用再判断了
                    checkData.isContinue = false;
                }
            }
        }
    }

    /**
     * 检测并添加所有相关联的炮走法，包括所有合理走法以及可以提供保护的走法
     *
     * @param boardPosition 所面对的棋局局势
     * @param startX        移动起始点X坐标
     * @param startY        移动起始点Y坐标
     * @param targetX       移动目标点X坐标
     * @param targetY       移动目标点Y坐标
     * @param checkData     检测所需数据
     * @param result        走法集合
     */
    private void checkCannonRelateMove(PointState[][] boardPosition, int startX, int startY, int targetX, int targetY, CheckData checkData, List<Position> result) {
        PointState startState = boardPosition[startX][startY];
        PointState targetState = boardPosition[targetX][targetY];
        if (targetState == PointState.NO_CHESS) {
            if (!checkData.hasChess) {
                // 目标点没有棋子，且起始点和目标点之间没有棋子隔着，为可走位置
                result.add(Position.valueOf(targetX, targetY));
            }
        } else {
            if (!checkData.hasChess) {
                // 目标点有棋子，且起始点和目标点之间没有棋子隔着，此棋子是第一个阻碍，设置标识
                checkData.hasChess = true;
            } else {
                // 是第二个棋子，若为己方棋子，可提供保护，若为敌方棋子，可杀
                result.add(Position.valueOf(targetX, targetY));
                checkData.isContinue = false;
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
