package app.evaluation;

import app.Entity.Card;
import app.Entity.Hand;
import app.Enum.HandRank;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The algo to evaluate different hand.
 */
public class EvaluateHand {

    /**
     * transfer Ten, Queen .. to respective number.
     * @param hand
     * @return
     */
    private static List<Integer> getValueOfHand(Hand hand) {
        List<Integer> cardValue = new ArrayList<Integer>();
        for (Card card : hand.playerHand) {
            if(card.value.equals("T")){
                card.value = "10";
            }else if(card.value.equals("J")){
                card.value = "11";
            }else if(card.value.equals("Q")){
                card.value = "12";
            }else if(card.value.equals("K")){
                card.value = "13";
            }else if(card.value.equals("A")){
                card.value = "14";
            }
            cardValue.add(Integer.parseInt(card.value));
        }
        Collections.sort(cardValue);
        return cardValue;
    }

    /**
     * Calulate number of pair in a hand.
     * @param hand
     * @return
     */
    private static int nombreDePair(Hand hand) {
        List<Integer> cardValue = getValueOfHand(hand);
        Set<Integer> cardValueSet = new HashSet<Integer>();
        int counter;
        for (Integer value : cardValue) {
            cardValueSet.add(value);
        }
        return cardValueSet.size();
    }

    /**
     * Evaluate o hand if is a three of kind.
     * @param hand
     * @return
     */
    private static boolean isThreeOfKind(Hand hand) {
        List<Integer> cardValue = getValueOfHand(hand);
        return ((cardValue.get(0) == cardValue.get(2)
                || cardValue.get(1) == cardValue.get(3)
                || cardValue.get(2) == cardValue.get(4)));
    }

    /**
     * Evaluate o hand if is four of kind.
     * @param hand
     * @return
     */
    private static boolean isFourKind(Hand hand) {
        List<Integer> cardValue = getValueOfHand(hand);
        return ((cardValue.get(0) == cardValue.get(3)
                || cardValue.get(1) == cardValue.get(4)));
    }

    /**
     * Evaluate o hand if is straight.
     * @param hand
     * @return
     */
    private static boolean isStraight(Hand hand) {

        List<Integer> cardValue = getValueOfHand(hand);
        return cardValue.get(4) - cardValue.get(0) == 4;
    }

