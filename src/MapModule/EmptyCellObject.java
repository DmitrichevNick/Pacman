/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.CellObjectType;

/**
 *
 * @author August
 */
public class EmptyCellObject extends CellObject{
    public EmptyCellObject(){
        super.SetCellObjectType(CellObjectType.EmptyCellObject);
    }
}
