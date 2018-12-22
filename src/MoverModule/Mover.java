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
    
    private CreatureCellObject GetActiveNeighbour(Pair position, ArrayList<CellObject> creatures){
        CreatureCellObject result = null;
        for(int i = 0; i< creatures.size();i++){
            CreatureCellObject yep = (CreatureCellObject)creatures.get(i);
            if (yep!=null){
                if(yep.GetPosition().getKey()==position.getKey() && yep.GetPosition().getValue() == position.getValue())
                    result = yep;
            }
        }
        return result;
    }
    
    private void MovePacmans(ArrayList<CellObject> pacmans){
        for (int i = 0; i < pacmans.size(); i++){
            if(pacmans.get(i).GetCellObjectType()==CellObjectType.PacmanObject){
                CreatureCellObject pacman = (CreatureCellObject)pacmans.get(i);
                if(pacman.GetDeadStatus())
                    continue;
                IAlgorithm algorithm = AlgorithmMaker(pacman);
                algorithm.SetMovingObject(pacman);
                MoveType nextMove = algorithm.CalcNextMove(null);
                Pair neighbour = new Pair(0,0);
                RuleResultType ruleResult = RuleResultType.NoRule;
                boolean die = false;
                CreatureCellObject creature= null;
                switch (nextMove){
                    case NoMove:
                        break;
                    case TopMove:
                        neighbour = new Pair(((int)pacman.GetPosition().getKey())-1,pacman.GetPosition().getValue());
                        creature = GetActiveNeighbour(neighbour, pacmans);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.PacmanObject);  
                        else
                            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);  
                        break;
                    case BottomMove:
                        neighbour = new Pair(((int)pacman.GetPosition().getKey())+1,pacman.GetPosition().getValue());
                        creature = GetActiveNeighbour(neighbour, pacmans);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.PacmanObject);  
                        else
                            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);  
                        break;
                    case LeftMove:
                        neighbour = new Pair(pacman.GetPosition().getKey(),(int)(pacman.GetPosition().getValue())-1);
                        creature = GetActiveNeighbour(neighbour, pacmans);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.PacmanObject);  
                        else
                            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject);                      
                        break;
                    case RightMove:
                        neighbour = new Pair(pacman.GetPosition().getKey(),(int)(pacman.GetPosition().getValue())+1);
                        creature = GetActiveNeighbour(neighbour, pacmans);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.PacmanObject);  
                        else
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
                    ((CreatureCellObject)pacman.GetCellObject()).SetDeadStatus(true);
                }
                continue;
            }
        }
    }
    
    private IAlgorithm AlgorithmMaker(CreatureCellObject creatureCellObject){
        IAlgorithm algorithm = null;
        
        switch (creatureCellObject.GetCreatureType()){
            case CommonPacman:
                algorithm = _pacmanAlgorithm;
                break;
            case StupidGhost:
                algorithm = _stupidAlgorithm;
                break;
        }
        
        return algorithm;
    }
    
    private void MoveGhosts(ArrayList<CellObject> ghosts){
        for (int i = 0; i < ghosts.size(); i++){
            if(ghosts.get(i).GetCellObjectType()==CellObjectType.GhostObject){
                CreatureCellObject ghost = (CreatureCellObject)ghosts.get(i);
                IAlgorithm algorithm = AlgorithmMaker(ghost);
                algorithm.SetMovingObject(ghost);
                MoveType nextMove = algorithm.CalcNextMove(null);
                Pair neighbour = new Pair(0,0);
                boolean die = false;
                CreatureCellObject creature= null;
                RuleResultType ruleResult = RuleResultType.NoRule;
                switch (nextMove){
                    case NoMove:
                        break;
                    case TopMove:
                        neighbour = new Pair(((int)ghost.GetPosition().getKey())-1,ghost.GetPosition().getValue());
                        creature = GetActiveNeighbour(neighbour, ghosts);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.GhostObject);  
                        else
                            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.GhostObject);    
                        break;
                    case BottomMove:
                        neighbour = new Pair(((int)ghost.GetPosition().getKey())+1,ghost.GetPosition().getValue());
                        creature = GetActiveNeighbour(neighbour, ghosts);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.GhostObject);  
                        else
                            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.GhostObject);                       
                        break;
                    case LeftMove:
                        neighbour = new Pair(ghost.GetPosition().getKey(),(int)(ghost.GetPosition().getValue())-1);
                        creature = GetActiveNeighbour(neighbour, ghosts);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.GhostObject);  
                        else
                            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.GhostObject);                       
                        break;
                    case RightMove:
                        neighbour = new Pair(ghost.GetPosition().getKey(),(int)(ghost.GetPosition().getValue())+1);
                        creature = GetActiveNeighbour(neighbour, ghosts);
                        if(creature!=null)
                            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.GhostObject);  
                        else
                            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.GhostObject);                         
                        break;
                }
                if (ruleResult==RuleResultType.SavePositions){
                    continue;
                }
                else if (ruleResult == RuleResultType.OldDestroyed){
                    //((CreatureCellObject)ghost.GetCellObject()).SetDeadStatus(true);
                    //((CreatureCellObject)_labyrinth.GetCell(neighbour)).SetDeadStatus(true);
                    
                    for(int j =0; j<ghosts.size();j++){
                        CreatureCellObject obj = (CreatureCellObject)ghosts.get(j);
                        if(obj.GetCellObjectType()==CellObjectType.PacmanObject){
                            if(obj.GetPosition()==neighbour){
                                obj.SetDeadStatus(true);
                            }
                        }       
                    }
                    
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
