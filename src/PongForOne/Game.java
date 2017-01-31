package PongForOne;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;

import static java.lang.System.exit;

public class Game {
    private final Canvas canvas;
    private Ball ball;
    private Player player;

    public Game(Canvas canvas) {
        this.canvas = canvas;
        player = new Player(canvas);
        ball = new Ball(canvas, player);
    }

    private void clearCanvas() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void run(KeyCode keyCode) {
        clearCanvas();
        boolean gameOver = ball.update();
        checkStatus(gameOver, keyCode);
        player.update(keyCode);
    }

    private void checkStatus(boolean gameOver, KeyCode keyCode) {
        if(gameOver) {
            canvas.getGraphicsContext2D().fillText(
                    PongResources.GAME_OVER_TEXT,
                    canvas.getWidth() / 3,
                    canvas.getHeight() / 3,
                    300
            );
            if(keyCode == null) {
                return;
            }
            switch (keyCode) {
                case ENTER:
                    player = new Player(canvas);
                    ball = new Ball(canvas, player);
                    break;
                case ESCAPE:
                    exit(0);
            }
        }
    }
}