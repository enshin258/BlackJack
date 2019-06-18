package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.*;

/**
 * Card deck storage class
 */
public class Deck {

    static Vector<String> suit;
    static Vector<Card> deckOfCards;

    /**
     * Method that create a new deck of cards
     * Its iterate through all suits (clubs, diamonds, hearts , spades) and create cards based on values and suits
     * @see Card for more information
     * After creating deck, cards are shuffled using Collections.shuffle()
     */
    static void newDeck()
    {
        suit = new Vector<>();
        suit.add("spades");
        suit.add("hearts");
        suit.add("clubs");
        suit.add("diamonds");

        deckOfCards = new Vector<>();
        //iterating through all suits
        for (String s:suit)
        {
            //array of cards in same suit
            Card[] c = new Card[13];
            for (int j = 2; j < 11; j++) {
                c[j-2] = new Card(""+j,s);
                deckOfCards.add(c[j-2]);
            }
            c[9]=new Card("jack",s);
            c[10]=new Card("queen",s);
            c[11]=new Card("king",s);
            c[12]=new Card("ace",s);
            c[9].setValue("10");
            c[10].setValue("10");
            c[11].setValue("10");
            c[12].setValue("11");
            deckOfCards.add(c[9]);
            deckOfCards.add(c[10]);
            deckOfCards.add(c[11]);
            deckOfCards.add(c[12]);
        }
        //shuffle deck
        Collections.shuffle(deckOfCards);
        //play sound
        String path = "src/sounds/shuffle.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        System.out.println("deck created");

    }

    public static List<Card> getDeckOfCards() {
        return deckOfCards;
    }
}
