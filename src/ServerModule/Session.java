/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerModule;

import MapModule.Map;
import java.util.ArrayList;

/**
 *
 * @author August
 */
public class Session {
    private int _lastPlayerId;
    private Map _map;
    private ArrayList<Player> _players;
    private String _name;
    
    public Session(String name){
        _map = new Map();
        _lastPlayerId = 1;
        _name = name;
        _players = new ArrayList<Player>();
    }
    
    public ArrayList<Player> GetPlayers(){
        return _players;
    }
    
    public Map GetMap(){
        return _map;
    }
    
    private int MakeNextId(){
        int oldId = _lastPlayerId;
        _lastPlayerId++;
        return oldId;
    }
    
    public void AddPlayer(){
        Player player = new Player(MakeNextId(),_map.getDefaultPacmanPos());
        _players.add(player);
        _map.AddPacman();
        // в мапу добавить пакмана
    }
    
}
