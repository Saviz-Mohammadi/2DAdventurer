import javax.swing.*;
import java.awt.*;

/**
 * This class creates a 'GameFrame' and 'GamePanel' and starts our thread.
 *
 * @author Saviz
 * @version 1.0
 */
public class Game implements Runnable {

    // DEPENDENCIES:
    private GamePanel gamePanel = null;
    private GameFrame gameFrame = null;
    private Thread gameThread = null;

    // PROPERTIES:
    private final int FPS = 120;
    private final int UPS = 200;

    public Game() {

        this.gamePanel = new GamePanel();
        this.gameFrame = new GameFrame();
        this.gameThread = new Thread(this);
    }

    public void init() {

        // It is a good idea to 'init()' the 'ResourceManager' here to make sure files are loaded as many components require these to function correctly.
        Outcome<Void> outcome1 = ResourceManager.getInstance().init();
        Outcome<Void> outcome2 = this.gamePanel.init();
        Outcome<Void> outcome3 = this.gameFrame.init();

        if(!outcome1.isSuccessful || !outcome2.isSuccessful || !outcome3.isSuccessful) {

            String message = "Something went wrong!" + " ";

            if(!outcome1.isSuccessful) {

                message += outcome1.message;
            }

            if(!outcome2.isSuccessful) {

                message += outcome2.message;
            }

            if(!outcome3.isSuccessful) {

                message += outcome3.message;
            }

            System.out.println(message);
        }

        gameFrame.addGamePanel(this.gamePanel);
        gameFrame.pack();
        gamePanel.requestFocus(); // For input to work correctly.
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

				gamePanel.update();

				deltaU--;
			}

			// RENDER:
			if(deltaF >= 1) {

				gamePanel.repaint();

				deltaF--;
			}
        }
    }
}
