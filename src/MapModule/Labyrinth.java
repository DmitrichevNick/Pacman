/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.naming.Reference;

/**
 *
 * @author August
 */
public class Labyrinth {
    private CellObject[][] _objectsMatrix;
    private ArrayList<Pair> _activeCells;
    
    public void SetCell(Pair position, CellObject cellObject){
        _objectsMatrix[(int)position.getKey()][(int)position.getValue()] = cellObject; 
    }
    
    public CellObject SetCell(Pair position){
        return _objectsMatrix[(int)position.getKey()][(int)position.getValue()]; 
    }
}
