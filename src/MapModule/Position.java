/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

/**
 *
 * @author August
 */
public class Position {
    private int _x;
    private int _y;
    
    public Position(int x, int y){
        _x = x;
        _y = y;
    }
    
    public Position(Position pos){
        _x = pos.GetX();
        _y = pos.GetY();
    }

    
    
    public int GetX(){
        return _x;
    }
    
    public int GetY(){
        return _y;
    }
    
    public void SetPosition(int x, int y){
        _x = x;
        _y = y;
    }
    
}
