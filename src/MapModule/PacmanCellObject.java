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
public class PacmanCellObject extends CellObject {
    private int _ownerId;
    
    public PacmanCellObject(int ownerId){
        super.SetCellObjectType(CellObjectType.PacmanObject);
        _ownerId = ownerId;
    }
    
    public void SetOwnerId(int ownerId){
        _ownerId = ownerId;
    }
    
    public int GetOwnerId(){
        return _ownerId;
    }
}
