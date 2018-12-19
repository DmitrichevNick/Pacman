/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerModule;

import MapModule.PacmanCellObject;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Player {
    private int _id;
    private PacmanCellObject _pacman;
    
    public Player(int id, Pair position){
        _id = id;
        _pacman = new PacmanCellObject(id, position);
    }
    
    public Pair GetPosition(){
        return _pacman.GetPosition();
    }
}
