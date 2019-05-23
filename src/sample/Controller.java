package sample;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML Pane pane;
    @FXML Text title;
    @FXML StackPane deck_place;
    @FXML HBox player_place;
    @FXML HBox dealer_place;
    @FXML Button hit;
    @FXML Button pass;
    @FXML Text player_score_place;
    @FXML Text dealer_score_place;

    boolean ended_move=false;



    public void getNewCard(String who)
    {
        int result=0;
        int old_points=0;
        int new_points=0;

        if(who.equals("dealer"))
        {
            //getting card from deck
            Node temp = deck_place.getChildren().get(deck_place.getChildren().size()-1);

            //remove top card
            deck_place.getChildren().remove(temp);

            //add that card to hand
            dealer_place.getChildren().add(temp);

            //calculating value
            String old_points_dealer_s = dealer_score_place.getText();
            String new_card_points_s = Deck.getDeck_of_cards().get(Deck.getDeck_of_cards().size()-1).getValue();

            old_points = Integer.parseInt(old_points_dealer_s);
            new_points = Integer.parseInt(new_card_points_s);

            //calculating result
            result=old_points+new_points;
            dealer_score_place.setText("" + result);
            Deck.getDeck_of_cards().remove(Deck.getDeck_of_cards().size()-1);

        }
        else if(who.equals("player"))
        {
            Node temp = deck_place.getChildren().get(deck_place.getChildren().size()-1);
            //remove top card
            deck_place.getChildren().remove(temp);
            //add that card to hand
            player_place.getChildren().add(temp);
            //calculating value
            String old_points_player_s = player_score_place.getText();
            String new_card_points_s = Deck.getDeck_of_cards().get(Deck.getDeck_of_cards().size()-1).getValue();

            old_points = Integer.parseInt(old_points_player_s);
            new_points = Integer.parseInt(new_card_points_s);

            result=old_points+new_points;
            player_score_place.setText("" + result);
            Deck.getDeck_of_cards().remove(Deck.getDeck_of_cards().size()-1);

        }
    }
    public int getPoints(String who)
    {
        if(who.equals("dealer"))
        {
            int result;
            String result_s;
            result_s=dealer_score_place.getText();
            result=Integer.parseInt(result_s);
            return result;
        }
        else if(who.equals("player"))
        {
            int result;
            String result_s;
            result_s=player_score_place.getText();
            result=Integer.parseInt(result_s);
            return result;
        }
        return 0;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //creating new deck
        Deck.new_deck();
        //adding deck on table
        for (Card c:Deck.getDeck_of_cards()) {
            deck_place.getChildren().add(c.getCard());
        }

        //hiding deck
        StackPane hider = new StackPane();
        Rectangle r = new Rectangle();
        ImageView image = new ImageView("graphics/single_cards/back_of_card.png");
        image.setRotate(90);
        hider.getChildren().addAll(r,image);
        hider.setTranslateX(1510);
        hider.setTranslateY(370);
        pane.getChildren().add(hider);

        //starting dealer

        getNewCard("dealer");
        getNewCard("player");
        getNewCard("player");

        hit.setOnMouseClicked(event ->
        {
            getNewCard("player");

            if(getPoints("player")>21)
            {
                title.setText("YOU LOOSE :(");
            }

        });

        pass.setOnMouseClicked(event ->
        {
            while (getPoints("dealer")<16)
            {
                getNewCard("dealer");

                if(getPoints("dealer")>21)
                {
                    title.setText("YOU WIN :)");
                    break;
                }
            }

            if(getPoints("dealer")>getPoints("player"))
            {
                title.setText("YOU LOOSE :(");

            }
            else
            {
                title.setText("YOU WIN :)");

            }

        });







        }

}


