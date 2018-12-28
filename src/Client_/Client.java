/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_;


import Enums.ClientCommand;
import Enums.ServerCommand;
import MapModule.IChangeable;
import MapModule.Labyrinth;
import Client.ViewClient;
import Enums.MoveType;
import static Enums.ServerCommand.Disconnect;
import static Enums.ServerCommand.Move;
import ServerModule.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.util.Pair;
import javax.swing.SwingUtilities;

/**
 *
 * @author August
 */
public class Client implements IClient{
    TestClient _testClient;
    int _port = 5676;
    InetAddress _ip;
    private Socket cs;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Labyrinth _labyrinth;
    private ArrayList<IChangeable> _activeObjects;
    private ArrayList<Player> _players;
    private ViewClient _view;

    public Labyrinth getLabyrinth() {
        return _labyrinth;
    }
    
    public void SendMove(MoveType move){
        try {
            Pair parameter = new Pair(Move,move);
            oos.writeObject(parameter);
            oos.flush();
            oos.reset();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setLabyrinth(Labyrinth _labyrinth) {
        this._labyrinth = _labyrinth;
    }

    public ArrayList<IChangeable> getActiveObjects() {
        return _activeObjects;
    }

    public void setActiveObjects(ArrayList<IChangeable> _activeObjects) {
        this._activeObjects = _activeObjects;
    }

    public ArrayList<Player> getPlayers() {
        return _players;
    }

    public void setPlayers(ArrayList<Player> _players) {
        this._players = _players;
    }
    
    
    public Client(TestClient tc){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                  _testClient = tc;
                createStreams();
                startListening();
           }
        });
    }

    public void createStreams(){    
        if(cs != null) return;
            try {
                _ip = InetAddress.getByName("localhost");
                cs = new Socket(_ip, _port);
                System.out.println("Client started");

                oos = new ObjectOutputStream(cs.getOutputStream());
                ois = new ObjectInputStream(cs.getInputStream());
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
    public void startListening() {
        new Thread(){
            @Override
            public void run() {
                try {
                    int count = 0;
                    while(true) {
                        if(cs==null || ois==null)continue;
                        Pair parametres = (Pair)ois.readObject(); 
                        ClientCommand command = (ClientCommand)parametres.getKey();
                       
                        
                        if(command==ClientCommand.Labyrinth){
                            _labyrinth = null;
                            _labyrinth = (Labyrinth)parametres.getValue();
                        }
                        
                        if (command == ClientCommand.ActiveObjects) {
                            _activeObjects = null;
                            _activeObjects = (ArrayList<IChangeable>) parametres.getValue();
                            if (count != 0 && _view!=null) {
                                Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    _view.StartEvent(_activeObjects);
                                }
                                });
                            }
                            count++;
                        }

                        if(command==ClientCommand.Players){
                            _players = null;
                            _players = (ArrayList<Player>)parametres.getValue();
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }}.start();
    }
    
    @Override
    public UUID CreateSession(String sessionName) {
        try {
            oos.writeObject(ServerCommand.CreateSession);
            UUID id = (UUID)ois.readObject();
            return id;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }

    @Override
    public void ConnectToSession(String sessionName, String name, boolean play) {
        try {
            ArrayList<Object> parametres = new ArrayList<>();
            parametres.add(sessionName);
            parametres.add(name);
            parametres.add(play);
            
            Pair params = new Pair(ServerCommand.ConnectToSession, parametres);
            
            oos.writeObject(params);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DisconnectFromSession() {
         try {
            Pair parameter = new Pair(Disconnect,0);
            oos.writeObject(parameter);
            oos.flush();
            oos.reset();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Scene CreateView(Labyrinth labyrinth, ArrayList<IChangeable> objects){
        _view = new ViewClient(labyrinth, objects);
        return _view.testCreated();
    }
    
}
