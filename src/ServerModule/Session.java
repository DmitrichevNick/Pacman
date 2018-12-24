/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerModule;

import Enums.CellObjectType;
import MapModule.CreatureCellObject;
import MapModule.IChangeable;
import MapModule.Labyrinth;
import MapModule.Map;
import MapModule.Position;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Session {
    private Map _map;
    private String _name;
    private ArrayList<Player> _players;
    private ArrayList<CreatureCellObject> _ghosts;
    
    public Session(String name){
        try {
            _map = new Map();
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        _name = name;
        _players = new ArrayList<>();
        _ghosts= new ArrayList<>();
    }
    
    public void Update(){
        _map.Refresh();
    }
    
    // Is for send to client
    public Labyrinth GetLabyrinth(){
        return _map.GetLabyrinth();
    }
    
    
    
    public ArrayList<Player> GetPlayers(){
        return _players;
    }
    
    public ArrayList<CreatureCellObject> GetGhosts(){
        return _ghosts;
    }
    
    public Map GetMap(){
        return _map;
    }

    
    public void AddPlayer(UUID id){        
        //Player player = new Player(id,_map.getDefaultPacmanPos());
        Player player = new Player(id, new Position(28,1));
        _players.add(player);
        _map.AddPacman(player);
    }
    
    
    public void AddGhost(UUID id){        
        CreatureCellObject ghost = new CreatureCellObject(CellObjectType.GhostObject);
        ghost.SetId(id);
        ghost.SetPosition(new Position(8,8));
        _ghosts.add(ghost);
        _map.AddGhost(ghost);
    } 
}
