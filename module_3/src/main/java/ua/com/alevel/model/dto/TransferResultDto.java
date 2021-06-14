package ua.com.alevel.model.dto;

import ua.com.alevel.model.entity.TransferResult;

public class TransferResultDto {

    private TransferResult.Status status;
    private String reason;

    public TransferResultDto(TransferResult.Status status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public TransferResult.Status getStatus() {
        return status;
    }

    public void setStatus(TransferResult.Status status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "TransferResultDto{" +
                "status=" + status +
                ", reason='" + reason + '\'' +
                '}';
    }
}
