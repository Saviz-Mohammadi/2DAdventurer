import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;

/**
 * This Abstract Class provides a base for all different types of entities (Player, Enemies, etc.).
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public abstract class Entity {

    // ENUM:
    protected enum State {

        IDLE, MOVING, JUMPING, ATTACKING;

        public static String getStateString(int number) {

            for (State state : State.values()) {

                if (state.ordinal() == number) {

                    return(state.toString());
                }
            }

            throw new IllegalArgumentException("Incorrect state value!");
        }

        @Override
        public String toString() {

            String result = "";

            switch (this) {

                case IDLE:
                    result = "IDLE";
                    break;
                case MOVING:
                    result = "MOVING";
                    break;
                case JUMPING:
                    result = "JUMPING";
                    break;
                case ATTACKING:
                    result = "ATTACKING";
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected state: " + this);
            }

            return(result);
        }
    }

    protected State state = State.IDLE;
    protected Direction direction = Direction.EAST;

    // PROPERTIES:
    protected float xCoordinate = 0;
    protected float yCoordinate = 0;
    protected int movementSpeed = 0;

    public Entity() {

    }
}
