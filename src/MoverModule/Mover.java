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
    private StupidAlgorithm _stupidAlgorithm;
    
    public Mover(Labyrinth labyrinth, JudgeMaker judgeMaker){
        _labyrinth = labyrinth;
        _judgeMaker = judgeMaker;
        
        _pacmanAlgorithm = new PacmanAlgorithm(labyrinth);
        _stupidAlgorithm = new StupidAlgorithm(labyrinth);
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
    
    private void MoveGhosts(ArrayList<CellObject> ghosts){
        for (int i = 0; i < ghosts.size(); i++){
            if(ghosts.get(i).GetCellObjectType()==CellObjectType.GhostObject){
                CreatureCellObject ghost = (CreatureCellObject)ghosts.get(i);
                _stupidAlgorithm.SetMovingObject(ghost);
                MoveType nextMove = _stupidAlgorithm.CalcNextMove(null);
                Pair neighbour = new Pair(0,0);
                RuleResultType ruleResult = RuleResultType.NoRule;
                switch (nextMove){
                    case NoMove:
                        break;
                    case TopMove:
                        neighbour = new Pair(((int)ghost.GetPosition().getKey())-1,ghost.GetPosition().getValue());
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);  
                        break;
                    case BottomMove:
                        neighbour = new Pair(((int)ghost.GetPosition().getKey())+1,ghost.GetPosition().getValue());
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);                       
                        break;
                    case LeftMove:
                        neighbour = new Pair(ghost.GetPosition().getKey(),(int)(ghost.GetPosition().getValue())-1);
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);                       
                        break;
                    case RightMove:
                        neighbour = new Pair(ghost.GetPosition().getKey(),(int)(ghost.GetPosition().getValue())+1);
                        ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);                       
                        break;
                }
                if (ruleResult==RuleResultType.SavePositions){
                    continue;
                }
                else if (ruleResult == RuleResultType.OldDestroyed){
                    ghost.SetPosition(neighbour);
                }
                else if (ruleResult == RuleResultType.PassiveBack){
                    ghost.SetPosition(neighbour);
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
        MoveGhosts(objects);
    }
}
