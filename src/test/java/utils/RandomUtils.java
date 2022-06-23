package utils;

import java.util.Random;

public class RandomUtils {

    public static String createRandomWord(int stringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String createRandomNumbers(int stringLength) {
        int leftLimit = 0;   //  0
        int rightLimit = 9;  //  9

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(stringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
