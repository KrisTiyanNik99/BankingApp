package main.services.generators.impls;

import main.configs.ConfigurationInfo;
import main.services.generators.CredentialsGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* This is a base implementation of the main interface class "CredentialGenerator" for generating usernames and passwords.
* This class takes care of generating a unique username and password to meet minimum security requirements.
* */
public class DefaultGenerator implements CredentialsGenerator {

    @Override
    public String generateUsername() {
        String username = createRandomString(ConfigurationInfo.DEFAULT_LENGTH, false);
        return username;
    }

    @Override
    public String generatePassword() {
        String username = createRandomString(ConfigurationInfo.DEFAULT_LENGTH, true);
        return username;
    }

    private String createRandomString(int length, boolean isPassword) {
        String credential = ConfigurationInfo.ALPHABETIC + ConfigurationInfo.SYMBOLS;
        if (isPassword) {
            credential = credential + ConfigurationInfo.NUMBERS;
        }

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        List<Integer> usedNumbers = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int index = getUnusedIndex(credential, random, usedNumbers);
            usedNumbers.add(index);
            sb.append(credential.charAt(index));
        }

        return sb.toString();
    }

    private int getUnusedIndex(String credential, Random random, List<Integer> usedNumber) {
        /*
        * We create a separate method that will monitor if we already have the given symbol and if we have it will
        * generate a new one based on the indices. We do it this way because we want passwords or usernames to be
        * unique, without repeating characters.
        */
        int number = random.nextInt(credential.length());
        while (usedNumber.contains(number)) {
            number = random.nextInt(credential.length());
        }

        return number;
    }
}
