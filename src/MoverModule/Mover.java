/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoverModule;
import Enums.CellObjectType;
import Enums.MoveType;
import Enums.RuleResultType;
import JudgeModule.JudgeMaker;
import MapModule.Labyrinth;
import MapModule.CellObject;
import MapModule.CreatureCellObject;
import java.util.ArrayList;
import java.util.UUID;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Mover {
    private JudgeMaker _judgeMaker;
    private Labyrinth _labyrinth;
    private PacmanAlgorithm _pacmanAlgorithm;
    
    public Mover(Labyrinth labyrinth, JudgeMaker judgeMaker){
        _labyrinth = labyrinth;
        _judgeMaker = judgeMaker;
        
        _pacmanAlgorithm = new PacmanAlgorithm(labyrinth);
    }
    
    private void MovePacmans(ArrayList<CellObject> pacmans){
        for (int i = 0; i < pacmans.size(); i++){
            if(pacmans.get(i).GetCellObjectType()==CellObjectType.PacmanObject){
                CreatureCellObject pacman = (CreatureCellObject)pacmans.get(i);
                _pacmanAlgorithm.SetMovingObject(pacman);
                MoveType nextMove = _pacmanAlgorithm.CalcNextMove(null);
                Pair neighbour = new Pair(0,0);
                RuleResultType ruleResult = RuleResultType.NoRule;
                switch (nextMove){
                    case NoMove:
                        break;
                    case TopMove:
                        neighbour = new Pair(((int)pacman.GetPosition().getKey())-1,pacman.GetPosition().getValue());
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);  
                        break;
                    case BottomMove:
                        neighbour = new Pair(((int)pacman.GetPosition().getKey())+1,pacman.GetPosition().getValue());
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);                       
                        break;
                    case LeftMove:
                        neighbour = new Pair(pacman.GetPosition().getKey(),(int)(pacman.GetPosition().getValue())-1);
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);                       
                        break;
                    case RightMove:
                        neighbour = new Pair(pacman.GetPosition().getKey(),(int)(pacman.GetPosition().getValue())+1);
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);                       
                        break;
                }
                if (ruleResult==RuleResultType.SavePositions){
                    continue;
                }
                else if (ruleResult == RuleResultType.OldDestroyed){
                    pacman.SetPosition(neighbour);
                }
                else if (ruleResult == RuleResultType.PassiveBack){
                    pacman.SetPosition(neighbour);
                }
                else if(ruleResult == RuleResultType.NewDestroyed){
                    //FUCK
                }
                continue;
            }
        }
    }
    
    public void MoveAll(ArrayList<CellObject> objects){
        MovePacmans(objects);
    }
    
    private void MoveGhosts(ArrayList<CellObject> ghosts){
        
    }
    
    public void MovePacman(UUID idPacman, MoveType direction){
        Pair position= _labyrinth.GetPosition(idPacman);
        Pair nextPosition = null;
        switch (direction){
            case LeftMove:
                nextPosition = new Pair((int)position.getKey(),((int)position.getValue())-1);
                break;
            case RightMove:
                nextPosition = new Pair((int)position.getKey(),((int)position.getValue())+1);
                break;
            case TopMove:
                nextPosition = new Pair(((int)position.getKey())-1,position.getValue());
                break;
            case BottomMove:
                nextPosition = new Pair(((int)position.getKey())+1,position.getValue());
                break;
        }
        
        CellObject nextCell = _labyrinth.GetCell(nextPosition);
        
        RuleResultType res = _judgeMaker.CheckRules(CellObjectType.PacmanObject, nextCell.GetCellObjectType());
        
        switch (res){
            //case Old
        }
    }
    
    public void MoveGhost(){
        // TODO
    }
}
