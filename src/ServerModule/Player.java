/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerModule;

import Enums.CellObjectType;
import MapModule.CreatureCellObject;
import MapModule.Position;
import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author August
 */
public class Player implements Serializable {
    private CreatureCellObject _creatureCellObject;
    
    public Player(UUID id, Position position){
        _creatureCellObject = new CreatureCellObject(CellObjectType.PacmanObject);
        _creatureCellObject.SetId(id);
        _creatureCellObject.SetPosition(position);
    }
    
    
    public Position GetPosition(){
        return _creatureCellObject.GetPosition();
    }
    
    public CreatureCellObject GetPacman(){
        return _creatureCellObject;
    }
}
