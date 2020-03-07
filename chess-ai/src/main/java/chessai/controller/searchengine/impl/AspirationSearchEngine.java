package chessai.controller.searchengine.impl;

import chessai.controller.evaluation.IBoardEvaluator;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;

/**
 * “期望搜索”引擎
 */
public class AspirationSearchEngine extends FailSoftAlphaBetaSearchEngine {

    /**
     * 期望搜索的窗口总宽度
     */
    private static final int WINDOW = 100;

    public AspirationSearchEngine(IBoardEvaluator boardEvaluator) {
        super(boardEvaluator);
    }

    @Override
    public ChessMove searchBestMove() {
        PointState[][] boardPosition = ChessBoard.getInstance().getCurChessBoard();
        bestMove = null;
        // 先进行浅层搜索，猜测目标值范围
        int shallowDepth = ChessBoardUtils.SEARCH_DEPTH - 1;
        int shallowResult = failSoftAlphaBeta(boardPosition, shallowDepth, shallowDepth, -ChessBoardUtils.MAX_SEARCH_VALUE, ChessBoardUtils.MAX_SEARCH_VALUE);
        // 对目标值附近的小窗口进行搜索
        bestMove = null;
        int windowAlpha = shallowResult - WINDOW / 2;
        int windowBeta = shallowResult + WINDOW / 2;
        int windowResult = failSoftAlphaBeta(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, windowAlpha, windowBeta);
        if (windowResult <= windowAlpha) {
            // fail low 再次搜索
            failSoftAlphaBeta(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, -ChessBoardUtils.MAX_SEARCH_VALUE, windowResult);
        }

        if (windowResult >= windowBeta) {
            // fail high 再次搜索
            failSoftAlphaBeta(boardPosition, ChessBoardUtils.SEARCH_DEPTH, ChessBoardUtils.SEARCH_DEPTH, windowResult, ChessBoardUtils.MAX_SEARCH_VALUE);
        }

        return bestMove;
    }
}
