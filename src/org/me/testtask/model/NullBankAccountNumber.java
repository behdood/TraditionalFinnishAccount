package org.me.testtask.model;


public class NullBankAccountNumber implements NationalBankAccountNumber {
    @Override
    public String getLongFormat() {
        return "";
    }

    @Override
    public String getShortFormat() {
        return "";
    }
}
