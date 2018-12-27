/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerModule;

import Enums.CellObjectType;
import Enums.SessionStatus;
import MapModule.CreatureCellObject;
import MapModule.IChangeable;
import MapModule.Labyrinth;
import MapModule.Map;
import MapModule.Position;
import Server_.BaseUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author August
 */
public class Session {
    private UUID _id;
    private String _name;
    private SessionStatus _status;
    
    private Map _map;

    private ArrayList<Player> _players;
    private ArrayList<BaseUser> _users;

    public String GetName(){
        return _name;
    }
    
    public Session(String name){
        try {
            _map = new Map();
            _id = UUID.randomUUID();
            _name = name;
            _players = new ArrayList<>();
            _status = SessionStatus.Prepare;
            _users = new ArrayList<>();
            AddGhost(new Position(8, 9));
            AddGhost(new Position(28, 7));
            SessionUpdater();
        } catch (IOException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void SessionUpdater(){
        new Thread(){
            @Override
            public void run() {
                try {
                    while(true){
                        sleep(500);
                        if(_status == SessionStatus.Prepare){
                            // wait
                        }
                        else if(_status == SessionStatus.Play){
                            Update();
                            for(BaseUser user : _users){
                                user.SendLabyrinth(_map.GetLabyrinth());
                                user.SendActiveObjects(_map.GetActiveObjects());
                                user.SendPlayers(_players);
                            }
                        }
                        else if (_status == SessionStatus.Pause){
                            // nothing
                        }
                        else if (_status == SessionStatus.Finish){
                            break;
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
                }
            }}.start();
    }
    
    public void SetStatus(SessionStatus status){
        _status = status;
    }
    
     public SessionStatus GetStatus(){
        return _status;
    }

    public ArrayList<IChangeable> GetActiveObjects(){
        return _map.GetActiveObjects();
    }
    
    public void Update(){
        _map.Refresh();
    }
       
    
    public ArrayList<Player> GetPlayers(){
        return _players;
    }
    
    public Labyrinth GetLabyrinth(){
        return _map.GetLabyrinth();
    }

    
    public void AddPlayer(BaseUser user){        
        if(user.GetIsPlayer()){
            Player player = new Player(user.GetId(), new Position(28,1));
            _players.add(player);
            _map.AddPacman(player);
        }
        _users.add(user);
    }
    
    
    public void AddGhost(Position position){        
        CreatureCellObject ghost = new CreatureCellObject(CellObjectType.GhostObject);
        ghost.SetId(UUID.randomUUID());
        ghost.SetPosition(position);
        _map.AddGhost(ghost);
    } 
}
