/**
 * This Enum provides directions in our game.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public enum Direction {

    NORTH, EAST, SOUTH, WEST;

    @Override
    public String toString() {

        String result = "";

        switch (this) {

            case NORTH:
                result = "NORTH";
                break;
            case EAST:
                result = "EAST";
                break;
            case SOUTH:
                result = "SOUTH";
                break;
            case WEST:
                result = "WEST";
                break;
            default:
                throw new IllegalArgumentException("Unexpected direction: " + this);
        }

        return(result);
    }
}
