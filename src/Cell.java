import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class Cell {

        // Properties:
        BufferedImage bufferedImage;
        boolean isCollidable;


        public Cell() {

        }

        public Cell(BufferedImage bufferedImage, boolean isCollidable) {

                setBufferedImage(bufferedImage);
                setIsCollidable(isCollidable);
        }

        // Getters

        public BufferedImage getBufferedImage() {

                return (this.bufferedImage);
        }

        public boolean getIsCollidable() {

                return (this.isCollidable);
        }

        // Setters

        public void setBufferedImage(BufferedImage newBufferedImage) {

                this.bufferedImage = newBufferedImage;
        }

        public void setIsCollidable(boolean newStatus) {

                this.isCollidable = newStatus;
        }
}
