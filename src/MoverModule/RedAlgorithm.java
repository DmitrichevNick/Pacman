/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoverModule;

import Enums.CellObjectType;
import Enums.MoveType;
import MapModule.CreatureCellObject;
import MapModule.Labyrinth;
import MapModule.Position;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author August
 */


public class RedAlgorithm implements IAlgorithm{
    private Labyrinth _labyrinth;
    private CreatureCellObject _creatureCellObject;
    
    private class Node{
        int _length;
        Position _position;
        
        private Node(int length, Position pos){
            _length = length;
            _position = pos;
        }
    }
    
    public RedAlgorithm(Labyrinth labyrinth){
        _labyrinth = labyrinth;
    }

    @Override
    public MoveType CalcNextMove(ArrayList<CreatureCellObject> activeObjects) {
        Position ghostPos = new Position(_creatureCellObject.GetPosition());
        ArrayList<Position> pacmans = new ArrayList<>();
        ArrayList<boolean[][]> visitedArray = new ArrayList<>();
        ArrayList<Queue<Position>> queueVector = new ArrayList<>();
        
        for(int i = 0; i < activeObjects.size(); i++){
            if(activeObjects.get(i).GetCellObject().GetCellObjectType()==CellObjectType.PacmanObject && !activeObjects.get(i).GetDeadStatus()){
                pacmans.add(new Position(activeObjects.get(i).GetPosition()));
                
                boolean[][] visited = new boolean[_labyrinth.GetHeight()][_labyrinth.GetWidth()];
                for(int j = 0; j < _labyrinth.GetHeight(); j++){
                    for(int z = 0; z < _labyrinth.GetWidth(); z++)
                        visited[j][z] = false;
                }
                visitedArray.add(visited);
                
                Queue<Position> curQuere = new LinkedList<Position>();
                curQuere.add(new Position(activeObjects.get(i).GetPosition()));
                queueVector.add(curQuere);
            }           
        }        
        
        boolean exit = false;
        Position pos = null;
        
        int colNum[] = {-1, 0, 0, 1}; 
        int rowNum[] = {0, -1, 1, 0}; 
        
        while(!exit){
            if(pacmans.isEmpty())
                exit = true;
            for(int i = 0; i < pacmans.size(); i++){
               Position curPos = queueVector.get(i).peek();

               for(int j = 0; j < 4; j++){
                   int row = curPos.GetY()+colNum[j];
                   int col = curPos.GetX()+rowNum[j];
                   
                   if(ghostPos.GetY() == row && ghostPos.GetX() == col){
                       exit = true;
                       pos = new Position(curPos);
                       break;
                   }
                   
                   if(!visitedArray.get(i)[row][col] && _labyrinth.GetCell(new Position(col,row)).GetCellObjectType()==CellObjectType.EmptyCellObject){
                       visitedArray.get(i)[row][col] = true;
                       queueVector.get(i).add(new Position(col, row));
                   }
               }
               
               queueVector.get(i).poll();   
               if(exit || queueVector.isEmpty()) break;
            }
        }
        if(pos==null)
            return MoveType.NoMove;
        if(ghostPos.GetX()+1 == pos.GetX())
            return MoveType.RightMove;
        if(ghostPos.GetX()-1 == pos.GetX())
            return MoveType.LeftMove;
        if(ghostPos.GetY()-1 == pos.GetY())
            return MoveType.TopMove;
        if(ghostPos.GetY()+1 == pos.GetY())
            return MoveType.BottomMove;
        
        return MoveType.NoMove;
    }

    @Override
    public void SetMovingObject(CreatureCellObject creatureCellObject) {
        _creatureCellObject = creatureCellObject;
    }
    
}
