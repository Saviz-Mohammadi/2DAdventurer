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
    protected float xCoordinate = 0;
    protected float yCoordinate = 0;
    protected int movementSpeed = 0;
    protected Rectangle2D.Float hitBox = null;

    protected Entity() {

        this.hitBox = new Rectangle2D.Float();
    }

    protected void init(Game game) {

        this.game = game;
    }

    protected void updateHitBox() {

        this.hitBox.x = this.xCoordinate;
        this.hitBox.y = this.yCoordinate;
        this.hitBox.width = SettingsManager.getInstance().TILE_SCALED_SIZE;
        this.hitBox.height = SettingsManager.getInstance().TILE_SCALED_SIZE;
    }

    // NOTE(SAVIZ): This method is used to only visualize the 'hitBox' for debugging. This should not be used in the final release of the game:
    protected void renderHitBox(Graphics graphics) {

        graphics.setColor(Color.GREEN);
        graphics.drawRect((int)this.hitBox.x, (int)this.hitBox.y, (int)this.hitBox.width, (int)this.hitBox.height);
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
}
