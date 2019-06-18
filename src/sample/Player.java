package sample;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.File;

/**
 * Class that describe player (user and dealer)
 * Its store name, point, cards on hand and shared deck place
 */
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

    /**
     * Method responsible for the card draw
     * First, its remove card from deck and add to player or dealer hand
     * After that, calculate new sum of points
     */
    public synchronized void getNewCard()
    {
        //get top card from deck
        Card drawnCard = Deck.getDeckOfCards().get(Deck.getDeckOfCards().size()-1);

        //remove card from memory
        Deck.getDeckOfCards().remove(drawnCard);

        //remove from table
        deck_place.getChildren().remove(drawnCard);

        //move card
        TranslateTransition transition= new TranslateTransition(Duration.millis(250),drawnCard.getCard());
        transition.setInterpolator(Interpolator.EASE_IN);
        transition.setByX(-100);
        transition.play();

        //add card to hand
        hand.getChildren().add(drawnCard.getCard());

        //play sound;
        String path = "src/sounds/cardSlide.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        //calculating value
        String new_card_points_s = drawnCard.getValue();
        int new_points = Integer.parseInt(new_card_points_s);

        points+=new_points;
        points_counter.setText("" + points);

    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        this.points_counter.setText("" + points);
    }

    public static void setDeckPlace(StackPane deck_place) {
        Player.deck_place = deck_place;
    }
}
