/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoverModule;

import Enums.CellObjectType;
import Enums.MoveType;
import MapModule.CreatureCellObject;
import MapModule.Labyrinth;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class StupidAlgorithm implements IAlgorithm{
    private Labyrinth _layrinth;
    private CreatureCellObject _creatureCellObject;
    
    public StupidAlgorithm(Labyrinth labyrinth){
        _layrinth = labyrinth;
    }
    
    @Override
    public void SetMovingObject(CreatureCellObject creatureCellObject){
        _creatureCellObject = creatureCellObject;
    } 
    
    @Override
    public MoveType CalcNextMove(ArrayList<CreatureCellObject> activeObjects) {
        ArrayList<MoveType> dirs = new ArrayList<MoveType>();
        //TOP
        Pair neighbour = new Pair((int)(_creatureCellObject.GetPosition().getKey())-1,_creatureCellObject.GetPosition().getValue());
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.TopMove);
        }
        //BOT
        neighbour = new Pair((int)(_creatureCellObject.GetPosition().getKey())+1,_creatureCellObject.GetPosition().getValue());
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.BottomMove);
        }
        //LEFT
        neighbour = new Pair(_creatureCellObject.GetPosition().getKey(),(int)(_creatureCellObject.GetPosition().getValue())-1);
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.LeftMove);
        }
        //RIGHT
        neighbour = new Pair(_creatureCellObject.GetPosition().getKey(),(int)(_creatureCellObject.GetPosition().getValue())+1);
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.RightMove);
        }
        
        Random random = new Random();
        return dirs.get(random.nextInt(dirs.size()));
    }
    
}
