/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class PacmanCellObject extends CellObject {
    private int _ownerId;
    private Pair _position;
    
    public PacmanCellObject(int ownerId, Pair position){
        super.SetCellObjectType(CellObjectType.PacmanObject);
        _ownerId = ownerId;
        _position = position;
    }
    
    public void SetPosition(Pair position){
        _position = position;
    }
    
    public Pair GetPosition(){
        return _position;
    }
    
    public void SetOwnerId(int ownerId){
        _ownerId = ownerId;
    }
    
    public int GetOwnerId(){
        return _ownerId;
    }
}
