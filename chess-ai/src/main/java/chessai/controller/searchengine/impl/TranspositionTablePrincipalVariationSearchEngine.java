package chessai.controller.searchengine.impl;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.model.transpositiontable.HashItemType;
import chessai.model.transpositiontable.TranspositionTable;
import chessai.util.ChessBoardUtils;

import java.util.List;

/**
 * 结合置换表和极小窗口算法的搜索引擎
 */
public class TranspositionTablePrincipalVariationSearchEngine extends AbstractSearchEngine {

    public TranspositionTablePrincipalVariationSearchEngine(IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    @Override
    public ChessMove searchBestMove() {
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        clearPossibleMoves();
        bestMove = null;
        principalVariation(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
        return bestMove;
    }

    private int principalVariation(PointState[][] boardPosition, int curDepth, int maxDepth, int alpha, int beta) {
        int current = -ChessBoardUtils.MAX_SEARCH_VALUE;
        TranspositionTable transpositionTable = ChessBoard.getInstance().getTranspositionTable();
        boolean isMaxNode = (maxDepth + curDepth) % 2 == 0;
        Integer score = transpositionTable.lookUpHashTable(alpha, beta, curDepth, isMaxNode);
        if (score != null) {
            // 置换表命中
            return score;
        }
        if (isGameOver(boardPosition) || curDepth <= 0) {
            return evaluate(boardPosition, curDepth, maxDepth);
        }

        // 生成合理走法
        createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        List<ChessMove> allPossibleMoves = getPossibleMoves(curDepth);

        for (int i = 0; i < allPossibleMoves.size(); i++) {
            score = 0;
            ChessMove move = allPossibleMoves.get(i);
            // 根据走法产生新局面
            transpositionTable.calcMakeMoveHashKey(move);
            PointState oldState = makeMove(boardPosition, move);
            if (i == 0) {
                // 使用全窗口搜索第一个节点
                score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -beta, -alpha);
                transpositionTable.enterHashTable(HashItemType.Exact, curDepth - 1, score, isMaxNode);
            } else {
                // 用极小窗口搜索其他节点
                score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -alpha - 1, -alpha);
                if (score > alpha && score < beta) {
                    // 用全窗口重新搜索
                    score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -beta, -score);
                    transpositionTable.enterHashTable(HashItemType.Exact, curDepth - 1, score, isMaxNode);
                }
            }
            // 恢复当前局面
            transpositionTable.calcUnMakeMoveHashKey(move, oldState);
            unMakeMove(boardPosition, move, oldState);

            if (score > current) {
                current = score;
                if (score >= beta) {
                    transpositionTable.enterHashTable(HashItemType.LowerBound, curDepth, score, isMaxNode);
                    return score;
                }
                if (score > alpha) {
                    alpha = score;

                    if (curDepth == maxDepth) {
                        bestMove = move;
                    }
                }
            }
        }


        return current;
    }
}
