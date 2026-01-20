package com.example.Testcards;

import java.util.ArrayList;
import java.util.List;

public class CardSet {

    private String id;
    private String name;
    private List<Card> cards = new ArrayList<>();

    public CardSet(String name) {
        this.id = String.valueOf(System.currentTimeMillis());
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<Card> getCards() { return cards; }

    public void addCard(String term, String definition) {
        cards.add(new Card(term, definition));
    }

    // -------- INNER CARD CLASS --------
    public static class Card {
        private String term;
        private String definition;

        public Card(String term, String definition) {
            this.term = term;
            this.definition = definition;
        }

        public String getTerm() { return term; }
        public String getDefinition() { return definition; }
    }
}