/**
 * This Enum provides different states that our game can be in at any given time.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public enum GameState {

    MAINMENU, OPTIONSMENU, PLAYING, QUIT;

    @Override
    public String toString() {

        String result = "";

        switch (this) {

            case MAINMENU:
                result = "MAINMENU";
                break;
            case OPTIONSMENU:
                result = "OPTIONSMENU";
                break;
            case PLAYING:
                result = "PLAYING";
                break;
            case QUIT:
                result = "QUIT";
                break;
            default:
                throw new IllegalArgumentException("Unexpected state: " + this);
        }

        return(result);
    }
}
