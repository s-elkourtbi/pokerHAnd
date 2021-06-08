package app;

import app.Entity.Card;
import app.Entity.Hand;
import app.Enum.CardSuit;
import app.evaluation.EvaluateHand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainClass {

    /**
     *
     * @return
     * @throws IOException
     */
    private static List<Card> readHand() throws IOException {
        List<Card> cardList = new ArrayList<>();
        BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
        String[] card=entree.readLine().split("\\s+");
        for(int i = 0; i < 5; i++) {
            String[] detail = card[i].split("");
            Card cardS = new Card();
            cardS.setValue(detail[0]);
            cardS.setSuit(CardSuit.valueOf(detail[1]));
            cardList.add(cardS);
        }
        return cardList;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--------------------- FIRST HAND ---------------------");
        Hand winner = new Hand();
        try {
            while(true) {

                System.out.println("Black hand :");
                Hand blackHand = new Hand();
                List<Card> blackHandCard = readHand();
                blackHand.setPlayerHand(blackHandCard);


                System.out.println("White hand :");
                Hand whiteHand = new Hand();
                List<Card> whiteHandCard = readHand();
                whiteHand.setPlayerHand(whiteHandCard);

                EvaluateHand.evaluateTwoHand(blackHand, whiteHand);
                System.out.println("--------------------- NEXT HAND ---------------------");
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
