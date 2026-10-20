package lab05;

import java.util.IdentityHashMap;
import java.util.Map;

import e3.chapter3.Rank;
import e3.chapter3.Suit;

public final class CardEager {

    private static final Map<Rank, Map<Suit, CardEager>> CARDS = new IdentityHashMap<>();

    // Tạo sẵn đủ 52 lá khi lớp được nạp
    static {
        for (Rank r : Rank.values()) {
            Map<Suit, CardEager> innerMap = new IdentityHashMap<>();
            for (Suit s : Suit.values()) {
                innerMap.put(s, new CardEager(r, s));
            }
            CARDS.put(r, innerMap);
        }
    }

    private final Rank aRank;
    private final Suit aSuit;

    private CardEager(Rank pRank, Suit pSuit) {
        aRank = pRank;
        aSuit = pSuit;
    }

    public static CardEager get(Rank pRank, Suit pSuit) {
        return CARDS.get(pRank).get(pSuit);
    }

    public Rank rank() { return aRank; }
    public Suit suit() { return aSuit; }

    @Override
    public String toString() { return aRank + " of " + aSuit; }
}