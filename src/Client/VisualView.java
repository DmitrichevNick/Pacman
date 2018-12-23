/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Enums.CellObjectType;
import MapModule.CreatureCellObject;
import MapModule.IChangeable;
import MapModule.Labyrinth;
import MapModule.Position;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Arno-
 */
public class VisualView{
    private Scene _scene;
    private Grid _grid;
    private Pane _root;
    private Pane _infoPanel;
    private int _width;
    private int _height;
    
    private Image _imagePacman1;
    private Image _imageWall;
    private Image _imageGhostB;
    private Image _imageGhostP;
    private Image _imageGhostI;
    private Image _imageGhostC;
    private Image _imagePrimeFood;
    private ArrayList<IChangeable> _objects;
        
    
    public VisualView(Stage stage, Labyrinth labyrinth, ArrayList<IChangeable> objects){
        _width = labyrinth.GetWidth()*40;
        _height = labyrinth.GetHeight()*40 + 100;
        _grid = new Grid();
        _root = new Pane();
        _root.setStyle("-fx-background-color: black");
        _objects = objects;
        
        _scene = new Scene(_root, _width, _height);
        
        setStartIcon();
        
        for (int i = 0; i < Enums.PacmansParameter.ROW_COUNT; i++) {
            for (int j = 0; j < Enums.PacmansParameter.COLUMN_COUNT; j++) {
                Position currentPosition = new Position(i,j);
                CellObjectType type = labyrinth.GetCell(currentPosition).GetCellObjectType();
                CellView cell = new CellView(currentPosition);
            	_grid.addCell(cell);
                switch (type) {
                    case WallObject:
                        _root.getChildren().add(cell.getNode(new ImageView(_imageWall)));
                        break;
                    case EmptyCellObject:
                    default:
                        break;
                }
            }
            Position currentPosition = new Position(i,Enums.PacmansParameter.COLUMN_COUNT);
            CellView cell = new CellView(currentPosition);
            _root.getChildren().add(cell.getNode());
        }
        
        _infoPanel = new Pane();
        _infoPanel.setStyle("-fx-background-color: white");
        
        Button refresh = new Button();
        refresh.setText("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Rendering(_objects);
            }
        });

        refresh.setLayoutX(0);
        refresh.setLayoutY(0);
        _infoPanel.setLayoutX(0);
        _infoPanel.setLayoutY(_height - 95);
        _infoPanel.getChildren().add(refresh);
        _root.getChildren().add(_infoPanel);

        stage.setTitle("PAC-MAN");
        stage.setScene(_scene);
        stage.show();
    }

    public void Rendering(ArrayList<IChangeable> activeObject) {
        for (IChangeable object : activeObject) {
            Position currentPosition = object.GetPosition();
            CellObjectType type = object.GetCellObject().GetCellObjectType();
            CellView cell = new CellView(currentPosition);
            _grid.addCell(cell);
            switch (type) {
                case PacmanObject:
                    _root.getChildren().add(cell.getNode(new ImageView(_imagePacman1)));
                    break;
                case FoodObject:
                    _root.getChildren().add(cell.getNode(new ImageView(_imagePrimeFood)));
                    break;
                case GhostObject:
                    CreatureCellObject temp = (CreatureCellObject) object.GetCellObject();
                    switch (temp.GetCreatureType()) {
                        case BlinkyGhost:
                            _root.getChildren().add(cell.getNode(new ImageView(_imageGhostB)));
                            break;
                        case PinkyGhost:
                            _root.getChildren().add(cell.getNode(new ImageView(_imageGhostP)));
                            break;
                        case InkyGhost:
                            _root.getChildren().add(cell.getNode(new ImageView(_imageGhostI)));
                            break;
                        case ClydeGhost:
                            _root.getChildren().add(cell.getNode(new ImageView(_imageGhostC)));
                            break;
                        case StupidGhost:
                            //_root.getChildren().add(cell.getNode(new ImageView(_imageGhostP)));
                            break;
                    }
                case EmptyCellObject:
                default:
                    break;
            }
        }
    }

    public void setStartIcon() {
        Class<?> classRes = getClass();
        
        InputStream resPacman1 = classRes.getResourceAsStream("ImagePacMan1.png");
        InputStream resWall = classRes.getResourceAsStream("ImageWall.png");
        InputStream resGhostB = classRes.getResourceAsStream("ImageBlinky.png");
        InputStream resGhostP = classRes.getResourceAsStream("ImagePinky.png");
        InputStream resGhostI = classRes.getResourceAsStream("ImageInky.png");
        InputStream resGhostC = classRes.getResourceAsStream("ImageClyde.png");
        InputStream resPrimeFood = classRes.getResourceAsStream("ImagePrimeFood.png");

        _imagePacman1 = new Image(resPacman1);
        _imageWall = new Image(resWall);
        _imageGhostB = new Image(resGhostB);
        _imageGhostP = new Image(resGhostP);
        _imageGhostI = new Image(resGhostI);
        _imageGhostC = new Image(resGhostC);
        _imagePrimeFood = new Image(resPrimeFood);
    }
}
