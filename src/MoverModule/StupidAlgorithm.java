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
import MapModule.Position;
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
        Position neighbour = new Position(_creatureCellObject.GetPosition().GetX()-1,_creatureCellObject.GetPosition().GetY());
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.TopMove);
        }
        //BOT
        neighbour = new Position(_creatureCellObject.GetPosition().GetX()+1,_creatureCellObject.GetPosition().GetY());
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.BottomMove);
        }
        //LEFT
        neighbour = new Position(_creatureCellObject.GetPosition().GetX(),_creatureCellObject.GetPosition().GetY()-1);
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.LeftMove);
        }
        //RIGHT
        neighbour = new Position(_creatureCellObject.GetPosition().GetX(),_creatureCellObject.GetPosition().GetY()+1);
        if(_layrinth.GetCell(neighbour).GetCellObjectType()!=CellObjectType.WallObject){
            dirs.add(MoveType.RightMove);
        }
        
        Random random = new Random();
        return dirs.get(random.nextInt(dirs.size()));
    }
    
}
