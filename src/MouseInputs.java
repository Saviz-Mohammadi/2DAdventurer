import java.awt.event.*;

/**
 * This class provides input handling for our game.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    // PROPERTIES:
    public int xDelta = 0;
    public int yDelat = 0;

    public MouseInputs() {

    }

    public MouseInputs(GamePanel gamePanel) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

        this.xDelta = mouseEvent.getX();
        this.yDelat = mouseEvent.getY();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

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
