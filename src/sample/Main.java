/**
 * Project - "BlackJack
 * BlackJack game is a card game in which the player's task is to get the score closest to 21.
 *
 * The game uses a standard deck of cards, the cards have certain values:
 * Cards from 2 to 10 - value such as the number on the card
 * Figures: Jack, lady, king - value 10
 * Aces - value 11
 *
 * The game begins with one card being dealt to the dealer and two cards being dealt to the player.
 * The player can either draw a card (HIT) or fold (PASS) at any given moment.
 * If, by drawing cards, the total score exceeds 21, the player loses...
 *
 * At the moment of a fold, the dealer draws his cards in such a way as to defeat the player.
 * Ties are settled in favour of the player.
 *
 * In the game added a button "debug mode" allowing you to view the next cards (program tests).
 *
 * @author Jakub Wincenciak I7Y2S1
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main method where stage is created (window of program)
 */
public class Main extends Application {
    public static Stage mainStage;

    /**
     * in start method, program create scene based on .fxml file
     * @param primaryStage reference to starting default stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage=primaryStage;
        Parent menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
        mainStage.setTitle("BlackJack");
        mainStage.setScene(new Scene(menu, 900, 600));
        mainStage.show();

    }

    /**
     * launch a standalone application
     * @param args get optional arguments (running from console)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
