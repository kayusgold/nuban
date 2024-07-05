package com.kayusgold.nubanjava.services;

import com.kayusgold.nubanjava.models.Bank;
import com.kayusgold.nubanjava.models.BankUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NubanService {

    public List<Bank> getAccountNumberBanks(String accountNumber) throws InvalidAccountException, IllegalArgumentException {
        List<Bank> foundBanks = new ArrayList<>();

        List<Bank> defaultBanks = BankUtil.getBanks();

        for (Bank bank : defaultBanks) {
            if (startSearch(accountNumber, bank)) {
                foundBanks.add(bank);
            }
        }

        return foundBanks;
    }

    private static boolean startSearch(String accountNumber, Bank bank) throws InvalidAccountException, IllegalArgumentException {
        if (accountNumber == null || accountNumber.length() != 10 || bank == null) {
            throw new InvalidAccountException("Invalid account number or bank information");
        }
        String serialNumber = accountNumber.substring(0, 9);
        String code;
        if (bank.type().equals("DMB")) {
            code = String.format("%06d", Integer.parseInt(bank.code()));
        } else {
            code = String.format("%-6s", bank.code()).replace(' ', '9');  // Pad left with 9s
        }
        System.out.println("Bank code: " + code + " -- (" + bank.name() + ")");
        int checkDigit = generateCheckDigit(serialNumber, code);

        return checkDigit == Character.getNumericValue(accountNumber.charAt(9));  // Only return true if check digit is valid
    }

    private static int generateCheckDigit(String serialNumber, String bankCode) {
        if (serialNumber.length() != 9) {
            throw new IllegalArgumentException("Serial number must be 9 digits");
        }

        // Seed array defined with the provided values
        int[] seed = {3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3, 3, 7, 3};

        System.out.println("serialNumber: " + serialNumber);
        String cipher = bankCode + serialNumber;
        System.out.println("cipher: " + cipher);
        int sum = 0;
        for (int i = 0; i < cipher.length(); i++) {
            int digitValue = Character.getNumericValue(cipher.charAt(i));
            sum += digitValue * seed[i];
//            System.out.printf("At index: %d, picked cypher: %d, picked seed: %d, sum: %d\n", i, digitValue, seed[i], sum);
        }
//        System.out.println("Modulus: " + sum % 10);
        int checkDigit = 10 - (sum % 10);
        checkDigit = checkDigit == 10 ? 0 : checkDigit;
        System.out.println("Returned Check Digit: " + checkDigit);
        return checkDigit;
    }

    public static class InvalidAccountException extends RuntimeException {
        public InvalidAccountException(String message) {
            super(message);
        }
    }
}
