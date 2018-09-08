package pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 07.09.2018
 */
public class RectangleMove implements Runnable {

    private final Rectangle rect;
    private boolean goOut = false;
    private final int sizeX;
    private final int sizeY;

    public RectangleMove(Rectangle rect, int sizeX, int sizeY) {
        this.rect = rect;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void isInterrupt(boolean isExit) {
        this.goOut = isExit;
    }

    @Override
    public void run() {
        int x = (int) rect.getX();
        int y = (int) rect.getY();
        int delX = 1;
        int delY = -1;
        while (!goOut) {
            if (x > sizeX || x < 0) {
                delX =  - delX;
            }
            this.rect.setX(this.rect.getX() + delX);
            x = x + delX;

            if (y > sizeY || y < 0) {
                delY =  - delY;
            }
            this.rect.setY(this.rect.getY() + delY);
            y = y + delY;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
