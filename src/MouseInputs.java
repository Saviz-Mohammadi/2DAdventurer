import java.awt.event.*;

/**
 * This class provides input handling for our game.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    // PROPERTIES:
    public int xCoordinate = 0;
    public int yCoordinate = 0;
    public boolean isPressed = false;

    public MouseInputs() {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        this.xCoordinate = mouseEvent.getX();
        this.yCoordinate = mouseEvent.getY();
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

        this.xCoordinate = mouseEvent.getX();
        this.yCoordinate = mouseEvent.getY();
        isPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
