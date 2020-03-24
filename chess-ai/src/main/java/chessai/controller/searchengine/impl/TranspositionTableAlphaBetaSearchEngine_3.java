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
 * 结合置换表和Alpha-Beta fail-soft算法的搜索引擎
 * 区别：不设定UpperBound上边界值
 */
public class TranspositionTableAlphaBetaSearchEngine_3 extends AbstractSearchEngine {
    public TranspositionTableAlphaBetaSearchEngine_3(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        clearPossibleMoves();
        bestMove = null;
        transpositionTableAlphaBeta(ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
        return bestMove;
    }

    private int transpositionTableAlphaBeta(int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        TranspositionTable transpositionTable = ChessBoard.getInstance().getTranspositionTable();
        boolean isMaxNode = (maxDepth + curDepth) % 2 == 0;
        Integer score = transpositionTable.lookUpHashTable(alpha, beta, curDepth, isMaxNode);
        if (score != null) {
            // 置换表命中
            return score;
        }
        if (isGameOver(boardPosition) || curDepth <= 0) {
            score = evaluate(boardPosition, curDepth, maxDepth);
            transpositionTable.enterHashTable(HashItemType.Exact, curDepth, score, isMaxNode);
            return score;
        }

        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        HashItemType itemType = HashItemType.UpperBound;
        for (ChessMove move : getPossibleMoves(curDepth)) {
            // 根据走法产生新局面，注，这里必须是先计算HASH值，再执行走法
            transpositionTable.calcMakeMoveHashKey(move);
            PointState oldTargetState = makeMove(boardPosition, move);
            // 递归调用Alpha-Beta剪枝搜索下一层的节点
            score = -transpositionTableAlphaBeta(curDepth - 1, maxDepth, -beta, -alpha);
            // 恢复当前局面，注，这里必须是先计算HASH值，再撤销走法
            transpositionTable.calcUnMakeMoveHashKey(move, oldTargetState);
            unMakeMove(boardPosition, move, oldTargetState);

            if (score > current) {
                current = score;
                if (score >= beta) {
                    // 将节点下边界存入置换表
                    // 此时这个节点的具体值并不确定，只能确定它的下边界值
                    transpositionTable.enterHashTable(HashItemType.LowerBound, curDepth, score, isMaxNode);
                    return score;
                }
                if (score > alpha) {
                    alpha = score;
                    // 将节点的确切值存入置换表
                    itemType = HashItemType.Exact;
                    if (curDepth == maxDepth) {
                        bestMove = move;
                    }
                }
            }
        }
        transpositionTable.enterHashTable(HashItemType.Exact, curDepth, current, isMaxNode);
        return current;
    }
}
