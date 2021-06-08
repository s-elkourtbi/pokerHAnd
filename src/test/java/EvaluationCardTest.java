import app.Entity.Card;
import app.Entity.Hand;
import app.Enum.CardSuit;
import app.Enum.HandRank;
import app.evaluation.EvaluateHand;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EvaluationCardTest {

    static Hand highValue = new Hand();
    static Hand pair = new Hand();
    static Hand twoPair = new Hand();
    static Hand threeOfKind = new Hand();
    static Hand straight = new Hand();
    static Hand flush = new Hand();
    static Hand fullHouse = new Hand();
    static Hand fourOfKind = new Hand();
    static Hand straightFlush = new Hand();
    static Hand straightFlushNoWinner = new Hand();


    @BeforeAll
    public static void beforeEveryTest() {
        Card card10 = new Card(CardSuit.C, "1");
        Card card11 = new Card(CardSuit.D, "1");
        Card card12 = new Card(CardSuit.H, "1");
        Card card13 = new Card(CardSuit.S, "1");

        Card card20 = new Card(CardSuit.C, "2");
        Card card21 = new Card(CardSuit.D, "2");
        Card card22 = new Card(CardSuit.H, "2");
        Card card23 = new Card(CardSuit.S, "2");

        Card card30 = new Card(CardSuit.C, "3");
        Card card31 = new Card(CardSuit.D, "3");
        Card card32 = new Card(CardSuit.H, "3");
        Card card33 = new Card(CardSuit.S, "3");

        Card card40 = new Card(CardSuit.C, "4");
        Card card43 = new Card(CardSuit.S, "4");

        Card card50 = new Card(CardSuit.C, "5");
        Card card51 = new Card(CardSuit.D, "5");
        Card card52 = new Card(CardSuit.S, "5");

        Card card63 = new Card(CardSuit.C, "6");

        Card card70 = new Card(CardSuit.C, "7");
        Card cardt = new Card(CardSuit.C, "T");


            highValue.playerHand.add(card10);
            highValue.playerHand.add(card70);
            highValue.playerHand.add(card32);
            highValue.playerHand.add(card52);
            highValue.playerHand.add(cardt);

            pair.playerHand.add(card10);
            pair.playerHand.add(card11);
            pair.playerHand.add(card30);
            pair.playerHand.add(card40);
            pair.playerHand.add(card63);

            twoPair.playerHand.add(card10);
            twoPair.playerHand.add(card11);
            twoPair.playerHand.add(card30);
            twoPair.playerHand.add(card31);
            twoPair.playerHand.add(card63);


            threeOfKind.playerHand.add(card10);
            threeOfKind.playerHand.add(card11);
            threeOfKind.playerHand.add(card12);
            threeOfKind.playerHand.add(card31);
            threeOfKind.playerHand.add(card63);


            straight.playerHand.add(card10);
            straight.playerHand.add(card21);
            straight.playerHand.add(card32);
            straight.playerHand.add(card40);
            straight.playerHand.add(card50);

            flush.playerHand.add(card10);
            flush.playerHand.add(card20);
            flush.playerHand.add(cardt);
            flush.playerHand.add(card40);
            flush.playerHand.add(card63);

            fullHouse.playerHand.add(card10);
            fullHouse.playerHand.add(card11);
            fullHouse.playerHand.add(card52);
            fullHouse.playerHand.add(card50);
            fullHouse.playerHand.add(card51);

            fourOfKind.playerHand.add(card10);
            fourOfKind.playerHand.add(card11);
            fourOfKind.playerHand.add(card12);
            fourOfKind.playerHand.add(card13);
            fourOfKind.playerHand.add(card51);

            straightFlush.playerHand.add(card10);
            straightFlush.playerHand.add(card20);
            straightFlush.playerHand.add(card30);
            straightFlush.playerHand.add(card40);
            straightFlush.playerHand.add(card50);

            straightFlushNoWinner.playerHand.add(card13);
            straightFlushNoWinner.playerHand.add(card23);
            straightFlushNoWinner.playerHand.add(card33);
            straightFlushNoWinner.playerHand.add(card43);
            straightFlushNoWinner.playerHand.add(card52);



    }

    @Test
    public void handPair() {
        Hand handEvaluated = EvaluateHand.evaluateHand(pair);
        Assert.assertEquals(handEvaluated.handRank, HandRank.PAIR);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(2));

    }

    @Test
    public void twoPair(){
        Hand handEvaluated = EvaluateHand.evaluateHand(twoPair);
        Assert.assertEquals(handEvaluated.handRank, HandRank.TWOPAIR);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(3));
    }

    @Test
    public void threeKind(){
        Hand handEvaluated = EvaluateHand.evaluateHand(threeOfKind);
        Assert.assertEquals(handEvaluated.handRank, HandRank.THREEOFAKIND);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(4));
    }

    @Test
    public void straight(){
        Hand handEvaluated = EvaluateHand.evaluateHand(straight);
        Assert.assertEquals(handEvaluated.handRank, HandRank.STRAIGHT);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(5));
    }

    @Test
    public void flush(){
        Hand handEvaluated = EvaluateHand.evaluateHand(flush);
        Assert.assertEquals(handEvaluated.handRank, HandRank.FLUSH);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(6));
    }

    @Test
    public void fullHouse(){
        Hand handEvaluated = EvaluateHand.evaluateHand(fullHouse);
        Assert.assertEquals(handEvaluated.handRank, HandRank.FULLHOUSE);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(7));
    }

    @Test
    public void fourOfKind(){
        Hand handEvaluated = EvaluateHand.evaluateHand(fourOfKind);
        Assert.assertEquals(handEvaluated.handRank, HandRank.FOUROFAKIND);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(8));
    }

    @Test
    public void straightFlush(){
        Hand handEvaluated = EvaluateHand.evaluateHand(straightFlush);
        Assert.assertEquals(handEvaluated.handRank, HandRank.STRAIGHTFLUSH);
        Assert.assertEquals(handEvaluated.rank, Integer.valueOf(9));
    }

    @Test
    public void compareTwoHand() {
        Hand hand1= EvaluateHand.evaluateHand(straightFlush);
        Hand hand2= EvaluateHand.evaluateHand(pair);

        Assert.assertEquals(EvaluateHand.evaluateTwoHand(hand1, hand2), hand1);
    }

    @Test
    public void noWinner(){
        Hand hand1= EvaluateHand.evaluateHand(straightFlush);
        Hand hand2= EvaluateHand.evaluateHand(straightFlushNoWinner);

        Assert.assertEquals(EvaluateHand.evaluateTwoHand(hand1, hand2), null);

    }

    @Test
    public void evaluateTwoHandPairWinner() {
        Hand hand1= EvaluateHand.evaluateHand(highValue);
        Hand hand2= EvaluateHand.evaluateHand(pair);

        Assert.assertEquals(EvaluateHand.evaluateTwoHand(hand1, hand2), hand2);
    }

    @Test
    public void evaluateTwoHandTwoPairWinner() {
        Hand hand1= EvaluateHand.evaluateHand(pair);
        Hand hand2= EvaluateHand.evaluateHand(twoPair);

        Assert.assertEquals(EvaluateHand.evaluateTwoHand(hand1, hand2), hand2);
    }
}
