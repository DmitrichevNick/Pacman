/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerModule;

import Enums.CellObjectType;
import MapModule.CreatureCellObject;
import java.util.UUID;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Player {
    private CreatureCellObject _creatureCellObject;
    
    public Player(UUID id, Pair position){
        _creatureCellObject = new CreatureCellObject(CellObjectType.PacmanObject);
        _creatureCellObject.SetId(id);
        _creatureCellObject.SetPosition(position);
    }
    
    public Pair GetPosition(){
        return _creatureCellObject.GetPosition();
    }
    
    public CreatureCellObject GetPacman(){
        return _creatureCellObject;
    }
}
