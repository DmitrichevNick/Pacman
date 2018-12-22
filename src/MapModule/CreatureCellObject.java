/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import Enums.MoveType;
import java.util.UUID;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class CreatureCellObject extends CellObject implements IChangeable{
    private Pair _position;
    private UUID _uuid; 
    private MoveType _currentDir;
    private MoveType _nextDir;
    
    public CreatureCellObject(CellObjectType cellObjectType){
        super.SetCellObjectType(cellObjectType);
        _currentDir = MoveType.NoMove;
        _nextDir = MoveType.NoMove;
        
        if(cellObjectType == CellObjectType.GhostObject){
            _uuid = UUID.randomUUID();
        }
    } 
    
    public UUID GetId(){
        return _uuid;
    }
    
    public void SetId(UUID uuid){
        if(super.GetCellObjectType()==CellObjectType.PacmanObject){
            _uuid = uuid;
        }
    }
    
    @Override
    public Pair GetPosition() {
        return _position;
    }

    @Override
    public void SetPosition(Pair position) {
        _position = position;
    }

    @Override
    public CellObject GetCellObject() {
        return this;
    }

    public void SetCurDir(MoveType moveType) {
        _currentDir = moveType;
    }

    public void SetNextDir(MoveType moveType) {
        _nextDir = moveType;
    }

    public MoveType GetCurDir() {
        return _currentDir;
    }

    public MoveType GetNextDir() {
        return _nextDir;
    }
}
