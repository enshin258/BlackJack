package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Contains logic of game,
 * here is programed behavior of buttons and dealer AI
 */

public class GameController implements Initializable {


    @FXML public Pane pane;
    @FXML public StackPane deck_place;
    @FXML public HBox user_hand;
    @FXML public HBox dealer_hand;
    @FXML public Button hit;
    @FXML public Button pass;
    @FXML public Button debug_button;
    @FXML public Text user_counter;
    @FXML public Text dealer_counter;
    @FXML public Text moneyCounter;

    //player and dealer
    Player user;
    Player dealer;

    /**
     * enum for result of game
     */
    public enum result {win,loose}

    /**
     * actual money of player
     */
    int money=0;

    /**
     * Method used at start of new game
     * It cleans the players' points, their cards on the hand, and creates a new deck on the table.
     * After that, it sets the buttons to working and deals the starting cards to the player and the dealer.
     */
    void newGame() {

        //clear points
        user.setPoints(0);
        dealer.setPoints(0);

        //clear counters
        user.points_counter.setText("0");
        dealer.points_counter.setText("0");

        //clear hands of players
        user_hand.getChildren().clear();
        dealer_hand.getChildren().clear();

        //create new deck
        deck_place.getChildren().clear();
        Deck.newDeck();

        //adding deck on table
        for (Card c : Deck.getDeckOfCards()) {
            deck_place.getChildren().add(c.getCard());
        }

        Player.setDeckPlace(deck_place);
        //enable button again
        hit.setDisable(false);
        pass.setDisable(false);
        debug_button.setDisable(false);
        //first round
        dealer.getNewCard();
        user.getNewCard();
        user.getNewCard();

    }

    /**
     * Method used at the end of the game
     * It creates a notification window where the game result is shown and buttons used to restart the game or exit the program
     * @param r indicates, if player win or loose game
     */
    void endGame(result r)
    {
        //block hit and pass button
        hit.setDisable(true);
        pass.setDisable(true);
        debug_button.setDisable(true);

        //set reference to main stage
        Stage stage =(Stage)deck_place.getScene().getWindow();

        //create pop up window
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Game ended");
        alert.initOwner(stage);
        alert.setX(500);
        alert.setY(420);

        //buttons
        ButtonType restart_button = new ButtonType("Restart");
        ButtonType close_button = new ButtonType("End");

        //set text based on result
        if(r==result.win)
        {
            alert.setHeaderText("***** YOU WIN !!! *****");
            money=money*2;
            moneyCounter.setText(money + "$");
        }
        else
        {
            alert.setHeaderText("***** YOU LOOSE !!! *****");
            money=money/2;
            moneyCounter.setText(money + "$");
        }
        //no money
        if(money==0)
        {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("NO MONEY !!!");
            alert.getButtonTypes().setAll(close_button);
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()==close_button)
            {
                alert.close();
                stage.close();
            }
        }
        else
        {
            alert.setContentText("Play again?");
            alert.getButtonTypes().setAll(restart_button,close_button);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == restart_button)
            {
                alert.close();
                newGame();
            }
            else if(result.get() == close_button)
            {
                alert.close();
                stage.close();
            }
        }

    }

    /**
     * A method that add a card to the top of the deck to cover the others
     * It allows to view the next cards from the deck with a "debug mode" button
     */
    void DebugMode()
    {

        //create hider
        Card hider = new Card("back","card");
        hider.getCard().setRotate(90);
        hider.getCard().setTranslateX(deck_place.getLayoutX()+20);
        hider.getCard().setTranslateY(deck_place.getLayoutY()+30);

        pane.getChildren().add(hider.getCard());

        //on click, change visibility of hider
        debug_button.setOnAction(event ->
        {
            hider.getCard().setVisible(!hider.getCard().isVisible());
            System.out.println("changed debug mode");
        });

    }

    /**
     * used to run program
     * this method create players and start new game
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //creating players
        Player.setDeckPlace(deck_place);
        dealer = new Player("dealer",dealer_hand,dealer_counter);
        user = new Player("user",user_hand,user_counter);

        //start new game
        newGame();
        DebugMode();
        moneyCounter.setText(MenuController.money + "$");
        money=MenuController.money;


        //on click, add new card for player and check points
        hit.setOnAction(event ->
        {
            user.getNewCard();
            if(user.getPoints()>=21)
            {
                if(user.getPoints()==21)
                {
                    endGame(result.win);
                }
                else
                {
                    endGame(result.loose);
                }
            }

        });

        //start dealer moves, and compare points
        pass.setOnAction(event ->
        {
            while (dealer.getPoints()<=user.getPoints())
            {
                dealer.getNewCard();
            }
            if(dealer.getPoints()>21)
            {
                endGame(result.win);
            }
            else if(dealer.getPoints()>user.getPoints())
            {
                endGame(result.loose);
            }
            else
            {
                endGame(result.win);
            }
        });
    }
}


