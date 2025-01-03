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
        return createRandomString(ConfigurationInfo.DEFAULT_LENGTH, false);
    }

    @Override
    public String generatePassword() {
        return createRandomString(ConfigurationInfo.DEFAULT_LENGTH, true);
    }

    private String createRandomString(int length, boolean isPassword) {
        String credential = ConfigurationInfo.ALPHABETIC + ConfigurationInfo.SYMBOLS;
        if (isPassword) {
            credential = credential + ConfigurationInfo.NUMBERS;
        }

        StringBuilder sb = new StringBuilder();

        List<Integer> usedNumbers = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int index = getUnusedIndex(credential.length(), usedNumbers);
            usedNumbers.add(index);
            sb.append(credential.charAt(index));
        }

        return sb.toString();
    }

    private int getUnusedIndex(int credential, List<Integer> usedNumber) {
        /*
            We create a separate method that will monitor if we already have the given symbol and if we have it will
            generate a new one based on the indices. We do it this way because we want passwords or usernames to be
            unique, without repeating characters.
        */

        Random random = new Random();
        int number = random.nextInt(credential);
        while (usedNumber.contains(number)) {
            number = random.nextInt(credential);
        }

        return number;
    }
}
