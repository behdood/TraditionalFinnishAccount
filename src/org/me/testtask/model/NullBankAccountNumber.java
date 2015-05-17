package org.me.testtask.model;


public class NullBankAccountNumber implements BankAccountNumber {
    @Override
    public String getLongFormat() {
        return "";
    }

    @Override
    public String getShortFormat() {
        return "";
    }
}
