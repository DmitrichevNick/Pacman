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
public class NothingRule implements IRule{

    public NothingRule(){
        
    }
    @Override
    public RuleResultType checkRule(CellObjectType passiveObject, CellObjectType activeObject) {
        if(passiveObject==CellObjectType.EmptyCellObject)
            return RuleResultType.OldDestroyed;
        
        return RuleResultType.NoRule;
    }
    
}
