package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Class
 *
 * @author sigaevaleksandr
 * @since 07.09.2018
 */
public class PingPong extends Application {

    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(0, 100, 10, 10);
        group.getChildren().add(rect);
        RectangleMove rectangleMove = new RectangleMove(rect, limitX, limitY);
        Thread newThread = new Thread(rectangleMove);
        newThread.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> newThread.interrupt());
        stage.show();
    }
}
