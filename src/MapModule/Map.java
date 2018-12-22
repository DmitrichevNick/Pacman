/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import JudgeModule.JudgeMaker;
import MoverModule.Mover;
import ServerModule.Player;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Map {
    private Labyrinth _labyrinth;
    private Mover _mover;
    private ArrayList<IChangeable> _activeCells;
    
    public Map(){
        _labyrinth = new Labyrinth();
        JudgeMaker jm = new JudgeMaker();
        _mover = new Mover(_labyrinth,jm);
        _activeCells = new ArrayList<IChangeable>();
    }
    
    public Pair getDefaultPacmanPos(){
        return _labyrinth.GetDefaultPacmanPos();
    }
    
    public ArrayList<IChangeable> GetActiveObjects(){
        return _activeCells;
    }
    
    public void Refresh(){
        ArrayList<CellObject> objects = new ArrayList<CellObject>();
        for(int  i =0; i< _activeCells.size();i++){
            objects.add(_activeCells.get(i).GetCellObject());
        }
        _mover.MoveAll(objects);
    }
    
    public Labyrinth GetLabyrinth(){
        return _labyrinth;
    }
    
    public void AddPacman(Player player){
        _activeCells.add(player.GetPacman());
    }
    
    public void DelPacman(int id){
        // КАК-ТО ИЗ ЛАБИРИНТА ВЫТАЩИТЬ АКТИВНЫЙ СПИСОК И ПО НЕМУ НАЙТИ ПАКМАНА С НУЖНЫМ ID
    }
    
    public void AddGhost(CreatureCellObject creatureCellObject){
        _activeCells.add(creatureCellObject);
    }
    
    public void DelGhost(int id){
        // КАК-ТО ИЗ ЛАБИРИНТА ВЫТАЩИТЬ АКТИВНЫЙ СПИСОК И ПО НЕМУ НАЙТИ ПРИЗРАКА C НУЖНЫМ ID
    }
    
            
}
