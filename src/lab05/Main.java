package lab05;

import java.util.function.BiFunction;

import e3.chapter3.Rank;
import e3.chapter3.Suit;

public class Main {

    private static void measure(String name, BiFunction<Rank, Suit, Object> source) {
        long startMs = System.currentTimeMillis();
        long startNs = System.nanoTime();
        int count = 0;
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                source.apply(r, s);
                count++;
            }
        }
        long ms = System.currentTimeMillis() - startMs;
        long us = (System.nanoTime() - startNs) / 1000;
        System.out.println(name + ": " + count + " lá, " + ms + " ms (" + us + " µs)");
    }

    public static void main(String[] args) {
        // Khởi động trước để không tính thời gian nạp Rank, Suit và lambda vào kết quả
        measure("Warm-up (bỏ qua)", (r, s) -> r);

        measure("Khởi tạo muộn (lazy)", CardLazy::get);
        measure("Khởi tạo sẵn (eager)", CardEager::get);
    }
}