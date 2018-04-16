package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 14.04.2018
 */
public class BoardTest {
    @Test
    public void whenMoveFigureThenFigureExist()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(4, 4);
        board.add(new Bishop(source));
        assertThat(true, is(board.move(source, dest)));
    }

    @Test
    public void whenMoveFigureThenFigureNotExist()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(4, 5);
        board.add(new Bishop(source));
        try {
            boolean result;
            result = board.move(source, dest);
            assertThat(false, is(result));
        } catch (ImpossibleMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void whenMoveFromAnEmptyCellThenFigureNotExist()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell emptyCell = new Cell(4, 4);
        Cell dest = new Cell(8, 8);
        board.add(new Bishop(source));
        try {
            boolean result;
            result = board.move(emptyCell, dest);
            assertThat(false, is(result));
        } catch (FigureNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void whenMoveFromOccupiedWayThenFigureNotExist()
            throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        Board board = new Board();
        Cell source = new Cell(1, 1);
        Cell dest = new Cell(4, 4);
        Cell busyCell = new Cell(2, 2);
        Figure figure = new Bishop(source);
        board.add(figure);
        board.add(new Bishop(busyCell));
        try {
            boolean result;
            result = board.move(source, dest);
            assertThat(false, is(result));
        } catch (OccupiedWayException e) {
            System.out.println(e.getMessage());
        }
    }

}

