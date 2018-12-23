/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoverModule;

import Enums.MoveType;
import MapModule.CreatureCellObject;
import MapModule.Labyrinth;
import java.util.ArrayList;

/**
 *
 * @author August
 */
public class RedAlgorithm implements IAlgorithm{
    private Labyrinth _labyrinth;
    
    public RedAlgorithm(Labyrinth labyrinth){
        _labyrinth = labyrinth;
    }

    @Override
    public MoveType CalcNextMove(ArrayList<CreatureCellObject> activeObjects) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetMovingObject(CreatureCellObject creatureCellObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
