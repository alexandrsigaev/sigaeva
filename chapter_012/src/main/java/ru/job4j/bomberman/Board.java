package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 16.12.2018
 */
public class Board {

    private final Lock[][] board;
    private final int boardWidth;
    private final int boardHeight;
    private final static int MILLIS_FOR_TRY_LOCK = 500;

    public Board(int width, int height) {
        this.board = new Lock[width][height];
        this.boardWidth = width;
        this.boardHeight = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    public boolean move(Cell source, Cell dist) throws InterruptedException {
        boolean result = false;
        if (dist.getBoardY() >= this.boardHeight || dist.getBoardX() >= this.boardWidth
                || dist.getBoardY() < 0 || dist.getBoardX() < 0) {
            return false;
        }
        result = board[dist.getBoardX()][dist.getBoardY()].tryLock(MILLIS_FOR_TRY_LOCK, TimeUnit.MILLISECONDS);
        if (result) {
            board[source.getBoardX()][source.getBoardY()].unlock();
        }
        return result;
    }

    public boolean init(Cell dist) throws InterruptedException {
        boolean result = false;
        if (dist.getBoardY() > this.boardHeight || dist.getBoardX() > this.boardWidth
                || dist.getBoardY() < 0 || dist.getBoardX() < 0) {
            return false;
        }
        result = board[dist.getBoardX()][dist.getBoardY()].tryLock(MILLIS_FOR_TRY_LOCK, TimeUnit.MILLISECONDS);
        return result;
    }


}

