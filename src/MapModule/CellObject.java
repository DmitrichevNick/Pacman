/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;

/**
 *
 * @author August
 */
public class CellObject {
    private CellObjectType _cellObjectType;
    
    public CellObject(){
        _cellObjectType = CellObjectType.EmptyCellObject;
    }
    
    public CellObject(CellObjectType cellObjectType){
        SetCellObjectType(cellObjectType);
    }
    public boolean IsActive(){
        return _cellObjectType == CellObjectType.PacmanObject ||
                _cellObjectType == CellObjectType.GhostObject;
    }
    
    public void SetCellObjectType(CellObjectType cellObjectType){
        _cellObjectType = cellObjectType;
    }
    
    public CellObjectType GetCellObjectType(){
        return _cellObjectType;
    }
}