    /**
     * Evaluate o hand if is flush.
     * @param hand
     * @return
     */
    private static boolean isFlush(Hand hand) {

        for (int i = 0; i < hand.playerHand.size() - 1; i++) {
            if (hand.playerHand.get(i).getSuit() != hand.playerHand.get(i + 1).getSuit()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Evaluate o hand if is full house.
     * @param hand
     * @return
     */
    private static boolean isFullHouse(Hand hand) {
        List<Integer> cardValue = getValueOfHand(hand);
        return ((cardValue.get(0) == cardValue.get(2) && cardValue.get(2) != cardValue.get(3)
                && cardValue.get(3) == cardValue.get(4)) ||
                (cardValue.get(0) == cardValue.get(1) && cardValue.get(1) != cardValue.get(2)
                        && cardValue.get(2) == cardValue.get(4)));
    }

    /**
     * Evaluate o hand if is straight flush.
     * @param hand
     * @return
     */
    private static boolean isStraightFlush(Hand hand) {

        return isStraight(hand) && isFlush(hand);
    }

    /**
     * Evaluate a hand and complete the handRank and rank.
     * @param hand
     * @return
     */
    public static Hand evaluateHand(Hand hand) {
        List<Integer> cardValue = getValueOfHand(hand);
        Map<Integer, Long> cardValueMAp =
                cardValue.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        if (isStraightFlush(hand)) {
            hand.handRank = HandRank.STRAIGHTFLUSH;
            hand.rank = Integer.valueOf(9);
            hand.winner = cardValue.get(4);
            return hand;
        } else if (isFourKind(hand)) {
            hand.handRank = HandRank.FOUROFAKIND;
            hand.rank = Integer.valueOf(8);
            hand.winner = getKey(cardValueMAp, 4L);
            return hand;
        } else if (isFullHouse(hand)) {
            hand.handRank = HandRank.FULLHOUSE;
            hand.rank = Integer.valueOf(7);
            hand.winner = getKey(cardValueMAp, 3L);
            return hand;
        } else if (isFlush(hand)) {
            hand.handRank = HandRank.FLUSH;
            hand.rank = Integer.valueOf(6);
            hand.winner = cardValue.get(4);
            return hand;
        } else if (isStraight(hand)) {
            hand.handRank = HandRank.STRAIGHT;
            hand.rank = Integer.valueOf(5);
            hand.winner = cardValue.get(4);
            return hand;
        } else if (isThreeOfKind(hand)) {
            hand.handRank = HandRank.THREEOFAKIND;
            hand.rank = Integer.valueOf(4);
            hand.winner = getKey(cardValueMAp, 3L);
            return hand;
        } else if (nombreDePair(hand) == 3) {
            hand.handRank = HandRank.TWOPAIR;
            hand.rank = Integer.valueOf(3);
            hand.winner = twoPairWinnerCard(hand);
            return hand;
        } else if (nombreDePair(hand) == 4) {
            hand.handRank = HandRank.PAIR;
            hand.rank = Integer.valueOf(2);
            hand.winner = getKey(cardValueMAp, 2L);
            return hand;
        } else {
            hand.handRank = HandRank.HIGHCARD;
            hand.rank = Integer.valueOf(1);
            hand.winner = cardValue.get(4);
            return hand;
        }
    }

    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        return map.entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    private static Integer twoPairWinnerCard(Hand hand){
        List<Integer> cardValue = getValueOfHand(hand);
        List<Integer> pair = new ArrayList<>();
        for (int i = 0; i <= 3; i++){
            if(cardValue.get(i) == cardValue.get(i+1)) {
                pair.add(cardValue.get(i));
            }
        }
        return pair.get(1);
    }

    /**
     * To get winner card.
     * @param hand
     * @return

    private static Integer getWinnerCard(Hand hand) {
        Integer winnerCard;
        List<Integer> cardValue = getValueOfHand(hand);
        Map<Integer, Long> cardValueMAp =
                cardValue.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        switch (hand.handRank) {
            case FOUROFAKIND:
                winnerCard = getKey(cardValueMAp, 4L);
                break;
            case FULLHOUSE:
                winnerCard = getKey(cardValueMAp, 3L);
                break;
            case THREEOFAKIND:
                winnerCard = getKey(cardValueMAp, 3L);
                break;
            case TWOPAIR:
                winnerCard = twoPairWinnerCard(hand);
                break;
            case PAIR:
                winnerCard = getKey(cardValueMAp, 2L);
                break;
            default:
                winnerCard = cardValue.get(4);
                break;

        }
        return winnerCard;
    }
    */

    /**
     * Methode evaluating two hand and return the winner hand.
     * @param firstHand
     * @param secondHand
     * @return
     */
    public static Hand evaluateTwoHand(Hand firstHand, Hand secondHand) {

        Hand blackHand = evaluateHand(firstHand);
        Hand whiteHand = evaluateHand(secondHand);

        if(blackHand.rank == whiteHand.rank){
            if(blackHand.winner == whiteHand.winner) {
                System.out.println("Same Hand No Winner !!");
                return null;
            } else if (blackHand.winner > whiteHand.winner) {
                System.out.println("Black wins. - with " +blackHand.handRank + ": " + blackHand.winner);
                return blackHand;
            } else {
                System.out.println("White wins. - with " +whiteHand.handRank + ": " + whiteHand.winner);
                return whiteHand;
            }
        } else if(blackHand.rank > whiteHand.rank) {
            System.out.println("Black wins. - with " +blackHand.handRank + ": " + blackHand.winner);
            return blackHand;
        } else {
            System.out.println("White wins. - with " +whiteHand.handRank + ": " + whiteHand.winner);
            return whiteHand;
        }
    }
}
