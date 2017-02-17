package PongForOne;

import javafx.scene.image.Image;

interface PongResources {
    Image PLAYER_IMAGE = new Image("file:images/player.png");
    Image BALL_IMAGE = new Image("file:images/ball.png");
    String GAME_OVER_TEXT = "You lose.\nHit ENTER to play again.\nHit ESCAPE to Quit.";
}
