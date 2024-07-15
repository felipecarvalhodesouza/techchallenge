package br.com.postech.techchallenge.infraestrutura.helper;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";
    private static final String ALL_CHARACTERS = UPPERCASE_LETTERS + NUMBERS + SPECIAL_CHARACTERS;
    private static final int PASSWORD_LENGTH = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generatePassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        password.append(getRandomCharacter(UPPERCASE_LETTERS));
        password.append(getRandomCharacter(NUMBERS));
        password.append(getRandomCharacter(LOWER_CASE));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS));

        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(getRandomCharacter(ALL_CHARACTERS));
        }

        return shuffleString(password.toString());
    }

    private static char getRandomCharacter(String characters) {
        int index = RANDOM.nextInt(characters.length());
        return characters.charAt(index);
    }

    private static String shuffleString(String input) {
        char[] a = input.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }
}

