package org.me.server.model;

import org.me.server.exceptions.IncorrectAccountFormatException;
import org.me.server.exceptions.InvalidBankCodeException;
import org.me.server.exceptions.InvalidCheckDigitException;

public class BankAccountNumberFactory {

    public static final int FINNISH_ACCOUNT = 1;

    public static BankAccountNumber getNationalBankAccount(String accountNumber, int country) {

        try {
            if (accountNumber != null && country == FINNISH_ACCOUNT)
                return new FinnishBankAccountNumber(accountNumber);
        } catch (InvalidBankCodeException ignored) {
        } catch (InvalidCheckDigitException ignored) {
        } catch (IncorrectAccountFormatException ignored) {
        }

        return new NullBankAccountNumber();
    }


}
