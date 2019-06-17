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

public class Controller implements Initializable {


    @FXML public Pane pane;
    @FXML public StackPane deck_place;
    @FXML public HBox user_hand;
    @FXML public HBox dealer_hand;
    @FXML public Button hit;
    @FXML public Button pass;
    @FXML public Button debug_button;
    @FXML public Text user_counter;
    @FXML public Text dealer_counter;

    //player and dealer
    Player user;
    Player dealer;

    public enum result {win,loose;}

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
        user.hand.getChildren().removeAll();
        dealer.hand.getChildren().removeAll();

        //create new deck
        deck_place.getChildren().clear();
        deck_place.getChildren().removeAll();
        Deck.new_deck();

        //adding deck on table
        for (Card c : Deck.getDeck_of_cards()) {
            deck_place.getChildren().add(c.getCard());
        }

        Player.setDeck_place(deck_place);
        //enable button again
        hit.setDisable(false);
        pass.setDisable(false);
        //first round
        dealer.getNewCard();
        user.getNewCard();
        user.getNewCard();


    }

    void endGame(result r)
    {
        //block hit and pass button
        hit.setDisable(true);
        pass.setDisable(true);

        //set reference to main stage
        Stage stage =(Stage)pane.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Game ended");
        alert.initOwner(stage);
        alert.setX(400);
        alert.setY(420);
        if(r==result.win)
        {
            alert.setHeaderText("***** YOU WIN !!! *****");
        }
        else
        {
            alert.setHeaderText("***** YOU LOOSE !!! *****");
        }
        alert.setContentText("Restart game?");

        ButtonType restart_button = new ButtonType("Yes");
        ButtonType close_button = new ButtonType("No");


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
     void DebugMode()
    {

        Card hider = new Card("back","card");
        hider.getCard().setRotate(90);
        hider.getCard().setTranslateX(deck_place.getLayoutX()+20);
        hider.getCard().setTranslateY(deck_place.getLayoutY()+30);

        pane.getChildren().add(hider.getCard());

        debug_button.setOnAction(event ->
        {
            hider.getCard().setVisible(!hider.getCard().isVisible());
            System.out.println("changed debug mode");
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //creating players
        Player.setDeck_place(deck_place);
        dealer = new Player("dealer",dealer_hand,dealer_counter);
        user = new Player("user",user_hand,user_counter);
        DebugMode();
        newGame();

        //check if on start is 21 points (in this implementation ace is always for 11 points
        if(user.points==21)
        {
            endGame(result.win);
        }
        if(user.points>21)
        {
            endGame(result.loose);
        }

        hit.setOnMouseClicked(event ->
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

        pass.setOnMouseClicked(event ->
        {
            while (dealer.getPoints()<16 || dealer.points <= user.points)
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


