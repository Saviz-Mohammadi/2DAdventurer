import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class OptionsMenu {

    // DEPENDENCIES:
    private Game game = null;

    // PROPERTIES:
    private int xCoordinate = 0;
    private int yCoordinate = 0;
    private int width = 0;
    private int height = 0;
    private String imageKey = "";
    private MenuButton[] menuButtons = null;

    public OptionsMenu() {

        this.menuButtons = new MenuButton[5];
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

        MenuButton musicIncreaseMenuButton = new MenuButton();
        musicIncreaseMenuButton.init(this.game, xCoordinate + 120, yCoordinate + 108, 120, 45, "ui_button_increase");

        MenuButton musicDecreaseMenuButton = new MenuButton();
        musicDecreaseMenuButton.init(this.game, xCoordinate + 240, yCoordinate + 108, 120, 45, "ui_button_decrease");

        MenuButton sfxIncreaseMenuButton = new MenuButton();
        sfxIncreaseMenuButton.init(this.game, xCoordinate + 120, yCoordinate + 153, 120, 45, "ui_button_increase");

        MenuButton sfxDecreaseMenuButton = new MenuButton();
        sfxDecreaseMenuButton.init(this.game, xCoordinate + 240, yCoordinate + 153, 120, 45, "ui_button_decrease");

        MenuButton mainMenuButton = new MenuButton();
        mainMenuButton.init(this.game, xCoordinate + 115, yCoordinate + 198, 120, 45, "ui_button_main_menu");

        this.menuButtons[0] = musicIncreaseMenuButton;
        this.menuButtons[1] = musicDecreaseMenuButton;
        this.menuButtons[2] = sfxIncreaseMenuButton;
        this.menuButtons[3] = sfxDecreaseMenuButton;
        this.menuButtons[4] = mainMenuButton;
    }

    public void update() {

        for (MenuButton menuButton : this.menuButtons) {

            menuButton.update();
        }


        if(menuButtons[0].isClicked) {

            SettingsManager.getInstance().increaseMusicVolume(10);
        }

        if(menuButtons[1].isClicked) {

            SettingsManager.getInstance().decreaseMusicVolume(10);
            menuButtons[1].isClicked = false;
        }

        if(menuButtons[2].isClicked) {

            SettingsManager.getInstance().increaseSFXVolume(10);
        }

        if(menuButtons[3].isClicked) {

            SettingsManager.getInstance().decreaseSFXVolume(10);
        }

        if(menuButtons[4].isClicked) {

            this.game.setGameState(GameState.MAINMENU);
        }
    }

    public void render(Graphics graphics) {

        graphics.drawImage(ResourceManager.getInstance().getImageUsingKey(this.imageKey), this.xCoordinate, this.yCoordinate, this.width, this.height, null);

        graphics.drawString("MUSIC: " + SettingsManager.getInstance().getMusicVolume(), this.xCoordinate + 30, this.yCoordinate + 108);
        graphics.drawString("SFX: " + SettingsManager.getInstance().getSFXVolume(), this.xCoordinate + 30, this.yCoordinate + 153);

        for (MenuButton menuButton : this.menuButtons) {

            menuButton.render(graphics);
        }
    }
}
