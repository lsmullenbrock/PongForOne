package PongForOne;

import javafx.scene.canvas.Canvas;

import static PongForOne.PongResources.BALL_IMAGE;

public class Ball {
    private final Canvas canvas;
    private final double xBound, yBound;
    private final double xSize, ySize;
    private double velocity;
    private final Player player1;

    private double xVelocity, yVelocity;
    private double xCoord, yCoord;

    public Ball(Canvas canvas, Player player1) {
        this.canvas = canvas;
        this.player1 = player1;
        xBound = canvas.getWidth();
        yBound = canvas.getHeight();
        xSize = BALL_IMAGE.getWidth();
        ySize = BALL_IMAGE.getHeight();
        velocity = 2;
        xVelocity = velocity;
        yVelocity = velocity;
        xCoord = (xBound / 2) * Math.random();
        yCoord = (yBound / 2) * Math.random();
    }

    private void checkCollision() {
        if(xCoord <= 0) {
            player1.setLose(true);
            xVelocity = 0;
            yVelocity = 0;
            return;
        } else if (xCoord + xSize >= xBound){
            xVelocity = -velocity;
            player1.setScore(player1.getScore() + 1);
        }

        if(yCoord <= 0 ) {
            yVelocity = velocity;
        }
        else if(yCoord + ySize >= yBound){
            yVelocity = -velocity;
        }

        if((xCoord >= player1.getxCoord()) && (xCoord <= player1.getxCoord() + player1.getxSize())) {
            if((yCoord <= player1.getyCoord() + player1.getySize()) && (yCoord >= player1.getyCoord())) {
                //increase speed
                velocity++;
                //flip X only
                xVelocity = (xVelocity > 0) ? -velocity : velocity;
            }
        }
    }


    private void move() {
        xCoord += xVelocity;
        yCoord += yVelocity;
    }

    private void render() {
        canvas.getGraphicsContext2D().drawImage(
                BALL_IMAGE,
                xCoord,
                yCoord
        );
    }

    public boolean update() {
        move();
        checkCollision();
        render();
        return player1.isLose();
    }
}
