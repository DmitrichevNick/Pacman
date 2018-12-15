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
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Mover {
    private JudgeMaker _judgeMaker;
    private Labyrinth _labyrinth;
    
    public void MovePacman(int idPacman, MoveType direction){
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
