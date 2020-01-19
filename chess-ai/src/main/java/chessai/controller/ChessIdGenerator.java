package chessai.controller;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;


/**
 * 棋子ID生成器
 */
@Component
public class ChessIdGenerator {
    
    private static ChessIdGenerator self;

    private int id;

    @PostConstruct
    public void init(){
        self = this;
    }

    public int take(){
        return id++;
    }

    public static ChessIdGenerator getInstance(){
        return self;
    }
}
