import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class MainMenu {

    // DEPENDENCIES:
    private Game game = null;

    // PROPERTIES:
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private int width = 0;
    private int height = 0;
    private String imageKey = "";
    private MenuButton[] menuButtons = null;

    public MainMenu() {

        this.menuButtons = new MenuButton[2];
    }

    public void init(Game game, int xCoordinate, int yCoordinate, int width, int height, String imageKey) {

        // DEPENDENCIES:
        this.game = game;

        // PROPERTIES:
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;
        this.imageKey = imageKey;

        MenuButton optionsMenuButton = new MenuButton();
        optionsMenuButton.init(this.game, xCoordinate + 115, yCoordinate + 108, 120, 45, "ui_button_options");

        MenuButton quitMenuButton = new MenuButton();
        quitMenuButton.init(this.game, xCoordinate + 115, yCoordinate + 153, 120, 45, "ui_button_quit");

        this.menuButtons[0] = optionsMenuButton;
        this.menuButtons[1] = quitMenuButton;
    }

    public void update() {

        for (MenuButton menuButton : this.menuButtons) {

            menuButton.update();
        }

        // OPTIONS BUTTON:
        if(menuButtons[0].isClicked) {

            this.game.setGameState(GameState.OPTIONSMENU);
        }

        // QUIT BUTTON:
        if(menuButtons[1].isClicked) {

            this.game.setGameState(GameState.QUIT);
        }
    }

    public void render(Graphics graphics) {

        graphics.drawImage(ResourceManager.getInstance().getImageUsingKey(this.imageKey), this.xCoordinate, this.yCoordinate, this.width, this.height, null);

        for (MenuButton menuButton : this.menuButtons) {

            menuButton.render(graphics);
        }
    }
}
