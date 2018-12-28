/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_;

import Enums.SessionStatus;
import ServerModule.Session;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author August
 */
public class Server implements IServer{
    private int port = 5676;
    private InetAddress _inetAddress = null;
    ServerSocket _port;
    static ArrayList<Session> _sessions;
    
    @Override
    public void StartServer(){
        try {
            _inetAddress = InetAddress.getByName("localhost");
            _sessions = new ArrayList<>();
            _sessions.add(new Session("TEST"));
            
            _port = new ServerSocket(port, 0, _inetAddress);
            System.out.println("Server started");
            new Thread(){
                @Override
                public void run() {
                    Socket cs;
                    while (true){
                        try {
                            cs = _port.accept();
                            System.out.println("New connection");
                            BaseUser bs = new BaseUser(cs);
                            new Thread(){
                                @Override
                                public void run() {
                                    bs.SetSessions(_sessions);
                                }
                            }.start();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }}.start();                
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        new Server().StartServer();
        Scanner scanner = new Scanner(System.in);

        while(true){
            String str = scanner.nextLine();
            if("start".equals(str.split(" ")[0])){
                for(Session session : _sessions){
                    if(session.GetName() == null ? str.split(" ")[1] == null : session.GetName().equals(str.split(" ")[1])){
                        session.SetStatus(SessionStatus.Play);
                    }
                }
            }
            if("pause".equals(str.split(" ")[0])){
                for(Session session : _sessions){
                    if(session.GetName() == null ? str.split(" ")[1] == null : session.GetName().equals(str.split(" ")[1])){
                        session.SetStatus(SessionStatus.Pause);
                    }
                }
            }
            if("create".equals(str.split(" ")[0])){
                Session session = new Session(str.split(" ")[1]);
                _sessions.add(session);
            }
        }
    }
}
