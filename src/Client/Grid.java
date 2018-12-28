/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Enums.CellObjectType;
import MapModule.IChangeable;
import MapModule.Position;
import java.util.ArrayList;

/**
 *
 * @author Arno-
 */
public class Grid {

    protected static CellView[][] grid;
    private int _row = Enums.PacmansParameter.ROW_COUNT;
    private int _column = Enums.PacmansParameter.COLUMN_COUNT;

    public Grid() {
        
        grid = new CellView[_row][_column];
    }
 
    public Grid(int countRow, int countColumn) {
        _row = countRow;
        _column = countColumn;
        grid = new CellView[_row][_column];
    }

    public static void addCell(CellView cell) {
        grid[cell.position.GetY()][cell.position.GetX()] = cell;
    }
    
    public static void delCell(int i, int j) {
        grid[i][j] = null;
    }
        
    public void ClearActiveObjectCell() {
        for (int i = 0; i < _row; i++) 
        {
            for (int j = 0; j < _column; j++) 
            {
                if (grid[i][j]!=null && grid[i][j].getType() !=  CellObjectType.WallObject && grid[i][j].getType() !=  CellObjectType.EmptyCellObject){
                    delCell(i,j);
                    CellView cell = new CellView(new Position(j,i), CellObjectType.EmptyCellObject);
                    addCell(cell);
                }
                else if (grid[i][j]==null){
                    CellView cell = new CellView(new Position(j,i), CellObjectType.EmptyCellObject);
                    addCell(cell);
                }
            }
        }
    }

    public static CellView getCell(int row, int column) {
        return grid[row][column];
    }
}
