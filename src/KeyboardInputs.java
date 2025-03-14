import java.awt.event.*;

/**
 * This class provides input handling for our game.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class KeyboardInputs implements KeyListener {

    // PROPERTIES:
    public boolean isMovingUp = false;
    public boolean isMovingLeft = false;
    public boolean isMovingDown = false;
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

            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.isMovingUp = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.isMovingLeft = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.isMovingDown = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.isMovingRight = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int keyCode = keyEvent.getKeyCode();

        switch(keyCode) {

            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                this.isMovingUp = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                this.isMovingLeft = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                this.isMovingDown = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                this.isMovingRight = false;
                break;
            default:
                break;
        }
    }
}
