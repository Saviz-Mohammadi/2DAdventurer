import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;

/**
 * This Abstract Class provides a base for all different types of interactable objects (KeyCards, etc.).
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class KeyCard extends Interactable {

    private String imageKey;

    protected KeyCard() {

        super();
    }

    public void init(Game game, float xCoordinate, float yCoordinate, int width, int height, boolean isActive, String imageKey) {

        super.init(game, xCoordinate, yCoordinate, width, height, isActive);

        this.setImageKey(imageKey);
    }

    public void update() {

        updateState();
    }

    public void render(Graphics graphics) {

        switch(super.direction) {

            case EAST:
                graphics.drawImage(, (int)(super.xCoordinate) - super.game.getLevelManager().xLevelOffset, (int)(super.yCoordinate) - super.game.getLevelManager().yLevelOffset, super.width, super.height, null);
                break;
            case WEST:
                graphics.drawImage(, (int)(super.xCoordinate) + super.width - super.game.getLevelManager().xLevelOffset, (int)(super.yCoordinate) - super.game.getLevelManager().yLevelOffset, -super.width, super.height, null);
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

    // GETTERS:
    public String getImageKey() {

        return(this.imageKey);
    }

    // SETTERS:
    public void setImageKey(String newImageKey) {

        if(this.imageKey == newImageKey) {

            return;
        }

        this.imageKey = newImageKey;
    }
}
