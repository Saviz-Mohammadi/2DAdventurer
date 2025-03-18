/**
 * This Enum provides different states that our game can be in at any given time.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public enum GameState {

    PAUSED, PLAYING;

    @Override
    public String toString() {

        String result = "";

        switch (this) {

            case PAUSED:
                result = "PAUSED";
                break;
            case PLAYING:
                result = "PLAYING";
                break;
            default:
                throw new IllegalArgumentException("Unexpected state: " + this);
        }

        return(result);
    }
}
