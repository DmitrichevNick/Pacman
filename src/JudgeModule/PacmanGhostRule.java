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
public class PacmanGhostRule implements IRule{

    @Override
    public RuleResultType checkRule(CellObjectType passiveObject, CellObjectType activeObject) {
        RuleResultType result = RuleResultType.SavePositions;
        
        if(activeObject == CellObjectType.GhostObject)
            result = RuleResultType.OldDestroyed;
        else if (activeObject == CellObjectType.PacmanObject)
            result = RuleResultType.NewDestroyed;
        
        return result;
    }
    
}
