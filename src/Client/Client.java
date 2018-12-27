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
import java.util.List;
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
    private VisualView _client;
    private DataChange _initiater;
    
    @Override
    public void start(Stage primaryStage) {
        testCreated();
        _client = new VisualView(primaryStage, this, _labyrinth, _activeCells);
        
        _initiater = new DataChange();
        Render render = new Render();

        _initiater.foundVisualView(_client);
        _initiater.addListener(render);

        //_initiater.DataIsChanged(); 
    }
        
    public void testCreated(){
        _session = new Session("Test");
        UUID idPlayer = UUID.randomUUID();
        UUID idGhost = UUID.randomUUID();
        _session.AddPlayer(idPlayer);
        _session.AddGhost(idGhost);
        _map = _session.GetMap();
        _labyrinth = _map.GetLabyrinth();
        _activeCells = _map.GetActiveObjects();
    }
    
    public void UpdateActiveObject(){
        _session.Update();
        _activeCells = _session.GetMap().GetActiveObjects();
    }
    
    public VisualView getVisualClient() {
        return _client;
    }
    
    public ArrayList<IChangeable> getActiveObjects() {
        return _activeCells;
    }
    
    public DataChange getDataChange() {
        return _initiater;
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
//Изменение данных
class DataChange{

    private List<DataChangeListener> listeners = new ArrayList<DataChangeListener>();
    private VisualView _view;
    
    public void foundVisualView(VisualView view) {
        _view = view;
    }

    public void addListener(DataChangeListener toAdd) {
        listeners.add(toAdd);
    }

    public void DataIsChanged() {
        listeners.forEach((hl) -> {
            hl.Rendering(_view);
        });
    }
}
