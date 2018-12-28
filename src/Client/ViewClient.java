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
import javafx.scene.Scene;

/**
 *
 * @author Arno-
 */
public class ViewClient{

    public ViewClient(Labyrinth labyrinth, ArrayList<IChangeable> activeCells) {
        this._labyrinth = labyrinth;
        this._activeCells = activeCells;
    }
    private Labyrinth _labyrinth;
    private ArrayList<IChangeable> _activeCells;
    private VisualView _client;
    private DataChange _initiater;
        
    public Scene testCreated(){
        _client = new VisualView(this, _labyrinth, _activeCells);
        
        _initiater = new DataChange();
        Render render = new Render(_client);

        //_initiater.foundVisualView(_client);
        _initiater.addListener(render);
        return _client.getScene();
    }
    
    public void UpdateActiveObject(ArrayList<IChangeable> activeObjects){
        _activeCells = activeObjects;
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
    
    public void StartEvent(ArrayList<IChangeable> activeObjects) {
        _activeCells = activeObjects;
        
        _client.setObjects(activeObjects);
        _initiater.DataIsChanged(activeObjects);
    }
}
//Изменение данных
class DataChange{

    private List<DataChangeListener> listeners = new ArrayList<DataChangeListener>();

    public void addListener(DataChangeListener toAdd) {
        listeners.add(toAdd);
    }

    public void DataIsChanged(ArrayList<IChangeable> activeObjects) {
        for(DataChangeListener ls : listeners){
            ls.Rendering(activeObjects);
        }
    }
}
