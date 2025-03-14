import javax.swing.*;
import java.awt.*;

/**
 * This class is where all components come together. It creates a 'GameFrame' and 'GamePanel' and enables to start our game thread.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class Game implements Runnable {

    // PROPERTIES:
    private final int FPS = 120;
    private final int UPS = 200;
    private boolean operatingSystemIsUnix = false;

    // COMPONENTS:
    private Thread gameThread = null;
    private KeyboardInputs keyboardInputs = null;
    private MouseInputs mouseInpus = null;
    private Player player = null;
    private GamePanel gamePanel = null;
    private GameFrame gameFrame = null;

    public Game() {

        // THREAD:
        this.gameThread = new Thread(this);

        // INPUTS:
        this.keyboardInputs = new KeyboardInputs();
        this.mouseInpus = new MouseInputs();

        // PLAYER:
        this.player = new Player();

        // FRAME & PANEL:
        this.gamePanel = new GamePanel();
        this.gameFrame = new GameFrame();
    }

    public void init() {

        // RESOURCES:
        ResourceManager.getInstance().init();

        // INPUTS:
        this.gamePanel.addKeyListener(this.keyboardInputs);
        this.gamePanel.addMouseListener(this.mouseInpus);
        this.gamePanel.addMouseMotionListener(this.mouseInpus);

        // PLAYER:
        this.player.init(this, 100, 100, 1);

        // FRAME & PANEL:
        this.gamePanel.init(this);
        this.gameFrame.init();
        this.gameFrame.addGamePanel(this.gamePanel);
        this.gameFrame.pack();
        this.gamePanel.requestFocus(); // For input to work correctly.

        // OS:
        if (System.getProperty("os.name").toLowerCase().contains("linux") || System.getProperty("os.name").toLowerCase().contains("mac")) {

            this.operatingSystemIsUnix = true;
        }
    }

    public void startGameLoop() {

        this.gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000.0 / this.FPS;
        double timePerUpdate = 1_000_000_000.0 / this.UPS;

        long previousTime = System.nanoTime();

        double deltaU = 0;
        double deltaF = 0;

        while(true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            // LOGIC:
            if(deltaU >= 1) {

                this.update();
                deltaU--;
            }

            // RENDER:
            if(deltaF >= 1) {

                this.gamePanel.repaint();
                deltaF--;

                // OS:
                //
                // NOTE(SAVIZ): This part is confusing to me, but here is the explanation from 'StackOverflow':
                //
                // Some Unix systems (Linux, macOS) may buffer graphics operations. The call to the 'sync()' method will force the API to push all pending operations and changes through to the OS, thereby reducing potential delays or rendering glitches (Lag).

                if (this.operatingSystemIsUnix) {

                    Toolkit.getDefaultToolkit().sync();
                }
            }
        }
    }

    // GETTERS
    public KeyboardInputs getKeyboardInputs() {

        return(this.keyboardInputs);
    }

    public MouseInputs getMouseInputs() {

        return(this.mouseInpus);
    }

    public Player getPlayer() {

        return(this.player);
    }

    // PRIVATE
    private void update() {

        this.player.update();
    }
}
