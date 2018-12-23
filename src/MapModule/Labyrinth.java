/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author August
 */
public class Labyrinth {
    private int _width;
    private int _height;

    private CellObject[][] _objectsMatrix;
    private ArrayList<CellObject> _activeObjects;
    
    public int GetWidth(){
        return _width;
    }
    
    public int GetHeight(){
        return _height;
    }
    // Дефолтная фигня. Квадрат 20 на 20
    public Labyrinth(){
        _width = Enums.PacmansParameter.ROW_COUNT;
        _height = Enums.PacmansParameter.COLUMN_COUNT;
        _objectsMatrix = new CellObject[_height][_width];
        
        // Делаю все пустым
        for(int i = 0; i < _height; i++){
            for (int j = 0; j < _width; j++){
                _objectsMatrix[i][j] = new EmptyCellObject();
            }
        }
        
        for (int i = 0; i < _width; i++){
            _objectsMatrix[0][i] = new WallCellObject();
            _objectsMatrix[_height-1][i] = new WallCellObject();
        }
        
        for (int i = 0; i < _height; i++){
            _objectsMatrix[i][0] = new WallCellObject();
            _objectsMatrix[i][_width - 1] = new WallCellObject();
        }
    }
    
    public Position GetDefaultPacmanPos(){
        return new Position(5,10);
    }
    
    public void SetCell(Position position, CellObject cellObject){
        _objectsMatrix[position.GetY()][position.GetX()] = cellObject; 
    }
    
    public CellObject GetCell(Position position){
        return _objectsMatrix[position.GetY()][position.GetX()]; 
    }    
}
