import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

public class Player extends Entity {

    // ENUM:
    private enum PlayerState {

        IDLE(0), MOVING(1);

        public static String getStateString(int number) {

            for (PlayerState playerState : PlayerState.values()) {

                if (playerState.value == number) {

                    return(palyerState.toString());
                }
            }

            throw new IllegalArgumentException("Unexpected player state value: " + value);
        }

        @Override
        public String toString() {

            String result = "";

            switch (this) {

                case IDLE:
                    result = "IDLE";
                    break;
                case MOVING:
                    result = "MOVING";
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected player state: " + this);
            }

            return(result);
        }
    }

    // DEPENDENCIES:
    GamePanel gamePanel = null;

    // PROPERTIES:
    PlayerState playerState = PlayerState.IDLE;
    private BufferedImage[][] animations;

    public Player() {

        super();
//                 this.screenXCoordinate = (this.gamePanel.screenWidth / 2) - (gamePanel.scaledCellSize / 2);
//                 this.screenYCoordinate = (this.gamePanel.screenHeight / 2) - (gamePanel.scaledCellSize / 2);
//
//                 // The values for 'meshCollider' are hand picked. You can change them if you like:
//                 this.collider = new Collider();
//                 this.collider.isEnabled = true;
//                 this.collidingArea = new Rectangle(0, 0, gamePanel.scaledCellSize, gamePanel.scaledCellSize);
    }

    public void init(GamePanel gamePanel, float xCoordinate, float yCoordinate, int movementSpeed) {

        // DEPENDENCIES:
        this.gamePanel = gamePanel;

        // PROPERTIES:
        super.xCoordinate = xCoordinate;
        super.yCoordinate = yCoordinate;
        super.movementSpeed = movementSpeed;

        // ANIMATIONS:
        // NOTE (SAVIZ): For we assume that there are only 2 states: 'IDLE' and 'MOVING', each having only 2 images:
        this.animations = new BufferedImage[2][2];

        for (int stateIndex = 0; stateIndex < this.animations.length; stateIndex++) {

            for (int imageIndex = 0; imageIndex < this.animations[stateIndex].length; imageIndex++) {

                // NOTE (SAVIZ): This will only work under the condition that image and file naming/management is absolutely perfect ("which it should be"):
                String key = "player_" + PlayerState.getStateString(stateIndex) + "_" + imageIndex;

                animations[stateIndex][imageIndex] = ResourceManager.getInstance().getImageUsingKey(key);
            }
        }
    }

        public void update() {

            updatePosition();
            updateAnimationTick();
            updateState();

                // Movement check:
//                 boolean isMoving = this.gamePanel.getInputHandler().isMovingUp || this.gamePanel.getInputHandler().isMovingLeft || this.gamePanel.getInputHandler().isMovingDown || this.gamePanel.getInputHandler().isMovingRight;
//
//                 if(!isMoving) {
//
//                         return;
//                 }


                // Collision check:
//                 if(collider.isColliding(this, this.collidingArea, this.gamePanel) == false) {
//
//                         switch(super.getDirection()) {
//
//                                 case UP:
//                                         super.setWorldYCoordinate(super.getWorldYCoordinate() + super.getMovementSpeed());
//                                         break;
//                                 case LEFT:
//                                         super.setWorldXCoordinate(super.getWorldXCoordinate() + super.getMovementSpeed());
//                                         break;
//                                 case DOWN:
//                                         super.setWorldYCoordinate(super.getWorldYCoordinate() - super.getMovementSpeed());
//                                         break;
//                                 case RIGHT:
//                                         super.setWorldXCoordinate(super.getWorldXCoordinate() - super.getMovementSpeed());
//                                         break;
//                                 default:
//                                         break;
//                         }
//
//                         // Sprite Logic:
//                         spriteCounter++;
//
//                         // Every 12 frames change the variation:
//                         if(spriteCounter > 12) {
//
//                                 switch(spriteVariation) {
//                                         case 1:
//                                                 spriteVariation = 2;
//                                                 break;
//                                         case 2:
//                                                 spriteVariation = 1;
//                                                 break;
//                                         default:
//                                                 break;
//                                 }
//
//                                 spriteCounter = 0;
//                         }
//                 }
        }

        public void render(Graphics graphics) {

                BufferedImage bufferedImage = null;

                switch(super.getDirection()) {

                        case UP:
                                if(spriteVariation == 1) {

                                        bufferedImage = super.getSpriteMap().get("up_1");
                                }

                                if(spriteVariation == 2) {

                                        bufferedImage = super.getSpriteMap().get("up_2");
                                }
                                break;
                        case LEFT:
                                if(spriteVariation == 1) {

                                        bufferedImage = super.getSpriteMap().get("left_1");
                                }

                                if(spriteVariation == 2) {

                                        bufferedImage = super.getSpriteMap().get("left_2");
                                }
                                break;
                        case DOWN:
                                if(spriteVariation == 1) {

                                        bufferedImage = super.getSpriteMap().get("down_1");
                                }

                                if(spriteVariation == 2) {

                                        bufferedImage = super.getSpriteMap().get("down_2");
                                }
                                break;
                        case RIGHT:
                                if(spriteVariation == 1) {

                                        bufferedImage = super.getSpriteMap().get("right_1");
                                }

                                if(spriteVariation == 2) {

                                        bufferedImage = super.getSpriteMap().get("right_2");
                                }
                                break;
                        default:
                                break;
                }

            graphics.drawImage(this.animations[playerAction][aniIndex], (int)super.xCoordinate, (int)super.yCoordinate, 21, 21, null);
        }

    private void updatePosition() {

        KeyboardInputs keyboardInputs = this.gamePanel.getKeyboardInputs();

        // NOTE (SAVIZ): The reason why we do it this way instead of using a switch statement to allow simultaneous horizontal and vertical movement. If we use a switch statement we will only be able to move in one direction at any given time.
        if(keyboardInputs.isMovingLeft && !keyboardInputs.isMovingRight) {

            super.xCoordinate -= super.movementSpeed;
            moving = true;
        }

        else if(keyboardInputs.isMovingRight && !keyboardInputs.isMovingLeft) {

            super.xCoordinate += super.movementSpeed;
            moving = true;
        }

        if(keyboardInputs.isMovingUp && !keyboardInputs.isMovingDown) {

            super.yCoordinate -= super.movementSpeed;
            moving = true;
        }

        else if(keyboardInputs.isMovingDown && !keyboardInputs.isMovingUp) {

            super.yCoordinate += super.movementSpeed;
            moving = true;
        }
    }
}
