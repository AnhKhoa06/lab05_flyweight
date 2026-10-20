package lab05;

import java.util.IdentityHashMap;
import java.util.Map;

import e3.chapter3.Rank;
import e3.chapter3.Suit;

public final class CardLazy {

    private static final Map<Rank, Map<Suit, CardLazy>> CARDS = new IdentityHashMap<>();

    private final Rank aRank;
    private final Suit aSuit;

    private CardLazy(Rank pRank, Suit pSuit) {
        assert pRank != null && pSuit != null;
        aRank = pRank;
        aSuit = pSuit;
    }

    public static CardLazy get(Rank pRank, Suit pSuit) {
        assert pRank != null && pSuit != null;
        Map<Suit, CardLazy> innerMap = CARDS.getOrDefault(pRank, new IdentityHashMap<>());
        CARDS.putIfAbsent(pRank, innerMap);
        if (!innerMap.containsKey(pSuit)) {
            innerMap.put(pSuit, new CardLazy(pRank, pSuit));   // chỉ tạo khi có người xin
        }
        return innerMap.get(pSuit);
    }

    public Rank rank() { return aRank; }
    public Suit suit() { return aSuit; }

    @Override
    public String toString() { return aRank + " of " + aSuit; }
}