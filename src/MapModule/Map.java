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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author August
 */
public class Map  implements Serializable{
    private Labyrinth _labyrinth;
    private Mover _mover;
    private ArrayList<IChangeable> _activeCells;
    
    
    public Map() throws IOException{
        _labyrinth = new Labyrinth("D:\\Test\\laby.txt");
        JudgeMaker jm = new JudgeMaker();
        _mover = new Mover(_labyrinth,jm);
        _activeCells = new ArrayList<>();
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
    
    public ArrayList<IChangeable> GetActiveObjects(){
        return _activeCells;
    }
    
    public void Refresh(){
        ArrayList<CellObject> objects = new ArrayList<>();
        
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
    
    public void DelPacman(UUID id){
        CreatureCellObject pacman = null;
        for(IChangeable obj : _activeCells){
            if(obj.GetCellObject().GetCellObjectType()==CellObjectType.PacmanObject){
                if(((CreatureCellObject)obj.GetCellObject()).GetId()==id){
                    pacman = (CreatureCellObject)obj.GetCellObject();
                    break;
                }
            }
        }
        if(pacman!=null)
            _activeCells.remove(pacman);
    }
    
    public void AddGhost(CreatureCellObject creatureCellObject){
        _activeCells.add(creatureCellObject);
    }
    
    public void DelGhost(UUID id){
        CreatureCellObject ghost = null;
        for(IChangeable obj : _activeCells){
            if(obj.GetCellObject().GetCellObjectType()==CellObjectType.GhostObject){
                if(((CreatureCellObject)obj.GetCellObject()).GetId()==id){
                    ghost = (CreatureCellObject)obj.GetCellObject();
                    break;
                }
            }
        }
        if(ghost!=null)
            _activeCells.remove(ghost);
    }         
}
