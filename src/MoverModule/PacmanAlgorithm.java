/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoverModule;

import Enums.MoveType;
import MapModule.Labyrinth;
import MapModule.CreatureCellObject;
import java.util.ArrayList;

/**
 *
 * @author August
 */
public class PacmanAlgorithm implements IAlgorithm{
    private Labyrinth _layrinth;
    private CreatureCellObject _creatureCellObject;
    
    public PacmanAlgorithm(Labyrinth labyrinth){
        _layrinth = labyrinth;
    }
    
    public void SetMovingObject(CreatureCellObject creatureCellObject){
        _creatureCellObject = creatureCellObject;
    } 
    
    
    @Override
    public MoveType CalcNextMove(ArrayList<CreatureCellObject> activeObjects) {
        return _creatureCellObject.GetNextDir();
    } 
}
