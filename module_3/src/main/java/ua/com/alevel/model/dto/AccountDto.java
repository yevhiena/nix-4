package ua.com.alevel.model.dto;

import java.math.BigDecimal;

public class AccountDto {
    private long accountNumber;
    private BigDecimal balance;

    public  AccountDto(){}
    public AccountDto(long accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
