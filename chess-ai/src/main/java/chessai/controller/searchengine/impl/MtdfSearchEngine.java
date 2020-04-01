package chessai.controller.searchengine.impl;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.model.transpositiontable.HashItemType;
import chessai.model.transpositiontable.TranspositionTable;
import chessai.util.ChessBoardUtils;

/**
 * MTD(f)搜索引擎 Memory-enhanced Test Driver with node n and value f
 */
public class MtdfSearchEngine extends AbstractSearchEngine {

    public MtdfSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        clearPossibleMoves();
        bestMove = null;
        int firstGuess = -ChessBoardUtils.MAX_SEARCH_VALUE;
        for (int searchDepth = 1; searchDepth <= ChessBoardUtils.SEARCH_DEPTH; searchDepth++) {
            firstGuess = mtdf(firstGuess, searchDepth);
        }
        return bestMove;
    }

    /**
     * MTD(f)搜索函数
     *
     * @param firstGuess 起始值
     * @param depth      搜索深度
     * @return 为下一次搜索计算的起始值
     */
    private int mtdf(int firstGuess, int depth) {
        int beta = 0;
        int g = firstGuess;
        int lowerBound = -ChessBoardUtils.MAX_SEARCH_VALUE;
        int upperBound = ChessBoardUtils.MAX_SEARCH_VALUE;
        while (lowerBound < upperBound) {
            if (g == lowerBound) {
                beta = g + 1;
            } else {
                beta = g;
            }
            // 空窗探测
            g = alphaBeta(depth, depth, beta - 1, beta);
            if (g < beta) {
                // fail low
                upperBound = g;
            } else {
                // fail high
                lowerBound = g;
            }
        }
        return g;
    }

    /**
     * 带有置换表的fail-soft alpha-beta
     */
    private int alphaBeta(int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        TranspositionTable transpositionTable = ChessBoard.getInstance().getTranspositionTable();
        boolean isMaxNode = (maxDepth + curDepth) % 2 == 0;
        // Integer score = transpositionTable.lookUpHashTable(alpha, beta, curDepth, isMaxNode);
//        if (score != null) {
//            // 置换表命中
//            return score;
//        }
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
//            transpositionTable.enterHashTable(HashItemType.Exact, curDepth, score, isMaxNode);
//            return score;
        }

        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        HashItemType itemType = HashItemType.UpperBound;
        for (ChessMove move : getPossibleMoves(curDepth)) {
            // 根据走法产生新局面，注，这里必须是先计算HASH值，再执行走法
            // transpositionTable.calcMakeMoveHashKey(move);
            PointState oldTargetState = makeMove(boardPosition, move);
            // 递归调用Alpha-Beta剪枝搜索下一层的节点
            int score = -alphaBeta(curDepth - 1, maxDepth, -beta, -alpha);
            // 恢复当前局面，注，这里必须是先计算HASH值，再撤销走法
            //   transpositionTable.calcUnMakeMoveHashKey(move, oldTargetState);
            unMakeMove(boardPosition, move, oldTargetState);

            if (score > current) {
                current = score;
                if (score > alpha) {
                    alpha = score;
                    if (curDepth == maxDepth) {
                        bestMove = move;
                    }
                }
                if (score >= beta) {
                    // 将节点下边界存入置换表
                    // 此时这个节点的具体值并不确定，只能确定它的下边界值
                    // transpositionTable.enterHashTable(HashItemType.LowerBound, curDepth, score, isMaxNode);
                    return score;
                }
            }
        }
        //  transpositionTable.enterHashTable(HashItemType.Exact, curDepth, current, isMaxNode);
        return current;
    }
}
