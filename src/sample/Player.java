package sample;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class Player {

    String name;
    int points;
    HBox hand;
    Text points_counter;
    static StackPane deck_place;

    public Player(String name, HBox hand, Text points_counter) {
        this.name = name;
        this.hand = hand;
        this.points_counter = points_counter;
    }

    public void getNewCard()
    {
        //getting card from deck
        Node temp = deck_place.getChildren().get(deck_place.getChildren().size()-1);

        //remove top card
        deck_place.getChildren().remove(temp);
        TranslateTransition transition= new TranslateTransition(Duration.millis(300),temp);
        transition.setInterpolator(Interpolator.EASE_IN);
        transition.setByX(-100);
        transition.play();

        //add that card to hand
        hand.getChildren().add(temp);

        //calculating value
        String new_card_points_s = Deck.getDeck_of_cards().get(Deck.getDeck_of_cards().size()-1).getValue();

        int new_points = Integer.parseInt(new_card_points_s);
        points+=new_points;
        points_counter.setText("" + points);
        Deck.getDeck_of_cards().remove(Deck.getDeck_of_cards().size()-1);
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        this.points_counter.setText("" + points);
    }

    public static void setDeck_place(StackPane deck_place) {
        Player.deck_place = deck_place;
    }
}
