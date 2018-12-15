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
    private CellObject[][] _objectsMatrix;
    private ArrayList<Pair> _activeCells;
    
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
