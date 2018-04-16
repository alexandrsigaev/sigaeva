package ru.job4j.chess;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 28.03.2018
 */
public class Board {
    private Figure[] figures = new Figure[32];
    private int position = 0;

    public void add(Figure figure) {
        figures[position++] = figure;
    }

    public boolean move(Cell source, Cell dest)
            throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        int index = findPosition(source);
        if (index == -1) {
            throw new FigureNotFoundException("cell is empty");
        }
        for (Cell cell : figures[index].way(source, dest)) {
            if (!isEmpty(cell)) {
                throw new OccupiedWayException("the way is occupied");
            }
        }
        result = true;
        return result;
    }

    private int findPosition(Cell cell) {
        int index = -1;
        for (int i = 0; i < position; i++) {
            if (cell.equals(figures[i].position)) {
                index = i;
            }
        }
        return index;
    }

    private boolean isEmpty(Cell cell) {
        return findPosition(cell) == -1;
    }
}
