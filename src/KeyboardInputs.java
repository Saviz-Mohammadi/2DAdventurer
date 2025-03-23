import java.awt.event.*;

/**
 * This class provides input handling for our game.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class KeyboardInputs implements KeyListener {

    // PROPERTIES:
    public boolean isEscaping = false;
    public boolean isJumping = false;
    public boolean isMovingLeft = false;
    public boolean isMovingRight = false;

    public KeyboardInputs() {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        switch(keyCode) {

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.isMovingLeft = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.isMovingRight = true;
                break;
            case KeyEvent.VK_SPACE:
                this.isJumping = true;
                break;
            default:
                break;
        }

        switch(keyCode) {

            case KeyEvent.VK_ESCAPE:
                // Only toggle if not already pressed
                if (!this.isEscaping) {

                    this.isEscaping = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        switch(keyCode) {

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.isMovingLeft = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.isMovingRight = false;
                break;
            case KeyEvent.VK_SPACE:
                this.isJumping = false;
                break;
            default:
                break;
        }
    }
}
