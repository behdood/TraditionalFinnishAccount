package org.me.testtask.model.validation;


import org.me.testtask.model.exceptions.IncorrectAccountFormatException;
import org.me.testtask.model.exceptions.InvalidBankCodeException;
import org.me.testtask.model.exceptions.InvalidCheckDigitException;


public class FinnishAccountValidator implements Validator {
    //    public boolean validate();
    private final static String REGEX_ACCOUNT_FORMAT = "\\d{6}\\-\\d{2,8}";
    private final static String REGEX_GROUP1_START_FORMAT = "^(1|2|31|33|34|36|37|38|39|6|8).*";
    private final static String REGEX_GROUP2_START_FORMAT = "^(4|5).*";

    @Override
    public String validateAndConvertToElectronicFormat(String accountNumber)
            throws IncorrectAccountFormatException, InvalidCheckDigitException, InvalidBankCodeException {
        performSyntacticCheck(accountNumber);
        String accountNo14 = convertTo14DigitString(accountNumber);

        performSemanticCheck(accountNo14);

        return accountNo14;
    }

    private void performSyntacticCheck(String accountNumber) throws IncorrectAccountFormatException {
        if (!accountNumber.matches(REGEX_ACCOUNT_FORMAT))
            throw new IncorrectAccountFormatException();
    }

    private String convertTo14DigitString(String accountNumber) throws InvalidBankCodeException {
        String firstPart = accountNumber.substring(0, 6);
        String secondPart = accountNumber.substring(7);

        int nZeros = 8 - secondPart.length();
        StringBuilder sb = new StringBuilder();
        String zero_pads = new String(new char[nZeros]).replace('\0', '0');

        String accountNumber14;
        if (accountNumber.matches(REGEX_GROUP1_START_FORMAT))
            accountNumber14 = firstPart + zero_pads + secondPart;
        else if (accountNumber.matches(REGEX_GROUP2_START_FORMAT))
            accountNumber14 = firstPart + secondPart.substring(0, 1) + zero_pads + secondPart.substring(1);
        else
            throw new InvalidBankCodeException(accountNumber.substring(0, 2));

        return accountNumber14;
    }

    private void performSemanticCheck(String accountNumber14) throws InvalidCheckDigitException {
        int sum = 0;
        for (int i = 0; i < accountNumber14.length() - 1; i++) {
            int coef = 2 - (i % 2);
            int temp = coef * (accountNumber14.charAt(i) - '0');
            sum += (temp / 10) + (temp % 10);
        }

        int checkDigit = 10 - (sum % 10);
        int accountLastDigit = accountNumber14.charAt(accountNumber14.length() - 1) - '0';

        if (checkDigit != accountLastDigit)
            throw new InvalidCheckDigitException();
    }

}
