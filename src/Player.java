import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

public class Player extends Entity {

    // PROPERTIES:
    private BufferedImage[][] animations;
    private int animationTick = 0;
    private int animationIndex = 0;
    private int animationSpeed = 60;

    public Player() {

        super();
    }

    public void init(Game game, float xCoordinate, float yCoordinate, int width, int height, int movementSpeed) {

        super.init(game, xCoordinate, yCoordinate, width, height, movementSpeed);

        // ANIMATIONS:
        // NOTE (SAVIZ): We assume that there are only 2 states: 'IDLE' and 'MOVING', each having only 2 images:
        this.animations = new BufferedImage[2][2];

        for (int stateIndex = 0; stateIndex < this.animations.length; stateIndex++) {

            for (int imageIndex = 0; imageIndex < this.animations[stateIndex].length; imageIndex++) {

                // NOTE (SAVIZ): This will only work under the condition that image and file naming/management is absolutely perfect ("which it should be"):
                String key = "player_" + Entity.State.getStateString(stateIndex) + "_" + imageIndex;

                animations[stateIndex][imageIndex] = ResourceManager.getInstance().getImageUsingKey(key);
            }
        }
    }

    public void update() {

        updateState();
        updatePosition();
        updateAnimation();
    }

    public void render(Graphics graphics) {

        switch(super.direction) {

            case EAST:
                graphics.drawImage(this.animations[super.state.ordinal()][this.animationIndex], (int)(super.xCoordinate), (int)(super.yCoordinate), super.width, super.height, null);
                break;
            case WEST:
                graphics.drawImage(this.animations[super.state.ordinal()][this.animationIndex], (int)(super.xCoordinate) + super.width, (int)(super.yCoordinate), -super.width, super.height, null);
                break;
            default:
                // We should never encounter this case.
                break;
        }

        // NOTE(SAVIZ): This method is used to only visualize the 'hitBox' for debugging. This should not be used in the final release of the game:
        super.renderHitBox(graphics);
    }

    private void updateState() {

        KeyboardInputs keyboardInputs = super.game.getKeyboardInputs();

        boolean isMoving = keyboardInputs.isMovingUp || keyboardInputs.isMovingLeft || keyboardInputs.isMovingDown || keyboardInputs.isMovingRight;

        if(isMoving) {

            super.state = Entity.State.MOVING;
        }

        else {

            super.state = Entity.State.IDLE;
        }
    }

    private void updatePosition() {

        KeyboardInputs keyboardInputs = super.game.getKeyboardInputs();

        float xSpeed = 0;
        float ySpeed = 0;

        // NOTE (SAVIZ): The reason why we do it this way instead of using a switch statement to allow simultaneous horizontal and vertical movement. If we use a switch statement we will only be able to move in one direction at any given time.
        if(keyboardInputs.isMovingLeft && !keyboardInputs.isMovingRight) {

            super.direction = Direction.WEST;
            xSpeed = -super.movementSpeed;
        }

        else if(keyboardInputs.isMovingRight && !keyboardInputs.isMovingLeft) {

            super.direction = Direction.EAST;
            xSpeed = +super.movementSpeed;
        }

        if(keyboardInputs.isMovingUp && !keyboardInputs.isMovingDown) {

            // We do not change our dircetion here.
            ySpeed = -super.movementSpeed;
        }

        else if(keyboardInputs.isMovingDown && !keyboardInputs.isMovingUp) {

            // We do not change our direction here.
            ySpeed = +super.movementSpeed;
        }

        // Check each 4 corners of the 'hitBox' in the future position:
        float xFutureCoordinate = super.hitBox.x + xSpeed;
        float yFutureCoordinate = super.hitBox.y + ySpeed;

        boolean tlWillCollide = super.willCollide(xFutureCoordinate, yFutureCoordinate);
        boolean trWillCollide = super.willCollide(xFutureCoordinate + super.hitBox.width, yFutureCoordinate);
        boolean blWillCollide = super.willCollide(xFutureCoordinate, yFutureCoordinate + super.hitBox.height);
        boolean brWillCollide = super.willCollide(xFutureCoordinate + super.hitBox.width, yFutureCoordinate + super.hitBox.height);

        if(!tlWillCollide && !brWillCollide && !trWillCollide && !blWillCollide) {

            // Move player and the hitBox to the new location:
            super.xCoordinate += xSpeed;
            super.yCoordinate += ySpeed;
            super.hitBox.x += xSpeed;
            super.hitBox.y += ySpeed;
        }
    }

    private void updateAnimation() {

        this.animationTick++;

        if (this.animationTick >= this.animationSpeed) {

            this.animationTick = 0;

            this.animationIndex++;

            if (this.animationIndex >= this.animations[super.state.ordinal()].length) {

                this.animationIndex = 0;
            }
        }
    }
}
