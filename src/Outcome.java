/**
 * This class acts as a struct helper which contains the result of an operation.
 *
 * @author Saviz Mohammadi
 * @version 1.0
 */
public class Outcome<T> {

    public boolean isSuccessful = true;
    public String message = "Operation completed successfully!";
    public T data;

    public Outcome() {

    }

    public Outcome(boolean status, String message, T data) {

        this.isSuccessful = status;
        this.message = message;
        this.data = data;
    }
}
