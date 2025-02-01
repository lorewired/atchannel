package com.lore.atchannel_api.usecase;

import java.util.Random;

public class VisualIdentifier {

    private static Random rand = new Random();

    private static char[] alpha = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static char[] numbers = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static char[] operators = {
        '-', '+', '%', '&', '/'
    };

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        int sqrt = (int)Math.sqrt((double) n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static String GenerateId() {
        var id = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int rn = rand.nextInt(150);
            if (rn > 100 && i > 0 && i < 8) {
                if (isPrime(rn)) {
                    id.append(operators[rn % operators.length]);
                } else {
                    id.append(numbers[rn % numbers.length]);
                }
            } else {
                id.append(alpha[rn % alpha.length]);
            }
        }
        return id.toString();
    }

}