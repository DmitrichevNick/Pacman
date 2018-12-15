/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JudgeModule;

import Enums.CellObjectType;
import Enums.RuleResultType;
import java.util.ArrayList;

/**
 *
 * @author August
 */
public class JudgeMaker {
    private ArrayList<IRule> _rules;
    
    public void AddRule(IRule rule){
        _rules.add(rule);
    }
    
    public void RemoveRule(IRule rule){
        _rules.remove(rule);
    }
    
    public void ClearRules(){
        _rules.clear();
    }
    
    public RuleResultType CheckRules(CellObjectType passiveCell, CellObjectType activeCell){
        RuleResultType result = RuleResultType.SavePositions;
        
        if (_rules.contains(new PacmanGhostRule()) && (passiveCell == CellObjectType.GhostObject && activeCell == CellObjectType.PacmanObject ||
                passiveCell == CellObjectType.PacmanObject && activeCell == CellObjectType.GhostObject))
            result = (new PacmanGhostRule()).checkRule(passiveCell, activeCell);
        else if (_rules.contains(new PacmanGhostRule()) && (passiveCell == CellObjectType.GhostObject && activeCell == CellObjectType.PacmanObject ||
                passiveCell == CellObjectType.PacmanObject && activeCell == CellObjectType.GhostObject))
            result = (new PacmanWallRule()).checkRule(passiveCell, activeCell);
        
        return result;
    }
}
