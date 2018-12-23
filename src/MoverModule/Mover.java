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
import MapModule.FoodCellObject;
import MapModule.IChangeable;
import MapModule.Position;
import java.util.ArrayList;

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
    
    private IChangeable GetActiveNeighbour(Position position, ArrayList<IChangeable> creatures){
        IChangeable result = null;
        for(int i = 0; i< creatures.size();i++){
            IChangeable yep = (IChangeable)creatures.get(i);
            if (yep!=null){
                if(yep.GetPosition().GetX()==position.GetX() && yep.GetPosition().GetY() == position.GetY())
                    result = yep;
            }
        }
        return result;
    }
    
    private void MovePacmans(ArrayList<IChangeable> pacmans){
        for (int i = 0; i < pacmans.size(); i++){
            if(pacmans.get(i).GetCellObject().GetCellObjectType()==CellObjectType.PacmanObject){
                CreatureCellObject pacman = (CreatureCellObject)pacmans.get(i);
                if(pacman.GetDeadStatus())
                    continue;
                IAlgorithm algorithm = AlgorithmMaker(pacman);
                algorithm.SetMovingObject(pacman);
                MoveType nextMove = algorithm.CalcNextMove(null);
                Position neighbour = new Position(0,0);
                RuleResultType ruleResult = RuleResultType.NoRule;

                CreatureCellObject creature= null;
                switch (nextMove){
                    case NoMove:
                        break;
                    case TopMove:
                        neighbour = new Position(pacman.GetPosition().GetX(),pacman.GetPosition().GetY()-1);
                        ruleResult = GetRuleResult(neighbour, pacmans);
                        break;
                    case BottomMove:
                        neighbour = new Position(pacman.GetPosition().GetX(),pacman.GetPosition().GetY()+1);
                        ruleResult = GetRuleResult(neighbour, pacmans); 
                        break;
                    case LeftMove:
                        neighbour = new Position(pacman.GetPosition().GetX()-1,pacman.GetPosition().GetY());
                        ruleResult = GetRuleResult(neighbour, pacmans);                    
                        break;
                    case RightMove:
                        neighbour = new Position(pacman.GetPosition().GetX()+1, pacman.GetPosition().GetY());
                        ruleResult = GetRuleResult(neighbour, pacmans);                     
                        break;
                }
                if (ruleResult==RuleResultType.SavePositions){
                    continue;
                }
                else if (ruleResult == RuleResultType.OldDestroyed){
                    for(int k =0; k < pacmans.size(); k++){
                        if(pacmans.get(k).GetPosition().GetX() == neighbour.GetX() && pacmans.get(k).GetPosition().GetY() == neighbour.GetY()){
                            //FoodCellObject food = (FoodCellObject)pacmans.get(k).GetCellObject();
                            pacmans.remove(pacmans.get(k));
                            //food = null;
                            break;
                        }                                   
                    }
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
    
    private RuleResultType GetRuleResult(Position neighbour, ArrayList<IChangeable> pacmans){
        RuleResultType ruleResult = null;
        IChangeable creature = GetActiveNeighbour(neighbour, pacmans);
        if(creature!=null)
            ruleResult = _judgeMaker.CheckRules(creature.GetCellObject().GetCellObjectType(),CellObjectType.PacmanObject);  
        else
            ruleResult = _judgeMaker.CheckRules(_labyrinth.GetCell(neighbour).GetCellObjectType(),CellObjectType.PacmanObject); 
        
        return ruleResult;
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
    
    private void MoveGhosts(ArrayList<IChangeable> ghosts){
        for (int i = 0; i < ghosts.size(); i++){
            if(ghosts.get(i).GetCellObject().GetCellObjectType()==CellObjectType.GhostObject){
                CreatureCellObject ghost = (CreatureCellObject)ghosts.get(i);
                IAlgorithm algorithm = AlgorithmMaker(ghost);
                algorithm.SetMovingObject(ghost);
                MoveType nextMove = algorithm.CalcNextMove(null);
                Position neighbour = new Position(0,0);
                CreatureCellObject creature= null;
                RuleResultType ruleResult = RuleResultType.NoRule;
                switch (nextMove){
                    case NoMove:
                        break;
                    case TopMove:
                        neighbour = new Position(ghost.GetPosition().GetX()-1,ghost.GetPosition().GetY());
                        ruleResult = GetRuleResult(neighbour, ghosts);
                        break;
                    case BottomMove:
                        neighbour = new Position(ghost.GetPosition().GetX()+1,ghost.GetPosition().GetY());
                        ruleResult = GetRuleResult(neighbour, ghosts); 
                        break;
                    case LeftMove:
                        neighbour = new Position(ghost.GetPosition().GetX(),ghost.GetPosition().GetY()-1);
                        ruleResult = GetRuleResult(neighbour, ghosts);                     
                        break;
                    case RightMove:
                        neighbour = new Position(ghost.GetPosition().GetX(), ghost.GetPosition().GetY()+1);
                        ruleResult = GetRuleResult(neighbour, ghosts);                     
                        break;
                }
                if (ruleResult==RuleResultType.SavePositions){
                    continue;
                }
                else if (ruleResult == RuleResultType.OldDestroyed){
                    for(int j =0; j<ghosts.size();j++){
                        if(ghosts.get(j).GetCellObject().GetCellObjectType()!=CellObjectType.FoodObject){
                            CreatureCellObject obj = (CreatureCellObject)ghosts.get(j);
                            if(obj.GetCellObjectType()==CellObjectType.PacmanObject){
                                if(obj.GetPosition()==neighbour){
                                    obj.SetDeadStatus(true);
                                }
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
    
    public void MoveAll(ArrayList<IChangeable> objects){
        MovePacmans(objects);
        MoveGhosts(objects);
    }
}
