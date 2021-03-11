package anhnpp.model;

import java.util.Random;

/**
 *
 * @author nguye
 */
public class RandomCode {

    public static String random_Code(int len) {
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String values = Capital_chars
                + Small_chars
                + numbers;

        Random random = new Random();

        String code = "";

        for (int i = 0; i < len; i++) {
            code += values.charAt(random.nextInt(values.length()));

        }
        return code;
    }
}
