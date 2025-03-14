import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;

public class Collider {

        // Properties:
        public boolean isEnabled = false;

        public boolean isColliding(Entity entity, Rectangle collidingArea, GamePanel gamePanel) {

                // If 'Collider' is disabled, then just return that nothing is collidng:
                if(!this.isEnabled) {

                        return (false);
                }

                int entityLeftXCoordinate = entity.getWorldXCoordinate() + collidingArea.x;
                int entityRightXCoordinate = entity.getWorldXCoordinate() + collidingArea.x + collidingArea.width;
                int entityTopYCoordinate = entity.getWorldYCoordinate() + collidingArea.y;
                int entityBottomYCoordinate = entity.getWorldYCoordinate() + collidingArea.y + collidingArea.height;

                int entityLeftColumn = entityLeftXCoordinate / gamePanel.scaledCellSize;
                int entityRightColumn = entityRightXCoordinate / gamePanel.scaledCellSize;
                int entityTopRow = entityTopYCoordinate / gamePanel.scaledCellSize;
                int entityBottomRow = entityBottomYCoordinate / gamePanel.scaledCellSize;

                CellManager cellManager = gamePanel.getCellManager();
                Cell cell1;
                Cell cell2;

                boolean isColliding = false;

                switch(entity.getDirection()) {

                        case UP:
                                entityTopRow = (entityTopYCoordinate - entity.getMovementSpeed()) / gamePanel.scaledCellSize;
                                cell1 = cellManager.getCells()[entityLeftColumn][entityTopRow];
                                cell2 = cellManager.getCells()[entityRightColumn][entityTopRow];

                                if(cell1.getIsCollidable() || cell2.getIsCollidable()) {

                                        isColliding = true;
                                }
                                break;
                        case LEFT:

                                break;
                        case DOWN:

                                break;
                        case RIGHT:

                                break;
                        default:
                                break;
                }

                return (isColliding);
        }
}
