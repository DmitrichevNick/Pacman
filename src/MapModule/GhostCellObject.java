///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package MapModule;
//
//import Enums.CellObjectType;
//import Enums.GhostType;
//import javafx.util.Pair;
//
///**
// *
// * @author August
// */
//public class GhostCellObject extends CellObject{
//    private GhostType _ghostType;
//    private int _id;
//    private Pair _position;
//    
//    public GhostCellObject(int id, Pair position){
//        super.SetCellObjectType(CellObjectType.GhostObject);
//        _id = id;
//        _ghostType = GhostType.RedGhost;
//        _position = position;
//    }
//    
//    public void SetPosition(Pair position){
//        _position = position;
//    }
//    
//    public Pair GetPosition(){
//        return _position;
//    }
//    
//    public GhostCellObject(int id, GhostType ghostType){
//        super.SetCellObjectType(CellObjectType.GhostObject);
//        _id = id;
//        _ghostType = ghostType;
//    }
//    
//    public void SetGhostType(GhostType ghostType){
//        _ghostType = ghostType;
//    }
//    
//    public GhostType GetGhostType(){
//        return _ghostType;
//    }
//    
//    public void SetId(int id){
//        _id = id;
//    }
//    
//    public int GetId(){
//        return _id;
//    }
//}
