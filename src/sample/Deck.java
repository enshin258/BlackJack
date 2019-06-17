package sample;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class Deck {
    static List<String> suit = new LinkedList<>();
    public static List<Card> deck_of_cards = new LinkedList<>();

    static void new_deck()
    {
        suit.add("spades");
        suit.add("hearts");
        suit.add("clubs");
        suit.add("diamonds");

        //iterating through all suits
        for (String s:suit)
        {
            //array of cards in same suit
            Card[] c = new Card[13];
            for (int j = 2; j < 11; j++) {
                c[j-2] = new Card(""+j,s);
                deck_of_cards.add(c[j-2]);
            }
            c[9]=new Card("jack",s);
            c[10]=new Card("queen",s);
            c[11]=new Card("king",s);
            c[12]=new Card("ace",s);
            c[9].setValue("10");
            c[10].setValue("10");
            c[11].setValue("10");
            c[12].setValue("11");
            deck_of_cards.add(c[9]);
            deck_of_cards.add(c[10]);
            deck_of_cards.add(c[11]);
            deck_of_cards.add(c[12]);
        }
        Collections.shuffle(deck_of_cards);
        //play sound
        String path = "src/sounds/shuffle.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        System.out.println("deck created");

    }

    public static List<Card> getDeck_of_cards() {
        return deck_of_cards;
    }
}
