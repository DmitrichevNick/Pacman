/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JudgeModule;
import Enums.CellObjectType;
import Enums.RuleResultType;

/**
 *
 * @author August
 */
public class Rule {
    private CellObjectType _passive;
    private CellObjectType _active;
    private RuleResultType _result;
    
    public Rule(CellObjectType passive, CellObjectType active, RuleResultType result){
        _passive = passive;
        _active = active; 
        _result = result;
    }
    
    public boolean IsMakeable(CellObjectType passive, CellObjectType active){
        return passive==_passive && active == _active;
    }
    
    public RuleResultType GetRuleResult(){
        return _result;
    }
}
