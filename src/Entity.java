import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;

/**
 * This Abstract Class provides a base for all different types of entities (Player, Enemies, etc.).
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public abstract class Entity {

    // ENUM:
    protected enum State {

        IDLE, MOVING, JUMPING, ATTACKING;

        public static String getStateString(int number) {

            for (State state : State.values()) {

                if (state.ordinal() == number) {

                    return(state.toString());
                }
            }

            throw new IllegalArgumentException("Incorrect state value!");
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
                case JUMPING:
                    result = "JUMPING";
                    break;
                case ATTACKING:
                    result = "ATTACKING";
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected state: " + this);
            }

            return(result);
        }
    }

    protected State state = State.IDLE;
    protected Direction direction = Direction.EAST;

    // DEPENDENCIES:
    Game game = null;

    // PROPERTIES:
    protected float xCoordinate = 0.0f;
    protected float yCoordinate = 0.0f;
    protected int width = 0;
    protected int height = 0;
    protected int movementSpeed = 0;
    protected Rectangle2D.Float hitBox = null;
    protected float xSpeed = 0.0f;
    protected float ySpeed = 0.0f;
    protected float gravity = 0.04f;
    protected float jumpSpeed = -2.25f;
    protected float fallSpeedAfterCollision = 0.5f;
    protected boolean isInAir = false;

    protected Entity() {

        this.hitBox = new Rectangle2D.Float();
    }

    protected void init(Game game, float xCoordinate, float yCoordinate, int width, int height, int movementSpeed) {

        this.game = game;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
        this.hitBox.x = xCoordinate;
        this.hitBox.y = yCoordinate;
        this.hitBox.width = (float)width;
        this.hitBox.height = (float)height;

        if (!this.isGrounded()) {

            this.isInAir = true;
        }
    }

    // NOTE(SAVIZ): This method is used only to visualize the 'hitBox' for debugging. This should not be used in the final release of the game:
    protected void renderHitBox(Graphics graphics) {

        graphics.setColor(Color.GREEN);
        graphics.drawRect((int)this.hitBox.x, (int)this.hitBox.y, (int)this.hitBox.width, (int)this.hitBox.height);
    }

    protected boolean canMoveToCoordinates(float xCoordinate, float yCoordinate) {

        boolean tlWillCollide = this.willCollide(xCoordinate, yCoordinate);
        boolean trWillCollide = this.willCollide(xCoordinate + this.hitBox.width, yCoordinate);
        boolean blWillCollide = this.willCollide(xCoordinate, yCoordinate + this.hitBox.height);
        boolean brWillCollide = this.willCollide(xCoordinate + this.hitBox.width, yCoordinate + this.hitBox.height);

        if(!tlWillCollide) {

            if(!brWillCollide) {

                if(!trWillCollide) {

                    if(!blWillCollide) {

                        return(true);
                    }
                }
            }
        }

        return(false);
    }

    protected boolean willCollide(float xFutureCoordinate, float yFutureCoordinate) {

        boolean willCollide = false;

        // If the movement causes the player to go outside the boundaries of the window or panel:
        if (xFutureCoordinate < 0 || xFutureCoordinate >= SettingsManager.getInstance().GAME_WIDTH) {

            willCollide = true;
        }

        if (yFutureCoordinate < 0 || yFutureCoordinate >= SettingsManager.getInstance().GAME_HEIGHT) {

            willCollide = true;
        }

        // Check 'Tile':
        float xIndex = xFutureCoordinate / SettingsManager.getInstance().TILE_SCALED_SIZE;
        float yIndex = yFutureCoordinate / SettingsManager.getInstance().TILE_SCALED_SIZE;

        Tile[][] tiles = this.game.getLevelManager().getTiles();
        Tile tile = tiles[(int) xIndex][(int) yIndex];


        // NOTE(SAVIZ): Due to the way that 'LevelManager' creates the levels, sometimes we will deal with indexes that have no Tiles, they instead will have enemies or collectables. Since we do not care about collision with collectables and enemies we simply make a check to see if tile exists and if it deosn't then we don't care about it.
        if(tile != null) {

            if(tile.isSolid == true) {

                willCollide = true;
            }
        }

        return(willCollide);
    }

    protected boolean isGrounded() {

        // Check the pixel below bottomleft and bottomright:
        if (!willCollide(this.hitBox.x, this.hitBox.y + this.hitBox.height + 1)) {

            if (!willCollide(this.hitBox.x + this.hitBox.width, this.hitBox.y + this.hitBox.height + 1)) {

                return(false);
            }
        }

        return(true);
    }

    protected float obtainXPosition(float xSpeed) {

        int currentTile = (int) (this.hitBox.x / SettingsManager.getInstance().TILE_SCALED_SIZE);

        // Right:
        if (xSpeed > 0.0f) {

            return (currentTile * SettingsManager.getInstance().TILE_SCALED_SIZE) + (int) (SettingsManager.getInstance().TILE_SCALED_SIZE - this.hitBox.width) - 1;
        }

        // Left:
        else {

            return (currentTile * SettingsManager.getInstance().TILE_SCALED_SIZE);
        }
    }

    protected float obtainYPosition(float ySpeed) {

        int currentTile = (int) (this.hitBox.y / SettingsManager.getInstance().TILE_SCALED_SIZE);

        // Falling:
        if (ySpeed > 0.0f) {

            return (currentTile * SettingsManager.getInstance().TILE_SCALED_SIZE) + (int) (SettingsManager.getInstance().TILE_SCALED_SIZE - this.hitBox.height) - 1;
        }

        // Jumping:
        else {

            return (currentTile * SettingsManager.getInstance().TILE_SCALED_SIZE);
        }
    }
}
