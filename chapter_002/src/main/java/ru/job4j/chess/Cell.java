package ru.job4j.chess;

import java.util.Objects;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 28.03.2018
 */
public class Cell {
    private int boardX;
    private int boardY;


    public Cell(int boardX, int boardY) {
        this.boardX = boardX;
        this.boardY = boardY;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell cell = (Cell) o;
        return getBoardX() == cell.getBoardX() && getBoardY() == cell.getBoardY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoardX(), getBoardY());
    }
}
