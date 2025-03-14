import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class CellManager {

        // Dependencies:
        GamePanel gamePanel;

        // Properties:
        Cell[][] cells;

        public CellManager(GamePanel gamePanel) {

                this.gamePanel = gamePanel;

                // I don't understand why, but for some reason we decided to put columns first and then rows  ¯\_(ツ)_/¯. KILL ME NOW!!!!
                this.cells = new Cell[gamePanel.worldColumns][gamePanel.worldRows];
        }

        public void setupFromMapFile(String path) {

                int columnIndex = 0;
                int rowIndex = 0;

                try {
                        BufferedReader bufferReader = new BufferedReader(new FileReader(path));

                        while (columnIndex < gamePanel.worldColumns && rowIndex < gamePanel.worldRows) {

                                String line = bufferReader.readLine();

                                while (columnIndex < gamePanel.worldColumns) {

                                        String[] values = line.trim().split(" ");

                                        int number = Integer.parseInt(values[columnIndex]);

                                        Cell cell = new Cell();

                                        switch(number) {

                                                case 0:
                                                        cell.setBufferedImage(ImageIO.read(new File("./resources/sprite files/tiles/grass.png")));
                                                        cell.setIsCollidable(false);
                                                        break;
                                                case 1:
                                                        cell.setBufferedImage(ImageIO.read(new File("./resources/sprite files/tiles/wall.png")));
                                                        cell.setIsCollidable(true);
                                                        break;
                                                case 2:
                                                        cell.setBufferedImage(ImageIO.read(new File("./resources/sprite files/tiles/water.png")));
                                                        cell.setIsCollidable(true);
                                                        break;
                                                case 3:
                                                        cell.setBufferedImage(ImageIO.read(new File("./resources/sprite files/tiles/earth.png")));
                                                        cell.setIsCollidable(false);
                                                        break;
                                                case 4:
                                                        cell.setBufferedImage(ImageIO.read(new File("./resources/sprite files/tiles/tree.png")));
                                                        cell.setIsCollidable(true);
                                                        break;
                                                case 5:
                                                        cell.setBufferedImage(ImageIO.read(new File("./resources/sprite files/tiles/sand.png")));
                                                        cell.setIsCollidable(false);
                                                        break;
                                                default:
                                                        break;
                                        }

                                        cells[columnIndex][rowIndex] = cell;
                                        columnIndex++;
                                }

                                if(columnIndex == gamePanel.worldColumns) {

                                        columnIndex = 0;
                                        rowIndex++;
                                }
                        }

                        bufferReader.close();
                }

                catch (IOException exception) {

                        exception.printStackTrace();
                }
        }

        public void onDraw(Graphics graphics) {

                Graphics2D graphics2D = (Graphics2D) graphics;

                int columnIndex = 0;
                int rowIndex = 0;

                while (columnIndex < gamePanel.worldColumns && rowIndex < gamePanel.worldRows) {

                        Cell cell = cells[columnIndex][rowIndex];

                        int worldXCoordinate = columnIndex * gamePanel.scaledCellSize;
                        int worldYCoordinate = rowIndex * gamePanel.scaledCellSize;
                        int screenXCoordinate = worldXCoordinate - gamePanel.getPlayer().screenXCoordinate + gamePanel.getPlayer().getWorldXCoordinate();
                        int screenYCoordinate = worldYCoordinate - gamePanel.getPlayer().screenYCoordinate + gamePanel.getPlayer().getWorldYCoordinate();

                        graphics2D.drawImage(cell.getBufferedImage(), screenXCoordinate, screenYCoordinate, gamePanel.scaledCellSize, gamePanel.scaledCellSize, null);

                        columnIndex++;

                        if(columnIndex == gamePanel.worldColumns) {

                                columnIndex = 0;
                                rowIndex++;
                        }
                }
        }

        public Cell[][] getCells() {

                return (this.cells);
        }
}
