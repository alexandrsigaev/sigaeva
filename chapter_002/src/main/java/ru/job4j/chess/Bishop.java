package ru.job4j.chess;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 28.03.2018
 */
public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell sours, Cell dest) throws ImpossibleMoveException {
        int countMoveX = Math.abs(dest.getBoardX() - sours.getBoardX());
        int countMoveY = Math.abs(dest.getBoardY() - sours.getBoardY());
        if (dest.equals(sours) || (dest.getBoardX() > 8 || dest.getBoardY() > 8) || !(countMoveX == countMoveY)) {
            throw new ImpossibleMoveException("move is impossible");
        }
        int deltaX = dest.getBoardX() - sours.getBoardX() > 0 ? 1 : -1;
        int deltaY = dest.getBoardY() - sours.getBoardY() > 0 ? 1 : -1;
        Cell[] result = new Cell[countMoveX];
        int tmpX = sours.getBoardX();
        int tmpY = sours.getBoardY();
        for (int count = 0; count < countMoveX; count++) {
            tmpX = tmpX + deltaX;
            tmpY = tmpY + deltaY;
            result[count] = new Cell(tmpX, tmpY);
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }
}
