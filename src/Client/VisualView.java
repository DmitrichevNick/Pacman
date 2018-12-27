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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private int _countRow;
    private int _countColumn;
    private Client _app;
    
    private Image _imagePacman1;
    private Image _imageWall;
    private Image _imageGhostB;
    private Image _imageGhostP;
    private Image _imageGhostI;
    private Image _imageGhostC;
    private Image _imagePrimeFood;
    private ArrayList<IChangeable> _objects;
        
    
    public VisualView(Stage stage, Client app, Labyrinth labyrinth, ArrayList<IChangeable> objects){
        _app = app;
        _countRow = labyrinth.GetHeight();
        _countColumn = labyrinth.GetWidth();
        _width = labyrinth.GetWidth()*40;
        _height = labyrinth.GetHeight()*40 + 100;
        _grid = new Grid(_countRow, _countColumn);
        _root = new Pane();
        _root.setStyle("-fx-background-color: black");
        _objects = objects;
        
        _scene = new Scene(_root, _width, _height);
        
        setStartIcon();
        
        for (int i = 0; i < _countRow; i++) {
            for (int j = 0; j < _countColumn; j++) {
                Position currentPosition = new Position(j,i);
                CellObjectType type = labyrinth.GetCell(currentPosition).GetCellObjectType();
                CellView cell = new CellView(currentPosition, type);
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
        }
        
        for (int i = 0; i < _countColumn; i++) {
            Position currentPosition = new Position(i, _countRow);
            CellView cell = new CellView(currentPosition, CellObjectType.EmptyCellObject);
            _root.getChildren().add(cell.getNode());
        }
        
        _infoPanel = new Pane();
        _infoPanel.setStyle("-fx-background-color: white");
        
        Button refresh = new Button();
        refresh.setText("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                _app.UpdateActiveObject();
                _objects = _app.getActiveObjects();
                _app.getDataChange().DataIsChanged();
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
        Rendering(_objects);
    }
    
    public void Rendering(ArrayList<IChangeable> activeObject) {
        _grid.ClearActiveObjectCell();
        for (IChangeable object : activeObject) {
            Position currentPosition = object.GetPosition();
            CellObjectType type = object.GetCellObject().GetCellObjectType();
            CellView cell = new CellView(currentPosition, type);
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
    
    public Grid getGrid() {
        return _grid;
    }

    public Pane getRoot() {
        return _root;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public ArrayList<IChangeable> getActiveObjects() {
        return _objects;
    }

    public Image getImagePacman1() {
        return _imagePacman1;
    }

    public Image getImageWall() {
        return _imageWall;
    }

    public Image getImageGhostB() {
        return _imageGhostB;
    }

    public Image getImageGhostP() {
        return _imageGhostP;
    }

    public Image getImageGhostI() {
        return _imageGhostI;
    }

    public Image getImageGhostC() {
        return _imageGhostC;
    }

    public Image getImagePrimeFood() {
        return _imagePrimeFood;
    }
}

// Интерфейс, который будет реализован всеми, кто заинтересован в событиях "Изменение данных"
interface DataChangeListener {
    void Rendering(VisualView view);
}

class Render implements DataChangeListener {
    @Override
    public void Rendering(VisualView view) {
        view.Rendering(view.getActiveObjects());
//        for (IChangeable object : view.getActiveObjects()) {
//            Position currentPosition = object.GetPosition();
//            CellObjectType type = object.GetCellObject().GetCellObjectType();
//            CellView cell = new CellView(currentPosition, type);
//            view.getGrid().addCell(cell);
//            switch (type) {
//                case PacmanObject:
//                    view.getRoot().getChildren().add(cell.getNode(new ImageView(view.getImagePacman1())));
//                    break;
//                case FoodObject:
//                    view.getRoot().getChildren().add(cell.getNode(new ImageView(view.getImagePrimeFood())));
//                    break;
//                case GhostObject:
//                    CreatureCellObject temp = (CreatureCellObject) object.GetCellObject();
//                    switch (temp.GetCreatureType()) {
//                        case BlinkyGhost:
//                            view.getRoot().getChildren().add(cell.getNode(new ImageView(view.getImageGhostB())));
//                            break;
//                        case PinkyGhost:
//                            view.getRoot().getChildren().add(cell.getNode(new ImageView(view.getImageGhostP())));
//                            break;
//                        case InkyGhost:
//                            view.getRoot().getChildren().add(cell.getNode(new ImageView(view.getImageGhostI())));
//                            break;
//                        case ClydeGhost:
//                            view.getRoot().getChildren().add(cell.getNode(new ImageView(view.getImageGhostC())));
//                            break;
//                        case StupidGhost:
//                            break;
//                    }
//                case EmptyCellObject:
//                default:
//                    break;
//            }
//        }
    }
}
