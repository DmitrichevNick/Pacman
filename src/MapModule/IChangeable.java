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
public interface IChangeable{
    Position GetPosition();
    void SetPosition(Position position);
    CellObject GetCellObject();
}
