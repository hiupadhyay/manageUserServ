package com.user.manage.util;

import org.apache.commons.text.RandomStringGenerator;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import static org.apache.commons.text.CharacterPredicates.ASCII_ALPHA_NUMERALS;


import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String generatePassword() {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS,ASCII_ALPHA_NUMERALS)
                .build();
        return pwdGenerator.generate(10);
    }

    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
