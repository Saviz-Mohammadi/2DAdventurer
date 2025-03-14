import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;

public class Player extends Entity {

        // Dependencies:
        GamePanel gamePanel;
        Collider collider;
        Rectangle collidingArea;

        // Properties:
        public final int screenXCoordinate;
        public final int screenYCoordinate;
        int spriteCounter = 0;
        int spriteVariation = 1;


        public Player(GamePanel gamePanel) {

                super();
                this.gamePanel = gamePanel;
                this.screenXCoordinate = (this.gamePanel.screenWidth / 2) - (gamePanel.scaledCellSize / 2);
                this.screenYCoordinate = (this.gamePanel.screenHeight / 2) - (gamePanel.scaledCellSize / 2);

                // The values for 'meshCollider' are hand picked. You can change them if you like:
                this.collider = new Collider();
                this.collider.isEnabled = true;
                this.collidingArea = new Rectangle(0, 0, gamePanel.scaledCellSize, gamePanel.scaledCellSize);
        }

        public void Init(int xCoordinate, int yCoordinate, int movementSpeed) {

                super.setWorldXCoordinate(xCoordinate);
                super.setWorldYCoordinate(yCoordinate);
                super.setMovementSpeed(movementSpeed);



                super.setDirection(Direction.RIGHT);
        }

        public void onUpdate() {

                // Movement check:
                boolean isMoving = this.gamePanel.getInputHandler().isMovingUp || this.gamePanel.getInputHandler().isMovingLeft || this.gamePanel.getInputHandler().isMovingDown || this.gamePanel.getInputHandler().isMovingRight;

                if(!isMoving) {

                        return;
                }


                // Movement logic:
                if(this.gamePanel.getInputHandler().isMovingUp) {

                        super.setDirection(Direction.UP);
                }

                if(this.gamePanel.getInputHandler().isMovingLeft) {

                        super.setDirection(Direction.LEFT);
                }

                if(this.gamePanel.getInputHandler().isMovingDown) {

                        super.setDirection(Direction.DOWN);
                }

                if(this.gamePanel.getInputHandler().isMovingRight) {

                        super.setDirection(Direction.RIGHT);
                }


                // Collision check:
                if(collider.isColliding(this, this.collidingArea, this.gamePanel) == false) {

                        switch(super.getDirection()) {

                                case UP:
                                        super.setWorldYCoordinate(super.getWorldYCoordinate() + super.getMovementSpeed());
                                        break;
                                case LEFT:
                                        super.setWorldXCoordinate(super.getWorldXCoordinate() + super.getMovementSpeed());
                                        break;
                                case DOWN:
                                        super.setWorldYCoordinate(super.getWorldYCoordinate() - super.getMovementSpeed());
                                        break;
                                case RIGHT:
                                        super.setWorldXCoordinate(super.getWorldXCoordinate() - super.getMovementSpeed());
                                        break;
                                default:
                                        break;
                        }

                        // Sprite Logic:
                        spriteCounter++;

                        // Every 12 frames change the variation:
                        if(spriteCounter > 12) {

                                switch(spriteVariation) {
                                        case 1:
                                                spriteVariation = 2;
                                                break;
                                        case 2:
                                                spriteVariation = 1;
                                                break;
                                        default:
                                                break;
                                }

                                spriteCounter = 0;
                        }
                }
        }

        public void onDraw(Graphics graphics) {

                Graphics2D graphics2D = (Graphics2D) graphics;

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

                // The player will always be drawn at the exact same location on the screen and the 'CellManager' will change the environment tiles according to our world coordinates.
                graphics2D.drawImage(bufferedImage, this.screenXCoordinate, this.screenYCoordinate, gamePanel.scaledCellSize, gamePanel.scaledCellSize, null);
        }
}
