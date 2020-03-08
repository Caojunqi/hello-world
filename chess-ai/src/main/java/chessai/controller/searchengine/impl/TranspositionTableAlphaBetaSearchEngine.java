package chessai.controller.searchengine.impl;

import chessai.controller.ChessMoveManager;
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
 */
public class TranspositionTableAlphaBetaSearchEngine extends AbstractSearchEngine {

    public TranspositionTableAlphaBetaSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
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
        ChessMoveManager.getInstance().createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        HashItemType itemType = HashItemType.UpperBound;
        for (ChessMove move : ChessMoveManager.getInstance().getPossibleMoves(curDepth)) {
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
                    transpositionTable.enterHashTable(HashItemType.Exact, curDepth, score, isMaxNode);
                    if (curDepth == maxDepth) {
                        bestMove = move;
                    }
                }
            }
        }
        // 将节点的上边界存入置换表，已经遍历了所有子节点的值，这些值既没有突破beat，也没有修改alpha，可见他们的最大值都不会超过alpha
        if (itemType == HashItemType.UpperBound) {
            transpositionTable.enterHashTable(HashItemType.UpperBound, curDepth, alpha, isMaxNode);
        }
        return current;
    }
}
