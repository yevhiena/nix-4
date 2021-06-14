package ua.com.alevel.dao;

import ua.com.alevel.exception.DataNotFoundException;
import ua.com.alevel.model.dto.AccountDto;
import ua.com.alevel.model.entity.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    Account findAccountByNumber(long number) throws SQLException, DataNotFoundException;

    List<AccountDto> findAccountByUserId(long id) throws SQLException, DataNotFoundException;

}
