/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javafx.application.Application;
import javafx.stage.Stage;
import Client.VisualView;
import MapModule.IChangeable;
import MapModule.Labyrinth;
import MapModule.Map;
import ServerModule.Session;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Arno-
 */
public class Client extends Application{
    private Labyrinth _labyrinth;
    private ArrayList<IChangeable> _activeCells;
    private Map _map;
    private Session _session;
    
    @Override
    public void start(Stage primaryStage) {
        testCreated();
        VisualView client = new VisualView(primaryStage, _labyrinth, _activeCells);
    }
    
    public void testCreated(){
        _session = new Session("Test");
        UUID idPlayer = UUID.randomUUID();
        UUID idGhost = UUID.randomUUID();
        _session.AddPlayer(idPlayer);
        _session.AddGhost(idPlayer);
        _map = _session.GetMap();
//_map = new Map();
        _labyrinth = _map.GetLabyrinth();
        _activeCells = _map.GetActiveObjects();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
