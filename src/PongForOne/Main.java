package PongForOne;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {
    private static KeyCode keyCode = null;

    private static final double W = 600, H = 400;

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Canvas canvas = new Canvas(W, H);
        group.getChildren().add(canvas);

        Game game = new Game(canvas);

        Scene scene = new Scene(group, W, H);
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> keyCode = event.getCode());
        scene.setOnKeyReleased(event -> keyCode = null);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.run(keyCode);
            }
        };
        timer.start();

        stage.setTitle("Pong for One");
        stage.getIcons().add(PongResources.BALL_IMAGE);

        stage.show();
    }

    public static void main(String[] args) {
	    launch(args);
    }
}
