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
        ChessMoveManager.getInstance().createPossibleMoves(boardPosition, curDepth, maxDepth);
        // 遍历所有合理走法
        List<ChessMove> allPossibleMoves = ChessMoveManager.getInstance().getPossibleMoves(curDepth);

        for (int i = 0; i < allPossibleMoves.size(); i++) {
            score = 0;
            ChessMove move = allPossibleMoves.get(i);
            // 根据走法产生新局面
            transpositionTable.calcMakeMoveHashKey(move);
            PointState oldState = makeMove(boardPosition, move);
            if (i == 0) {
                // 使用全窗口搜索第一个节点
                score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -beta, -alpha);
            } else {
                // 用极小窗口搜索其他节点
                score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -alpha - 1, -alpha);
                if (score > alpha && score < beta) {
                    // 用全窗口重新搜索
                    // 一旦跳入全窗口搜索，就表示前面搜索的该子节点的结果不准确，删掉，子节点的isMaxNode和父节点刚好相反
                    // TODO 这个算法还有可以优化的地方，因为PVS搜索引擎剪枝比较粗暴，导致往置换表中存储的Exact类型的数据有时候是不准确的，
                    //  例如A节点有两个子节点B和C，B子节点发生剪枝，在置换表中存储了一个LowerBound数据并返回，C子节点也发生剪枝，也在置换表中存储了一个LowerBound数据也返回了，
                    //  结果A节点根据两个子节点的LowerBound数据得出了一个Exact数据，存储到置换表中并返回，此时的这个Exact就不一定是准确的值了，
                    //  一旦该节点再次跳入全局搜索，就表明之前存储的Exact数据并不准确，需要重新搜索，我采取的措施是直接移除掉之前的Exact数据，重新计算一遍，
                    //  后面可以想一想，能否直接就不存这个不正确的值，这样可以把置换表搞小一些。
                    transpositionTable.removeHashTable(!isMaxNode);
                    score = -principalVariation(boardPosition, curDepth - 1, maxDepth, -beta, -score);
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

        transpositionTable.enterHashTable(HashItemType.Exact, curDepth, current, isMaxNode);
        return current;
    }
}
