/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import JudgeModule.JudgeMaker;
import MoverModule.Mover;
import ServerModule.Player;
import java.io.IOException;
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
    
    
    public Map() throws IOException{
       // _labyrinth = new Labyrinth();
        _labyrinth = new Labyrinth("D:\\Test\\laby.txt");
        JudgeMaker jm = new JudgeMaker();
        _mover = new Mover(_labyrinth,jm);
        _activeCells = new ArrayList<IChangeable>();
        GenerateFood();
    }
    
    private void GenerateFood(){
        for (int i = 0; i < _labyrinth.GetHeight(); i++){
            for (int j = 0; j < _labyrinth.GetWidth(); j++){
                Position position = new Position(j,i);
                if(_labyrinth.GetCell(position).GetCellObjectType()==CellObjectType.EmptyCellObject){
                    FoodCellObject foodCell = new FoodCellObject();
                    foodCell.SetPosition(position);
                    _activeCells.add(foodCell);
                }
            }
        }
    }
    
    public Position getDefaultPacmanPos(){
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
        _mover.MoveAll(_activeCells);
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
