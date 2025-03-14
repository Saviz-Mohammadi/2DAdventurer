import javax.swing.*;
import java.awt.*;

/**
 * This class creates a JFrame for our game that acts as the window.
 *
 * @author Saviz
 * @version 1.0
 */
public class GameFrame{

    private JFrame frame = null;

    public GameFrame() {

        this.frame = new JFrame();
    }

    public Outcome<Void> init() {

        Outcome<Void> outcome = new Outcome<Void>();

        this.frame.setTitle("2D Adventurer");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        return(outcome);
    }

    public void addGamePanel(GamePanel gamePanel) {

        this.frame.add(gamePanel);
    }

    public void pack() {

        this.frame.pack();
    }
}
