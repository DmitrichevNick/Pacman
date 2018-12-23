/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import MapModule.IChangeable;
import java.io.InputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Arno-
 */
public class Grid {

    protected static CellView[][] grid;

    public Grid() {

        grid = new CellView[Enums.PacmansParameter.ROW_COUNT][Enums.PacmansParameter.COLUMN_COUNT];
    }

    public static void addCell(CellView cell) {
        grid[cell.position.GetX()][cell.position.GetY()] = cell;

    }

    public static CellView getCell(int row, int column) {

        return grid[row][column];

    }
}
