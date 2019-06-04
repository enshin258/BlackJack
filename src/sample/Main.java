package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("BlackJack");



        Pane layout = new Pane();
        Button button = new Button("click me");
        layout.getChildren().add(button);
        Scene scene = new Scene(layout,1920,1080);
        primaryStage.setScene(scene);

        button.setOnMouseClicked(event ->
        {
            primaryStage.setScene(new Scene(root, 1920,1080));
            primaryStage.setMaximized(true);
            primaryStage.setFullScreen(true);
        });



        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
