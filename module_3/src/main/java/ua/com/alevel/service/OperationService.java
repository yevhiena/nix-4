package ua.com.alevel.service;

import ua.com.alevel.dao.CommonOperationDao;
import ua.com.alevel.model.dto.AccountDto;
import ua.com.alevel.model.dto.OperationDto;
import ua.com.alevel.model.dto.TransferResultDto;
import ua.com.alevel.model.entity.Account;
import ua.com.alevel.model.entity.Category;
import ua.com.alevel.model.entity.Operation;
import ua.com.alevel.model.entity.TransferResult;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OperationService{
    private final CommonOperationDao operationDao;

    public OperationService(CommonOperationDao operationDao) {
        this.operationDao = operationDao;
    }


    public TransferResultDto makeTransfer(OperationDto operationDto) throws SQLException {

            Account account = operationDao.findAccountByNumber(operationDto.getAccountNumber());
            Operation operation = new Operation(account, operationDto.getTransferAmount());

            TransferResult transferResult;
            if(operationDto.getTransferAmount().longValue() == 0) {
                transferResult = new TransferResult(TransferResult.Status.REJECTED, "Invalid transfer amount. Cannot transfer zero units");
            } else if(account.getBalance().add(operationDto.getTransferAmount()).longValue() <0){
                transferResult = new TransferResult(TransferResult.Status.REJECTED, "Insufficient funds");
            }else {
                Category.Type type;
                if(operationDto.getTransferAmount().longValue() > 0 ) type = Category.Type.INCOME;
                else type = Category.Type.EXPENSE;

                try {
                    List<Category> categories = operationDao.findAllCategories();
                    Set<Category> categorySet = new HashSet<>();
                    for (String categoryName: operationDto.getCategory()){
                        categorySet.add(categories.stream()
                                .filter(category -> category.getName().equals(categoryName) && category.getType().equals(type))
                                .findFirst()
                                .orElseThrow(RuntimeException::new));
                    }
                    operation.setCategories(categorySet);
                    transferResult = new TransferResult(TransferResult.Status.ACCEPTED, "");
                }catch (RuntimeException e){
                    transferResult = new TransferResult(TransferResult.Status.REJECTED, "Invalid category for operation type " + type);
                }
            }

            operation.setResult(transferResult);
            operationDao.createOperation(operation);

            return new TransferResultDto(operation.getResult().getStatus(), operation.getResult().getReason());



    }

    public List<AccountDto> getAccounts(String email) throws SQLException {
        long id = operationDao.getUserIdByEmail(email);
        return operationDao.findAccountByUserId(id);
    }



}
