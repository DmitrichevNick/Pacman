/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_;

import Enums.MoveType;
import java.net.Socket;

/**
 *
 * @author August
 */
public class User extends BaseUser{
    
    public User(Socket socket) {
        super(socket);
    }
    
    public void SetMoveType(MoveType move){
        
    }
    
}
