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

    // SETTINGS:
    public final static int TILE_DEFAULT_SIZE = 16;
	public final static int SCALE = 3;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILE_SCALED_SIZE = TILE_DEFAULT_SIZE * SCALE;
	public final static int GAME_WIDTH = TILE_SCALED_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILE_SCALED_SIZE * TILES_IN_HEIGHT;

    // COMPONENTS:
    private Thread gameThread = null;
    private KeyboardInputs keyboardInputs = null;
    private MouseInputs mouseInpus = null;
    private Player player = null;
    private GamePanel gamePanel = null;
    private JPanel backgroundPanel = null;
    private GameFrame gameFrame = null;

    public Game() {

        // THREAD:
        this.gameThread = new Thread(this);

        if (System.getProperty("os.name").toLowerCase().contains("linux") || System.getProperty("os.name").toLowerCase().contains("mac")) {

            this.operatingSystemIsUnix = true;
        }

        // INPUTS:
        this.keyboardInputs = new KeyboardInputs();
        this.mouseInpus = new MouseInputs();

        // PLAYER:
        this.player = new Player();

        // FRAME & PANEL & LAYOUT:
        this.gamePanel = new GamePanel();
        this.backgroundPanel = new JPanel();
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

        // GAME PANEL:
        this.gamePanel.init(this);
        this.gamePanel.setPreferredSize(new Dimension(1280, 720));
        this.gamePanel.setFocusable(true);

        // BACKGROUND PANEL:
        this.backgroundPanel.setBackground(Color.BLACK);
        this.backgroundPanel.setLayout(new GridBagLayout());
        this.backgroundPanel.add(this.gamePanel, new GridBagConstraints());
        this.backgroundPanel.setFocusable(false);

        // FRAME:
        this.gameFrame.init();
        this.gameFrame.setTitle("2D Adventurer");
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (graphicsDevice.isFullScreenSupported()) {

            this.gameFrame.setUndecorated(true);
            graphicsDevice.setFullScreenWindow(this.gameFrame);
        }

        else {

            this.gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        this.gameFrame.setContentPane(this.backgroundPanel);
        this.gameFrame.setVisible(true);
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
