/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_;

import MapModule.IChangeable;
import MapModule.Labyrinth;
import java.util.ArrayList;
import java.util.UUID;
import javafx.scene.Scene;

/**
 *
 * @author August
 */
public interface IClient {
    UUID CreateSession(String sessionName);
    void ConnectToSession(String sessionName, String name,boolean play);
    void DisconnectFromSession();
    Scene CreateView(Labyrinth labyrinth, ArrayList<IChangeable> objects);
}
