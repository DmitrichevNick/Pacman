
import java.util.TimerTask;
import javafx.application.Platform;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author August
 */
public class Updater extends TimerTask {
    private TestFrame _frame;
    public Updater(TestFrame frame){
        _frame = frame;
    }

    @Override
    public void run(){
        Platform.runLater(() -> {
            _frame.Uppp();
        });
      }
}