import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;

/**
 * This Abstract Class provides a base for all different types of interactable objects (Keys, etc.).
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public abstract class Interactable {

    // ENUMS:
    protected Direction direction = Direction.EAST;

    // DEPENDENCIES:
    Game game = null;

    // PROPERTIES:
    protected float xCoordinate = 0;
    protected float yCoordinate = 0;
    protected int width = 0;
    protected int height = 0;
    protected Rectangle2D.Float hitBox = null;
    protected boolean isActive = true;

    protected Interactable() {

        this.hitBox = new Rectangle2D.Float();
    }

    protected void init(Game game, float xCoordinate, float yCoordinate, int width, int height, boolean isActive) {

        this.game = game;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;
        this.hitBox.x = xCoordinate;
        this.hitBox.y = yCoordinate;
        this.hitBox.width = (float)width;
        this.hitBox.height = (float)height;
        this.isActive = isActive;
    }


    // When calling the update check for isActive or not and return if false;
    // When drawing the intercatbales care for offset in level manager.


    // NOTE(SAVIZ): This method is used only to visualize the 'hitBox' for debugging. This should not be used in the final release of the game:
    protected void renderHitBox(Graphics graphics) {

        graphics.setColor(Color.BLUE);
        graphics.drawRect((int)this.hitBox.x - this.game.getLevelManager().xLevelOffset, (int)this.hitBox.y - this.game.getLevelManager().yLevelOffset, (int)this.hitBox.width, (int)this.hitBox.height);
    }

    // GETTERS:
    public float getXCoordinate() {

        return(this.xCoordinate);
    }

    public float getYCoordinate() {

        return(this.yCoordinate);
    }

    public Rectangle2D getHitBox() {

        return(this.hitBox);
    }

    // SETTERS:
    public void setActiveState(boolean newState) {

        this.isActive = newState;
    }
}
