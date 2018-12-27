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
import ServerModule.Player;
import Server_.Server;
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
import javafx.util.Pair;

/**
 *
 * @author August
 */
public class Client implements IClient{
    int _port = 5676;
    InetAddress _ip;
    private Socket cs;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    
    
    public Client(){
        createStreams();
        startListening();
    }
    
    public static void main(String[] args) {        
        Client cl = new Client();
        cl.createStreams();
        cl.startListening();
        //new Server().startServer();
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
                    while(true) {
                        Pair parametres = (Pair)ois.readObject(); 
                        ClientCommand command = (ClientCommand)parametres.getKey();
                        
                        if(command==ClientCommand.Labyrinth){
                            Labyrinth labyrinth = (Labyrinth)parametres.getValue();
                            int tt = 5+5;
                        }
                        
                        if(command==ClientCommand.ActiveObjects){
                            ArrayList<IChangeable> activeObjects = (ArrayList<IChangeable>)parametres.getValue();
                            int tt = 5+5;
                        }
                        
                        if(command==ClientCommand.Players){
                            ArrayList<Player> activeObjects = (ArrayList<Player>)parametres.getValue();
                            int tt = 5+5;
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
    public void DisconnectFromSession(String sessionName, UUID idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
