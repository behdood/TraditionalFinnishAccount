package org.me.testtask.model;


import org.me.testtask.exceptions.IncorrectAccountFormatException;
import org.me.testtask.exceptions.InvalidBankCodeException;
import org.me.testtask.exceptions.InvalidCheckDigitException;
import org.me.testtask.validation.BankAccountNumberValidator;

public class FinnishBankAccountNumber implements NationalBankAccountNumber {
    private String accountNumberShortFormat = "";
    private String accountNumberLongFormat = "";

    FinnishBankAccountNumber(String accountNumber)
            throws InvalidBankCodeException, InvalidCheckDigitException, IncorrectAccountFormatException {

        BankAccountNumberValidator validator = new FinnishBankAccountNumberValidator();
        this.accountNumberLongFormat = validator.validateAndConvertToLongFormat(accountNumber);
        this.accountNumberShortFormat = accountNumber;

    }

    @Override
    public String getLongFormat() {
        return accountNumberLongFormat;
    }

    @Override
    public String getShortFormat() {
        return accountNumberShortFormat;
    }


    public static class FinnishBankAccountNumberValidator implements BankAccountNumberValidator {

        private final String REGEX_ACCOUNT_NUMBER_FORMAT = "\\d{6}\\-\\d{2,8}";
        private final String REGEX_BANK_CODE_GROUP1 = "^(1|2|31|33|34|36|37|38|39|6|8).*";
        private final String REGEX_BANK_CODE_GROUP2 = "^(4|5).*";
        private final String REGEX_WHITE_SPACE = " +";
        private final int LONG_ACCOUNT_NUMBER_LENGTH = 14;

        @Override
        public String validateAndConvertToLongFormat(String accountNumShortFormat)
                throws IncorrectAccountFormatException, InvalidBankCodeException, InvalidCheckDigitException {

            accountNumShortFormat = removeWhiteSpaces(accountNumShortFormat);

            // check account number has the correct text format: 6 numbers, followed by 2-8 numbers
            if (!verifyAccountNumberTextFormat(accountNumShortFormat))
                throw new IncorrectAccountFormatException();

            // do the zero-padding to convert number into 14-digit electronic format
            String accountNumLongFormat = convertToLongFormat(accountNumShortFormat);
            if (accountNumLongFormat == null)
                throw new InvalidBankCodeException(accountNumShortFormat.substring(0, 2));

            if (!verifyCheckDigit(accountNumLongFormat))
                throw new InvalidCheckDigitException();

            return accountNumLongFormat;
        }


        private String removeWhiteSpaces(String accountNumShortFormat) {
            return accountNumShortFormat.replaceAll(REGEX_WHITE_SPACE, "");
        }

        private boolean verifyAccountNumberTextFormat(String accountNumShortFormat) {
            return accountNumShortFormat.matches(REGEX_ACCOUNT_NUMBER_FORMAT);
        }

        private String convertToLongFormat(String accountNumberShortFormat) throws InvalidBankCodeException {
            accountNumberShortFormat = accountNumberShortFormat.replace("-", ""); // remove hyphen


            int nZeros = LONG_ACCOUNT_NUMBER_LENGTH - accountNumberShortFormat.length();
            String zeros = new String(new char[nZeros]).replace('\0', '0');

            if (accountNumberShortFormat.matches(REGEX_BANK_CODE_GROUP1))
                return accountNumberShortFormat.substring(0, 6) + zeros + accountNumberShortFormat.substring(6);
            else if (accountNumberShortFormat.matches(REGEX_BANK_CODE_GROUP2))
                return accountNumberShortFormat.substring(0, 7) + zeros + accountNumberShortFormat.substring(7);
            else
                return null;
        }

        private boolean verifyCheckDigit(String accountNumLongFormat) {
            int sum = 0;
            for (int i = 0; i < accountNumLongFormat.length() - 1; i++) {
                int coef = 2 - (i % 2);
                int temp = coef * (accountNumLongFormat.charAt(i) - '0');
                sum += (temp / 10) + (temp % 10);
            }

            int checkDigit = 10 - (sum % 10);
            int accountLastDigit = accountNumLongFormat.charAt(accountNumLongFormat.length() - 1) - '0';

            return (checkDigit == accountLastDigit);
        }

    }

}
