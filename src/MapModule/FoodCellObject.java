/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;
import Enums.FoodType;

/**
 *
 * @author August
 */
public class FoodCellObject extends CellObject implements IChangeable {
    private Position _position;
    private FoodType _foodType;
    
    public FoodCellObject(){
        super.SetCellObjectType(CellObjectType.FoodObject);
        _foodType = FoodType.SimpleFood;
    }
    
    public FoodCellObject(FoodType foodType){
        super.SetCellObjectType(CellObjectType.FoodObject);
        _foodType = foodType;
    }
    
    public void SetFoodType(FoodType foodType){
        _foodType = foodType;
    }
    
    public FoodType GetFoodType(){
        return _foodType;
    }

    @Override
    public Position GetPosition() {
        return _position;
    }

    @Override
    public void SetPosition(Position position) {
       _position = position;
    }

    @Override
    public CellObject GetCellObject() {
        return this;
    }
    
}
