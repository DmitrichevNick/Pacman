/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author August
 */
public class Labyrinth implements Serializable{
    private int _width;
    private int _height;

    private CellObject[][] _objectsMatrix;
    private ArrayList<CellObject> _activeObjects;
    
    public int GetWidth(){
        return _width;
    }
    
    public int GetHeight(){
        return _height;
    }

    public Labyrinth(){
        _width = Enums.PacmansParameter.ROW_COUNT;
        _height = Enums.PacmansParameter.COLUMN_COUNT;
        _objectsMatrix = new CellObject[_height][_width];
        
        // Делаю все пустым
        for(int i = 0; i < _height; i++){
            for (int j = 0; j < _width; j++){
                _objectsMatrix[i][j] = new EmptyCellObject();
            }
        }
        
        for (int i = 0; i < _width; i++){
            _objectsMatrix[0][i] = new WallCellObject();
            _objectsMatrix[_height-1][i] = new WallCellObject();
        }
        
        for (int i = 0; i < _height; i++){
            _objectsMatrix[i][0] = new WallCellObject();
            _objectsMatrix[i][_width - 1] = new WallCellObject();
        }
    }
    
    public Labyrinth(String path){
        File file = new File(path); 
  
        BufferedReader br; 
        try {
            br = new BufferedReader(new FileReader(file));       

            String st; 
            st = br.readLine();
            int height = Integer.parseInt(st.split(" ")[0]);
            _height = height;
            int width = Integer.parseInt(st.split(" ")[1]);
            _width = width;

            _objectsMatrix = new CellObject[height][width];

            height = 0;
            while ((st = br.readLine()) != null) {
                for(int i = 0; i < width; i++){
                    if(st.charAt(i)=='1')
                        _objectsMatrix[height][i] = new WallCellObject();
                    if(st.charAt(i)=='0')
                        _objectsMatrix[height][i] = new EmptyCellObject();
                }
                height++;

            } 
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Labyrinth.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
                Logger.getLogger(Labyrinth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Position GetDefaultPacmanPos(){
        return new Position(18,18);
    }
    
    public void SetCell(Position position, CellObject cellObject){
        _objectsMatrix[position.GetY()][position.GetX()] = cellObject; 
    }
    
    public CellObject GetCell(Position position){
        return _objectsMatrix[position.GetY()][position.GetX()]; 
    }    
}
