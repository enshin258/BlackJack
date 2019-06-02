package sample;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML Pane pane;
    @FXML Text title;
    @FXML StackPane deck_place;
    @FXML HBox user_hand;
    @FXML HBox dealer_hand;
    @FXML Button hit;
    @FXML Button pass;
    @FXML Button restart_button;
    @FXML ToggleButton debug;
    @FXML Text user_counter;
    @FXML Text dealer_counter;

    Player user;
    Player dealer;
    
    public void end_game()
    {
        hit.setDisable(true);
        pass.setDisable(true);
        restart_button.setOpacity(1);
        restart_button.setDisable(false);
        restart_button.setOnMouseClicked(event -> {
            title.setText("BlackJack");
            restart_button.setOpacity(0);
            restart_button.setDisable(true);
            user.setPoints(0);
            dealer.setPoints(0);
            user.points_counter.setText("0");
            dealer.points_counter.setText("0");

            user_hand.getChildren().clear();
            dealer_hand.getChildren().clear();
            user.hand.getChildren().removeAll();
            dealer.hand.getChildren().removeAll();

            deck_place.getChildren().clear();
            deck_place.getChildren().removeAll();

            Deck.new_deck();

            //adding deck on table
            for (Card c:Deck.getDeck_of_cards()) {
                deck_place.getChildren().add(c.getCard());
            }

            Player.setDeck_place(deck_place);


            hit.setDisable(false);
            pass.setDisable(false);

            dealer.getNewCard();
            user.getNewCard();
            user.getNewCard();

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //creating new deck
        Deck.new_deck();

        //adding deck on table
        for (Card c:Deck.getDeck_of_cards()) {
            deck_place.getChildren().add(c.getCard());
        }


        Player.setDeck_place(deck_place);
        dealer = new Player("dealer",dealer_hand,dealer_counter);
        user = new Player("user",user_hand,user_counter);
        //starting dealer

        dealer.getNewCard();
        user.getNewCard();
        user.getNewCard();

        hit.setOnMouseClicked(event ->
        {
            user.getNewCard();

            if(user.getPoints()>21)
            {
                title.setText("You Loose :(");
                end_game();
            }

        });

        pass.setOnMouseClicked(event ->
        {
            while (dealer.getPoints()<16 || dealer.points<user.points)
            {
                dealer.getNewCard();
                if(dealer.getPoints()>21)
                {
                    title.setText("You Win! :)");
                    end_game();
                    break;
                }
                else if(dealer.getPoints()>user.getPoints())
                {
                    title.setText("You Loose :(");
                    end_game();
                    break;
                }
                else
                {
                    title.setText("You Win! :)");
                    end_game();
                    break;
                }
            }
        });
    }
}


