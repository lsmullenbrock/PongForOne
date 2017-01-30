package PongForOne;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;

import static PongForOne.PongResources.PLAYER_IMAGE;

public class Player {
    private final Canvas canvas;
    private final double yBound;
    private double xCoord, yCoord;
    private double xSize, ySize;

    private int score;
    private boolean lose;

    private final double moveByAmt;

    public Player(Canvas canvas) {
        this.canvas = canvas;
        xCoord = 10;
        yCoord = (canvas.getHeight() / 2) - PLAYER_IMAGE.getHeight();
        yBound = canvas.getHeight();
        xSize = PLAYER_IMAGE.getWidth();
        ySize = PLAYER_IMAGE.getHeight();

        score = 0;
        lose = false;

        moveByAmt = 5;
    }

    private void moveUp() {
        yCoord -= moveByAmt;
    }

    private void moveDown() {
        yCoord += moveByAmt;
    }

    private void checkCollision() {
        if(yCoord <= 0) {
            moveDown();
        } else if(yCoord + ySize >= yBound) {
            moveUp();
        }
    }

    private void move(KeyCode keyCode) {
        if(keyCode == null) {
            return;
        }
        if(lose) {
            return;
        }
        switch (keyCode) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                break;
        }
    }

    private void render() {
        canvas.getGraphicsContext2D().drawImage(
                PLAYER_IMAGE,
                xCoord,
                yCoord
        );

        canvas.getGraphicsContext2D().fillText(
                "Score: " + score,
                Math.sqrt(canvas.getWidth()),
                Math.sqrt(canvas.getHeight())
        );
    }

    public void update(KeyCode keyCode) {
        render();
        move(keyCode);
        checkCollision();
    }

    /*
     * SET/GET
     */

    public double getxCoord() {
        return xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public double getxSize() {
        return xSize;
    }

    public double getySize() {
        return ySize;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }
}
