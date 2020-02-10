package chessai;

import chessai.common.SystemOut;
import chessai.util.RandomUtils;

/**
 * 启动类
 */
public class Start {

    public static void main(String[] args) {
        for (int i = 0; i < 64; i++) {
            SystemOut.println(RandomUtils.betweenInt(0, 100, false));
        }
    }
}
