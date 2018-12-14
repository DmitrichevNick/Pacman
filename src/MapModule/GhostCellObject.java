/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.GhostType;

/**
 *
 * @author August
 */
public class GhostCellObject extends CellObject{
    private GhostType _ghostType;
    private int _id;
    
    public void SetGhostType(GhostType ghostType){
        _ghostType = ghostType;
    }
    
    public GhostType GetGhostType(){
        return _ghostType;
    }
    
    public void SetId(int id){
        _id = id;
    }
    
    public int GetId(){
        return _id;
    }
}
