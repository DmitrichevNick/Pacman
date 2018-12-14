/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.FoodType;

/**
 *
 * @author August
 */
public class FoodCellObject extends CellObject {
    private FoodType _foodType;
    
    public void SetFoodType(FoodType foodType){
        _foodType = foodType;
    }
    
    public FoodType GetFoodType(){
        return _foodType;
    }
    
}
