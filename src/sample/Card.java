package sample;


import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * Class representing single Card
 * The Card consits of
 * - card -> physical representation of Card
 * - value -> storing value of card
 * - suit -> storing suit of card
 *
 */
public class Card {

    StackPane card;
    String value;
    String suit;

    /**
     * In constructor , the card receives a physical representation, and the graphics are loaded
     * based on
     * @param suit
     * and
     * @param value
     */

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;

        card = new StackPane();
        ImageView image = new ImageView("graphics/single_cards/" + this.value + "_of_" + this.suit + ".png");
        Rectangle rectangle = new Rectangle();

        card.getChildren().addAll(rectangle, image);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StackPane getCard() {
        return card;
    }


}