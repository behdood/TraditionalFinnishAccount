package org.me.server.model;


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
