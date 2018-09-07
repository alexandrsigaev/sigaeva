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

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int x = 0;
        int y = 100;
        int delX = 5;
        int delY = -5;
        while (true) {
            if (x > 300 || x < 0) {
                delX =  - delX;
            }
            this.rect.setX(this.rect.getX() + delX);
            x = x + delX;

            if (y > 300 || y < 0) {
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
