package sample;


import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Card{

        StackPane card;
        String value;
        String suit;

        public Card(String value, String suit) {
                this.value = value;
                this.suit = suit;

                card = new StackPane();
                ImageView image = new ImageView("graphics/single_cards/" + this.value + "_of_" + this.suit + ".png");
                Rectangle rectangle = new Rectangle();
                card.getChildren().addAll(rectangle,image);
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