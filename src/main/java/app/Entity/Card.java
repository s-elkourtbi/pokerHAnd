package app.Entity;

/**
 * the class that defines the card object
 */

import app.Enum.CardSuit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    /**
     * Suit param.
     */
    public CardSuit suit;

    /**
     * The value of card.
     */
    public String value;


}
