/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import JudgeModule.JudgeMaker;
import MoverModule.Mover;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Map {
    private Labyrinth _labyrinth;
    private Mover _mover;
    
    public Map(){
        _labyrinth = new Labyrinth();
        JudgeMaker jm = new JudgeMaker();
        _mover = new Mover(_labyrinth,jm);
    }
    
    public Pair getDefaultPacmanPos(){
        return _labyrinth.GetDefaultPacmanPos();
    }
    
    public Labyrinth GetLabyrinth(){
        return _labyrinth;
    }
    
    public void AddPacman(/*Player player*/){
        
    }
    
    public void DelPacman(int id){
        // КАК-ТО ИЗ ЛАБИРИНТА ВЫТАЩИТЬ АКТИВНЫЙ СПИСОК И ПО НЕМУ НАЙТИ ПАКМАНА С НУЖНЫМ ID
    }
    
    public void AddGhost(GhostCellObject ghostCellObject){
        // А КАК? А ХЕР ЗНАЕТ!
    }
    
    public void DelGhost(int id){
        // КАК-ТО ИЗ ЛАБИРИНТА ВЫТАЩИТЬ АКТИВНЫЙ СПИСОК И ПО НЕМУ НАЙТИ ПРИЗРАКА C НУЖНЫМ ID
    }
    
            
}
