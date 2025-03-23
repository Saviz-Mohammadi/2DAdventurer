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

        boolean isMoving = keyboardInputs.isJumping || super.isInAir || keyboardInputs.isMovingLeft || keyboardInputs.isMovingRight;

        if(isMoving) {

            super.state = Entity.State.MOVING;
        }

        else {

            super.state = Entity.State.IDLE;
        }
    }

    private void updatePosition() {

        KeyboardInputs keyboardInputs = super.game.getKeyboardInputs();

        if(super.state != Entity.State.MOVING) {

            return;
        }

        if (keyboardInputs.isJumping) {

            this.jump();
        }

        if (!super.isInAir) {

            if (!super.isGrounded()) {

                super.isInAir = true;
            }
        }

        super.xSpeed = 0.0f;

        // NOTE (SAVIZ): The reason why we do it this way instead of using a switch statement to allow simultaneous horizontal and vertical movement. If we use a switch statement we will only be able to move in one direction at any given time.
        if(keyboardInputs.isMovingLeft) {

            super.direction = Direction.WEST;
            super.xSpeed -= super.movementSpeed;
        }

        if(keyboardInputs.isMovingRight) {

            super.direction = Direction.EAST;
            super.xSpeed += super.movementSpeed;
        }

        // Check for both and x and y movement:
        if(super.isInAir) {

            if (super.canMoveToCoordinates(super.hitBox.x, super.hitBox.y + super.ySpeed)) {

                super.yCoordinate += super.ySpeed;
                super.hitBox.y += super.ySpeed;
                super.ySpeed += super.gravity;
                this.updateXPosition();
            }

            else {

                float newYPosition = obtainYPosition(super.ySpeed);

                super.yCoordinate = newYPosition;
                super.hitBox.y = newYPosition;

                if(super.ySpeed > 0.0f) {

                    super.isInAir = false;
                    super.ySpeed = 0.0f;
                }

                else {

                    super.ySpeed = super.fallSpeedAfterCollision;
                }

                this.updateXPosition();
            }
        }

        // Check for x movement:
        else {

            this.updateXPosition();
        }
    }

    private void jump() {

        if (super.isInAir) {

            return;
        }

        super.isInAir = true;
        super.ySpeed = super.jumpSpeed;
    }

    private void updateXPosition() {

        if (super.canMoveToCoordinates(this.hitBox.x + super.xSpeed, this.hitBox.y)) {

            super.xCoordinate += super.xSpeed;
            super.hitBox.x += super.xSpeed;
        }

        else {

            float newXPosition = obtainXPosition(super.xSpeed);

            super.xCoordinate = newXPosition;
            super.hitBox.x = newXPosition;
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
