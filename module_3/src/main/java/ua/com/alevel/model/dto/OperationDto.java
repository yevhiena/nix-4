package ua.com.alevel.model.dto;

import java.math.BigDecimal;
import java.util.List;

public class OperationDto {

    private long accountNumber;
    private BigDecimal transferAmount;
    private List<String> category;

    public OperationDto(long accountNumber, BigDecimal transferAmount, List<String> category) {
        this.accountNumber = accountNumber;
        this.transferAmount = transferAmount;
        this.category = category;
    }

    public OperationDto(){}

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
