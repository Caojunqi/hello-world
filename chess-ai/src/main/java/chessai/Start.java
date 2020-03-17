package chessai;

import chessai.controller.ChessMoveManager;
import chessai.controller.evaluation.jia.JiaBoardEvaluator;
import chessai.controller.searchengine.AbstractSearchEngine;
import chessai.controller.searchengine.impl.TranspositionTablePrincipalVariationSearchEngine;
import chessai.model.ChessBoard;
import chessai.model.ChessMove;
import chessai.model.PointState;
import chessai.util.ChessBoardUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

/**
 * 启动类
 */
public class Start {

    /**
     * 默认的上下文配置名
     */
    private static final String DEFAULT_APPLICATION_CONTEXT = "applicationContext.xml";

    public static void main(String[] args) {
        // 引入Spring配置
        new ClassPathXmlApplicationContext(DEFAULT_APPLICATION_CONTEXT);
        // 初始化棋盘
        ChessBoard.getInstance().initChessBoard();
        AbstractSearchEngine searchEngine = new TranspositionTablePrincipalVariationSearchEngine(JiaBoardEvaluator.getInstance());
        // 开始下棋
        System.err.println("开始下棋，玩家先走：");
        Scanner scanner = new Scanner(System.in);
        int step = 0;
        while (true) {
            String string = scanner.nextLine();
            ChessMove playerMove = convertStr2Move(string);
            if (playerMove == null) {
                System.err.println("玩家移动不合法！！");
                continue;
            }
            PointState playerState = ChessBoard.getInstance().getPointState(playerMove.getStartX(), playerMove.getStartY());
            if (playerState.isSameCamp(ChessBoardUtils.AI_CAMP)) {
                System.err.println("玩家走了AI的棋！！");
                continue;
            }
            boolean isValidMove = ChessMoveManager.getInstance().isValidMove(ChessBoard.getInstance().getCurChessBoard(), playerMove.getStartX(), playerMove.getStartY(), playerMove.getTargetX(), playerMove.getTargetY());
            if (!isValidMove) {
                System.err.println("玩家移动不合法！！");
                continue;
            }
            PointState oldAiState = ChessBoard.getInstance().makeMove(playerMove);
            System.err.println("玩家走一步：[" + playerMove.getStartX() + "," + playerMove.getStartY() + "] --> [" + playerMove.getTargetX() + "," + playerMove.getTargetY() + "]");
            if (oldAiState != PointState.NO_CHESS) {
                System.err.println("【" + oldAiState.getName() + "】阵亡！");
            }
            if (oldAiState == ChessBoardUtils.AI_CAMP.getSelfKing()) {
                System.err.println("AI阵营主将阵亡！AI失败！棋局结束！");
                break;
            }
            // ChessBoardUtils.printChessBoard(ChessBoard.getInstance().getCurChessBoard());
            // AI走一步
            ChessMoveManager.getInstance().clearPossibleMoves();
            ChessMove aiMove = searchEngine.searchBestMove();
            PointState oldPlayerState = ChessBoard.getInstance().makeMove(aiMove);
            System.err.println("AI走一步：[" + aiMove.getStartX() + "," + aiMove.getStartY() + "] --> [" + aiMove.getTargetX() + "," + aiMove.getTargetY() + "]");
            if (oldPlayerState != PointState.NO_CHESS) {
                System.err.println("【" + oldPlayerState.getName() + "】阵亡！");
            }
            if (oldPlayerState == ChessBoardUtils.AI_CAMP.getEnemyKing()) {
                System.err.println("玩家阵营主将阵亡！玩家失败！棋局结束！");
                break;
            }
            //  ChessBoardUtils.printChessBoard(ChessBoard.getInstance().getCurChessBoard());
        }
    }

    /**
     * 把指定的字符串输入转成一步象棋走法
     *
     * @param str 象棋走法的字符串表示，由4个数字加上空格组成，4个数字分别表示起始点坐标和目标点坐标。
     *            例如，输入“1 2 3 4”，就表示把坐标点(1,2)上的棋子移动到(3,4)
     * @return 转换后的象棋走法
     */
    private static ChessMove convertStr2Move(String str) {
        char[] charArr = str.toCharArray();
        if (charArr.length != 4) {
            return null;
        }
        int[] intArr = new int[4];
        for (int i = 0; i < 4; i++) {
            intArr[i] = Integer.parseInt(new String("" + charArr[i]));
        }
        return ChessMove.valueOf(intArr[0], intArr[1], intArr[2], intArr[3]);
    }

}
