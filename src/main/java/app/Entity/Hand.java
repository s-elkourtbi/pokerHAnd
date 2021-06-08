package app.Entity;

/**
 * the class that defines the Hand object
 */

import app.Enum.HandRank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hand {

    /**
     * A player hand is a list of card.
     */
    public List<Card> playerHand = new ArrayList<Card>();

    /**
     * Rank of player hand.
     */
    public HandRank handRank;

    /**
     * classification of hand rank from 1 to 9 used to compare different Hand.
     */
    public Integer rank;
}
