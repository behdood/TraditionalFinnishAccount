package org.me.testtask.model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.me.testtask.exceptions.IncorrectAccountFormatException;
import org.me.testtask.exceptions.InvalidBankCodeException;
import org.me.testtask.exceptions.InvalidCheckDigitException;

public class TestFinnishBankAccountNumber {

    private final int finnish_account = BankAccountNumberFactory.FINNISH_ACCOUNT;

    @Test
    public void testFinnishBankAccountNumber_returnValidObject() {
        String account1_num = "123456-785"; // Nordea (bank group 1)
        String account2_num = "423456-781"; // Sp (bank group 2)

        NationalBankAccountNumber account1, account2;
        account1 = BankAccountNumberFactory.getNationalBankAccount(account1_num, finnish_account);
        account2 = BankAccountNumberFactory.getNationalBankAccount(account2_num, finnish_account);


        assertEquals(account1_num, account1.getShortFormat());
        assertEquals("12345600000785", account1.getLongFormat());

        assertEquals(account2_num, account2.getShortFormat());
        assertEquals("42345670000081", account2.getLongFormat());
    }

    @Test
    public void testFinnishBankAccountNumber_ReturnsNull_IncorrectFormatReturnsNull() {
        // a valid account number should contain 6 digits, followed by a hyphen, and then 2-8 digits
        String account_num1 = "123456-7";
        String account_num2 = "12345-789";
        String account_num3 = "FI1234-789";

        NationalBankAccountNumber account1 =
                BankAccountNumberFactory.getNationalBankAccount(account_num1, finnish_account);
        NationalBankAccountNumber account2 =
                BankAccountNumberFactory.getNationalBankAccount(account_num2, finnish_account);
        NationalBankAccountNumber account3 =
                BankAccountNumberFactory.getNationalBankAccount(account_num3, finnish_account);

        assertNull(account1);
        assertNull(account2);
        assertNull(account3);
    }

    @Test
    public void testFinnishBankAccountNumber_ReturnsNull_UnknownBankCodeReturnsNull() {
        String account_num = "311111-111";

        NationalBankAccountNumber account =
                BankAccountNumberFactory.getNationalBankAccount(account_num, finnish_account);

        assertNull(account);
    }

    @Test
    public void testFinnishBankAccountNumber_ReturnsNull_IncorrectCheckDigit() {
        String account_num = "123456-789";

        NationalBankAccountNumber account =
                BankAccountNumberFactory.getNationalBankAccount(account_num, finnish_account);

        assertNull(account);
    }
}