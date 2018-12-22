/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapModule;

import Enums.MoveType;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public interface IChangeable {
    Pair GetPosition();
    void SetPosition(Pair position);
    CellObject GetCellObject();
}
