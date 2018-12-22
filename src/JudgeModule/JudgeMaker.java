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
    private ArrayList<Rule> _rules;
    
    public JudgeMaker(){
        _rules = new ArrayList<Rule>();
        
        _rules.add(new Rule(CellObjectType.EmptyCellObject, CellObjectType.PacmanObject, RuleResultType.PassiveBack));
        _rules.add(new Rule(CellObjectType.EmptyCellObject, CellObjectType.GhostObject, RuleResultType.PassiveBack));
        
        _rules.add(new Rule(CellObjectType.WallObject, CellObjectType.PacmanObject,RuleResultType.SavePositions));
        _rules.add(new Rule(CellObjectType.WallObject, CellObjectType.GhostObject,RuleResultType.SavePositions));
        
        _rules.add(new Rule(CellObjectType.PacmanObject, CellObjectType.GhostObject,RuleResultType.OldDestroyed));
        
        _rules.add(new Rule(CellObjectType.GhostObject, CellObjectType.PacmanObject,RuleResultType.NewDestroyed));
        
        _rules.add(new Rule(CellObjectType.FoodObject, CellObjectType.PacmanObject,RuleResultType.OldDestroyed));
        _rules.add(new Rule(CellObjectType.FoodObject, CellObjectType.GhostObject,RuleResultType.PassiveBack));
    }
    
    public RuleResultType CheckRules(CellObjectType passive, CellObjectType active){
        for(int i = 0; i < _rules.size(); i++){
            if(_rules.get(i).IsMakeable(passive, active)){
                if(_rules.get(i).GetRuleResult()==RuleResultType.NoRule)
                    continue;
                
                return _rules.get(i).GetRuleResult();     
            }
        }
        return RuleResultType.NoRule;
    }
}
