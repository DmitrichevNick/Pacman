/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Labyrinth {
    private int _width;
    private int _height;
    private Pair _defaultPacmanPosition;
    private CellObject[][] _objectsMatrix;
    private ArrayList<CellObject> _activeObjects;
    
    // Деволтная фигня. Квадрат 20 на 20
    public Labyrinth(){
        _width = 20;
        _height = 20;
        _objectsMatrix = new CellObject[_width][_height];
        _defaultPacmanPosition = new Pair(10,10);
        
        for(int i = 0; i < _width; i++){
            for (int j = 0; j < _height; j++){
                _objectsMatrix[i][j] = new FoodCellObject();
            }
        }
        
        for (int i = 0; i < _width; i++){
            _objectsMatrix[i][0] = new WallCellObject();
            _objectsMatrix[i][_height-1] = new WallCellObject();
        }
        
        for (int i = 0; i < _height; i++){
            _objectsMatrix[0][i] = new WallCellObject();
            _objectsMatrix[_width-1][i] = new WallCellObject();
        }
    }
    
    public Pair GetDefaultPacmanPos(){
        return _defaultPacmanPosition;
    }
    
    public void SetCell(Pair position, CellObject cellObject){
        _objectsMatrix[(int)position.getKey()][(int)position.getValue()] = cellObject; 
    }
    
    public CellObject GetCell(Pair position){
        return _objectsMatrix[(int)position.getKey()][(int)position.getValue()]; 
    }
    
    public Pair GetPosition(int id){
        for(int i = 0 ; i < _objectsMatrix.length; i++){
            for (int j = 0; j < _objectsMatrix[0].length; j++){
                if(_objectsMatrix[i][j].GetCellObjectType()==CellObjectType.PacmanObject){
                    PacmanCellObject pacman = (PacmanCellObject)_objectsMatrix[i][j];
                    if(pacman.GetOwnerId()==id){
                        return new Pair(i,j);
                    }
                } 
                else if (_objectsMatrix[i][j].GetCellObjectType()==CellObjectType.GhostObject){
                    GhostCellObject ghost = (GhostCellObject)_objectsMatrix[i][j];
                    if(ghost.GetId()==id){
                        return new Pair(i,j);
                    }
                }
            }
        }
        return new Pair(null,null);
    }   
}
