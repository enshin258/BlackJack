package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * contains logic of menu window
 * using slider can adjust starting money
 */

public class MenuController implements Initializable {

    @FXML
    Button startButton;
    @FXML
    Slider valueSlider;
    @FXML
    Text moneyValue;

    public static int money=0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valueSlider.valueProperty().addListener((observable, oldValue, newValue) ->
        {
           if(newValue.intValue()>0)
           {
               startButton.setDisable(false);
               Platform.runLater(()->
               {
                   moneyValue.setText(newValue.intValue()+ "$");
                   money=newValue.intValue();
               });
           }
           else
           {
               startButton.setDisable(true);
               Platform.runLater(()->
               {
                   moneyValue.setText(newValue.intValue()+ "$");
                   money=newValue.intValue();
               });
           }
        });

        startButton.setOnAction(event ->
        {
            startButton.setDisable(false);
            try
            {
                startButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("game.fxml")));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Main.mainStage.close();
            Main.mainStage.setWidth(1920);
            Main.mainStage.setHeight(1080);
            Main.mainStage.setFullScreen(true);
            Main.mainStage.setMaximized(true);
            Main.mainStage.show();
        });
    }
}
