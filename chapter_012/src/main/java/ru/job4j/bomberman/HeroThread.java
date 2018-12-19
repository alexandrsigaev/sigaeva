package ru.job4j.bomberman;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 16.12.2018
 */
public class HeroThread implements Runnable {

    private static final int TIME_TO_MOVIE = 1000;
    private static final int COUNT_OF_MOVIE = 10;
    private Cell startCell;
    private final Board board;
    private Person person;
    private MovePerson movePerson;

    public HeroThread(Cell startCell, Board board, MovePerson movePerson) {
        this.board = board;
        this.movePerson = movePerson;
        this.startCell = startCell;
    }

    @Override
    public void run() {
        try {
            int count = COUNT_OF_MOVIE;
            boolean isInit = board.init(this.startCell);
            if (isInit) {
                this.person = new Person(this.startCell);
                System.out.println(String.format("Hero thread %s: start from %s",
                        Thread.currentThread().getName(),
                        this.startCell.toString()));
            }
            while (count > 0) {
                Cell personCell = this.person.getPosition();
                Cell nextCell = new Cell(personCell.getBoardX() + this.movePerson.getDeltX(),
                        personCell.getBoardY() + this.movePerson.getDeltY());
                boolean isMovie = board.move(personCell, nextCell);
                if (isMovie) {
                    System.out.println(String.format("Hero thread %s: movie from %s to %s",
                            Thread.currentThread().getName(),
                            personCell.toString(),
                            nextCell.toString()));
                    this.person.setPosition(nextCell);
                } else {
                    this.movePerson = NextMovePerson.nextMovie(movePerson);
                }
                count--;
                Thread.sleep(TIME_TO_MOVIE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
