import java.awt.event.*;

/**
 * This class provides input handling for our game.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    // PROPERTIES:
    public MouseEvent mouseEvent;

    public MouseInputs() {

    }

    public MouseInputs(GamePanel gamePanel) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        this.mouseEvent = mouseEvent;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
