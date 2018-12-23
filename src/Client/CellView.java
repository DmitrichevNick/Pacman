/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Enums.CellObjectType;
import MapModule.Position;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Arno-
 */
public class CellView {
    //private CellObjectType type;
    protected Position position;
    private Node node;

    public CellView(Position position){//, CellObjectType type) {
        this.position = position;
        //this.type = type;
    }

//    public void setType(CellObjectType type) {
//        this.type = type;
//    }
//    public CellObjectType getType() {
//        return type;
//    }

    public Node getNode(ImageView image) {
        //image.setFitWidth(Enums.PacmansParameter.WIDTH_CELL);
	//image.setFitHeight(Enums.PacmansParameter.HEIGHT_CELL);
        
	//image.setX(position.GetX()*Enums.PacmansParameter.WIDTH_CELL + Enums.PacmansParameter.WIDTH_CELL/2);
	//image.setY(position.GetY()*Enums.PacmansParameter.HEIGHT_CELL + Enums.PacmansParameter.HEIGHT_CELL/2);
        
        this.node=image;
        node.setLayoutX(position.GetX()*Enums.PacmansParameter.WIDTH_CELL); //+ Enums.PacmansParameter.WIDTH_CELL/2);
        node.setLayoutY(position.GetY()*Enums.PacmansParameter.HEIGHT_CELL); //+ Enums.PacmansParameter.HEIGHT_CELL/2);
        return node;
    }
    
    public Node getNode() {
        this.node= new Rectangle(position.GetX()*Enums.PacmansParameter.WIDTH_CELL,position.GetY()*Enums.PacmansParameter.HEIGHT_CELL,Enums.PacmansParameter.HEIGHT_CELL,5);
			((Rectangle)node).setFill(Color.BLUE);
        return node;
    }
}
