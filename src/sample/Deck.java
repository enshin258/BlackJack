package sample;

import java.util.*;

public class Deck {

    public static List<Card> deck_of_cards = new LinkedList<>();

    static void new_deck()
    {
        String suit="";
        for (int i=0;i<4;i++)
        {
            switch (i)
            {
                case 0:
                {
                    suit="diamonds";
                    break;
                }
                case 1:
                {
                    suit="spades";
                    break;
                }
                case 2:
                {
                    suit="hearts";
                    break;
                }
                case 3:
                {
                    suit="clubs";
                    break;
                }
            }
            //array of cards in same suit
            Card[] c = new Card[13];
            for (int j = 2; j < 11; j++) {
                c[j-2] = new Card(""+j,suit);
                deck_of_cards.add(c[j-2]);
            }
            c[9]=new Card("jack",suit);
            c[10]=new Card("queen",suit);
            c[11]=new Card("king",suit);
            c[12]=new Card("ace",suit);
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
        System.out.println("deck created");

    }


    public static List<Card> getDeck_of_cards() {
        return deck_of_cards;
    }
}
