package org.me.testtask.model;

import org.me.testtask.exceptions.IncorrectAccountFormatException;
import org.me.testtask.exceptions.InvalidBankCodeException;
import org.me.testtask.exceptions.InvalidCheckDigitException;

public class BankAccountNumberFactory {

    public static final int FINNISH_ACCOUNT = 1;

    public static NationalBankAccountNumber getNationalBankAccount(String accountNumber, int country) {

        try {
            if (country == FINNISH_ACCOUNT)
                return new FinnishBankAccountNumber(accountNumber);
        } catch (InvalidBankCodeException ignored) {
        } catch (InvalidCheckDigitException ignored) {
        } catch (IncorrectAccountFormatException ignored) {
        }

        return new NullBankAccountNumber();
    }


}
